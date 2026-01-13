package com.adex.item.armor;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.Nullable;

public class ProtectiveArmor extends Item {

    private final TagKey<DamageType> damages;
    private final float protection;
    @Nullable
    private final TagKey<DamageType> damages2;
    private final float protection2;

    public ProtectiveArmor(Item.Properties properties, TagKey<DamageType> damages, float protection) {
        this(properties, damages, protection, null, 0.0f);
    }

    public ProtectiveArmor(Item.Properties properties, TagKey<DamageType> damages, float protection, @Nullable TagKey<DamageType> damages2, float protection2) {
        super(properties);
        this.damages = damages;
        this.protection = protection;

        this.damages2 = damages2;
        this.protection2 = protection2;
    }

    public float getExtraProtection(DamageSource damageSource) {
        return damageSource.is(damages) ? protection : damages2 != null && damageSource.is(damages2) ? protection2 : 0.0f;
    }
}
