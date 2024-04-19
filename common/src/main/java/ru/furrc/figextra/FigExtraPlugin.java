package ru.furrc.figextra;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.furrc.figextra.pv.figura.LuaPlasmoVoiceConfig;
import ru.furrc.figextra.pv.figura.PlasmoVoiceAPI;
import ru.furrc.figextra.pv.PlasmoVoiceAddon;
import su.plo.voice.api.client.PlasmoVoiceClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.figuramc.figura.lua.FiguraAPIManager.API_GETTERS;

@FiguraAPIPlugin
@LuaWhitelist
public class FigExtraPlugin implements FiguraAPI {

    public static final PlasmoVoiceAddon VOICE_ADDON = new PlasmoVoiceAddon();

    public static final String PLUGIN_ID = "figextra";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);
    private Avatar avatar;

    public FigExtraPlugin() {
        API_GETTERS.put("pv", r -> new PlasmoVoiceAPI(r.owner));
    }

    public FigExtraPlugin(Avatar avatar) {
        this.avatar = avatar;
    }

    public static void init() {
        PlasmoVoiceClient.getAddonsLoader().load(VOICE_ADDON);
    }

    @Override
    public FiguraAPI build(Avatar avatar) {
        return new FigExtraPlugin(avatar);
    }

    @Override
    public String getName() {
        return PLUGIN_ID;
    }

    @Override
    public Collection<Class<?>> getWhitelistedClasses() {
        List<Class<?>> classesToRegister = new ArrayList<>();
        for (Class<?> aClass : FIGEXTRA_PLUGIN_CLASSES) {
            if (aClass.isAnnotationPresent(LuaWhitelist.class)) {
                classesToRegister.add(aClass);
            }
        }
        return classesToRegister;
    }

    @Override
    public Collection<Class<?>> getDocsClasses() {
        return List.of();
    }

    public static final Class<?>[] FIGEXTRA_PLUGIN_CLASSES = new Class[] {
            FigExtraPlugin.class,

            PlasmoVoiceAPI.class,

            LuaPlasmoVoiceConfig.class,
            LuaPlasmoVoiceConfig.LuaPlasmoVoiceConfigVoice.class,
    };

}
