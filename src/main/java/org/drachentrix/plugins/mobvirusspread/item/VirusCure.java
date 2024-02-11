package org.drachentrix.plugins.mobvirusspread.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;
import org.drachentrix.plugins.mobvirusspread.item.custom.CureItem;
import org.drachentrix.plugins.mobvirusspread.item.custom.VirusItem;

public class VirusCure {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobVirusSpread.MOD_ID);

    public static final RegistryObject<Item> CURE = ITEMS.register("cure", () -> new CureItem(
            new Item.Properties().stacksTo(1)
                                 .durability(1)));

    public static final RegistryObject<Item> VIRUS = ITEMS.register("virus", () -> new VirusItem(
            new Item.Properties().stacksTo(1).durability(1) ));

    public static final RegistryObject<Item> VOIDBERRY_SEEDS = ITEMS.register("voidberry_seeds",
            () -> new ItemNameBlockItem(VirusBlocks.VOIDBERRY_CROP.get(),
            new Item.Properties()));

    public static final RegistryObject<Item> VOIDBERRY = ITEMS.register("voidberry", () -> new Item(
            new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1f).build())));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
