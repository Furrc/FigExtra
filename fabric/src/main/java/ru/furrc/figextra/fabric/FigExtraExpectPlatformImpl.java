package ru.furrc.figextra.fabric;

import ru.furrc.figextra.FigExtraExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FigExtraExpectPlatformImpl {
    /**
     * This is our actual method to {@link FigExtraExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
