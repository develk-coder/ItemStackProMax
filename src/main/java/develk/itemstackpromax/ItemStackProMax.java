package develk.itemstackpromax;

import develk.itemstackpromax.config.ModConfig;
import develk.itemstackpromax.util.Colors;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemStackProMax implements ModInitializer {
	public static final String MOD_ID = "itemstackpromax";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static ConfigHolder<ModConfig> config;
	private static int init = 0;
	
	public static ModConfig config() {
		if (init == 0) {
			config = AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
			init = 1;
        }
        return config.get();
    }
	
	@Override
	public void onInitialize() {
		Colors.init();
		LOGGER.info("ItemStackProMax!");
	}
}