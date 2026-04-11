package com.adex.item;

import com.adex.entity.projectile.SplashArrow;
import com.adex.util.Util;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TippedArrowItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SplashArrowItem extends TippedArrowItem {

    public Object result;

    public SplashArrowItem(Properties properties) {
        super(properties);
    }

    public static void addToItemGroup(FabricCreativeModeTabOutput itemGroup) {
        Optional<? extends HolderLookup.RegistryLookup<Potion>> optional = itemGroup.getContext().holders().lookup(Registries.POTION);
        if (optional.isEmpty()) return;

        HolderLookup.RegistryLookup<Potion> potionLookup = optional.get();
        FeatureFlagSet flagSet = itemGroup.getContext().enabledFeatures();

        for (Holder.Reference<Potion> potion : potionLookup.listElements().toList()) {
            if (!potion.value().isEnabled(flagSet)) continue;

            itemGroup.accept(PotionContents.createItemStack(ModItems.SPLASH_ARROW, potion));
        }

    }

    @Override
    public @NonNull AbstractArrow createArrow(@NonNull Level level, ItemStack arrowStack, @NonNull LivingEntity owner, @Nullable ItemStack weapon) {
        return new SplashArrow(level, owner, arrowStack.copyWithCount(1), weapon);
    }

    @Override
    public @NonNull Projectile asProjectile(@NonNull Level level, Position position, ItemStack itemStack, @NonNull Direction direction) {
        SplashArrow arrow = new SplashArrow(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

    /**
     * Returns a list of {@link MobEffectInstance}s of the {@link ItemStack}.
     * This method works for any item.
     *
     * @param arrow {@link ItemStack} of the arrow
     * @return List of every {@link MobEffectInstance} of the arrow
     */
    public static List<MobEffectInstance> getEffectInstances(ItemStack arrow) {
        PotionContents contents = getPotionContents(arrow);
        if (!contents.hasEffects()) return List.of();

        Optional<Holder<Potion>> potion = contents.potion();
        if (potion.isEmpty()) return contents.customEffects();

        return Util.combine(contents.customEffects(), potion.get().value().getEffects());
    }

    public static PotionContents getPotionContents(ItemStack arrow) {
        return arrow.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
    }
}
