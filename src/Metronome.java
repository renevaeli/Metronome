import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene on 06/01/2017.
 */
public class Metronome {

    static boolean clickOn = false;
    static int beat;
    static int tempo = 120;
    static int clickPeriod = 60000/getTempo();
    Timer clickTimer = new Timer();

    AudioClip clickSnd = loadSound(); //TODO: more sounds, user can choose one. Will be loaded according to input.
    //TODO: add first beat accent sound and choice button


    public static int getTempo() {
        return tempo;
    }

    public static void setTempo(int _tempo) {
        if (_tempo > 230) {
            tempo = 230;
        } else if (_tempo < 10) {
            tempo = 10;
        } else {
            tempo = _tempo;
        }

    }

    public void startClick(){
            clickTimer.schedule(new Click(), clickPeriod);
            beat = 0;
    }

    public class Click extends TimerTask {

        @Override
        public void run() {
            if (clickOn) {
                clickSnd.play();

                clickPeriod = 60000/getTempo();
                clickTimer.schedule(new Click(), clickPeriod);

                countBeat();

                UI.blink(beat);

            } else {
                return;
            }
        }
    }

    public AudioClip loadSound() {
        AudioClip clickSnd = new AudioClip(Paths.get("sounds\\metronome1.wav").toUri().toString());
        return clickSnd;
    }


    public static void countBeat() {
        beat++;
        if (beat > 4) {
            beat = 1;
        }
    }
    public static void incrementTempo(int i) {
         setTempo(getTempo() + i);
    }

    public static String tempoString() {
        return Integer.toString(getTempo());
    }
}
