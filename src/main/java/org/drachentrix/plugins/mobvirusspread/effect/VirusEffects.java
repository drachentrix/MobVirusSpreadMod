package org.drachentrix.plugins.mobvirusspread.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;



public class VirusEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MobVirusSpread.MOD_ID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> INFECTED = MOB_EFFECTS.register("infected",
            () -> new InfectedEffect(MobEffectCategory.HARMFUL, 3124687));
}
