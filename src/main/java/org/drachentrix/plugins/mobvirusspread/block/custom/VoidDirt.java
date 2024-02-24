package org.drachentrix.plugins.mobvirusspread.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;

public class VoidDirt extends Block {

    public VoidDirt(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));
        if (plant.getBlock() == VirusBlocks.VOIDBERRY_CROP.get()){
            return true;
        }
        return false;
    }
}
