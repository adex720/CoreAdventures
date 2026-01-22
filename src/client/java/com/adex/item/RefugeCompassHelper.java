package com.adex.item;

import com.adex.data.dimension.ModDimensions;
import com.adex.payload.LocateRefugeC2S;
import com.adex.payload.UpdateRefugeLocationS2C;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class RefugeCompassHelper {

    private static GlobalPos REFUGE_POS = null;
    private static Vec3 UPDATE_POS = null;
    private static long LAST_UPDATE = -1000000L;
    private static float DISTANCE_DIFFERENCE = -1f;
    private static boolean WAITING_FOR_UPDATE = false;

    public static final int MINIMUM_REQUEST_INTERVAL = 100;
    public static final float MINIMUM_UPDATE_DISTANCE = 10.0f;

    /**
     * Should only be called if the player is in the core dimension.
     *
     * @param level Level of player
     * @param owner Player
     * @return Cached {@link GlobalPos} of refuge.
     */
    @Nullable
    public static GlobalPos locate(ClientLevel level, Player owner) {
        // Return cached value if a new update is requested but not finished
        if (WAITING_FOR_UPDATE) return REFUGE_POS;

        long currentTime = level.getGameTime();
        Vec3 pos = owner.position();

        // Return cached value if it was updated recently
        if (LAST_UPDATE + MINIMUM_REQUEST_INTERVAL > currentTime) return REFUGE_POS;

        // Return cached value if player hasn't moved in a way where a new update is likely to change the closest refuge
        if (!isPositionValidForReupdate(pos)) return REFUGE_POS;

        // Request position update from the server
        requestUpdate(pos, currentTime);

        // Return old cached value for now since it takes a while for the server to response.
        // The cached values are automatically updated when the server responds.
        return REFUGE_POS;
    }

    private static void requestUpdate(Vec3 pos, long currentTime) {
        WAITING_FOR_UPDATE = true;
        LAST_UPDATE = currentTime;
        UPDATE_POS = pos;

        // Send payload to request cache update
        LocateRefugeC2S payload = new LocateRefugeC2S(BlockPos.containing(pos));
        ClientPlayNetworking.send(payload);
    }

    private static void update(GlobalPos refugePos) {
        WAITING_FOR_UPDATE = false;
        REFUGE_POS = refugePos;
        DISTANCE_DIFFERENCE = (float) UPDATE_POS.distanceTo(refugePos.pos().getCenter());
    }

    public static void received(UpdateRefugeLocationS2C payload, ClientPlayNetworking.Context context) {
        update(new GlobalPos(ModDimensions.CORE, payload.pos().atY(0)));
    }

    /**
     * Returns true if the player is at least {@link RefugeCompassHelper#MINIMUM_UPDATE_DISTANCE} away
     * from the position where {@link RefugeCompassHelper#REFUGE_POS} was last updated and
     * if the player's current position is further from {@link RefugeCompassHelper#REFUGE_POS}
     * than the position the player was when it was last updated.
     * Differences on the y-axis is ignored.
     */
    private static boolean isPositionValidForReupdate(Vec3 playerPos) {
        if (REFUGE_POS == null) return true; // no cached value so position is valid

        playerPos = new Vec3(playerPos.x, 0, playerPos.z);
        return !playerPos.closerThan(UPDATE_POS, MINIMUM_UPDATE_DISTANCE)
                && !playerPos.closerThan(REFUGE_POS.pos().getCenter(), DISTANCE_DIFFERENCE);
    }
}
