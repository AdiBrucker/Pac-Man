package Controller;

import View.StartGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class which the game starts from
 */
public class MainClass extends Application {

    /**
     * stat games method. A main class method which is responsible of creating the first instance of the game.
     * Creates an instance of the opening screen.
     *
     * @param stage
     */
    public void start(Stage stage) {

        PacmanController.CreateInstance();
        new StartGame(stage);
    }
}
