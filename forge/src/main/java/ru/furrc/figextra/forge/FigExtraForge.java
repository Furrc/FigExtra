package ru.furrc.figextra.forge;

import net.minecraftforge.fml.common.Mod;
import ru.furrc.figextra.FigExtraPlugin;

/**
 * A mod class is needed for Forge to load the Plugin
 */
@Mod(FigExtraPlugin.PLUGIN_ID)
public class FigExtraForge {
    public FigExtraForge() {
        FigExtraPlugin.init();
    }
}
