package io.github.thatpreston.mermod;

import io.github.thatpreston.mermod.client.render.TailStyle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * NeoForge-only platform hooks for the 26.3 port.
 *
 * Accessory integrations are currently disabled, so these safe defaults avoid
 * Architectury @ExpectPlatform calls reaching their assertion stubs at runtime.
 */
public class MermodPlatform {
    public static ItemStack getNecklaceFromAccessorySlot(Player player) {
        return ItemStack.EMPTY;
    }

    public static TailStyle getTailStyle(Player player) {
        return null;
    }

    public static boolean hasTailStyle(Player player) {
        return false;
    }
}
