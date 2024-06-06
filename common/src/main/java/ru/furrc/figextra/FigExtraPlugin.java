package ru.furrc.figextra;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.figuramc.figura.lua.FiguraAPIManager.API_GETTERS;
import static org.figuramc.figura.lua.FiguraAPIManager.WHITELISTED_CLASSES;

@FiguraAPIPlugin
@LuaWhitelist
public class FigExtraPlugin implements FiguraAPI {

    public static final Class<?>[] FIGEXTRA_PLUGIN_CLASSES = new Class[] {
            FigExtraPlugin.class,
    };

    public static final Class<?>[] FIGEXTRA_DOC_CLASSES = new Class[] {};

    public static final String PLUGIN_ID = "figextra";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);
    private Avatar avatar;

    public FigExtraPlugin() {
        if (FigExtraExpectPlatform.isModEnable("plasmovoice")) {
            WHITELISTED_CLASSES.add(ru.furrc.figextra.pv.PlasmoVoiceAPI.class);
            API_GETTERS.put("plasmovoice", r -> new ru.furrc.figextra.pv.PlasmoVoiceAPI());
        }

        if (FigExtraExpectPlatform.isModEnable("emotecraft")) {
            WHITELISTED_CLASSES.add(ru.furrc.figextra.emotes.EmotesAPI.class);
            WHITELISTED_CLASSES.add(ru.furrc.figextra.emotes.EmotesAPI.EmoteInfo.class);
            API_GETTERS.put("emotes", r -> new ru.furrc.figextra.emotes.EmotesAPI());
        }
    }

    public FigExtraPlugin(Avatar avatar) {
        this.avatar = avatar;
    }

    public static void init() {
        if (FigExtraExpectPlatform.isModEnable("plasmovoice")) {
            su.plo.voice.api.client.PlasmoVoiceClient
                    .getAddonsLoader().load(ru.furrc.figextra.pv.PlasmoVoiceAddon.INSTANCE);
        }
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
        List<Class<?>> classesToRegister = new ArrayList<>();
        for (Class<?> aClass : FIGEXTRA_DOC_CLASSES) {
            if (aClass.isAnnotationPresent(LuaTypeDoc.class)) {
                classesToRegister.add(aClass);
            }
        }
        return classesToRegister;
    }

}
