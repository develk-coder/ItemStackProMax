package develk.itemstackpromax.mixin;

import develk.itemstackpromax.ItemStackProMax;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Inventory.class)
public interface InventoryMixin {
    /**
     * @author develk
     * @reason Max Count Per Stack -> 1000000
     */
    @Overwrite
    default int getMaxCountPerStack() {
        return ItemStackProMax.config().MaxStackSize;
    }
}
