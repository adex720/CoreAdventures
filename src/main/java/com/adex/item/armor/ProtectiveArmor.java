package com.adex.item.armor;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;

public class ProtectiveArmor extends Item {

    private final TagKey<DamageType> damages;
    private final float protection;

    public ProtectiveArmor(Item.Properties properties, TagKey<DamageType> damages, float protection) {
        super(properties);
        this.damages = damages;
        this.protection = protection;
    }

    public float getExtraProtection(DamageSource damageSource) {
        return damageSource.is(damages) ? protection : 0.0f;
    }
}
