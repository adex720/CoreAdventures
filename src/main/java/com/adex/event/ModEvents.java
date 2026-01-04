package com.adex.event;

import com.adex.CoreAdventures;
import com.adex.enchantment.effect.BreakMultipleEnchantmentEffect;
import com.adex.enchantment.effect.ModEnchantmentEffectComponents;
import com.adex.entity.attribute.HeatManager;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModEvents {

    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(ModEvents::heatTick);
    }

    public static void heatTick(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(player -> HeatManager.serverHeatTick(player, server));
    }

    public static void onPlayerBlockBreak(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack itemStack) {
        // Loop trough every enchantment on the item used to break the block
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemStack.getEnchantments().entrySet()) {
            Enchantment enchantment = entry.getKey().value();
            // Run onBlockBroken for EnchantmentBrokenEffect types
            for (ConditionalEffect<EnchantmentValueEffect> conditionalEffect : enchantment.getEffects(ModEnchantmentEffectComponents.BREAK_MULTIPLE)) {
                if (conditionalEffect.effect() instanceof BreakMultipleEnchantmentEffect effect) {
                    effect.onBlockBroken(level, player, pos, state, blockEntity, itemStack, entry.getIntValue());
                } else {
                    CoreAdventures.LOGGER.error("Enchantment {} has effect BREAK_MULTIPLE but doesn't implement BreakMultipleEnchantmentEffect, ignoring effect ", enchantment.description());
                }
            }
        }
    }
}
