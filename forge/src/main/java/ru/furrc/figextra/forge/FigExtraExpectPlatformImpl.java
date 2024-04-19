package ru.furrc.figextra.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class FigExtraExpectPlatformImpl {
    /**
     * This is our actual method to {@link FigExtraExpectPlatformImpl#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
