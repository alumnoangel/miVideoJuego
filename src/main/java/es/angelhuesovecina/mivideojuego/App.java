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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    int posNinjaY = 280;
    int movNinjaY;
    int posShuriken1X = 1030;
    int posShuriken2X = 1310;
    int posShuriken3X = 1610;
    int posShurikenY = 300;
    int movShurikenX = -5;
    int contadorShuriken = 1;
    int posCoinX;
    int posCoinY;
    int random1;
    int random2;
    int random3;
    Random random = new Random();
    int vidas;
    int puntos;
    final int SCENE_TAM_X = 1000;
    final int SCENE_TAM_Y = 400;
    final int TEXT_SIZE = 30;
    boolean visibleCoin = false;
    int movCoinY;
    int distShuriken1;
    int distShuriken2;
    int distShuriken3;
    
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
        Image img4 = new Image (getClass().getResourceAsStream("/images/coinnn_1.png"));
        //ImageView coin = new ImageView(img4);
        
        //Imagen ninja
        Image img2 = new Image (getClass().getResourceAsStream("/images/ninjaBueno.png"));
        
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
        polygonPunta1.setFill(Color.RED);
        rectangleCuerpo1.setFill(Color.BROWN);
        circleCola1.setFill(Color.GREY);
        circleResta1.setFill(Color.BLACK);
        
        //Color shuriken2
        polygonPunta2.setFill(Color.GREY);
        rectangleCuerpo2.setFill(Color.BROWN);
        circleCola2.setFill(Color.GREY);
        circleResta2.setFill(Color.BLACK);
        
        //Color shuriken3
        polygonPunta3.setFill(Color.BLUE);
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
        
            //Agregar marco a Ninja
        Group groupNinja = new Group();
        ImageView ninja2 = new ImageView (img2);
        Rectangle zonaContacto = new Rectangle (55, 60);
        zonaContacto.setFill(Color.ALICEBLUE);
        groupNinja.getChildren().add(ninja2);
        groupNinja.getChildren().add(zonaContacto);
        groupNinja.setLayoutY(posNinjaY);
        zonaContacto.setVisible(false);
        
            //Agregar marco a coin
        Group groupCoin = new Group();
        ImageView coin = new ImageView (img4);
        Rectangle zonaCoin = new Rectangle (30,30);
        zonaCoin.setFill(Color.RED);
        groupCoin.getChildren().add(coin);
        groupCoin.getChildren().add(zonaCoin);
        zonaCoin.setVisible(false);
        groupCoin.setVisible(false);
        posCoinX = (int)(Math.random()*901);
        //posCoinY = (int)(Math.random()*(305-200+1)+200);
        groupCoin.setLayoutY(posCoinY);
        groupCoin.setLayoutX (posCoinX);

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
        
        //LAYOUTS PUNTUACIONES
            //Layaout principal
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setTranslateX(50);
        paneScores.setMinWidth(SCENE_TAM_X);
        paneScores.setSpacing (300); 
            //Layaout puntuacion actual
        HBox puntActual = new HBox();
        puntActual.setSpacing(10);
        paneScores.getChildren().add(puntActual);
            //Layaout vidas
        HBox puntVidas = new HBox();
        puntVidas.setSpacing(10);
        paneScores.getChildren().add(puntVidas);
            //Texto etiqueta para la puntuacion
        Text textEtiquetaPuntuacion = new Text("Puntos:");
        textEtiquetaPuntuacion.setFont(Font.font(TEXT_SIZE));
        textEtiquetaPuntuacion.setFill(Color.BLACK);
            //Texto para puntuacion
        Text textPuntuacion = new Text("0");
        textPuntuacion.setFont(Font.font(TEXT_SIZE));
        textPuntuacion.setFill(Color.BLACK);
            //Texto etiqueta vidas
        Text textEtiquetaVidas = new Text("Vidas:");
        textEtiquetaVidas.setFont(Font.font(TEXT_SIZE));
        textEtiquetaVidas.setFill(Color.BLACK);
        //Texto etiqueta para la puntuacion
        Text textVidas = new Text("2");
        textVidas.setFont(Font.font(TEXT_SIZE));
        textVidas.setFill(Color.BLACK);
            //Añadir texto a los layouts
        puntActual.getChildren().add(textEtiquetaPuntuacion);
        puntActual.getChildren().add(textPuntuacion);
        puntVidas.getChildren().add(textEtiquetaVidas);
        puntVidas.getChildren().add(textVidas);
        
        //Añadir imagen
        root.getChildren().add(fondo);
        root.getChildren().add(fondo2);
        root.getChildren().add(groupNinja);
        root.getChildren().add(groupShuriken1);
        root.getChildren().add(groupShuriken2);
        root.getChildren().add(groupShuriken3);
        root.getChildren().add(paneScores);
        root.getChildren().add(groupCoin);
        
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
                    if (posNinjaY == 280){
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
                
                if (visibleCoin == false){
                    groupCoin.setVisible(false);
                }else{
                    groupCoin.setVisible(true);
                }
                
                //Accion de movimiento Ninja
                posNinjaX += movNinjaX;
                groupNinja.setLayoutX(posNinjaX);
                posNinjaY += movNinjaY;
                groupNinja.setLayoutY(posNinjaY);
                // Incremento movimiento Shuriken
                if (puntos >= 5){
                    movShurikenX = -4;
                }
                if (puntos >= 15){
                    movShurikenX = -5;
                }
                //Movimiento Shuriken1
                posShuriken1X += movShurikenX;
                groupShuriken1.setLayoutX(posShuriken1X);
                //Movimiento Shuriken2
                posShuriken2X += movShurikenX;
                groupShuriken2.setLayoutX(posShuriken2X);
                //Movimiento Shuriken3
                posShuriken3X += movShurikenX;
                groupShuriken3.setLayoutX(posShuriken3X); 
                
                if (posNinjaX >= 900){
                    posNinjaX = 900;
                }
                
                if (posNinjaX <= 0){
                    posNinjaX = 0;
                }
                
                if (posNinjaY <= 200 ){
                    posNinjaY = 200;
                    movNinjaY = +3;
                }else if (posNinjaY >= 280){
                    posNinjaY = 280;
                    movNinjaY = 0;
                }
                
                //Bucle Shuriken 1
                if (posShuriken1X <= 0){
                    contadorShuriken ++;
                    random1=(random.nextInt(20)+50);
                    posShuriken1X= 1030 +random1;
                    if (posShuriken1X <= posShuriken3X+200){
                    posShuriken1X = posShuriken1X + random3;   
                    }
                }
                
                //Bucle Shuriken 2
                if (posShuriken2X <= 0){
                    random2=(random.nextInt(301) + 200);
                    posShuriken2X = posShuriken1X + random2;  
                }
                //Bucle Shuriken 3
                if (posShuriken3X <= 0){
                    random3=(random.nextInt(301) + 200);
                    posShuriken3X = posShuriken2X + random3;
                    if (posShuriken3X <= posShuriken1X+200){
                    posShuriken3X = posShuriken3X + random3;   
                    }   
                }
                //Colisiones
                Shape zonaColision1 = Shape.intersect(zonaContacto, polygonPunta1);
                boolean colisionVacia1 = zonaColision1.getBoundsInLocal().isEmpty();
                Shape zonaColision2 = Shape.intersect(zonaContacto, polygonPunta2);
                boolean colisionVacia2 = zonaColision2.getBoundsInLocal().isEmpty();
                Shape zonaColision3 = Shape.intersect(zonaContacto, polygonPunta3);
                boolean colisionVacia3 = zonaColision3.getBoundsInLocal().isEmpty();
                Shape zonaColision4 = Shape.intersect(zonaContacto, zonaCoin);
                boolean colisionVacia4 = zonaColision4.getBoundsInLocal().isEmpty();
                    if (colisionVacia4 == false && visibleCoin == true){
                        visibleCoin = false;
                        posCoinX = (int)(Math.random()*901);
                        posCoinY = -30;
                        //Sistema de puntos
                        puntos ++;
                        textPuntuacion.setText(String.valueOf(puntos));
                        System.out.println ("BIEN");
                        //Nueva posicion moneda
                        groupCoin.setLayoutY(posCoinY);
                        groupCoin.setLayoutX (posCoinX);
                        contadorShuriken = 1;
                    }
                
                    if (colisionVacia1 == false){
                        //Perdida de vidas
                        vidas --;
                        textVidas.setText(String.valueOf(vidas)); 
                    }
   
                    
                    if (colisionVacia2 == false){
                      
                    }
                    if (colisionVacia3 == false){
                        
                    }
                    //Aparicion monedas
                    if (contadorShuriken%2 == 0){
                        visibleCoin = true;
                        movCoinY = +2;
                        posCoinY += movCoinY;
                        if (posCoinY >= 310){
                            posCoinY = 310;
                        }
                        groupCoin.setLayoutY(posCoinY);
                        System.out.println(posCoinY);
                        
                    } 

            }));
        
        animacionFondo.setCycleCount(Timeline.INDEFINITE);
        animacionFondo.play(); 
        
        Timeline animacionMuerte = new Timeline(
            new KeyFrame(Duration.seconds(0.017), (ActionEvent ae)->{
                
            }));
        animacionMuerte.setCycleCount(Timeline.INDEFINITE);
        animacionMuerte.play();
    }

    public static void main(String[] args) {
        launch();
    }

}