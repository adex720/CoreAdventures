package com.adex.entity;

import com.adex.CoreAdventures;
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

    public static final EntityType<PrimedStrongTnt> PRIMED_STRONG_TNT = register("strong_tnt",
            EntityType.Builder.of((PrimedStrongTnt::new), MobCategory.MISC).noLootTable().fireImmune()
                    .sized(0.98F, 0.98F).eyeHeight(0.15F).clientTrackingRange(10).updateInterval(10));

    public static final EntityType<Boat> JUNIPER_BOAT = register("juniper_boat",
            EntityType.Builder.<Boat>of((entityType, level) -> new Boat(entityType, level, () -> ModItems.JUNIPER_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> JUNIPER_CHEST_BOAT = register("juniper_chest_boat",
            EntityType.Builder.<ChestBoat>of((entityType, level) -> new ChestBoat(entityType, level, () -> ModItems.JUNIPER_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

    public static final EntityType<GolemFireball> GOLEM_FIREBALL_ENTITY = register(GOLEM_FIREBALL_KEY,
            EntityType.Builder.<GolemFireball>of(GolemFireball::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<HeatBall> HEAT_BALL_ENTITY = register(HEAT_BALL_KEY,
            EntityType.Builder.<HeatBall>of(HeatBall::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).noLootTable());
    public static final EntityType<DynamiteProjectile> DYNAMITE_PROJECTILE = register(DYNAMITE_PROJECTILE_KEY,
            EntityType.Builder.<DynamiteProjectile>of(DynamiteProjectile::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).noLootTable());


    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
        return register(key, builder);
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
