package es.angelhuesovecina.mivideojuego;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    int posShuriken1X = 1030;
    int posShuriken2X = 1060;
    int posShuriken3X = 1090;
    int posShurikenY = 300;
    int movShurikenX = -3;
    int random1;
    int random2;
    int random3;
    Random random = new Random();
    
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
        
        //Creacion shuriken 1
            //Punta
        Polygon polygonPunta1 = new Polygon (new double[]{
            35.0, 10.0,
            10.0, 20.0,
            10.0, 0.0});
        polygonPunta1.setLayoutX(-22);
        polygonPunta1.setRotate(270);
            //Cuerpo
        Rectangle rectangleCuerpo1 = new Rectangle (-4, 22, 8, 10);
            //Cola
        Circle circleCola1 = new Circle ();
        circleCola1.setRadius(6);
        circleCola1.setCenterY(36);
        Circle circleResta1 = new Circle();
        circleResta1.setRadius(4);
        circleResta1.setCenterY(36);
        
        //Creacion shuriken 2
            //Punta
        Polygon polygonPunta2 = new Polygon (new double[]{
            35.0, 10.0,
            10.0, 20.0,
            10.0, 0.0});
        polygonPunta2.setLayoutX(-22);
        polygonPunta2.setRotate(270);
            //Cuerpo
        Rectangle rectangleCuerpo2 = new Rectangle (-4, 22, 8, 10);
            //Cola
        Circle circleCola2 = new Circle ();
        circleCola2.setRadius(6);
        circleCola2.setCenterY(36);
        Circle circleResta2 = new Circle();
        circleResta2.setRadius(4);
        circleResta2.setCenterY(36);
        
        //Creacion shuriken 3
            //Punta
        Polygon polygonPunta3 = new Polygon (new double[]{
            35.0, 10.0,
            10.0, 20.0,
            10.0, 0.0});
        polygonPunta3.setLayoutX(-22);
        polygonPunta3.setRotate(270);
            //Cuerpo
        Rectangle rectangleCuerpo3 = new Rectangle (-4, 22, 8, 10);
            //Cola
        Circle circleCola3 = new Circle ();
        circleCola3.setRadius(6);
        circleCola3.setCenterY(36);
        Circle circleResta3 = new Circle();
        circleResta3.setRadius(4);
        circleResta3.setCenterY(36);
        
        //Color shuriken1
        polygonPunta1.setFill(Color.GREY);
        rectangleCuerpo1.setFill(Color.BROWN);
        circleCola1.setFill(Color.GREY);
        circleResta1.setFill(Color.BLACK);
        
        //Color shuriken2
        polygonPunta2.setFill(Color.GREY);
        rectangleCuerpo2.setFill(Color.BROWN);
        circleCola2.setFill(Color.GREY);
        circleResta2.setFill(Color.BLACK);
        
        //Color shuriken3
        polygonPunta3.setFill(Color.GREY);
        rectangleCuerpo3.setFill(Color.BROWN);
        circleCola3.setFill(Color.GREY);
        circleResta3.setFill(Color.BLACK);
        
        //Agrupacion de elementos
            //Shuriken 1
        Group groupShuriken1 = new Group();
        groupShuriken1.getChildren().add(polygonPunta1);
        groupShuriken1.getChildren().add(rectangleCuerpo1);
        groupShuriken1.getChildren().add(circleCola1);
        groupShuriken1.getChildren().add(circleResta1);
            //Shuriken 2
        Group groupShuriken2 = new Group();
        groupShuriken2.getChildren().add(polygonPunta2);
        groupShuriken2.getChildren().add(rectangleCuerpo2);
        groupShuriken2.getChildren().add(circleCola2);
        groupShuriken2.getChildren().add(circleResta2);
            //Shuriken 3
        Group groupShuriken3 = new Group();
        groupShuriken3.getChildren().add(polygonPunta3);
        groupShuriken3.getChildren().add(rectangleCuerpo3);
        groupShuriken3.getChildren().add(circleCola3);
        groupShuriken3.getChildren().add(circleResta3);
        
        //Posicionamiento Shuriken
        groupShuriken1.setLayoutX(posShuriken1X);
        groupShuriken1.setLayoutY(posShurikenY);
        groupShuriken1.setRotate(270);
        //Posicionamiento Shuriken2
        groupShuriken2.setLayoutX(posShuriken2X);
        groupShuriken2.setLayoutY(posShurikenY);
        groupShuriken2.setRotate(270);
        //Posicionamiento Shuriken3
        groupShuriken3.setLayoutX(posShuriken3X);
        groupShuriken3.setLayoutY(posShurikenY);
        groupShuriken3.setRotate(270);
        
        //Añadir imagen
        root.getChildren().add(fondo);
        root.getChildren().add(fondo2);
        root.getChildren().add(ninja);
        root.getChildren().add(groupShuriken1);
        root.getChildren().add(groupShuriken2);
        root.getChildren().add(groupShuriken3);
        
        
        //Controles
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
                posShuriken1X += movShurikenX;
                groupShuriken1.setLayoutX(posShuriken1X);
                
                posShuriken2X += movShurikenX;
                groupShuriken2.setLayoutX(posShuriken2X);
                
                posShuriken3X += movShurikenX;
                groupShuriken3.setLayoutX(posShuriken3X);
                
                if (posNinjaX >= 800){
                    posNinjaX = 800;
                }
                
                if (posNinjaX <= 50){
                    posNinjaX = 50;
                }
                
                if (posNinjaY <= 160 ){
                    posNinjaY = 160;
                    movNinjaY = +2;
                }else if (posNinjaY == 240){
                    movNinjaY = 0;
                }
                
                //Bucle Shuriken 1
                if (posShuriken1X <= 0){
                    random1=(random.nextInt(20)+50);
                    posShuriken1X = posShuriken1X+random1;
                }
                
                //Bucle Shuriken 2
                if (posShuriken2X <= 0){
                    random2=(random.nextInt(151) + 200);
                    posShuriken2X = posShuriken1X + random2;
                    System.out.println(random1);
                }
                
                if (posShuriken3X <= 0){
                    posShuriken3X = 1090;
                }
                
            }));
        
        animacionFondo.setCycleCount(Timeline.INDEFINITE);
        animacionFondo.play();     

    }

    public static void main(String[] args) {
        launch();
    }

}