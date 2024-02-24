package org.drachentrix.plugins.mobvirusspread.block.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;
import org.drachentrix.plugins.mobvirusspread.item.VirusCure;

//CarrotBlock spaeter wieder back machen
public class VoidBerryCropBlock extends CarrotBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    public VoidBerryCropBlock(Properties properties) {
        super(properties);
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(blockState.getBlock().toString()));
        if (!(blockState.getBlock() instanceof VoidDirt) ){
            return false;
        }
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("HI Ich bin da"));
        return super.canSurvive(blockState, levelReader, blockPos);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.dimension() == Level.END){
            super.randomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VirusCure.VOIDBERRY_SEEDS.get();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
