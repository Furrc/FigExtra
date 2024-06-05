package ru.furrc.figextra.pv;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.minecraft.client.Minecraft;
import org.figuramc.figura.lua.LuaNotNil;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaMethodOverload;
import org.figuramc.figura.lua.docs.LuaTypeDoc;
import org.figuramc.figura.math.vector.FiguraVec3;
import org.figuramc.figura.utils.LuaUtils;
import su.plo.voice.proto.data.pos.Pos3d;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static ru.furrc.figextra.pv.PlasmoVoiceAddon.INSTANCE;

@LuaWhitelist
@LuaTypeDoc(
        name = "PlasmoVoice",
        value = "plasmo_voice"
)
public class PlasmoVoiceAPI {

    private static final LoadingCache<UUID, Float> voiceLevelsCache =
            CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(CacheLoader.from(() -> 0f));

    public static void handle(short[] data) {
        handle(Minecraft.getInstance().getUser().getProfileId(), data);
    }

    public static void handle(UUID uuid, short[] data) {
        voiceLevelsCache.put(uuid, data == null ? 0f : calcVoiceLevel(data));
    }

    private static float calcVoiceLevel(short[] data) {
        return (float) dbToPerc(getHighestAudioLevel(data));
    }

    private static double calculateAudioLevel(short[] samples, int offset, int length) {
        double rms = 0D;

        for (int i = offset; i < length; i++) {
            double sample = (double) samples[i] / (double) Short.MAX_VALUE;
            rms += sample * sample;
        }

        int sampleCount = length / 2;

        rms = (sampleCount == 0) ? 0 : Math.sqrt(rms / sampleCount);

        return rms > 0D ? Math.min(Math.max(20D * Math.log10(rms), -127D), 0D) : -127D;
    }

    private static double getHighestAudioLevel(short[] samples) {
        double highest = -127D;
        for (int i = 0; i < samples.length; i += 100) {
            double level = calculateAudioLevel(samples, i, Math.min(i + 100, samples.length));
            if (level > highest) {
                highest = level;
            }
        }
        return highest;
    }

    private static double dbToPerc(double db) {
        return (db + 127D) / 127D;
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = @LuaMethodOverload(
                    argumentTypes = String.class,
                    argumentNames = "uuid"
            ),
            value = "plasmo_voice.get_voice_level"
    )
    public static float getVoiceLevel(String uuid) {
        try {
            return voiceLevelsCache.get(UUID.fromString(uuid));
        } catch (Exception e) {
            return 0;
        }
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = @LuaMethodOverload(
                    argumentTypes = String.class,
                    argumentNames = "uuid"
            ),
            value = "plasmo_voice.is_muted"
    )
    public static boolean isMuted(String uuid) {
        return INSTANCE.isMuted(UUID.fromString(uuid));
    }

    @LuaWhitelist
    @LuaMethodDoc("plasmo_voice.get_version")
    public String getVersion() {
        return INSTANCE.voiceClient.getVersion();
    }

    @LuaWhitelist
    @LuaMethodDoc(
            overloads = {
                    @LuaMethodOverload(
                            argumentTypes = {Integer.class, String.class, FiguraVec3.class},
                            argumentNames = {"radius", "hex", "pos"}
                    ),
                    @LuaMethodOverload(
                            argumentTypes = {Integer.class, String.class, Double.class, Double.class, Double.class},
                            argumentNames = {"radius", "hex", "x", "y", "z"}
                    ),
            },
            value = "plasmo_voice.render_distance_visualizer"
    )
    public void renderDistanceVisualizer(@LuaNotNil Integer radius, @LuaNotNil String hex, @LuaNotNil Object x, Double y, Double z) {
        FiguraVec3 pos = LuaUtils.parseVec3("renderDistanceVisualizer", x, y, z);
        INSTANCE.voiceClient.getDistanceVisualizer()
                .render(radius, Integer.parseInt(hex, 16), new Pos3d(pos.x, pos.y, pos.z));
    }

    @Override
    public String toString() {
        return "PlasmoVoiceAPI";
    }

}
