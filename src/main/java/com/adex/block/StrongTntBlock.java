package com.adex.block;

import com.adex.CoreAdventures;
import com.adex.entity.PrimedStrongTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import org.jspecify.annotations.NonNull;

public class StrongTntBlock extends TntBlock {

    private final float explosionPower;

    public StrongTntBlock(Properties properties, float explosionPower) {
        super(properties);

        if (explosionPower < 0.0f) {
            explosionPower = 0.0f;
            CoreAdventures.LOGGER.warn("Negative explosion power detected, using value 0");
        } else if (explosionPower > 128.0f) {
            // Vanilla Primed Tnt Entity has a maximum explosion power of 128
            explosionPower = 128.0f;
            CoreAdventures.LOGGER.warn("Too large explosion power detected, using value 128");
        }
        this.explosionPower = explosionPower;
    }

    public float getExplosionPower() {
        return explosionPower;
    }

    public void registerDispenseBehavior() {
        DispenserBlock.registerBehavior(this, new StrongTntDispenseItemBehavior());
    }

    @Override
    public void wasExploded(ServerLevel serverLevel, @NonNull BlockPos blockPos, @NonNull Explosion explosion) {
        if (serverLevel.getGameRules().get(GameRules.TNT_EXPLODES)) {
            int fuseTimer = serverLevel.random.nextInt(PrimedStrongTnt.DEFAULT_FUSE_TIME / 4) + PrimedStrongTnt.DEFAULT_FUSE_TIME / 8;
            PrimedStrongTnt primedTnt = PrimedStrongTnt.create(serverLevel, blockPos, explosionPower, fuseTimer, explosion.getIndirectSourceEntity());
            serverLevel.addFreshEntity(primedTnt);
        }
    }

    public class StrongTntDispenseItemBehavior extends OptionalDispenseItemBehavior {

        @Override
        protected @NonNull ItemStack execute(@NonNull BlockSource blockSource, @NonNull ItemStack itemStack) {
            ServerLevel level = blockSource.level();

            if (level.getGameRules().get(GameRules.TNT_EXPLODES)) {
                BlockPos pos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                PrimedStrongTnt tnt = PrimedStrongTnt.create(level, pos, explosionPower, null);
                level.addFreshEntity(tnt);
                level.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, pos);
                itemStack.shrink(1);
                setSuccess(true);

            } else {
                setSuccess(false);
            }

            return itemStack;
        }
    }
}
