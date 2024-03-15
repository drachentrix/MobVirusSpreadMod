package org.drachentrix.plugins.mobvirusspread.block.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.IPlantable;
import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;

import java.util.List;

public class VoidDirt extends Block {

    public VoidDirt(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("HI Ich bin pflanzbar"));
        if (plant.getBlock() == VirusBlocks.VOIDBERRY_CROP.get()){
            return true;
        }
        return false;
    }


}
