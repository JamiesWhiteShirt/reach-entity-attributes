package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
interface InventoryValidationMixin {
    @Inject(
        method = "canPlayerUse(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/player/PlayerEntity;I)Z",
        at = @At(value = "RETURN", ordinal = 2), require = 1, allow = 1, cancellable = true)
    private static void getActualReachDistance(final BlockEntity blockEntity, final PlayerEntity player, final int reachDistance, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(ReachEntityAttributes.isInInventoryValidationRange(player, blockEntity.getPos(), reachDistance * reachDistance));
    }
}
