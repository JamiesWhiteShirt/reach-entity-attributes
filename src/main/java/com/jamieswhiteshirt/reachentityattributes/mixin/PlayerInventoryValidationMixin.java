package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerInventory.class)
abstract class PlayerInventoryValidationMixin {
    @ModifyConstant(
        method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z",
        require = 1, allow = 1, constant = @Constant(doubleValue = 64.0))
    private static double getActualReachDistance(final double reachDistance, final PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, reachDistance);
    }
}
