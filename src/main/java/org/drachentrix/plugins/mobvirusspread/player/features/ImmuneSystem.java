package org.drachentrix.plugins.mobvirusspread.player.features;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.drachentrix.plugins.mobvirusspread.player.FoodValues;

import java.util.Random;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class ImmuneSystem {
    public float immuneSystem = 100;
    public int resistanz = 1; // prozent wie sehr die Person resistent ist z.B. mit armor wird weniger verbraucht
    public FoodValues foodValues;

    public float getImmuneSystem() {
        return immuneSystem;
    }

    public void setImmuneSystem(float immuneSystem) {
        this.immuneSystem = immuneSystem;
    }

    public void onDamage(){

    }
    public void onEatingFood(ItemStack itemStack, LivingEntity entity) {
        if (!foodValues.storedFoods.containsKey(itemStack)) {
            foodValues.addToList(itemStack, entity);
        }
        Player player = (Player) entity;
        immuneSystem += foodValues.storedFoods.get(itemStack);
        foodValues.storedFoods.replace( itemStack, (foodValues.storedFoods.get(itemStack) - player.getFoodData().getFoodLevel() -
                new Random().nextInt(1, 10) + new Random().nextInt(1, 5) ) * (resistanz / 100));

        if (immuneSystem > 100) {
            immuneSystem = 100;
        } else if (immuneSystem < 0) {
            immuneSystem = 0;
        }
    }

}
