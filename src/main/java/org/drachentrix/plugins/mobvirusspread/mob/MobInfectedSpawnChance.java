package org.drachentrix.plugins.mobvirusspread.mob;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class MobInfectedSpawnChance extends Mob {
    MobEffect virus;


    protected MobInfectedSpawnChance(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }



}
