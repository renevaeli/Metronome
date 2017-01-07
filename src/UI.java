import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Rene on 06/01/2017.
 */
public class UI {

    static ToggleButton btnToggle;
    static Slider sldrTempo;
    static Button btnTempPlus;
    static Button btnTempMin;
    static Text txtTempo;
    static Circle blink0;
    static Circle blink1;
    static Circle blink2;
    static Circle blink3;
    static Color blinkOnColor;
    static Color blinkOffColor;
    static DropShadow glowingEdge;
    Scene scene;

    static String txtBtnStart = "Start";

    double scnWidth = 360;
    double scnHeight = 600;

    public UI(Stage primaryStage, Metronome metro) {

        String txtBtnStart = "Start";

        //Põhi on gridMain, selles 00 on GridBlink, 01 on gridTemp, 02 on tempo slider ja 03 on start/stop nupp
        GridPane gridMain = new GridPane();
        gridMain.setPadding(new Insets(10,10,10,10));
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
        gridBlink.setPadding(new Insets(0,20,0,20));
        ColumnConstraints col1_4 = new ColumnConstraints();
        col1_4.setPercentWidth(100/4);
        gridBlink.getColumnConstraints().addAll(col1_4,col1_4,col1_4,col1_4);
        gridBlink.setAlignment(Pos.CENTER);

        //gridMainis 01: this 00 on tempo tekst, 01 on "-" nupp ja 02 on "+" nupp
        GridPane gridTemp = new GridPane();
        gridTemp.setPadding(new Insets(0,25,0,25));
        gridTemp.setAlignment(Pos.CENTER);
        ColumnConstraints col1_2 = new ColumnConstraints();
        col1_2.setPercentWidth(100/2);
        gridTemp.getColumnConstraints().addAll(col1_2,col1_4,col1_4);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        gridTemp.getRowConstraints().add(row1);

        //Lisan kõigepealt gridBlinkile tema lapsed, gridTempile tema lapsed. Siis lisan gridMainile gridBlink, GridTemp, ja otsesed objektid

        //CREATING USER CONTROLS

        //BLINKERS
        blinkOnColor = Color.web("278df9");
        blinkOffColor = Color.web("2176d1");

        glowingEdge = new DropShadow();
        glowingEdge.setColor(blinkOnColor);
        glowingEdge.setRadius(40);

        //TODO: How to make them in variable quantity? For cycle with time signature as param? Names?
        blink0 = new Circle(25);
        blink0 = lightOff(blink0);
        gridBlink.setHalignment(blink0, HPos.CENTER);

        blink1 = new Circle(25);
        blink1 = lightOff(blink1);
        gridBlink.setHalignment(blink1, HPos.CENTER);

        blink2 = new Circle(25);
        blink2 = lightOff(blink2);
        gridBlink.setHalignment(blink2, HPos.CENTER);

        blink3 = new Circle(25);
        blink3 = lightOff(blink3);
        gridBlink.setHalignment(blink3, HPos.CENTER);

        //ADD BLINKERS TO GRIDBLINK
        gridBlink.setConstraints(blink0,0,0);
        gridBlink.setConstraints(blink1,1,0);
        gridBlink.setConstraints(blink2,2,0);
        gridBlink.setConstraints(blink3,3,0);
        gridBlink.getChildren().addAll(blink0,blink1,blink2,blink3);

        //GRIDTEMP
        ////TEMPO TEXT
        txtTempo = new Text(Integer.toString(metro.getTempo()));
        txtTempo.setFont(new Font("Verdana", 30));
        gridTemp.setHalignment(txtTempo, HPos.CENTER);
        gridTemp.setValignment(txtTempo, VPos.CENTER);

        ////TEMPO INCREMENT BUTTONS
        btnTempMin = new Button("-");
        btnTempMin.setMaxSize(scnWidth*0.15, scnWidth*0.15);
        btnTempMin.setFont(new Font("Verdana", 20));
        gridTemp.setHalignment(btnTempMin, HPos.CENTER);
        gridTemp.setValignment(btnTempMin, VPos.CENTER);

        btnTempPlus = new Button("+");
        btnTempPlus.setMaxSize(scnWidth*0.15, scnWidth*0.15);
        btnTempPlus.setFont(new Font("Verdana", 20));
        gridTemp.setHalignment(btnTempPlus, HPos.CENTER);
        gridTemp.setValignment(btnTempPlus, VPos.CENTER);

        ////ADD TEXT AND BUTTONS TO GRIDTEMP
        gridTemp.setConstraints(txtTempo,0,0);
        gridTemp.setConstraints(btnTempMin,1,0);
        gridTemp.setConstraints(btnTempPlus,2,0);
        gridTemp.getChildren().addAll(txtTempo,btnTempMin,btnTempPlus);

        //TEMPO SLIDER
        sldrTempo = new Slider(10,230, metro.getTempo()); //Kuidas muuta väärtust dünaamiliselt
        sldrTempo.setMaxWidth(scnWidth*0.8);
        gridMain.setHalignment(sldrTempo, HPos.CENTER);

        //TOGGLE METRONOME BUTTON
        btnToggle = new ToggleButton(txtBtnStart);
        btnToggle.setFont(new Font("Verdana", 30));
        gridMain.setHalignment(btnToggle, HPos.CENTER);
        gridMain.setValignment(btnToggle, VPos.CENTER);
        btnToggle.setMaxSize(scnWidth*0.8, scnHeight/3*0.8);

        gridMain.setConstraints(gridBlink,0,0);
        gridMain.setConstraints(gridTemp,0,1);
        gridMain.setConstraints(sldrTempo,0,2);
        gridMain.setConstraints(btnToggle,0,3);
        gridMain.getChildren().addAll(gridBlink,gridTemp,sldrTempo,btnToggle);

        //SET SCENE
        Scene scene = new Scene(gridMain, scnWidth, scnHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        //gridTemp.setGridLinesVisible(true);
        //gridBlink.setGridLinesVisible(true);
        //gridMain.setGridLinesVisible(true);

        userInputListeners(metro);
    }

    //BLINKING CONTROLS TODO: Try to put into separate class "Blinker"
    public static Circle lightOff (Circle c) {
        c.setFill(blinkOffColor);
        c.setStroke(blinkOffColor);
        c.setEffect(null);
        return c;
    }

    public static Circle lightOn (Circle c) {
        c.setFill(blinkOnColor);
        c.setStroke(blinkOnColor);
        c.setEffect(glowingEdge);
        return c;
    }

    public static void blink(int beat) {
        switch (beat) {
            case 1:
                resetBlinkers();
                blink0 = lightOn(blink0);
                break;
            case 2:
                resetBlinkers();
                blink1 = lightOn(blink1);
                break;
            case 3:
                resetBlinkers();
                blink2 = lightOn(blink2);
                break;
            case 4:
                resetBlinkers();
                blink3 = lightOn(blink3);
                break;
        }
    }
    public static void resetBlinkers() {
        blink0 = lightOff(blink0);
        blink1 = lightOff(blink1);
        blink2 = lightOff(blink2);
        blink3 = lightOff(blink3);
    }

    public void userInputListeners(Metronome metro) {


        btnToggle.setOnMouseClicked(event -> {

            Metronome.clickOn = btnToggle.isSelected();

            changeTogglText();

            if (Metronome.clickOn) {
                metro.startClick();
            }
        });

        sldrTempo.valueProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("Uus väärtus on: " + uusVaartus.intValue());
            Metronome.setTempo(newValue.intValue());
            changeTxtTempo();
        });

