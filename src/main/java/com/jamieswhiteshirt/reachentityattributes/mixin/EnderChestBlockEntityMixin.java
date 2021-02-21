package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderChestBlockEntity.class)
abstract class EnderChestBlockEntityMixin extends BlockEntity {
    EnderChestBlockEntityMixin(final BlockEntityType<?> type, final BlockPos pos, final BlockState state) {
        super(type, pos, state);
    }

    @ModifyConstant(
        method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z",
        require = 1, allow = 1, constant = @Constant(doubleValue = 64.0))
    private static double getActualReachDistance(final double reachDistance, final PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, reachDistance);
    }
}
