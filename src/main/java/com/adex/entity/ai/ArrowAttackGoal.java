package com.adex.entity.ai;

import com.adex.entity.golem.Golem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class ArrowAttackGoal extends EyeOfSightAttackGoal {

    private final float damage;
    private final float arrowSpeed;

    public ArrowAttackGoal(Golem golem, float minDistance, float maxDistance, int cooldown, float damage, float arrowSpeed) {
        super(golem, minDistance, maxDistance, cooldown, 1.0d);
        this.damage = damage;
        this.arrowSpeed = arrowSpeed;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void attack() {
        createArrow((ServerLevel) golem.level(), golem.getTarget());
    }

    public void createArrow(ServerLevel level, LivingEntity target) {
        Vec3 direction = createDirectionVector(target, level.getRandom(), 0.0d);
        Arrow arrow = new Arrow(level, golem.getX() + direction.x, golem.getY(0.5d) + 1.0d + direction.y, golem.getZ() + direction.z, new ItemStack(Items.ARROW), null);
        arrow.setBaseDamage(damage);
        arrow.setDeltaMovement(direction.scale(arrowSpeed));
        //arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        level.addFreshEntity(arrow);
    }
}
