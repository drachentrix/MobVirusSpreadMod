package org.drachentrix.plugins.mobvirusspread.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;
import org.drachentrix.plugins.mobvirusspread.item.custom.VirusItem;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class VirusCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MobVirusSpread.MOD_ID);

    public static final RegistryObject<CreativeModeTab> VIRUS_TAB = CREATIVE_MODE_TABS.register("virus_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(VirusCure.VIRUS.get()))
                    .title(Component.translatable("creativetab.virus_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(VirusCure.CURE.get());
                        output.accept(VirusCure.VIRUS.get());
                        output.accept(VirusBlocks.VOIDDIRT_BLOCK.get());
                        output.accept(VirusCure.VOIDBERRY.get());
                        output.accept(VirusCure.VOIDBERRY_SEEDS.get());
                        output.accept(VirusCure.SCYTHE.get());
                    } )
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
