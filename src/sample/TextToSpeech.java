package sample;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TextToSpeech {

    public TextToSpeech() {
    }

    public void speech(String s) {

        try {
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            synthesizer.allocate();

            synthesizer.resume();

            synthesizer.speakPlainText(
                    s, null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
