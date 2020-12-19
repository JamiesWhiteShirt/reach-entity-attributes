package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

// For some reason this class does not use the static helper method in the superclass
@Mixin(ForgingScreenHandler.class) // Anvil, Smithing Table
public abstract class ForgingScreenHandlerMixin extends ScreenHandler {
    protected ForgingScreenHandlerMixin(@Nullable ScreenHandlerType<?> screenHandlerType, int i) {
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
