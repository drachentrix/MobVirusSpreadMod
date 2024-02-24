package org.drachentrix.plugins.mobvirusspread.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.drachentrix.plugins.mobvirusspread.effect.InfectedEffect;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class MobNearbyInfectedScanner {

    private int tickCounter = 0;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;

            if (tickCounter >= 25 * 20) {
                tickCounter = 0;

                InfectedEffect.checkEffectDurations();
            }
        }
    }

    public void scanForNearbyEntitys(Mob infectedMob){
        BlockPos mobPos = infectedMob.blockPosition();

    }
}
