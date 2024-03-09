package org.drachentrix.plugins.mobvirusspread.block;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.block.custom.VoidBerryCropBlock;
import org.drachentrix.plugins.mobvirusspread.block.custom.VoidDirt;

import org.drachentrix.plugins.mobvirusspread.item.VirusCure;

import java.util.function.Supplier;


public class VirusBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobVirusSpread.MOD_ID);


    public static final RegistryObject<Block> VOIDBERRY_CROP = registryObject("voidberry_crop",
            () ->  new VoidBerryCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));


    public static final RegistryObject<Block> VOIDDIRT_BLOCK = registryObject("voiddirt_block",
            () ->  new VoidDirt(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).sound(SoundType.ROOTED_DIRT)));

    private static <T extends Block> RegistryObject<T> registryObject(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryObject(name, toReturn);
        return toReturn;
    }

    private static <T extends  Block> RegistryObject<Item> registryObject(String name, RegistryObject<T> block){
        return VirusCure.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus );
    }
}
