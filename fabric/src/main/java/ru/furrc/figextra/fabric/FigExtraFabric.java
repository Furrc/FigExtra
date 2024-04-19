package ru.furrc.figextra.fabric;

import net.fabricmc.api.ClientModInitializer;
import ru.furrc.figextra.FigExtraPlugin;

public class FigExtraFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FigExtraPlugin.init();
    }
}
