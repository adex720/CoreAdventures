package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;

public class PotionAttackGoal extends EyeOfSightAttackGoal {

    private static final ArrayList<Holder<Potion>> POTION_EFFECTS = new ArrayList<>();

    private final int damage;
    private final double throwSpeed;
    private final float throwChance;

    public PotionAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, int damage, double throwSpeed, float throwChance) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.throwSpeed = throwSpeed;
        this.throwChance = throwChance;
    }

    public static void initializeEffects() {
        addEffect(Potions.SLOWNESS);
        addEffect(Potions.WEAKNESS);
        addEffect(Potions.POISON);
    }

    public static void addEffect(Holder<Potion> potion) {
        POTION_EFFECTS.add(potion);
    }

    @Override
    public void attack() {
        if (golem.getRandom().nextFloat() < throwChance) createPotion((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createPotion(ServerLevel level, LivingEntity target) {
        PotionContents contents = new PotionContents(Util.getRandom(POTION_EFFECTS, level.getRandom()))
                .withEffectAdded(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 0, damage));

        ItemStack itemStack = new ItemStack(Items.SPLASH_POTION);
        itemStack.set(DataComponents.POTION_CONTENTS, contents);

        ThrownSplashPotion potion = new ThrownSplashPotion(level, golem, itemStack);
        potion.setDeltaMovement(createDirectionVector(target, level.getRandom(), 2.5d).scale(throwSpeed));
        potion.setPos(golem.getX(), golem.getY(0.5d) + 1.0d, golem.getZ());
        level.addFreshEntity(potion);
    }

    public float getDamage() {
        return damage;
    }
}
