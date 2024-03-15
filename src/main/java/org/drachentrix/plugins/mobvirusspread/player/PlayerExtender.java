package org.drachentrix.plugins.mobvirusspread.player;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.drachentrix.plugins.mobvirusspread.player.features.ImmuneSystem;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */

public class PlayerExtender{


    @SubscribeEvent
    public void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player) {
            ImmuneSystem immuneSystem = ImmuneSystem.of(((LivingEntity) event.getEntity()));
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof Player) {
            ImmuneSystem immuneSystem = ImmuneSystem.disconnect(((LivingEntity) event.getEntity()));
        }
    }


    @SubscribeEvent
    public void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        //PacketHandler.INSTANCE.send(new PacketHandler("Ur momyy"), PacketDistributor.PLAYER.with(Minecraft.getInstance()));
        if (event.getItem().isEdible()){
            ImmuneSystem immuneSystem = ImmuneSystem.getByPlayer(event.getEntity());
            if (immuneSystem != null) {
                immuneSystem.onEatingFood(event.getItem(), event.getEntity());
            }

        }

    }

    @SubscribeEvent
    public void onPlayerGetsDamage(LivingDamageEvent event){
        if (event.getEntity() instanceof Player){
            ImmuneSystem immuneSystem = ImmuneSystem.getByPlayer(event.getEntity());

            if (immuneSystem != null && event.getEntity().getPersistentData().getBoolean("isInfected")){
                immuneSystem.onDamage(event.getEntity());
            }
        }
    }

}
