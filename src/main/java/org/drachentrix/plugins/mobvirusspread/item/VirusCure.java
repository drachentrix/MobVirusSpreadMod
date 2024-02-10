package org.drachentrix.plugins.mobvirusspread.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.item.custom.CureItem;

public class VirusCure {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobVirusSpread.MOD_ID);

    public static final RegistryObject<Item> CURE = ITEMS.register("cure", () -> new CureItem(
            new Item.Properties().stacksTo(1)
                                 .durability(1)));

    public static final RegistryObject<Item> VIRUS = ITEMS.register("virus", () -> new Item(
            new Item.Properties().stacksTo(1)
    ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
