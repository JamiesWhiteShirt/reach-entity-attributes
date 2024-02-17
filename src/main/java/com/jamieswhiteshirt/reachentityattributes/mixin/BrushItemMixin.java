package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BrushItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BrushItem.class)
abstract class BrushItemMixin {
    @ModifyExpressionValue(
        method = "getHitResult",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getReachDistance(Z)F"))
    private float getActualReachDistance(final float reachDistance, final PlayerEntity player) {
        return (float) ReachEntityAttributes.getReachDistance(player, reachDistance);
    }
}
