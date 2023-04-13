package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Inventory.class)
interface InventoryValidationMixin {
    @Redirect(
        method = "canPlayerUse(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/player/PlayerEntity;I)Z",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;squaredDistanceTo(DDD)D"), require = 1, allow = 1)
    private static double getActualReachDistance(final PlayerEntity player, final double e, final double f, final double g, final BlockEntity blockEntity, final PlayerEntity ignoredPlayer, int reachDistance) {
        return ReachEntityAttributes.getInventoryValidationValue(player, player.squaredDistanceTo(e, f, g), reachDistance * reachDistance);
    }
}
