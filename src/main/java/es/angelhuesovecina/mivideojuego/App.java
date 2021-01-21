package es.angelhuesovecina.mivideojuego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
      StackPane root = new StackPane();
      var scene = new Scene (root, 800, 600);
      scene.setFill(Color.AQUAMARINE);
      stage.setTitle("Mi Juego");
      stage.setScene(scene);
      stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}