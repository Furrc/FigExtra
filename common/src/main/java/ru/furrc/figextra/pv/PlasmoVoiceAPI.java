package ru.furrc.figextra.pv;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.LuaNotNil;
import org.figuramc.figura.lua.LuaWhitelist;
import su.plo.voice.proto.data.pos.Pos3d;

@LuaWhitelist
public class PlasmoVoiceAPI {

    public static final PlasmoVoiceAddon VOICE_ADDON = new PlasmoVoiceAddon();

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

    @LuaWhitelist
    public void renderDistanceVisualizer(@LuaNotNil int radius, @LuaNotNil int color, @LuaNotNil double x, @LuaNotNil double y, @LuaNotNil double z) {
        VOICE_ADDON.voiceClient.getDistanceVisualizer().render(radius, color, new Pos3d(x, y, z));
    }

    @Override
    public String toString() {
        return "PlasmoVoiceAPI";
    }

}
