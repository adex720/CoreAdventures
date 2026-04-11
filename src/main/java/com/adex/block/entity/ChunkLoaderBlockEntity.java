package com.adex.block.entity;

import com.adex.CoreAdventures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

/**
 * Every chunk with at least one chunk loader will always be loaded.
 */
public class ChunkLoaderBlockEntity extends BlockEntity {

    public ChunkLoaderBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CHUNK_LOADER_BLOCK_ENTITY, blockPos, blockState);
    }

    /**
     * The constructor doesn't provide access for the level,
     * so the force-load status is added here.
     * Minecraft code calls this method after creating a new block entity.
     */
    @Override
    public void setLevel(@NonNull Level level) {
        super.setLevel(level);

        if (level instanceof ServerLevel) forceLoad(true);
    }

    /**
     * Updates FORCE chunk ticket.
     *
     * @param shouldLoad True if the chunk should be added to the list of always loaded chunks.
     *                   False if the chunk should be removed.
     */
    public void forceLoad(boolean shouldLoad) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        ChunkPos chunkPos = ChunkPos.containing(this.getBlockPos());
        serverLevel.setChunkForced(chunkPos.x(), chunkPos.z(), shouldLoad);
    }

    /**
     * Returns true if the chunk of this block entity contains at least one {@link ChunkLoaderBlockEntity}
     * whose {@link ChunkLoaderBlockEntity#remove} is false.
     */
    public boolean shouldChunkBeLoaded() {
        if (level == null) {
            CoreAdventures.LOGGER.warn("No level for blockEntity at {}", worldPosition);
            return false;
        }

        if (!(level instanceof ServerLevel)) return false;

        if (!remove) return true;

        for (BlockEntity entity : level.getChunkAt(worldPosition).getBlockEntities().values()) {
            if (entity.getType() == ModBlockEntities.CHUNK_LOADER_BLOCK_ENTITY && !entity.isRemoved()) return true;
        }

        return false;
    }

    /**
     * Removes force load if needed.
     */
    public void onBreak() {
        if (!shouldChunkBeLoaded()) forceLoad(false);
    }
}