        btnTempMin.setOnMouseClicked(event -> {
            Metronome.incrementTempo(-1);
            changeTxtTempo();
        });

        btnTempPlus.setOnMouseClicked(event -> {
            Metronome.incrementTempo(1);
            changeTxtTempo();
        });

        txtTempo.setOnMouseClicked(event -> {
            askUserForTempo();
        });
    }

    private void changeTogglText() {
        if  (Metronome.clickOn) {
            txtBtnStart = "Stop";
            btnToggle.setText(txtBtnStart);
        } else {
            txtBtnStart = "Start";
            btnToggle.setText(txtBtnStart);
            resetBlinkers();
        }
    }

    private static void askUserForTempo() {
        TextInputDialog dialogTempo = new TextInputDialog(Metronome.tempoString());
        dialogTempo.setHeaderText("Input New Tempo");
        dialogTempo.setTitle("Input New Tempo");
        dialogTempo.setContentText("Tempo");

        Optional<String> userInput = dialogTempo.showAndWait();
        if (userInput.isPresent()){
            String t = userInput.get();

            try {
                int i = Integer.parseInt(t);
                Metronome.setTempo(i);
            } catch(NumberFormatException e) {
                askUserForTempo();
                return;
            }

            changeTxtTempo();
            changeSliderValue();
        }
    }

    public static void changeTxtTempo() {
        txtTempo.setText(Integer.toString(Metronome.getTempo()));
    }

    public static void changeSliderValue() {
        sldrTempo.setValue(Metronome.getTempo());
    }
}
