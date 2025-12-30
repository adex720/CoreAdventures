package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.util.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class ModTextureProvider<T> implements DataProvider {

    public static final String VANILLA_TEXTURES_PATH = RepositoryRootPath.PATH + "\\src\\client\\resources\\vanillatextures\\";

    private final List<Map<T, BufferedImage>> generators = new ArrayList<>();

    public ModTextureProvider(FabricDataOutput packOutput) {
    }

    public abstract Path getPath(T key, int id);

    public abstract void buildTextures();

    protected void addRegister(Map<T, BufferedImage> generator) {
        generators.add(generator);
    }

    public BufferedImage replaceColorPalette(BufferedImage source, ColorPalette originalPalette, ColorPalette newPalette) {
        BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                int color = source.getRGB(x, y);
                int colorIndex = originalPalette.index(color);
                int newColor = colorIndex >= -1 ? newPalette.color(colorIndex) : color;
                g.setColor(new Color(newColor, true));
                g.drawLine(x, y, x, y);
            }
        }

        return image;
    }

    public static void listVanillaColors(String[] paths) {
        HashSet<Long> colors = new HashSet<>();

        for (String path : paths) {
            BufferedImage image;
            try {
                image = getTexture(path);
            } catch (IOException e) {
                ModDataGenerator.LOGGER.error("Failed to load vanilla texture: {}\n{}\n{}", path, e.getMessage(), Arrays.toString(e.getStackTrace()));
                continue;
            }

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    colors.add(Integer.toUnsignedLong(image.getRGB(x, y)));
                }
            }
        }

        ArrayList<String> list = new ArrayList<>(colors.size());
        colors.stream().sorted((a, b) -> ((int) (((a & 0xff) + ((a >> 8) & 0xff) + ((a >> 16) & 0xff)) - (b & 0xff) - ((b >> 8) & 0xff) - ((b >> 16) & 0xff))))
                .forEach(c -> list.add(Long.toHexString(c)));

        ModDataGenerator.LOGGER.info(Arrays.toString(list.toArray()));
    }

    public static BufferedImage getTexture(String path) throws IOException {
        return ImageIO.read(new File(VANILLA_TEXTURES_PATH + path));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        buildTextures();
        return CompletableFuture.allOf(saveAll(cachedOutput));
    }

    private CompletableFuture<?> saveAll(CachedOutput cachedOutput) {
        return CompletableFuture.runAsync(() -> {
            int i = 0;
            for (Map<T, BufferedImage> generator : generators) {
                for (Map.Entry<T, BufferedImage> entry : generator.entrySet()) {
                    save(cachedOutput, entry.getValue(), getPath(entry.getKey(), i));
                }
                i++;
            }
        }, Util.backgroundExecutor().forName("saveStable"));
    }

    private static void save(CachedOutput cachedOutput, BufferedImage image, Path path) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            HashingOutputStream hashingOutputStream = new HashingOutputStream(Hashing.sha1(), byteArrayOutputStream);

            ImageIO.write(image, "png", hashingOutputStream);

            cachedOutput.writeIfNeeded(path, byteArrayOutputStream.toByteArray(), hashingOutputStream.hash());
        } catch (IOException var10) {
            LOGGER.error("Failed to save file to {}", path, var10);
        }
    }
}
