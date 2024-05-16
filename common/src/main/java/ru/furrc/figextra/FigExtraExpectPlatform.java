package ru.furrc.figextra;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class FigExtraExpectPlatform {

    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModEnable(String modId) {
        throw new AssertionError();
    }

}
