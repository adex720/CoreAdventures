package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.block.ModBlocks;
import com.adex.block.StrongTntBlock;
import com.adex.entity.ai.PotionAttackGoal;
import com.adex.entity.golem.*;
import com.adex.entity.projectile.DynamiteProjectile;
import com.adex.entity.projectile.GolemFireball;
import com.adex.entity.projectile.HeatBall;
import com.adex.entity.projectile.SplashArrow;
import com.adex.entity.sentry.TraderSentry;
import com.adex.entity.sentry.WarriorSentry;
import com.adex.item.ModItems;
import com.adex.mixin.DefaultAttributesAccessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> GOLEM_FIREBALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "golem_fireball"));
    public static final ResourceKey<EntityType<?>> HEAT_BALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "heat_ball"));
    public static final ResourceKey<EntityType<?>> DYNAMITE_PROJECTILE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "dynamite_projectile"));

    public static final EntityType<ChalcedonyGolem> CHALCEDONY_GOLEM = register("chalcedony_golem", ChalcedonyGolem.builder(ChalcedonyGolem::new));
    public static final EntityType<GarnetGolem> GARNET_GOLEM = register("garnet_golem", GarnetGolem.builder(GarnetGolem::new));
    public static final EntityType<JadeGolem> JADE_GOLEM = register("jade_golem", JadeGolem.builder(JadeGolem::new));
    public static final EntityType<JasperGolem> JASPER_GOLEM = register("jasper_golem", JasperGolem.builder(JasperGolem::new));
    public static final EntityType<OnyxGolem> ONYX_GOLEM = register("onyx_golem", OnyxGolem.builder(OnyxGolem::new));
    public static final EntityType<OpalGolem> OPAL_GOLEM = register("opal_golem", OpalGolem.builder(OpalGolem::new));
    public static final EntityType<RubyGolem> RUBY_GOLEM = register("ruby_golem", RubyGolem.builder(RubyGolem::new));
    public static final EntityType<SapphireGolem> SAPPHIRE_GOLEM = register("sapphire_golem", SapphireGolem.builder(SapphireGolem::new));
    public static final EntityType<SpinelGolem> SPINEL_GOLEM = register("spinel_golem", SpinelGolem.builder(SpinelGolem::new));
    public static final EntityType<TigersEyeGolem> TIGERS_EYE_GOLEM = register("tigers_eye_golem", TigersEyeGolem.builder(TigersEyeGolem::new));

    public static final EntityType<TraderSentry> TRADER_SENTRY = register("trader_sentry", TraderSentry.builder(TraderSentry::new));
    public static final EntityType<WarriorSentry> WARRIOR_SENTRY = register("warrior_sentry", WarriorSentry.builder(WarriorSentry::new));

    public static final EntityType<PrimedStrongTnt> PRIMED_RED_TNT = registerTnt(ModBlocks.RED_TNT, "red_tnt");
    public static final EntityType<PrimedStrongTnt> PRIMED_ORANGE_TNT = registerTnt(ModBlocks.ORANGE_TNT, "orange_tnt");
    public static final EntityType<PrimedStrongTnt> PRIMED_YELLOW_TNT = registerTnt(ModBlocks.YELLOW_TNT, "yellow_tnt");
    public static final EntityType<PrimedStrongTnt> PRIMED_GREEN_TNT = registerTnt(ModBlocks.GREEN_TNT, "green_tnt");
    public static final EntityType<PrimedStrongTnt> PRIMED_BLUE_TNT = registerTnt(ModBlocks.BLUE_TNT, "blue_tnt");

    public static final EntityType<Boat> JUNIPER_BOAT = register("juniper_boat",
            EntityType.Builder.<Boat>of((entityType, level) -> new Boat(entityType, level, () -> ModItems.JUNIPER_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375f, 0.5625f).eyeHeight(0.5625f).clientTrackingRange(10));
    public static final EntityType<ChestBoat> JUNIPER_CHEST_BOAT = register("juniper_chest_boat",
            EntityType.Builder.<ChestBoat>of((entityType, level) -> new ChestBoat(entityType, level, () -> ModItems.JUNIPER_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375f, 0.5625f).eyeHeight(0.5625f).clientTrackingRange(10));
    public static final EntityType<FastBoat> SPEED_BOAT = register("speed_boat",
            EntityType.Builder.<FastBoat>of((entityType, level) -> new FastBoat(entityType, level, () -> ModItems.SPEED_BOAT, 2.5f), MobCategory.MISC)
                    .noLootTable().sized(1.375f, 0.5625f).eyeHeight(0.5625f).clientTrackingRange(10));
    public static final EntityType<FastChestBoat> SPEED_CHEST_BOAT = register("speed_chest_boat",
            EntityType.Builder.<FastChestBoat>of((entityType, level) -> new FastChestBoat(entityType, level, () -> ModItems.SPEED_CHEST_BOAT, 2.5f), MobCategory.MISC)
                    .noLootTable().sized(1.375f, 0.5625f).eyeHeight(0.5625f).clientTrackingRange(10));

    public static final EntityType<GolemFireball> GOLEM_FIREBALL_ENTITY = register(GOLEM_FIREBALL_KEY,
            EntityType.Builder.<GolemFireball>of(GolemFireball::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<HeatBall> HEAT_BALL_ENTITY = register(HEAT_BALL_KEY,
            EntityType.Builder.<HeatBall>of(HeatBall::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<DynamiteProjectile> DYNAMITE_PROJECTILE = register(DYNAMITE_PROJECTILE_KEY,
            EntityType.Builder.<DynamiteProjectile>of(DynamiteProjectile::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<SplashArrow> SPLASH_ARROW = register("splash_arrow",
            EntityType.Builder.<SplashArrow>of(SplashArrow::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13f).clientTrackingRange(4).updateInterval(20).noLootTable());


    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
        return register(key, builder);
    }

    private static EntityType<PrimedStrongTnt> registerTnt(StrongTntBlock block, String name) {
        return register(name, EntityType.Builder.of(PrimedStrongTnt.of(block), MobCategory.MISC).noLootTable().fireImmune()
                .sized(0.98f, 0.98f).eyeHeight(0.15f).clientTrackingRange(10).updateInterval(10));
    }

    public static void initialize() {
        Golem.registerAttributes(CHALCEDONY_GOLEM);
        Golem.registerAttributes(GARNET_GOLEM);
        JadeGolem.registerAttributes(JADE_GOLEM);
        Golem.registerAttributes(JASPER_GOLEM);
        OnyxGolem.registerAttributes(ONYX_GOLEM);
        Golem.registerAttributes(OPAL_GOLEM);
        Golem.registerAttributes(RUBY_GOLEM);
        SapphireGolem.registerAttributes(SAPPHIRE_GOLEM);
        Golem.registerAttributes(SPINEL_GOLEM);
        Golem.registerAttributes(TIGERS_EYE_GOLEM);

        registerAttributes(TRADER_SENTRY, TraderSentry.createAttributes());
        registerAttributes(WARRIOR_SENTRY, WarriorSentry.createAttributes());

        PotionAttackGoal.initializeEffects();
    }

    private static void registerAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder builder) {
        DefaultAttributesAccessor.coread$getSuppliers().put(entityType, builder.build());
    }
}
