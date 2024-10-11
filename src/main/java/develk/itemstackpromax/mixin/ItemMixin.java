package develk.itemstackpromax.mixin;

import develk.itemstackpromax.ItemStackProMax;
import develk.itemstackpromax.util.Colors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    /**
     * @author develk
     * @reason Add a line of tooltips to display the exact number
     */
    @Overwrite
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (ItemStackProMax.config().Tooltips) {
            if (stack.getCount() >= ItemStackProMax.config().Scope) {
                tooltip.add(Colors.toText("<color:#58d68d><lang:tooltip.itemstackpromax.stack:'<n1>'>", stack.getCount()));
            }
        }
    }
}
