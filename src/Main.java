import javafx.application.Application;
import javafx.stage.Stage;
import java.lang.String;

/**
 * Created by Rene on 05/12/2016.
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Metronome metro = new Metronome();
        UI ui = new UI(primaryStage, metro);




    }




}
