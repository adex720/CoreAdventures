package com.adex.datagen.texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Stores colors used for texture generation.
 * Each palette contains a fully transparent color, and it is accessed with index -1.
 * Index -2 is used for colors not in the palette.
 */
@SuppressWarnings("unused")
public class ColorPalette {

    public static final ColorPalette DIAMOND_HUMANOID = new ColorPalette(List.of(0xff145e53, 0xff11727a, 0xff1c919a, 0xff1aaaa7, 0xff0ebabd, 0xff20c5b5, 0xff15c2c6, 0xff2cc2b0, 0xff2dc4b2, 0xff30d0be, 0xff2ce0d8, 0xff3de0e5, 0xff4aedd9, 0xff4bede6, 0xff65f5e3, 0xff6bf3e3, 0xff70fbf0, 0xff9af8f0, 0xffa1fbe8, 0xff9efeeb, 0xffb4fdee, 0xffd5fff6, 0xffe5fffa, 0xffffffff));
    public static final ColorPalette DIAMOND_ARMOR = new ColorPalette(List.of(0xff082520, 0xff17172d, 0xff0e3f36, 0xff1aaaa7, 0xff20c5b5, 0xff4aedd9, 0xffa1fbe8, 0xffffffff));

    public static final ColorPalette CHALCEDONY_HUMANOID = matchVanillaHumanoidWith6(0xff2f4dc6, 0xff405ed8, 0xff5974e5, 0xff6d85eb, 0xff8ea1f1, 0xffa1b1f5);
    public static final ColorPalette CHALCEDONY_ARMOR = matchVanillaArmorWith6(0xff2f4dc6, 0xff405ed8, 0xff5974e5, 0xff6d85eb, 0xff8ea1f1, 0xffa1b1f5);

    public static final ColorPalette GARNET_HUMANOID = matchVanillaHumanoidWith6(0xff4e0202, 0xff660000, 0xff7e0606, 0xff950d0d, 0xffaa1616, 0xffbc1c1c);
    public static final ColorPalette GARNET_ARMOR = matchVanillaArmorWith6(0xff4e0202, 0xff660000, 0xff7e0606, 0xff950d0d, 0xffaa1616, 0xffbc1c1c);

    public static final ColorPalette JADE_HUMANOID = matchVanillaHumanoidWith6(0xff0c7114, 0xff148a1d, 0xff1c9926, 0xff26ab30, 0xff31bb3b, 0xff39c743);
    public static final ColorPalette JADE_ARMOR = matchVanillaArmorWith6(0xff0c7114, 0xff148a1d, 0xff1c9926, 0xff26ab30, 0xff31bb3b, 0xff39c743);

    public static final ColorPalette JASPER_HUMANOID = matchVanillaHumanoidWith6(0xffa3250f, 0xffb32b13, 0xffc8341a, 0xffda4126, 0xffe35036, 0xfff0634a);
    public static final ColorPalette JASPER_ARMOR = matchVanillaArmorWith6(0xffa3250f, 0xffb32b13, 0xffc8341a, 0xffda4126, 0xffe35036, 0xfff0634a);

    public static final ColorPalette ONYX_HUMANOID = matchVanillaHumanoidWith6(0xff000000, 0xff0f0e0f, 0xff151415, 0xff201e20, 0xff232023, 0xff262226);
    public static final ColorPalette ONYX_ARMOR = matchVanillaArmorWith6(0xff000000, 0xff0f0e0f, 0xff151415, 0xff201e20, 0xff232023, 0xff262226);

    public static final ColorPalette OPAL = new ColorPalette(List.of(0xffc1d5ff, 0xffedf3ff));

    public static final ColorPalette RUBY_HUMANOID = matchVanillaHumanoidWith6(0xff7d0000, 0xff9b0000, 0xffc30000, 0xffeb0000, 0xffff0000, 0xffff2828);
    public static final ColorPalette RUBY_ARMOR = matchVanillaArmorWith6(0xff7d0000, 0xff9b0000, 0xffc30000, 0xffeb0000, 0xffff0000, 0xffff2828);

    public static final ColorPalette SAPPHIRE_HUMANOID = matchVanillaHumanoidWith6(0xff000994, 0xff000aab, 0xff000bc1, 0xff000cd9, 0xff000df1, 0xff2632ff);
    public static final ColorPalette SAPPHIRE_ARMOR = matchVanillaArmorWith6(0xff000994, 0xff000aab, 0xff000bc1, 0xff000cd9, 0xff000df1, 0xff2632ff);

