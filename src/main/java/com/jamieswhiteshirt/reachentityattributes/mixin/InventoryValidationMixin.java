package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = {
    AbstractFurnaceBlockEntity.class,
    BrewingStandBlockEntity.class,
    LootableContainerBlockEntity.class,
    PlayerInventory.class,
    StorageMinecartEntity.class
}, targets = {
    "net.minecraft.block.entity.LecternBlockEntity$1"
})
abstract class InventoryValidationMixin implements Inventory {
    @ModifyExpressionValue(method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At(value = "CONSTANT", args = "doubleValue=64.0"))
    private static double getActualReachDistance(final double reachDistance, final PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, reachDistance);
    }
}
