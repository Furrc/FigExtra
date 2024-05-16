package ru.furrc.figextra.pv;

import com.mojang.datafixers.util.Pair;
import org.figuramc.figura.entries.FiguraEvent;
import org.figuramc.figura.entries.annotations.FiguraEventPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.LuaEvent;
import ru.furrc.figextra.FigExtraPlugin;

import java.util.Collection;
import java.util.Collections;


@FiguraEventPlugin
public class PlasmoVoiceEvent implements FiguraEvent {

    @LuaWhitelist
    public static final LuaEvent MICROPHONE = new LuaEvent();

    @Override
    public String getID() {
        return FigExtraPlugin.PLUGIN_ID;
    }

    @Override
    public Collection<Pair<String, LuaEvent>> getEvents() {
        return Collections.singleton(new Pair<>("MICROPHONE", MICROPHONE));
    }

}
