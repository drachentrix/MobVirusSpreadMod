package org.drachentrix.plugins.mobvirusspread.player;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.drachentrix.plugins.mobvirusspread.player.features.ImmuneSystem;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class PlayerExtender{
    public static ImmuneSystem immuneSystem;

    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getItem().isEdible()){
            immuneSystem.onEatingFood(event.getItem(), event.getEntity());
        }

    }

    @SubscribeEvent
    public static void onPlayerGetsDamage(LivingDamageEvent damageEvent){
        if (damageEvent.getEntity() instanceof Player){

        }
    }

}
