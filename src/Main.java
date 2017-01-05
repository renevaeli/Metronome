import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.String;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene on 05/12/2016.
 */
public class Main extends Application{

    boolean clickOn = false;
    int tempo = 120;
    Timer timer = new Timer();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 360, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        Circle btnPlayStop = new Circle(50);

        btnPlayStop.setCenterX(180);
        btnPlayStop.setCenterY(300);
        pane.getChildren().add(btnPlayStop);

        Text txtPlay = new Text(180, 300, "Play");
        txtPlay.setFont(new Font("Verdana", 20));
        txtPlay.setFill(Color.WHITE);
        pane.getChildren().add(txtPlay);

        Text txtStop = new Text (180, 300, "Stop");
        txtStop.setFont(new Font("Verdana", 20));
        txtStop.setFill(Color.WHITE);

        Text txtTempo = new Text(100, 150, "Tempo: ");
        txtTempo.setFont(new Font("Verdana", 20));
        //txtTempo.setFill(Color.BLACK);
        pane.getChildren().add(txtTempo);

        Text txtTempoValue = new Text(180, 150, Integer.toString(tempo));
        txtTempoValue.setFont(new Font("Verdana", 20));
        //txtTempoValue.setFill(Color.BLACK);
        pane.getChildren().add(txtTempoValue);


        Circle blink = new Circle(25);

        blink.setCenterX(180);
        blink.setCenterY(100);
        blink.setOpacity(0);

        pane.getChildren().add(blink);









        //make a method for initializing texts - setText(color, size, font).

        AudioClip clickSnd = new AudioClip(Paths.get("sounds\\metronome1.wav").toUri().toString());

        btnPlayStop.setOnMouseClicked(event -> {
            //System.out.println("KLIKK");
            clickOn = !clickOn;

            if  (!clickOn) {
                pane.getChildren().remove(txtStop);
                pane.getChildren().add(txtPlay);
            } else {
                pane.getChildren().remove(txtPlay);
                pane.getChildren().add(txtStop);
            }

            if (clickOn) {
                Timer clickTimer = new Timer();
                clickTimer.schedule(new TimerTask() {
                    public void run() {
                        if (clickOn) {
                            clickSnd.play();


                            blink.setOpacity(100);


                            //System.out.println("bip");
                        } else {
                            clickTimer.cancel();
                        }

                    }

                }, 0, 60000 / tempo);
            }
        });
    }

    public void loadScene() {

    }

}
