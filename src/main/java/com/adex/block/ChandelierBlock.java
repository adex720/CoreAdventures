package com.adex.block;

import com.adex.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ChandelierBlock extends Block {

    public ChandelierBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull VoxelShape getCollisionShape(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
        return Shapes.box(0.375f, 0.4375f, 0.375f, 0.625f, 1.0f, 0.625f);
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        for (Vec3 particlePos : getRandomParticlePoses(random, 1 + random.nextInt(4))) {
            createParticle(level, pos.getBottomCenter().add(particlePos), random);
        }
    }

    /**
     * Creates an array of Vec3 containing offsets for the particles from the bottom center of the chandelier block.
     * All the positions are unique.
     *
     * @param random   RandomSource
     * @param posCount Amount of particle counts
     * @return Vec3 offset array
     */
    private Vec3[] getRandomParticlePoses(RandomSource random, int posCount) {
        List<Integer> indexes = Util.generateUniqueRandomInts(11, posCount, random);
        Vec3[] poses = new Vec3[posCount];

        for (int i = 0; i < posCount; i++) {
            poses[i] = getParticlePos(indexes.get(i));
        }

        return poses;
    }

    /**
     * Creates a Vec3 for the offset of a candle from the bottom center of the chandelier block.
     * The 2 least significant bits are used for the direction with:
     * 0 for positive-positive, 1 for positive-negative, 2 for negative-positive, 3 for negative-negative.
     * The remaining bits are used for the distance of the candle from the middle.
     * 0 for the middle candles, 1 for the center candles of a direction and 2 for the outer candles.
     *
     * @param index Index of the candle
     * @return Vec3 offset
     */
    public Vec3 getParticlePos(int index) {
        return getParticlePos(index >> 2, index & 3);
    }

    /**
     * Creates a Vec3 for the offset of a candle from the bottom center of the chandelier block.
     *
     * @param candleNumber 0 for the middle candles, 1 for the center candles of a direction and 2 for the outer candles
     * @param direction    0 for positive-positive, 1 for positive-negative, 2 for negative-positive, 3 for negative-negative
     * @return Vec3 offset
     */
    public Vec3 getParticlePos(int candleNumber, int direction) {
        float distance = (2.0f + candleNumber * 3.5f) * Util.ONE_SIXTEENTH * Util.ONE_OVER_SQRT_2;
        float y = 0.625f - candleNumber * Util.ONE_SIXTEENTH;
        return switch (direction) {
            case 0 -> new Vec3(distance, y, distance);
            case 1 -> new Vec3(distance, y, -distance);
            case 2 -> new Vec3(-distance, y, distance);
            case 3 -> new Vec3(-distance, y, -distance);
            default -> new Vec3(0, 0, 0);
        };
    }

    /**
     * Creates a candle ambient particle at the given pos.
     * Has a 30% chance of also creating a small flame particle.
     * Has a 1% chance of playing a candle ambient sound.
     *
     * @param level  Level to create the particle in
     * @param pos    Position of the particle
     * @param random RandomSource
     */
    private static void createParticle(Level level, Vec3 pos, RandomSource random) {
        level.addParticle(ParticleTypes.SMALL_FLAME, pos.x, pos.y, pos.z, 0.0, 0.0, 0.0);

        if (random.nextFloat() < 0.15f) {
            level.addParticle(ParticleTypes.SMOKE, pos.x, pos.y, pos.z, 0.0, 0.0, 0.0);
        }

        if (random.nextFloat() < 0.03f) {
            level.playLocalSound(pos.x, pos.y, pos.z, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS,
                    1.0f + random.nextFloat(), random.nextFloat() * 0.75f + 0.25f, false);
        }

    }
}
