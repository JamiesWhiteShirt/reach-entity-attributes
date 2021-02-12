package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlockEntity.class)
public abstract class ChestBlockEntityMixin {
    @Inject(
        method = "countViewers(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/LockableContainerBlockEntity;III)I",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void countViewersAccountingForReach(final World level, final LockableContainerBlockEntity be, final int x, final int y, final int z, final CallbackInfoReturnable<Integer> info) {
        int count = 0;
        for (final PlayerEntity player : level.getPlayers()) {
            if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                final Inventory inv = ((GenericContainerScreenHandler) player.currentScreenHandler).getInventory();
                if ((inv == be) || ((inv instanceof DoubleInventory) && ((DoubleInventory) inv).isPart(be))) {
                    final double r = ReachEntityAttributes.getReachDistance(player, 5.0);
                    if (player.getBoundingBox().intersects(new Box(x - r, y - r, z - r, x + r, y + r, z + r))) {
                        ++count;
                    }
                }
            }
        }
        if (count > 0) {
            info.setReturnValue(count);
        }
    }
}
