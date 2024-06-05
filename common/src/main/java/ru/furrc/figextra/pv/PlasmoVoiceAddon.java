package ru.furrc.figextra.pv;

import com.google.inject.Inject;
import net.minecraft.client.Minecraft;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.jetbrains.annotations.NotNull;
import su.plo.voice.api.addon.AddonInitializer;
import su.plo.voice.api.addon.AddonLoaderScope;
import su.plo.voice.api.addon.annotation.Addon;
import su.plo.voice.api.client.PlasmoVoiceClient;
import su.plo.voice.api.client.connection.ServerConnection;
import su.plo.voice.api.client.event.audio.capture.AudioCaptureProcessedEvent;
import su.plo.voice.api.client.event.connection.UdpClientPacketSendEvent;
import su.plo.voice.api.event.EventSubscribe;
import su.plo.voice.proto.data.player.VoicePlayerInfo;
import su.plo.voice.proto.packets.udp.serverbound.PlayerAudioPacket;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Addon(
        id = "pv-addon-figextra",
        name = "FigExtra PlasmoVoice",
        scope = AddonLoaderScope.CLIENT,
        version = "1.0.0",
        authors = {"Furrc"}
)
public class PlasmoVoiceAddon implements AddonInitializer {

    public static final PlasmoVoiceAddon INSTANCE = new PlasmoVoiceAddon();

    @Inject
    public PlasmoVoiceClient voiceClient;

    @Override
    public void onAddonInitialize() {

    }

    @EventSubscribe
    public void onPlayerSpeak(@NotNull UdpClientPacketSendEvent event) {
        Avatar avatar = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if (avatar != null) {
            if (event.getPacket() instanceof PlayerAudioPacket audioPacket) {
                avatar.run("FIGEXTRA.PLASMOVOICE_PLAYER_SPEAK", avatar.tick,
                        audioPacket.getActivationId().toString(), audioPacket.getDistance());
            }
        }
    }

    @EventSubscribe
    public void onCaptureProcessed(AudioCaptureProcessedEvent event) {
        PlasmoVoiceAPI.handle(event.getMonoSamplesProcessed());
    }

    public boolean isMuted(UUID player) {
        Optional<ServerConnection> connection = voiceClient.getServerConnection();
        if (connection.isEmpty()) return false;

        Optional<VoicePlayerInfo> playerInfo = connection.get().getPlayerById(player);
        if (playerInfo.isEmpty()) return false;
        if (playerInfo.get().isMuted() || playerInfo.get().isMicrophoneMuted()) return true;

        if (Objects.equals(Minecraft.getInstance().getUser().getProfileId(), player)) {
            return voiceClient.getConfig().getVoice().getMicrophoneDisabled().value();
        }
        return false;
    }

}
