package develk.itemstackpromax.mixin;

import develk.itemstackpromax.Config;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public interface InventoryMixin {
    @Inject(method = "getMaxCountPerStack", at = @At("HEAD"), cancellable = true)
    private void overrideMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Config.GLOBAL_MAX);
    }
}
