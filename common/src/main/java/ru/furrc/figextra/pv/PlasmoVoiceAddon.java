package ru.furrc.figextra.pv;

import com.google.inject.Inject;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.jetbrains.annotations.NotNull;
import ru.furrc.figextra.FigExtraPlugin;
import su.plo.voice.api.addon.AddonInitializer;
import su.plo.voice.api.addon.AddonLoaderScope;
import su.plo.voice.api.addon.annotation.Addon;
import su.plo.voice.api.client.PlasmoVoiceClient;
import su.plo.voice.api.client.event.connection.UdpClientPacketSendEvent;
import su.plo.voice.api.event.EventSubscribe;
import su.plo.voice.proto.packets.udp.serverbound.PlayerAudioPacket;

@Addon(
        id = "pv-addon-figextra",
        name = "FigExtra PlasmoVoice",
        scope = AddonLoaderScope.CLIENT,
        version = "1.0.0",
        authors = {"Furrc"}
)
public class PlasmoVoiceAddon implements AddonInitializer {

    @Inject
    public PlasmoVoiceClient voiceClient;

    @Override
    public void onAddonInitialize() {
        FigExtraPlugin.LOGGER.info(voiceClient.getVersion());
    }

    @EventSubscribe
    public void onPlayerSpeak(@NotNull UdpClientPacketSendEvent event) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if (avatar != null) {
            if (event.getPacket() instanceof PlayerAudioPacket audioPacket) {
                avatar.run("FIGEXTRA.MICROPHONE", avatar.tick,
                        audioPacket.getActivationId().toString(),
                        audioPacket.getDistance(),
                        audioPacket.isStereo());
            }
        }
    }

}
