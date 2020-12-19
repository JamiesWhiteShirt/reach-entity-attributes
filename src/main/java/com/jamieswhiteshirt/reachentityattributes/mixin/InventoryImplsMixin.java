package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = {
    AbstractFurnaceBlockEntity.class,
    BrewingStandBlockEntity.class,
    EnderChestBlockEntity.class,
    LootableContainerBlockEntity.class,
    PlayerInventory.class,
    StorageMinecartEntity.class // Minecart with Chest, Minecart with Hopper
}, targets = {
    "net.minecraft.block.entity.LecternBlockEntity$1"
})
public abstract class InventoryImplsMixin implements Inventory {
    @ModifyConstant(
        method = "canPlayerUse",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
    )
    private static double modifyReachDistance(double value, PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, value);
    }
}
