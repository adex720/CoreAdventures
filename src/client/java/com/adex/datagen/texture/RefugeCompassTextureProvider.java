package com.adex.datagen.texture;

import com.adex.datagen.ModDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RefugeCompassTextureProvider extends ModTextureProvider<Integer> {

    public static final Identifier REFUGE_COMPASS_IDENTIFIER = Identifier.fromNamespaceAndPath(ModDataGenerator.MOD_ID, "refuge_compass");

    private final PackOutput.PathProvider itemPathProvider;
    private final Map<Integer, BufferedImage> generator;

    public RefugeCompassTextureProvider(FabricDataOutput packOutput) {
        super(packOutput);

        itemPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures\\item");
        generator = new HashMap<>();

        addGenerator(generator);
    }

    @Override
    public Path getPath(Integer index, int id) {
        return itemPathProvider.file(REFUGE_COMPASS_IDENTIFIER.withSuffix("_" + toTwoLongString(index)), "png");
    }

    /**
     * Returns the given int as a string.
     * If the absolute value of the int is less than 10, one leading zero is added.
     */
    public static String toTwoLongString(int i) {
        if (i <= -10 || i >= 10) return Integer.toString(i);
        if (i >= 0) return "0" + i;
        return "-0" + -i;
    }

    @Override
    public void buildTextures() {
        for (int i = 0; i < 32; i++) {
            try {
                BufferedImage image = ModTextureProvider.getTexture("item\\recovery_compass_" + toTwoLongString(i) + ".png");
                recolorBaseItem(i, image, ColorPalette.RECOVERY_COMPASS, ColorPalette.REFUGE_COMPASS);
            } catch (IOException e) {
                ModDataGenerator.LOGGER.error("Failed to load base armor item textures: {}\n{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void recolorBaseItem(int index, BufferedImage image, ColorPalette original, ColorPalette palette) {
        generator.put(index, replaceColorPalette(image, original, palette));
    }

    @Override
    public @NonNull String getName() {
        return "Refuge compass texture provider";
    }
}
