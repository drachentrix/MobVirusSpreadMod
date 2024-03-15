package org.drachentrix.plugins.mobvirusspread.player;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class FoodValues {
    public Map<ItemStack, Integer> storedFoods = new LinkedHashMap<>();

    public void restoreOtherFood(ItemStack foods){
        //formel zum restoren machen
    }
    public void addToList(ItemStack food, LivingEntity entity){
        Random random = new Random();
        FoodProperties unstoredFood = food.getItem().getFoodProperties(food, entity);
        int value = 0;
        if (random.nextInt(0,2 ) == 0){
            value = -(unstoredFood.getNutrition() + random.nextInt(1, 100)) ;
        } else {
            value = unstoredFood.getNutrition() + random.nextInt(1, 100);
        }
        storedFoods.put(food, value);
    }
    public Map<ItemStack, Integer> getStoredFoods() {
        return storedFoods;
    }

    public void setStoredFoods(Map<ItemStack, Integer> storedFoods) {
        this.storedFoods = storedFoods;
    }
}
