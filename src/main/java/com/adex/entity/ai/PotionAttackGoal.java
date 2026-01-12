package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class PotionAttackGoal extends EyeOfSightAttackGoal {

    private static final ArrayList<Holder<Potion>> POTION_EFFECTS = new ArrayList<>();

    private final float damage;
    private final double throwSpeed;

    public PotionAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, float damage, double throwSpeed) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.throwSpeed = throwSpeed;
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
        createPotion((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createPotion(ServerLevel level, LivingEntity target) {
        ThrownSplashPotion potion = new ThrownSplashPotion(level, golem, getEffect(level.getRandom()));
        potion.setDeltaMovement(createDirectionVector(target, level.getRandom(), 2.5d).scale(throwSpeed));
        potion.setPos(golem.getX(), golem.getY(0.5d) + 1.0d, golem.getZ());
        level.addFreshEntity(potion);
    }

    public ItemStack getEffect(RandomSource random) {
        return PotionContents.createItemStack(Items.SPLASH_POTION, Util.getRandom(POTION_EFFECTS, random));
    }
}
