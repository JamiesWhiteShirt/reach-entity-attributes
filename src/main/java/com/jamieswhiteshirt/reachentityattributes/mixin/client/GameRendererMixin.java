package com.jamieswhiteshirt.reachentityattributes.mixin.client;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(
        method = "updateTargetedEntity(F)V",
        constant = @Constant(doubleValue = 6.0D)
    )
    private double modifyReachDistance(double value) {
        return ReachEntityAttributes.getReachDistance(client.player, value);
    }

    @ModifyConstant(
        method = "updateTargetedEntity(F)V",
        constant = @Constant(doubleValue = 3.0D)
    )
    private double modifyAttackRange(double value) {
        return ReachEntityAttributes.getAttackRange(client.player, value);
    }

    @ModifyConstant(
        method = "updateTargetedEntity(F)V",
        constant = @Constant(doubleValue = 9.0D)
    )
    private double modifyAttackRange2(double value) {
        return ReachEntityAttributes.getSquaredAttackRange(client.player, value);
    }
}
