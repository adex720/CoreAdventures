package com.adex.option;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

import java.io.PrintWriter;

public class ModOptions {

    private static ModOptions MOD_OPTIONS;

    protected Minecraft minecraft;

    private static final Component HEAT_BAR_OFFSET_TOOLTIP = Component.translatable("options.coread.heat_bar_offset.tooltip");
    private final OptionInstance<Integer> heatBarOffset = new OptionInstance<>(
            "options.coread.heat_bar_offset",
            OptionInstance.cachedConstantTooltip(HEAT_BAR_OFFSET_TOOLTIP),
            (component, value) -> Options.genericValueLabel(component, Component.translatable("options.coread.heat_bar_offset.value", value)),
            new OptionInstance.IntRange(0, 10, true),
            0,
            _ -> {
            });

    public ModOptions(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public static void createModOptions(Minecraft minecraft) {
        MOD_OPTIONS = new ModOptions(minecraft);
    }

    public static ModOptions getModOptions() {
        return MOD_OPTIONS;
    }

    public OptionInstance<Integer> heatBarOffset() {
        return heatBarOffset;
    }

    public void save(PrintWriter writer) {
        write(writer, "coread.heat_bar_offset", heatBarOffset.get());
    }

    private void write(PrintWriter writer, String string, int value) {
        writer.println(string + ":" + value);
    }

    public void load(CompoundTag tag) {
        heatBarOffset.set(getInt(tag, "coread.heat_bar_offset", heatBarOffset.get()));
    }

    private String getString(CompoundTag tag, String name) {
        Tag valueTag = tag.get(name);
        if (valueTag != null) {
            if (valueTag instanceof StringTag(String value)) {
                return value;
            }
        }

        return null;
    }

    private int getInt(CompoundTag tag, String name, int defaultValue) {
        String valueString = getString(tag, name);
        if (valueString == null) return defaultValue;

        return Integer.parseInt(valueString);
    }

}
