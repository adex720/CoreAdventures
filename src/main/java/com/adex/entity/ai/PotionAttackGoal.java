package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PotionAttackGoal extends EyeOfSightAttackGoal {

    private static final ArrayList<Holder<MobEffect>> POTION_EFFECTS = new ArrayList<>();

    private final int damage;
    private final double throwSpeed;
    private final float throwChance;
    private final float durationMultiplier;

    public PotionAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, int damage, double throwSpeed, float throwChance, float durationMultiplier) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.throwSpeed = throwSpeed;
        this.throwChance = throwChance;
        this.durationMultiplier = durationMultiplier;
    }

    public static void initializeEffects() {
        addEffect(MobEffects.SLOWNESS);
        addEffect(MobEffects.WEAKNESS);
        addEffect(MobEffects.POISON);
    }

    public static void addEffect(Holder<MobEffect> potion) {
        POTION_EFFECTS.add(potion);
    }

    @Override
    public void attack() {
        if (golem.getRandom().nextFloat() < throwChance) createPotion((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createPotion(ServerLevel level, LivingEntity target) {
        ThrownSplashPotion potion = new ThrownSplashPotion(level, golem, createItemStack(level.getRandom()));
        potion.setDeltaMovement(createDirectionVector(target, level.getRandom(), 2.5d).scale(throwSpeed));
        potion.setPos(golem.getX(), golem.getY(0.5d) + 1.0d, golem.getZ());
        level.addFreshEntity(potion);
    }

    public ItemStack createItemStack(RandomSource random) {
        MobEffectInstance randomEffect = new MobEffectInstance(Util.getRandom(POTION_EFFECTS, random), (int) (cooldown * durationMultiplier), 1);

        PotionContents contents = new PotionContents(Optional.empty(),
                Optional.of(randomEffect.getEffect().value().getColor()),
                List.of(randomEffect, new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, damage)),
                Optional.of(randomEffect.getDescriptionId()));

        ItemStack itemStack = new ItemStack(Items.SPLASH_POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, contents);
        return itemStack;
    }

    public float getDamage() {
        return damage;
    }
}
