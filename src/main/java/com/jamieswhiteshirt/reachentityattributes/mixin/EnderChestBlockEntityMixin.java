package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderChestBlockEntity.class)
public abstract class EnderChestBlockEntityMixin {
    @ModifyConstant(
        method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
        )
    private static double modifyReachDistance(double value, PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, value);
    }
}
