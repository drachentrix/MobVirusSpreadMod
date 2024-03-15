package org.drachentrix.plugins.mobvirusspread.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.drachentrix.plugins.mobvirusspread.item.VirusCure;

import java.util.Random;

public class Events {
    @SubscribeEvent
    public void onBreakGras(BlockEvent.BreakEvent blockEvent) {
        if (blockEvent.getState().getBlock() == Blocks.SHORT_GRASS) {
            if (blockEvent.getPlayer().getMainHandItem().getItem() == VirusCure.SCYTHE.get()) {
                if (new Random().nextInt(0, 2) == 1) {
                    Level world = blockEvent.getPlayer().getCommandSenderWorld();
                    BlockPos pos = blockEvent.getPos();
                    ItemStack itemStack = new ItemStack(VirusCure.VOIDBERRY_SEEDS.get());
                    ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                    world.addFreshEntity(itemEntity);
                }
            }
        }
    }
}
