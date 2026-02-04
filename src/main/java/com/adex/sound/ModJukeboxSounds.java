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

    public static final ResourceKey<JukeboxSong> ALTO_SAXOPHONE1_KEY = createKey("alto_saxophone1");
    public static final JukeboxSong ALTO_SAXOPHONE1 = createSong(ALTO_SAXOPHONE1_KEY, ModSoundEvents.ALTO_SAXOPHONE1, 420, 8);

    public static final ResourceKey<JukeboxSong> BASS_SAXOPHONE1_KEY = createKey("bass_saxophone1");
    public static final JukeboxSong BASS_SAXOPHONE1 = createSong(BASS_SAXOPHONE1_KEY, ModSoundEvents.BASS_SAXOPHONE1, 420, 9);

    public static final ResourceKey<JukeboxSong> TIMPANI1_KEY = createKey("timpani1");
    public static final JukeboxSong TIMPANI1 = createSong(TIMPANI1_KEY, ModSoundEvents.TIMPANI1, 420, 9);

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static JukeboxSong createSong(ResourceKey<JukeboxSong> resourceKey, Holder.Reference<SoundEvent> soundEvent, int length, int comparatorOutput) {
        return new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", resourceKey.identifier())), length, comparatorOutput);
    }

    public static void initialize() {
        DynamicRegistrySetupCallback.EVENT.register(registryView -> registryView.getOptional(Registries.JUKEBOX_SONG).ifPresent(registry -> {
            Registry.register(registry, TROMBONE1_KEY, TROMBONE1);
            Registry.register(registry, ALTO_SAXOPHONE1_KEY, ALTO_SAXOPHONE1);
            Registry.register(registry, BASS_SAXOPHONE1_KEY, BASS_SAXOPHONE1);
            Registry.register(registry, TIMPANI1_KEY, TIMPANI1);
        }));
    }
}
