package develk.itemstackpromax.config;

import develk.itemstackpromax.ItemStackProMax;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = ItemStackProMax.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    
    @ConfigEntry.BoundedDiscrete(min = 1, max = 2147483647)
    public int MaxStackSize = 1000000;
    
    @ConfigEntry.Gui.PrefixText
    
    public boolean Tooltips = true;
    
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1000)
    public int Scope = 1000;
}