    public static final ColorPalette SPINEL_HUMANOID = matchVanillaHumanoidWith6(0xffa71387, 0xffb21890, 0xffbb1d98, 0xffc424a0, 0xffcb2ca7, 0xffda39b6);
    public static final ColorPalette SPINEL_ARMOR = matchVanillaArmorWith6(0xffa71387, 0xffb21890, 0xffbb1d98, 0xffc424a0, 0xffcb2ca7, 0xffda39b6);

    public static final ColorPalette TIGERS_EYE_HUMANOID = matchVanillaHumanoidWith6(0xff622609, 0xff70350e, 0xffc7a80f, 0xffd2c808, 0xffe9de10, 0xfff8ed25);
    public static final ColorPalette TIGERS_EYE_ARMOR = matchVanillaArmorWith6(0xff622609, 0xff70350e, 0xffc7a80f, 0xffd2c808, 0xffe9de10, 0xfff8ed25);

    public static final ColorPalette RECOVERY_COMPASS = new ColorPalette(List.of(0xff0d0d0d, 0xff212e2b, 0xff005479, 0xff006694, 0xff105257, 0xff138e99, 0xff14bbc6, 0xff29dfeb));
    public static final ColorPalette REFUGE_COMPASS = new ColorPalette(List.of(0xff130906, 0xff231310, 0xff26120c, 0xff311610, 0xff462721, 0xff4c3530, 0xff5e4843, 0xff70645e));

    public static final ColorPalette JUNIPER = new ColorPalette(List.of(0xff56402b, 0xff56402a, 0xff5a482e, 0xff5a482f, 0xff5f4e36, 0xff604e37, 0xff6b5336, 0xff6c5437, 0xff735a3b, 0xff745a3c, 0xff7a5f40, 0xff7a6040, 0xff7e623f, 0xff7f6340));
    public static final ColorPalette CHALCEDONY_BOAT = new ColorPalette(List.of(0xff2845bc, 0xff2845bc, 0xff2f4dc6, 0xff2f4dc6, 0xff405ed8, 0xff405ed8, 0xff5974e5, 0xff5974e5, 0xff6d85eb, 0xff6d85eb, 0xff8ea1f1, 0xff8ea1f1, 0xffa1b1f5, 0xffa1b1f5));

    public static final ColorPalette TNT = new ColorPalette(List.of(0xff912d11, 0xffb11527, 0xffdb2f1a, 0xffea4318));
    public static final ColorPalette RED_TNT = new ColorPalette(List.of(0xff630000, 0xff830505, 0xffb21616, 0xffb21616));
    public static final ColorPalette ORANGE_TNT = new ColorPalette(List.of(0xff965408, 0xffad6007, 0xffc56b04, 0xffda7807));
    public static final ColorPalette YELLOW_TNT = new ColorPalette(List.of(0xffa38906, 0xffbd9f0a, 0xffd6b50f, 0xffe9c513));
    public static final ColorPalette GREEN_TNT = new ColorPalette(List.of(0xff317e07, 0xff3a9608, 0xff47b30c, 0xff51cc0f));
    public static final ColorPalette BLUE_TNT = new ColorPalette(List.of(0xff0d719c, 0xff0f88bc, 0xff1ba0d8, 0xff30b5ef));


    public final List<Integer> colors;
    public final int colorCount;

    public ColorPalette(List<Integer> colors) {
        this.colors = colors;
        colorCount = colors.size();
    }

    public static ColorPalette matchVanillaHumanoidWith6(int c1, int c2, int c3, int c4, int c5, int c6) {
        return new ColorPalette(List.of(c1, c1, c1, c1, c1, c1, c1, c1, c1, c2, c3, c4, c4, c4, c5, c5, c5, c6, c6, c6, c6, c6, c6, c6));
    }

    public static ColorPalette matchVanillaArmorWith6(int c1, int c2, int c3, int c4, int c5, int c6) {
        return new ColorPalette(List.of(c1, c1, c2, c3, c4, c5, c6, c6));
    }

    public int color(int index) {
        if (index < 0) return index == -1 ? 0 : 0xff000000;

        if (index >= colorCount) return colors.get(colorCount - 1);
        return colors.get(index);
    }

    public int index(int color) {
        if ((color & 0xff000000) == 0) return -1; // opacity is 0

        for (int i = 0; i < colorCount; i++)
            if (colors.get(i) == color) return i;

        return -2; // not on palette
    }

    @SuppressWarnings("unused")
    public BufferedImage getColorImage() {
        BufferedImage image = new BufferedImage(colorCount, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        for (int i = 0; i < colorCount; i++) {
            g.setColor(new Color(colors.get(i), true));
            g.fillRect(i, 0, 1, 1);
        }

        return image;
    }
}
