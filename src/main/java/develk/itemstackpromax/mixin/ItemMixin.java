package develk.itemstackpromax.mixin;

import develk.itemstackpromax.ItemStackProMax;
import develk.itemstackpromax.util.Colors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void injected(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (ItemStackProMax.config().Tooltips) {
            if (stack.getCount() >= ItemStackProMax.config().Scope) {
                tooltip.add(Colors.toText("<color:#58d68d><lang:tooltip.itemstackpromax.stack:'<n1>'>", stack.getCount()));
            }
        }
    }
}
