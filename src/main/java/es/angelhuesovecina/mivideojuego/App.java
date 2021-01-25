package es.angelhuesovecina.mivideojuego;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    int fondoPantalla;
    int fondoPantalla2 = 999;
    int movFondo;
    int movNinjaX;
    int posNinjaX;
    int posNinjaY = 240;
    int movNinjaY;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene (root, 1000, 400);
        stage.setResizable(false);
        stage.setTitle("Mi Juego");
        stage.setScene(scene);
        stage.show();
      
        //Carga de imagenes
        //Imagenes de fondo
        Image img = new Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView fondo = new ImageView(img);
        Image img3 = new Image(getClass().getResourceAsStream("/images/fondo2.png"));
        ImageView fondo2 = new ImageView(img3);
        
        //Imagen ninja estatico
        Image img2 = new Image (getClass().getResourceAsStream("/images/idle_0.png"));
        ImageView ninja = new ImageView (img2);
        ninja.setY(posNinjaY);
        
        //Creacion shuriken
            //Punta
        Polygon polygonPunta = new Polygon (new double[]{
            35.0, 10.0,
            10.0, 20.0,
            10.0, 0.0});
        polygonPunta.setLayoutX(-22);
        polygonPunta.setRotate(270);
            //Cuerpo
        Rectangle rectangleCuerpo = new Rectangle (0, 22, 6, 10);
        rectangleCuerpo.setLayoutX(0);
            //Cola
        
        
        
        //Color shuriken
        polygonPunta.setFill(Color.GREY);
        rectangleCuerpo.setFill(Color.GREY);
        
        //Añadir imagen
        root.getChildren().add(fondo);
        root.getChildren().add(fondo2);
        root.getChildren().add(ninja);
        root.getChildren().add(polygonPunta);
        root.getChildren().add(rectangleCuerpo);
        
        
        
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case RIGHT:
                    movNinjaX = +2;
                    break;
                case LEFT:
                    movNinjaX = -2;
                    break;
                case SPACE:
                    if (posNinjaY == 240){
                    movNinjaY = -5;
                    }
                    break;
                     
            }
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            movFondo = 0;
            movNinjaX = 0;
            
        });
        
        Timeline animacionFondo = new Timeline(
            new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                //Accion de movimiento Ninja
                posNinjaX += movNinjaX;
                ninja.setX(posNinjaX);
                posNinjaY += movNinjaY;
                ninja.setY(posNinjaY);
                
                if (posNinjaX >= 800){
                    posNinjaX = 800;
                }
                
                if (posNinjaX <= 50){
                    posNinjaX = 50;
                }
                
                if (posNinjaY <= 160 ){
                    posNinjaY = 160;
                    movNinjaY = +1;
                }else if (posNinjaY == 240){
                    movNinjaY = 0;
                }
                
            }));
        
        animacionFondo.setCycleCount(Timeline.INDEFINITE);
        animacionFondo.play();
        

    }

    public static void main(String[] args) {
        launch();
    }

}