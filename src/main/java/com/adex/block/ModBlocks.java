package com.adex.block;

import com.adex.CoreAdventures;
import com.adex.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {

    public static final Block HARDENED_STONE = register("hardened_stone", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock HARDENED_STONE_SLAB = registerSlab("hardened_stone_slab", HARDENED_STONE);
    public static final StairBlock HARDENED_STONE_STAIRS = registerStairs("hardened_stone_stairs", HARDENED_STONE);
    public static final WallBlock HARDENED_STONE_WALL = registerWall("hardened_stone_wall", HARDENED_STONE);

    public static final Block HARDENED_STONE_BRICKS = register("hardened_stone_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock HARDENED_STONE_BRICKS_SLAB = registerSlab("hardened_stone_bricks_slab", HARDENED_STONE_BRICKS);
    public static final StairBlock HARDENED_STONE_BRICKS_STAIRS = registerStairs("hardened_stone_bricks_stairs", HARDENED_STONE_BRICKS);
    public static final WallBlock HARDENED_STONE_BRICKS_WALL = registerWall("hardened_stone_bricks_wall", HARDENED_STONE_BRICKS);

    public static final Block CRACKED_HARDENED_STONE_BRICKS = register("cracked_hardened_stone_bricks", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));

    public static final Block GABBRO = register("gabbro", BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock GABBRO_SLAB = registerSlab("gabbro_slab", GABBRO);
    public static final StairBlock GABBRO_STAIRS = registerStairs("gabbro_stairs", GABBRO);
    public static final WallBlock GABBRO_WALL = registerWall("gabbro_wall", GABBRO);

    public static final Block POLISHED_GABBRO = register("polished_gabbro", BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock POLISHED_GABBRO_SLAB = registerSlab("polished_gabbro_slab", POLISHED_GABBRO);
    public static final StairBlock POLISHED_GABBRO_STAIRS = registerStairs("polished_gabbro_stairs", POLISHED_GABBRO);
    public static final WallBlock POLISHED_GABBRO_WALL = registerWall("polished_gabbro_wall", POLISHED_GABBRO);

    public static final Block LARVIKITE = register("larvikite", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock LARVIKITE_SLAB = registerSlab("larvikite_slab", LARVIKITE);
    public static final StairBlock LARVIKITE_STAIRS = registerStairs("larvikite_stairs", LARVIKITE);
    public static final WallBlock LARVIKITE_WALL = registerWall("larvikite_wall", LARVIKITE);

    public static final Block POLISHED_LARVIKITE = register("polished_larvikite", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock POLISHED_LARVIKITE_SLAB = registerSlab("polished_larvikite_slab", POLISHED_LARVIKITE);
    public static final StairBlock POLISHED_LARVIKITE_STAIRS = registerStairs("polished_larvikite_stairs", POLISHED_LARVIKITE);
    public static final WallBlock POLISHED_LARVIKITE_WALL = registerWall("polished_larvikite_wall", POLISHED_LARVIKITE);

    public static final Block SERPENTINITE = register("serpentinite", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock SERPENTINITE_SLAB = registerSlab("serpentinite_slab", SERPENTINITE);
    public static final StairBlock SERPENTINITE_STAIRS = registerStairs("serpentinite_stairs", SERPENTINITE);
    public static final WallBlock SERPENTINITE_WALL = registerWall("serpentinite_wall", SERPENTINITE);

    public static final Block POLISHED_SERPENTINITE = register("polished_serpentinite", BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock POLISHED_SERPENTINITE_SLAB = registerSlab("polished_serpentinite_slab", POLISHED_SERPENTINITE);
    public static final StairBlock POLISHED_SERPENTINITE_STAIRS = registerStairs("polished_serpentinite_stairs", POLISHED_SERPENTINITE);
    public static final WallBlock POLISHED_SERPENTINITE_WALL = registerWall("polished_serpentinite_wall", POLISHED_SERPENTINITE);

    public static final Block SLATE = register("slate", BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock SLATE_SLAB = registerSlab("slate_slab", SLATE);
    public static final StairBlock SLATE_STAIRS = registerStairs("slate_stairs", SLATE);
    public static final WallBlock SLATE_WALL = registerWall("slate_wall", SLATE);

    public static final Block POLISHED_SLATE = register("polished_slate", BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock POLISHED_SLATE_SLAB = registerSlab("polished_slate_slab", POLISHED_SLATE);
    public static final StairBlock POLISHED_SLATE_STAIRS = registerStairs("polished_slate_stairs", POLISHED_SLATE);
    public static final WallBlock POLISHED_SLATE_WALL = registerWall("polished_slate_wall", POLISHED_SLATE);

    public static final Block TRAVERTINE = register("travertine", BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock TRAVERTINE_SLAB = registerSlab("travertine_slab", TRAVERTINE);
    public static final StairBlock TRAVERTINE_STAIRS = registerStairs("travertine_stairs", TRAVERTINE);
    public static final WallBlock TRAVERTINE_WALL = registerWall("travertine_wall", TRAVERTINE);

    public static final Block POLISHED_TRAVERTINE = register("polished_travertine", BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0f, 9.0f));
    public static final SlabBlock POLISHED_TRAVERTINE_SLAB = registerSlab("polished_travertine_slab", POLISHED_TRAVERTINE);
    public static final StairBlock POLISHED_TRAVERTINE_STAIRS = registerStairs("polished_travertine_stairs", POLISHED_TRAVERTINE);
    public static final WallBlock POLISHED_TRAVERTINE_WALL = registerWall("polished_travertine_wall", POLISHED_TRAVERTINE);

    public static final Block CHALCEDONY_ORE = register("chalcedony_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block GARNET_ORE = register("garnet_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block JADE_ORE = register("jade_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block JASPER_ORE = register("jasper_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block ONYX_ORE = register("onyx_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block OPAL_ORE = register("opal_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block RUBY_ORE = register("ruby_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block SAPPHIRE_ORE = register("sapphire_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block SPINEL_ORE = register("spinel_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));
    public static final Block TIGERS_EYE_ORE = register("tigers_eye_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 9.0f));

    public static final Block CHALCEDONY_GOLEM_BLOCK = register("chalcedony_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block GARNET_GOLEM_BLOCK = register("garnet_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block JADE_GOLEM_BLOCK = register("jade_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block JASPER_GOLEM_BLOCK = register("jasper_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block ONYX_GOLEM_BLOCK = register("onyx_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block OPAL_GOLEM_BLOCK = register("opal_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block RUBY_GOLEM_BLOCK = register("ruby_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block SAPPHIRE_GOLEM_BLOCK = register("sapphire_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block SPINEL_GOLEM_BLOCK = register("spinel_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));
    public static final Block TIGERS_EYE_GOLEM_BLOCK = register("tigers_eye_golem_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0f, 120.0f).sound(SoundType.METAL));

    public static final Block CHALCEDONY_BLOCK = register("chalcedony_block", GolemSpawningBlock.with(CHALCEDONY_GOLEM_BLOCK, ModEntities.CHALCEDONY_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block GARNET_BLOCK = register("garnet_block", GolemSpawningBlock.with(GARNET_GOLEM_BLOCK, ModEntities.GARNET_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_HYPHAE).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block JADE_BLOCK = register("jade_block", GolemSpawningBlock.with(JADE_GOLEM_BLOCK, ModEntities.JADE_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block JASPER_BLOCK = register("jasper_block", GolemSpawningBlock.with(JASPER_GOLEM_BLOCK, ModEntities.JASPER_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block ONYX_BLOCK = register("onyx_block", GolemSpawningBlock.with(ONYX_GOLEM_BLOCK, ModEntities.ONYX_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block OPAL_BLOCK = register("opal_block", GolemSpawningBlock.with(OPAL_GOLEM_BLOCK, ModEntities.OPAL_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block RUBY_BLOCK = register("ruby_block", GolemSpawningBlock.with(RUBY_GOLEM_BLOCK, ModEntities.RUBY_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block SAPPHIRE_BLOCK = register("sapphire_block", GolemSpawningBlock.with(SAPPHIRE_GOLEM_BLOCK, ModEntities.SAPPHIRE_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block SPINEL_BLOCK = register("spinel_block", GolemSpawningBlock.with(SPINEL_GOLEM_BLOCK, ModEntities.SPINEL_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));
    public static final Block TIGERS_EYE_BLOCK = register("tigers_eye_block", GolemSpawningBlock.with(TIGERS_EYE_GOLEM_BLOCK, ModEntities.TIGERS_EYE_GOLEM), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL));

    public static final Block JUNIPER_LOG = register("juniper_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(blockState -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.WOOD : MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block JUNIPER_LEAVES = register("juniper_leaves", properties -> new TintedParticleLeavesBlock(0.01F, properties), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never));

    public static final Block REINFORCED_ANCIENT_DEBRIS = register("reinforced_ancient_debris",
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(35.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS).pushReaction(PushReaction.BLOCK));

    public static final Block CORE_PORTAL_BLOCK = registerWithoutBlockItem("core_portal_block", CorePortalBlock::new,
            BlockBehaviour.Properties.of().noCollision().randomTicks().strength(-1.0f).sound(SoundType.GLASS).lightLevel(_ -> 11).pushReaction(PushReaction.BLOCK));

    private static Block register(String name, BlockBehaviour.Properties settings) {
        return register(name, Block::new, settings);
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        ResourceKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    @SuppressWarnings("unused")
    private static Block registerWithoutBlockItem(String name, BlockBehaviour.Properties settings) {
        return registerWithoutBlockItem(name, Block::new, settings);
    }

    private static Block registerWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static SlabBlock registerSlab(String string, Block block) {
        return (SlabBlock) register(string, SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(block));
    }

    private static StairBlock registerStairs(String string, Block block) {
        return (StairBlock) register(string, properties -> new StairBlock(block.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(block));
    }

    private static WallBlock registerWall(String string, Block block) {
        return (WallBlock) register(string, WallBlock::new, BlockBehaviour.Properties.ofFullCopy(block).forceSolidOn());
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CoreAdventures.MOD_ID, name));
    }

    public static void initialize() {
        addToItemGroups();
    }

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(itemGroup -> {
            itemGroup.accept(ModBlocks.HARDENED_STONE.asItem());

            itemGroup.accept(ModBlocks.GABBRO.asItem());
            itemGroup.accept(ModBlocks.LARVIKITE.asItem());
            itemGroup.accept(ModBlocks.SERPENTINITE.asItem());
            itemGroup.accept(ModBlocks.SLATE.asItem());
            itemGroup.accept(ModBlocks.TRAVERTINE.asItem());

            itemGroup.accept(ModBlocks.CHALCEDONY_ORE.asItem());
            itemGroup.accept(ModBlocks.GARNET_ORE.asItem());
            itemGroup.accept(ModBlocks.JADE_ORE.asItem());
            itemGroup.accept(ModBlocks.JASPER_ORE.asItem());
            itemGroup.accept(ModBlocks.ONYX_ORE.asItem());
            itemGroup.accept(ModBlocks.OPAL_ORE.asItem());
            itemGroup.accept(ModBlocks.RUBY_ORE.asItem());
            itemGroup.accept(ModBlocks.SAPPHIRE_ORE.asItem());
            itemGroup.accept(ModBlocks.SPINEL_ORE.asItem());
            itemGroup.accept(ModBlocks.TIGERS_EYE_ORE.asItem());

            itemGroup.accept(ModBlocks.JUNIPER_LOG.asItem());
            itemGroup.accept(ModBlocks.JUNIPER_LEAVES.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(itemGroup -> {
            itemGroup.accept(ModBlocks.HARDENED_STONE.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_SLAB.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_WALL.asItem());

            itemGroup.accept(ModBlocks.HARDENED_STONE_BRICKS.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_BRICKS_SLAB.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_BRICKS_STAIRS.asItem());
            itemGroup.accept(ModBlocks.HARDENED_STONE_BRICKS_WALL.asItem());

            itemGroup.accept(ModBlocks.CRACKED_HARDENED_STONE_BRICKS.asItem());

            itemGroup.accept(ModBlocks.GABBRO.asItem());
            itemGroup.accept(ModBlocks.GABBRO_SLAB.asItem());
            itemGroup.accept(ModBlocks.GABBRO_STAIRS.asItem());
            itemGroup.accept(ModBlocks.GABBRO_WALL.asItem());

            itemGroup.accept(ModBlocks.POLISHED_GABBRO.asItem());
            itemGroup.accept(ModBlocks.POLISHED_GABBRO_SLAB.asItem());
            itemGroup.accept(ModBlocks.POLISHED_GABBRO_STAIRS.asItem());
            itemGroup.accept(ModBlocks.POLISHED_GABBRO_WALL.asItem());

            itemGroup.accept(ModBlocks.LARVIKITE.asItem());
            itemGroup.accept(ModBlocks.LARVIKITE_SLAB.asItem());
            itemGroup.accept(ModBlocks.LARVIKITE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.LARVIKITE_WALL.asItem());

            itemGroup.accept(ModBlocks.POLISHED_LARVIKITE.asItem());
            itemGroup.accept(ModBlocks.POLISHED_LARVIKITE_SLAB.asItem());
            itemGroup.accept(ModBlocks.POLISHED_LARVIKITE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.POLISHED_LARVIKITE_WALL.asItem());

            itemGroup.accept(ModBlocks.SERPENTINITE.asItem());
            itemGroup.accept(ModBlocks.SERPENTINITE_SLAB.asItem());
            itemGroup.accept(ModBlocks.SERPENTINITE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.SERPENTINITE_WALL.asItem());

            itemGroup.accept(ModBlocks.POLISHED_SERPENTINITE.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SERPENTINITE_SLAB.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SERPENTINITE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SERPENTINITE_WALL.asItem());

            itemGroup.accept(ModBlocks.SLATE.asItem());
            itemGroup.accept(ModBlocks.SLATE_SLAB.asItem());
            itemGroup.accept(ModBlocks.SLATE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.SLATE_WALL.asItem());

            itemGroup.accept(ModBlocks.POLISHED_SLATE.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SLATE_SLAB.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SLATE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.POLISHED_SLATE_WALL.asItem());

            itemGroup.accept(ModBlocks.TRAVERTINE.asItem());
            itemGroup.accept(ModBlocks.TRAVERTINE_SLAB.asItem());
            itemGroup.accept(ModBlocks.TRAVERTINE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.TRAVERTINE_WALL.asItem());

            itemGroup.accept(ModBlocks.POLISHED_TRAVERTINE.asItem());
            itemGroup.accept(ModBlocks.POLISHED_TRAVERTINE_SLAB.asItem());
            itemGroup.accept(ModBlocks.POLISHED_TRAVERTINE_STAIRS.asItem());
            itemGroup.accept(ModBlocks.POLISHED_TRAVERTINE_WALL.asItem());

            itemGroup.accept(ModBlocks.CHALCEDONY_BLOCK.asItem());
            itemGroup.accept(ModBlocks.GARNET_BLOCK.asItem());
            itemGroup.accept(ModBlocks.JADE_BLOCK.asItem());
            itemGroup.accept(ModBlocks.JASPER_BLOCK.asItem());
            itemGroup.accept(ModBlocks.ONYX_BLOCK.asItem());
            itemGroup.accept(ModBlocks.OPAL_BLOCK.asItem());
            itemGroup.accept(ModBlocks.RUBY_BLOCK.asItem());
            itemGroup.accept(ModBlocks.SAPPHIRE_BLOCK.asItem());
            itemGroup.accept(ModBlocks.SPINEL_BLOCK.asItem());
            itemGroup.accept(ModBlocks.TIGERS_EYE_BLOCK.asItem());

            itemGroup.accept(ModBlocks.CHALCEDONY_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.GARNET_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.JADE_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.JASPER_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.ONYX_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.OPAL_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.RUBY_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.SAPPHIRE_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.SPINEL_GOLEM_BLOCK.asItem());
            itemGroup.accept(ModBlocks.TIGERS_EYE_GOLEM_BLOCK.asItem());

            itemGroup.accept(ModBlocks.JUNIPER_LOG.asItem());
            itemGroup.accept(ModBlocks.JUNIPER_LEAVES.asItem());

            itemGroup.accept(ModBlocks.REINFORCED_ANCIENT_DEBRIS.asItem());
        });
    }

}
