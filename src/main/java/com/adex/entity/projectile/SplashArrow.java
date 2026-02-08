package com.adex.entity.projectile;

import com.adex.entity.ModEntities;
import com.adex.mixin.AbstractArrowAccessor;
import com.adex.mixin.ArrowAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SplashArrow extends Arrow {

    public SplashArrow(EntityType<SplashArrow> entityType, Level level) {
        super(entityType, level);
    }

    public SplashArrow(Level level, double x, double y, double z, ItemStack arrow, @Nullable ItemStack weapon) {
        super(ModEntities.SPLASH_ARROW, level);
        init(level, x, y, z, arrow, weapon, null);
    }

    public SplashArrow(Level level, LivingEntity owner, ItemStack arrow, @Nullable ItemStack weapon) {
        super(ModEntities.SPLASH_ARROW, level);
        init(level, owner.getX(), owner.getY(0.75d), owner.getZ(), arrow, weapon, owner);
    }

    private void init(Level level, double x, double y, double z, ItemStack arrow, @Nullable ItemStack weapon, @Nullable LivingEntity owner) {
        applyComponentsFromItemStack(arrow);
        setPos(x, y, z);
        setOwner(owner);

        updatePickUp(arrow);

        if (weapon != null && level instanceof ServerLevel serverLevel) {
            if (weapon.isEmpty()) {
                throw new IllegalArgumentException("Invalid weapon firing an arrow");
            }

            ((AbstractArrowAccessor) this).coread$setFiredFromWeapon(weapon.copy());
            checkForPiercing(serverLevel, weapon);
        }
    }

    private void updatePickUp(ItemStack arrow) {
        setPickupItemStack(arrow.copy());

        Unit intangible = arrow.remove(DataComponents.INTANGIBLE_PROJECTILE);
        if (intangible != null) pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
    }

    private void checkForPiercing(ServerLevel level, ItemStack weapon) {
        int piercingLevel = EnchantmentHelper.getPiercingCount(level, weapon, getPickupItemStackOrigin());
        if (piercingLevel > 0) {
            ((AbstractArrowAccessor) this).coread$setPierceLevel((byte) piercingLevel);
        }
    }

    @Override
    protected void doPostHurtEffects(@NonNull LivingEntity entity) {
        PotionContents potionContents = ((ArrowAccessor) this).coread$getPotionContents();
        createSplash(entity.position(), potionContents, new BlockHitResult(Vec3.ZERO, Direction.UP, entity.blockPosition(), false));
    }

    @Override
    protected void onHitBlock(@NonNull BlockHitResult blockHitResult) {
        PotionContents potionContents = ((ArrowAccessor) this).coread$getPotionContents();
        createSplash(blockHitResult.getBlockPos().getBottomCenter(), potionContents, blockHitResult);
        this.discard();
    }

    public void createSplash(Vec3 pos, PotionContents potionContents, BlockHitResult blockHitResult) {
        if (!(level() instanceof ServerLevel level)) return;
        ItemStack arrow = getPickupItem();

        ThrownSplashPotion splashPotion = new ThrownSplashPotion(level(), pos.x, pos.y, pos.z, arrow);
        level().addFreshEntity(splashPotion);

        splashPotion.onHitAsPotion(level, arrow, blockHitResult);
        handleInstantEffects(level, potionContents);

        splashPotion.discard();
    }

    private void handleInstantEffects(ServerLevel level, PotionContents potionContents) {
        int eventId = potionContents.potion().isPresent() && ((Potion) ((Holder<?>) potionContents.potion().get()).value()).hasInstantEffects() ? 2007 : 2002;
        level.levelEvent(eventId, blockPosition(), potionContents.getColor());
    }
}
