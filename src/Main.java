import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
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
    String txtBtnStart = "Start";
    int scnWidth = 360;
    int scnHeight = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Põhi on gridMain, selles 00 on GridBlink, 01 on gridTemp, 02 on tempo slider ja 03 on start/stop nupp
        GridPane gridMain = new GridPane();
        gridMain.setPadding(new Insets(10,10,10,10));
        //gridMain.setGridLinesVisible(true);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        gridMain.getColumnConstraints().add(col1);

        RowConstraints row1_3 = new RowConstraints();
        row1_3.setPercentHeight(100/3);
        RowConstraints row1_6 = new RowConstraints();
        row1_6.setPercentHeight(100/6);
        gridMain.getRowConstraints().addAll(row1_3,row1_6,row1_6,row1_3);

        //gridMainis 00: this 00 on tempo tekst, 01 on "-" nupp ja 02 on "+" nupp
        GridPane gridBlink = new GridPane();
        //gridBlink.setGridLinesVisible(true);
        ColumnConstraints col1_4 = new ColumnConstraints();
        col1_4.setPercentWidth(100/4);
        gridBlink.getColumnConstraints().addAll(col1_4,col1_4,col1_4,col1_4);
        gridBlink.setAlignment(Pos.CENTER);

        gridMain.setConstraints(gridBlink,0,0);

        //gridMainis 01: this 00 on tempo tekst, 01 on "-" nupp ja 02 on "+" nupp
        GridPane gridTemp = new GridPane();
        gridTemp.setAlignment(Pos.CENTER);
        //gridTemp.setGridLinesVisible(true);
        ColumnConstraints col1_2 = new ColumnConstraints();
        col1_2.setPercentWidth(100/2);
        gridTemp.getColumnConstraints().addAll(col1_2,col1_4,col1_4);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        gridTemp.getRowConstraints().add(row1);

        gridMain.setConstraints(gridTemp,0,1);


        //Lisan kõigepealt gridBlinkile tema lapsed, gridTempile tema lapsed. Siis lisan gridMainile gridBlink, GridTemp, ja otsesed objektid

        //Teen nüüd nupud ja muud jupid
        //Alustan blinkijatest
        Circle blink0 = new Circle(25);
        gridBlink.setConstraints(blink0,0,0);
        gridBlink.setHalignment(blink0, HPos.CENTER);
        Circle blink1 = new Circle(25);
        gridBlink.setConstraints(blink1,1,0);
        gridBlink.setHalignment(blink1, HPos.CENTER);
        Circle blink2 = new Circle(25);
        gridBlink.setConstraints(blink2,2,0);
        gridBlink.setHalignment(blink2, HPos.CENTER);
        Circle blink3 = new Circle(25);
        gridBlink.setConstraints(blink3,3,0);
        gridBlink.setHalignment(blink3, HPos.CENTER);
        //Lisan asjad alamgridi gridBlink
        gridBlink.getChildren().addAll(blink0,blink1,blink2,blink3);


        //Teen tempo teksti ja nupud
        Text txtTempo = new Text(Integer.toString(tempo));
        txtTempo.setFont(new Font("Verdana", 30));
        gridTemp.setConstraints(txtTempo,0,0);
        gridTemp.setHalignment(txtTempo, HPos.CENTER);
        gridTemp.setValignment(txtTempo, VPos.CENTER);

        Button btnTempMin = new Button("-");
        gridTemp.setHalignment(btnTempMin, HPos.CENTER);
        gridTemp.setValignment(btnTempMin, VPos.CENTER);
        gridTemp.setConstraints(btnTempMin,1,0);

        Button btnTempPlus = new Button("+");
        gridTemp.setHalignment(btnTempPlus, HPos.CENTER);
        gridTemp.setValignment(btnTempPlus, VPos.CENTER);
        gridTemp.setConstraints(btnTempPlus,2,0);
        //Lisan asjad alamgridi gridTemp
        gridTemp.getChildren().addAll(txtTempo,btnTempMin,btnTempPlus);


        //Teen tempo slideri
        Slider sldrTempo = new Slider(10,230,tempo);
        gridMain.setConstraints(sldrTempo,0,2);

        ToggleButton btnToggle = new ToggleButton(txtBtnStart);
        gridTemp.setHalignment(btnToggle, HPos.CENTER);
        gridTemp.setValignment(btnToggle, VPos.CENTER);
        gridMain.setConstraints(btnToggle,0,3);

        btnToggle.setMaxSize(scnWidth*0.8, scnHeight/3*0.8);

        gridMain.getChildren().addAll(gridBlink,gridTemp,sldrTempo,btnToggle);

        Scene scene = new Scene(gridMain, scnWidth, scnHeight);
        primaryStage.setScene(scene);
        primaryStage.show();



//        Circle btnPlayStop = new Circle(50);
//
//        btnPlayStop.setCenterX(180);
//        btnPlayStop.setCenterY(300);
//        pane.getChildren().add(btnPlayStop);
//
//        Text txtPlay = new Text(180, 300, "Play");
//        txtPlay.setFont(new Font("Verdana", 20));
//        txtPlay.setFill(Color.WHITE);
//        pane.getChildren().add(txtPlay);
//
//        Text txtStop = new Text (180, 300, "Stop");
//        txtStop.setFont(new Font("Verdana", 20));
//        txtStop.setFill(Color.WHITE);
//
//        Text txtTempo = new Text(100, 150, "Tempo: ");
//        txtTempo.setFont(new Font("Verdana", 20));
//        //txtTempo.setFill(Color.BLACK);
//        pane.getChildren().add(txtTempo);
//
//        Text txtTempoValue = new Text(180, 150, Integer.toString(tempo));
//        txtTempoValue.setFont(new Font("Verdana", 20));
//        //txtTempoValue.setFill(Color.BLACK);
//        pane.getChildren().add(txtTempoValue);
//
//
//        gridMain.getChildren
//
//
//
//        Circle blink = new Circle(25);
//
//        blink.setCenterX(180);
//        blink.setCenterY(100);
//        blink.setOpacity(0);
//
//        pane.getChildren().add(blink);


        //make a method for initializing texts - setText(color, size, font).

        AudioClip clickSnd = new AudioClip(Paths.get("sounds\\metronome1.wav").toUri().toString());

        btnToggle.setOnMouseClicked(event -> {
            //System.out.println("KLIKK");
            clickOn = !clickOn;

            if  (btnToggle.isArmed()) {
                txtBtnStart = "Stop";
            } else {
                txtBtnStart = "Start";
            }

            if (clickOn) {
                Timer clickTimer = new Timer();
                clickTimer.schedule(new TimerTask() {
                    public void run() {
                        if (clickOn) {
                            clickSnd.play();
                        } else {
                            clickTimer.cancel();
                        }
                    }
                }, 0, 60000 / tempo);
            }
        });
    }


}
