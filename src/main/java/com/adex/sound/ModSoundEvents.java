package com.adex.sound;

import com.adex.CoreAdventures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

    public static final Holder.Reference<SoundEvent> TROMBONE1 = registerForHolder("music_disc.trombone1");
    public static final Holder.Reference<SoundEvent> TUBA1 = registerForHolder("music_disc.tuba1");
    public static final Holder.Reference<SoundEvent> ALTO_SAXOPHONE1 = registerForHolder("music_disc.alto_saxophone1");
    public static final Holder.Reference<SoundEvent> BASS_SAXOPHONE1 = registerForHolder("music_disc.bass_saxophone1");
    public static final Holder.Reference<SoundEvent> TIMPANI1 = registerForHolder("music_disc.timpani1");

    private static Holder.Reference<SoundEvent> registerForHolder(String name) {
        Identifier identifier = Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void initialize() {
    }
}
