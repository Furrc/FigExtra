package ru.furrc.figextra.fabric;

import ru.furrc.figextra.FigExtraExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FigExtraExpectPlatformImpl {

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static boolean isModEnable(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}
