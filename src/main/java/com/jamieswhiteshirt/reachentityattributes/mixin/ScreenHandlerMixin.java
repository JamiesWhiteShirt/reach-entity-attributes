package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {
    @ModifyConstant(
        //private static synthetic method_17696(Block,PlayerEntity,World,BlockPos)Boolean
        method = "method_17696",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
    )
    private static double modifyReachDistance(double value, /*Method parameters*/ Block block, PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, value);
    }
}
