package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.entity.golem.*;
import com.adex.entity.projectile.GolemFireball;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

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

    public static final EntityType<ChalcedonyGolem> CHALCEDONY_GOLEM = create(CHALCEDONY_GOLEM_KEY, ChalcedonyGolem.builder(ChalcedonyGolem::new));
    public static final EntityType<GarnetGolem> GARNET_GOLEM = create(GARNET_GOLEM_KEY, GarnetGolem.builder(GarnetGolem::new));
    public static final EntityType<JadeGolem> JADE_GOLEM = create(JADE_GOLEM_KEY, JadeGolem.builder(JadeGolem::new));
    public static final EntityType<JasperGolem> JASPER_GOLEM = create(JASPER_GOLEM_KEY, JasperGolem.builder(JasperGolem::new));
    public static final EntityType<OnyxGolem> ONYX_GOLEM = create(ONYX_GOLEM_KEY, OnyxGolem.builder(OnyxGolem::new));
    public static final EntityType<OpalGolem> OPAL_GOLEM = create(OPAL_GOLEM_KEY, OpalGolem.builder(OpalGolem::new));
    public static final EntityType<RubyGolem> RUBY_GOLEM = create(RUBY_GOLEM_KEY, RubyGolem.builder(RubyGolem::new));
    public static final EntityType<SapphireGolem> SAPPHIRE_GOLEM = create(SAPPHIRE_GOLEM_KEY, SapphireGolem.builder(SapphireGolem::new));
    public static final EntityType<SpinelGolem> SPINEL_GOLEM = create(SPINEL_GOLEM_KEY, SpinelGolem.builder(SpinelGolem::new));
    public static final EntityType<TigersEyeGolem> TIGERS_EYE_GOLEM = create(TIGERS_EYE_GOLEM_KEY, TigersEyeGolem.builder(TigersEyeGolem::new));

    public static final EntityType<GolemFireball> GOLEM_FIREBALL_ENTITY = create(GOLEM_FIREBALL_KEY,
            EntityType.Builder.<GolemFireball>of(GolemFireball::new, MobCategory.MISC)
                    .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10).noLootTable());

    private static <T extends Entity> EntityType<T> create(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void initialize() {
        Golem.registerAttributes(CHALCEDONY_GOLEM);
        Golem.registerAttributes(GARNET_GOLEM);
        Golem.registerAttributes(JADE_GOLEM);
        Golem.registerAttributes(JASPER_GOLEM);
        OnyxGolem.registerAttributes(ONYX_GOLEM);
        Golem.registerAttributes(OPAL_GOLEM);
        Golem.registerAttributes(RUBY_GOLEM);
        SapphireGolem.registerAttributes(SAPPHIRE_GOLEM);
        Golem.registerAttributes(SPINEL_GOLEM);
        Golem.registerAttributes(TIGERS_EYE_GOLEM);
    }
}
