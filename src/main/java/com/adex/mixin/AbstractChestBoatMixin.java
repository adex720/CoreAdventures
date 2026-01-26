package com.adex.mixin;

import com.adex.entity.FastChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(AbstractBoat.class)
public abstract class AbstractChestBoatMixin extends VehicleEntity implements Leashable {

    private AbstractChestBoatMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"), method = "controlBoat")
    private void updateSpeedModifier(Args args) {
        if (!(((AbstractBoat) (Object) this) instanceof FastChestBoat boat)) return;

        double xSpeed = args.get(0);
        double zSpeed = args.get(2);
        args.set(0, xSpeed * boat.accelerationMultiplier);
        args.set(2, zSpeed * boat.accelerationMultiplier);
    }

}
