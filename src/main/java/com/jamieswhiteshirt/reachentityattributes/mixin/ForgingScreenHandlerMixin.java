package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ForgingScreenHandler.class)
public abstract class ForgingScreenHandlerMixin extends ScreenHandler {
    protected ForgingScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @ModifyConstant(
        //private synthetic method_24924(PlayerEntity,World,BlockPos)Boolean
        method = "method_24924",
        constant = {
            @Constant(doubleValue = 64.0D)
        }
    )
    private double modifyReachDistance(double value, PlayerEntity player) {
        return ReachEntityAttributes.getSquaredReachDistance(player, value);
    }
}
