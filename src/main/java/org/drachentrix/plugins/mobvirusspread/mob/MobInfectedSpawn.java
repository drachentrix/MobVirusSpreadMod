package org.drachentrix.plugins.mobvirusspread.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class MobInfectedSpawn extends Mob {

    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent event){
        //testen ob das wircklich was macht
        //hier spaeter dann aus liste waehlen lassen fuer simple nur ein virus
        Random random = new Random();
        int number = random.nextInt() * 100;
        if (number <= 25){
            event.getEntity().getPersistentData().putBoolean("isInfected", true);
        }
    }

    protected MobInfectedSpawn(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }



}
