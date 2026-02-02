package com.adex.sound;

import com.adex.CoreAdventures;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public class ModJukeboxSounds {

    public static final ResourceKey<JukeboxSong> TROMBONE1_KEY = createKey("trombone1");
    public static final JukeboxSong TROMBONE1 = createSong(TROMBONE1_KEY, ModSoundEvents.TROMBONE1, 69, 1);

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static JukeboxSong createSong(ResourceKey<JukeboxSong> resourceKey, Holder.Reference<SoundEvent> soundEvent, int length, int comparatorOutput) {
        return new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", resourceKey.identifier())), length, comparatorOutput);
    }

    public static void initialize() {
        DynamicRegistrySetupCallback.EVENT.register(registryView -> registryView.getOptional(Registries.JUKEBOX_SONG).ifPresent(registry -> {
            Registry.register(registry, TROMBONE1_KEY, TROMBONE1);
        }));
    }
}
