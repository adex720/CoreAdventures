package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.block.ModBlocks;
import com.adex.block.StrongTntBlock;
import com.adex.entity.ai.PotionAttackGoal;
import com.adex.entity.golem.*;
import com.adex.entity.projectile.projectile.DynamiteProjectile;
import com.adex.entity.projectile.projectile.GolemFireball;
import com.adex.entity.projectile.projectile.HeatBall;
import com.adex.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> CHALCEDONY_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "chalcedony_golem"));
    public static final ResourceKey<EntityType<?>> GARNET_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "garnet_golem"));
    public static final ResourceKey<EntityType<?>> JADE_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "jade_golem"));
    public static final ResourceKey<EntityType<?>> JASPER_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "jasper_golem"));
    public static final ResourceKey<EntityType<?>> ONYX_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "onyx_golem"));
    public static final ResourceKey<EntityType<?>> OPAL_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "opal_golem"));
    public static final ResourceKey<EntityType<?>> RUBY_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "ruby_golem"));
    public static final ResourceKey<EntityType<?>> SAPPHIRE_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "sapphire_golem"));
    public static final ResourceKey<EntityType<?>> SPINEL_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "spinel_golem"));
    public static final ResourceKey<EntityType<?>> TIGERS_EYE_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "tigers_eye_golem"));

    public static final ResourceKey<EntityType<?>> GOLEM_FIREBALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "golem_fireball"));
    public static final ResourceKey<EntityType<?>> HEAT_BALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "heat_ball"));
    public static final ResourceKey<EntityType<?>> DYNAMITE_PROJECTILE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "dynamite_projectile"));

    public static final EntityType<ChalcedonyGolem> CHALCEDONY_GOLEM = register(CHALCEDONY_GOLEM_KEY, ChalcedonyGolem.builder(ChalcedonyGolem::new));
    public static final EntityType<GarnetGolem> GARNET_GOLEM = register(GARNET_GOLEM_KEY, GarnetGolem.builder(GarnetGolem::new));
    public static final EntityType<JadeGolem> JADE_GOLEM = register(JADE_GOLEM_KEY, JadeGolem.builder(JadeGolem::new));
    public static final EntityType<JasperGolem> JASPER_GOLEM = register(JASPER_GOLEM_KEY, JasperGolem.builder(JasperGolem::new));
    public static final EntityType<OnyxGolem> ONYX_GOLEM = register(ONYX_GOLEM_KEY, OnyxGolem.builder(OnyxGolem::new));
    public static final EntityType<OpalGolem> OPAL_GOLEM = register(OPAL_GOLEM_KEY, OpalGolem.builder(OpalGolem::new));
    public static final EntityType<RubyGolem> RUBY_GOLEM = register(RUBY_GOLEM_KEY, RubyGolem.builder(RubyGolem::new));
    public static final EntityType<SapphireGolem> SAPPHIRE_GOLEM = register(SAPPHIRE_GOLEM_KEY, SapphireGolem.builder(SapphireGolem::new));
    public static final EntityType<SpinelGolem> SPINEL_GOLEM = register(SPINEL_GOLEM_KEY, SpinelGolem.builder(SpinelGolem::new));
    public static final EntityType<TigersEyeGolem> TIGERS_EYE_GOLEM = register(TIGERS_EYE_GOLEM_KEY, TigersEyeGolem.builder(TigersEyeGolem::new));

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

    public static final EntityType<GolemFireball> GOLEM_FIREBALL_ENTITY = register(GOLEM_FIREBALL_KEY,
            EntityType.Builder.<GolemFireball>of(GolemFireball::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<HeatBall> HEAT_BALL_ENTITY = register(HEAT_BALL_KEY,
            EntityType.Builder.<HeatBall>of(HeatBall::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<DynamiteProjectile> DYNAMITE_PROJECTILE = register(DYNAMITE_PROJECTILE_KEY,
            EntityType.Builder.<DynamiteProjectile>of(DynamiteProjectile::new, MobCategory.MISC)
                    .sized(0.3125f, 0.3125f).clientTrackingRange(4).updateInterval(10).noLootTable());


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

        PotionAttackGoal.initializeEffects();
    }
}
