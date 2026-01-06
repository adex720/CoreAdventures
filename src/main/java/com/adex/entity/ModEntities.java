package com.adex.entity;

import com.adex.CoreAdventures;
import com.adex.entity.golem.ChalcedonyGolem;
import com.adex.entity.golem.Golem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> CHALCEDONY_GOLEM_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, "chalcedony_golem"));
    public static final EntityType<ChalcedonyGolem> CHALCEDONY_GOLEM = register(CHALCEDONY_GOLEM_KEY, ChalcedonyGolem.builder(ChalcedonyGolem::new));

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void initialize() {
        Golem.registerAttributes(CHALCEDONY_GOLEM);
    }
}
