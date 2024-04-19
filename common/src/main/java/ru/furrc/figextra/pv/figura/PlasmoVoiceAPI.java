package ru.furrc.figextra.pv.figura;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.LuaWhitelist;
import org.luaj.vm2.LuaTable;

import static ru.furrc.figextra.FigExtraPlugin.VOICE_ADDON;

@LuaWhitelist
public class PlasmoVoiceAPI {

    private final Avatar owner;

    public PlasmoVoiceAPI(Avatar owner) {
        this.owner = owner;
    }

    @LuaWhitelist
    public String getVersion() {
        return VOICE_ADDON.voiceClient.getVersion();
    }

    @LuaWhitelist
    public LuaPlasmoVoiceConfig getConfig() {
        return new LuaPlasmoVoiceConfig();
    }

    @Override
    public String toString() {
        return "PlasmoVoiceAPI";
    }

}
