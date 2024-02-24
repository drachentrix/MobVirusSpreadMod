package org.drachentrix.plugins.mobvirusspread.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.phys.Vec3;
import java.util.Random;


/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class MobNearbyInfectedScanner {

    private int tickCounter = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;
            Vec3 pos = event.player.position();
            Vec3 minVec = new Vec3(pos.x - 100, pos.y - 100, pos.z - 100);
            Vec3 maxVec = new Vec3(pos.x + 100, pos.y + 100, pos.z + 100);
            AABB scanArea = new AABB(minVec, maxVec);

            if (tickCounter >= 10 * 20) {
                tickCounter = 0;
                for(Mob entity: event.player.getCommandSenderWorld().getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, event.player, scanArea)){
                    if (entity.getPersistentData().getBoolean("isInfected")){
                        scanForNearbyEntitys(entity);
                    }
                }
            }
        }
    }

    public void scanForNearbyEntitys(Mob infectedMob){
        BlockPos mobPos = infectedMob.blockPosition();
        Vec3 minVec = new Vec3(mobPos.getX() - 5, mobPos.getY() - 5, mobPos.getZ() - 5);
        Vec3 maxVec = new Vec3(mobPos.getX() + 5, mobPos.getY() + 5, mobPos.getZ() + 5);
        AABB scanArea = new AABB(minVec, maxVec);
        Level world = infectedMob.getCommandSenderWorld();
        Random random = new Random();
        // wahrscheinlich condition falsch idk
        for (Entity entity: world.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, infectedMob, scanArea ) ){
            if (!entity.getPersistentData().getBoolean("isInfected") && (double) random.nextInt(0, 101) / 100 < 0.10){
                entity.getPersistentData().putBoolean("isInfected", true);
            }
        }
    }
}
