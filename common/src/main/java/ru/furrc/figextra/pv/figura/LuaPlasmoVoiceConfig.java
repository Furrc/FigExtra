package ru.furrc.figextra.pv.figura;

import org.figuramc.figura.lua.LuaNotNil;
import org.figuramc.figura.lua.LuaWhitelist;

import static ru.furrc.figextra.FigExtraPlugin.VOICE_ADDON;

@LuaWhitelist
public class LuaPlasmoVoiceConfig {

    @LuaWhitelist
    public boolean isDebug() {
        return VOICE_ADDON.voiceClient.getConfig().getDebug().value();
    }

    @LuaWhitelist
    public void setDebug(@LuaNotNil boolean val) {
        VOICE_ADDON.voiceClient.getConfig().getDebug().set(val);
    }

    @LuaWhitelist
    public LuaPlasmoVoiceConfigVoice getVoice() {
        return new LuaPlasmoVoiceConfigVoice();
    }

    @LuaWhitelist
    public static class LuaPlasmoVoiceConfigVoice {

        @LuaWhitelist
        public boolean isDisabled() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getDisabled().value();
        }

        @LuaWhitelist
        public void setDisabled(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getDisabled().set(val);
        }

        @LuaWhitelist
        public boolean isMicrophoneDisabled() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getMicrophoneDisabled().value();
        }

        @LuaWhitelist
        public void setMicrophoneDisabled(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getMicrophoneDisabled().set(val);
        }

        @LuaWhitelist
        public Double getActivationThreshold() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getActivationThreshold().value();
        }

        @LuaWhitelist
        public void setActivationThreshold(@LuaNotNil Double val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getActivationThreshold().set(val);
        }

        @LuaWhitelist
        public String getInputDevice() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getInputDevice().value();
        }

        @LuaWhitelist
        public void setInputDevice(@LuaNotNil String val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getInputDevice().set(val);
        }

        @LuaWhitelist
        public String getOutputDevice() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getOutputDevice().value();
        }

        @LuaWhitelist
        public void setOutputDevice(@LuaNotNil String val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getOutputDevice().set(val);
        }

        @LuaWhitelist
        public boolean isUseJavaxInput() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getUseJavaxInput().value();
        }

        @LuaWhitelist
        public void setUseJavaxInput(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getUseJavaxInput().set(val);
        }

        @LuaWhitelist
        public Double getMicrophoneVolume() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getMicrophoneVolume().value();
        }

        @LuaWhitelist
        public void setMicrophoneVolume(@LuaNotNil Double val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getMicrophoneVolume().set(val);
        }

        @LuaWhitelist
        public boolean isNoiseSuppression() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getNoiseSuppression().value();
        }

        @LuaWhitelist
        public void setNoiseSuppression(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getNoiseSuppression().set(val);
        }

        @LuaWhitelist
        public Double getVolume() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getVolume().value();
        }

        @LuaWhitelist
        public void setVolume(@LuaNotNil Double val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getVolume().set(val);
        }

        @LuaWhitelist
        public boolean isSoundOcclusion() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getSoundOcclusion().value();
        }

        @LuaWhitelist
        public void setSoundOcclusion(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getSoundOcclusion().set(val);
        }

        @LuaWhitelist
        public boolean isDirectionalSources() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getDirectionalSources().value();
        }

        @LuaWhitelist
        public void setDirectionalSources(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getDirectionalSources().set(val);
        }

        @LuaWhitelist
        public boolean isHrtf() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getHrtf().value();
        }

        @LuaWhitelist
        public void setHrtf(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getHrtf().set(val);
        }

        @LuaWhitelist
        public boolean isStereoCapture() {
            return VOICE_ADDON.voiceClient.getConfig().getVoice().getStereoCapture().value();
        }

        @LuaWhitelist
        public void setStereoCapture(@LuaNotNil boolean val) {
            VOICE_ADDON.voiceClient.getConfig().getVoice().getStereoCapture().set(val);
        }

    }
}
