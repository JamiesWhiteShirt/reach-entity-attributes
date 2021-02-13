package com.jamieswhiteshirt.reachentityattributes.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChestBlockEntity.class)
abstract class ChestBlockEntityMixin extends LootableContainerBlockEntity implements Tickable {
    ChestBlockEntityMixin(final BlockEntityType<?> type) {
        super(type);
    }

    @ModifyVariable(
        method = "countViewers(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/LockableContainerBlockEntity;III)I",
        require = 1, allow = 1,
        at = @At(
            shift = Shift.BEFORE, value = "INVOKE", opcode = Opcodes.INVOKEVIRTUAL,
            target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private static List<PlayerEntity> getPlayersAccountingForReach(final List<PlayerEntity> players, final World world, final LockableContainerBlockEntity inventory, final int x, final int y, final int z) {
        final List<PlayerEntity> playersWithinReach = new ArrayList<>(0);
        for (final PlayerEntity player : world.getPlayers()) {
            final double reach = ReachEntityAttributes.getSquaredReachDistance(player, 5.0);
            final double dx = (x + 0.5) - player.getX();
            final double dy = (y + 0.5) - player.getEyeY();
            final double dz = (z + 0.5) - player.getZ();
            if (((dx * dx) + (dy * dy) + (dz * dz)) <= reach) {
                playersWithinReach.add(player);
            }
        }
        return playersWithinReach;
    }
}
