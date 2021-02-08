package es.angelhuesovecina.mivideojuego;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
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
    int movShurikenX;
    int contadorShuriken1 = 1;
    int posCoinX;
    int posCoinY;
    int random1;
    int random2;
    int random3;
    Random random = new Random();
    int vidas = 2;
    int puntos;
    final int SCENE_TAM_X = 1000;
    final int SCENE_TAM_Y = 400;
    final int TEXT_SIZE = 30;
    final int TEXT_SIZE_YL = 100;
    boolean visibleCoin = false;
    boolean vivo = true;
    int movCoinY;
    int distShuriken1;
    int distShuriken2;
    int distShuriken3;
    int totalPosShuriken1;
    Text textPuntuacion;
    Group groupCoin;
    Group groupShuriken1;
    Group groupShuriken2;
    Group groupShuriken3;
    Group groupNinja;
    Text textVidas;
    Rectangle zonaContacto;
    Rectangle zonaCoin;
    Polygon polygonPunta1;
    Polygon polygonPunta2;
    Polygon polygonPunta3;
    Scene scene;
    ImageView ninjaMuerto;
    ImageView fondo;
    ImageView fondo2;
    ImageView gameOver;
    Image img4;
    Image img2;
    HBox paneScores;
    Pane root;
    AudioClip sonidoSalto;
    AudioClip sonidoMoneda;
    AudioClip sonidoAmbiente;
    
    @Override
    public void start(Stage stage) {
        root = new Pane();
        scene = new Scene (root, 1000, 400);
        stage.setResizable(false);
        stage.setTitle("Mi Juego");
        stage.setScene(scene);
        stage.show();        
        
        //Carga de imagenes
        cargaImagenes();
        
        //Creacion de Shurikens
        creacionShurikens();
        
        //Creacion de los marcos en las imagenes
        marcos();
        
        //Posicionamiento de los shurikens
        posShuriken();
        
        audios();

        //LAYOUTS PUNTUACIONES
        layaoutPuntuaciones();
        
        //Imagenes añadidas a root
        imagenesAñadidas();
        
        //Controles
        controles();

        Timeline animacionFondo = new Timeline(
            new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) ->{
                
                //Ninja Muerto
            ninjaMuerto.setX(posNinjaX);
            ninjaMuerto.setY(posNinjaY);
                
                //Visibilidad Moneda
                if (visibleCoin == false){
                    groupCoin.setVisible(false);
                }else{
                    groupCoin.setVisible(true);
                }
                
                // Vivo o Muerto
                if (vidas < 0){
                    vivo = false;
                }else{
                    vivo = true;
                }
                
                //Accion de movimiento Ninja
                posNinjaX += movNinjaX;
                groupNinja.setLayoutX(posNinjaX);
                posNinjaY += movNinjaY;
                groupNinja.setLayoutY(posNinjaY);

                //Condiciones vivo y muerto
                vivoMuerto();  
                
                //Movimiento de los Shurikens
                movShuriken();
                //Colisiones
                colisiones();
            }));
        
        animacionFondo.setCycleCount(Timeline.INDEFINITE);
        animacionFondo.play();}
        

    public static void main(String[] args) {
        launch();
    }
    
    void controles(){
        scene.setOnKeyPressed((KeyEvent event) -> {
                switch (event.getCode()) {
                    case RIGHT: 
                        if (vivo == true){
                            movNinjaX = +2;
                        }
                        break;
                    case LEFT: 
                        if (vivo == true){
                            movNinjaX = -2;
                        }
                        break;
                    case SPACE: 
                        if (vivo == true){
                            if (posNinjaY == 280){
                                movNinjaY = -5;
                                sonidoSalto.play(0.5);
                            }
                        }
                        break;
                    case ESCAPE: 
                        if (vivo == false){
                            vidas = 2;
                            puntos = 0;
                            vivo = true;
                            textVidas.setText(String.valueOf(vidas));
                            textPuntuacion.setText(String.valueOf(puntos));
                            posNinjaX = 0;
                            posShuriken1X = 1030;
                            posShuriken2X = 1310;
                            posShuriken3X = 1610;
                            posCoinY = -30;
                            sonidoAmbiente.play();
                        }
                        break;
                    }
            });       
             scene.setOnKeyReleased((KeyEvent event) -> {
                movFondo = 0;
                movNinjaX = 0;    
            });
    }
    
    void layaoutPuntuaciones(){
        //Layaout principal
        paneScores = new HBox();
        paneScores.setAlignment(Pos.CENTER);
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
        textEtiquetaPuntuacion.setLayoutX(100);
            //Texto para puntuacion
        textPuntuacion = new Text("0");
        textPuntuacion.setFont(Font.font(TEXT_SIZE));
        textPuntuacion.setFill(Color.BLACK);
        textPuntuacion.setLayoutX(250);
            //Texto etiqueta vidas
        Text textEtiquetaVidas = new Text("Vidas:");
        textEtiquetaVidas.setFont(Font.font(TEXT_SIZE));
        textEtiquetaVidas.setFill(Color.BLACK);
        //Texto etiqueta para la puntuacion
        textVidas = new Text ("2");
        textVidas.setFont(Font.font(TEXT_SIZE));
        textVidas.setFill(Color.BLACK);
            //Añadir texto a los layouts
        puntActual.getChildren().add(textEtiquetaPuntuacion);
        puntActual.getChildren().add(textPuntuacion);
        puntVidas.getChildren().add(textEtiquetaVidas);
        puntVidas.getChildren().add(textVidas);
    }
    
    void colisiones(){
        Shape zonaColision1 = Shape.intersect(zonaContacto, polygonPunta1);
        boolean colisionVacia1 = zonaColision1.getBoundsInLocal().isEmpty();
        Shape zonaColision2 = Shape.intersect(zonaContacto, polygonPunta2);
        boolean colisionVacia2 = zonaColision2.getBoundsInLocal().isEmpty();
        Shape zonaColision3 = Shape.intersect(zonaContacto, polygonPunta3);
        boolean colisionVacia3 = zonaColision3.getBoundsInLocal().isEmpty();
        Shape zonaColision4 = Shape.intersect(zonaContacto, zonaCoin);
        boolean colisionVacia4 = zonaColision4.getBoundsInLocal().isEmpty();
                    //Colision con la moneda
                    if (colisionVacia4 == false && visibleCoin == true && vivo == true){
                        sonidoMoneda.play(0.5);
                        visibleCoin = false;
                        posCoinX = (int)(Math.random()*901);
                        posCoinY = -30;
                        //Sistema de puntos
                        puntos ++;
                        textPuntuacion.setText(String.valueOf(puntos));
                        //Nueva posicion moneda
                        groupCoin.setLayoutY(posCoinY);
                        groupCoin.setLayoutX (posCoinX);
                        contadorShuriken1 = 1;
                    }
                     //Colision shuriken1
                    if (colisionVacia1 == false){
                        vidas --;
                        textVidas.setText(String.valueOf(vidas));
                        random1=(random.nextInt(301)+200);
                        posShuriken1X= 1030 +random1;

                    }
                    //Colision shuriken2
                    if (colisionVacia2 == false){
                        vidas --;
                        textVidas.setText(String.valueOf(vidas));
                        random2=(random.nextInt(301)+200);
                        posShuriken2X= 1310 +random2;

                    }
                    //Colision shuriken
                    if (colisionVacia3 == false){
                        vidas --;
                        textVidas.setText(String.valueOf(vidas));
                        random3=(random.nextInt(301)+200);
                        posShuriken3X= 1610 +random3;  

                    }
                    //Aparicion monedas
                    if (contadorShuriken1%2 == 0){
                        visibleCoin = true;
                        movCoinY = +2;
                        posCoinY += movCoinY;
                        if (posCoinY >= 310){
                            posCoinY = 310;
                        }
                        groupCoin.setLayoutY(posCoinY);  
                    }
    }
    
    void movShuriken(){
       posShuriken1X += movShurikenX;
                groupShuriken1.setLayoutX(posShuriken1X);
                //Movimiento Shuriken2
                posShuriken2X += movShurikenX;
                groupShuriken2.setLayoutX(posShuriken2X);
                //Movimiento Shuriken3
                posShuriken3X += movShurikenX;
                groupShuriken3.setLayoutX(posShuriken3X); 
                
                //Limite posicion ninja
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
                //Restart Shurikens
                if (posShuriken1X <= 0){
                    contadorShuriken1 ++;
                    posShuriken1X = 1030;
                    random1=(random.nextInt(301)+200);
                    totalPosShuriken1 = posShuriken1X + random1;
                    groupShuriken1.setLayoutX(totalPosShuriken1);
                
                }
                if (posShuriken2X <= 0){
                    random2=(random.nextInt(301)+200);
                    posShuriken2X = totalPosShuriken1 + random2;
                    groupShuriken2.setLayoutX(posShuriken2X);
                }
                if (posShuriken3X <=0){
                    random3=(random.nextInt(301)+200);
                    posShuriken3X=posShuriken2X + random3;
                    groupShuriken3.setLayoutX(posShuriken3X);
                }
    }
    
    void creacionShurikens(){
      //Creacion shuriken 1
            //Punta
        polygonPunta1 = new Polygon (new double[]{
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
        polygonPunta2 = new Polygon (new double[]{
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
        polygonPunta3 = new Polygon (new double[]{
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
        groupShuriken1 = new Group();
        groupShuriken1.getChildren().add(polygonPunta1);
        groupShuriken1.getChildren().add(rectangleCuerpo1);
        groupShuriken1.getChildren().add(circleCola1);
        groupShuriken1.getChildren().add(circleResta1);
            //Shuriken 2
        groupShuriken2 = new Group();
        groupShuriken2.getChildren().add(polygonPunta2);
        groupShuriken2.getChildren().add(rectangleCuerpo2);
        groupShuriken2.getChildren().add(circleCola2);
        groupShuriken2.getChildren().add(circleResta2);
            //Shuriken 3
        groupShuriken3 = new Group();
        groupShuriken3.getChildren().add(polygonPunta3);
        groupShuriken3.getChildren().add(rectangleCuerpo3);
        groupShuriken3.getChildren().add(circleCola3);
        groupShuriken3.getChildren().add(circleResta3);  
    }
    
    void audios(){
      //Sonido salto  
        URL urlAudioSalto = getClass().getResource("/audio/salto3.wav");
        if(urlAudioSalto != null) {
            try {
                sonidoSalto = new AudioClip(urlAudioSalto.toURI().toString());
                
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
        
        //Sonido salto  
        URL urlAudioMoneda = getClass().getResource("/audio/moneda.wav");
        if(urlAudioMoneda != null) {
            try {
                sonidoMoneda = new AudioClip(urlAudioMoneda.toURI().toString());
                
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
        
        //Sonido Ambiente  
        URL urlAudioAmbiente = getClass().getResource("/audio/ambiente.mp3");
        if(urlAudioAmbiente != null) {
            try {
                sonidoAmbiente = new AudioClip(urlAudioAmbiente.toURI().toString());
                sonidoAmbiente.setCycleCount(AudioClip.INDEFINITE);
                sonidoAmbiente.play();
                
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }  
    }
    
    void vivoMuerto(){
      //Acciones vivo - muerto
        if (vivo == true){
                    movShurikenX = -3;
                    gameOver.setVisible(false);
                    ninjaMuerto.setVisible(false);
                    groupNinja.setVisible(true);
                    if (puntos >= 5){
                        movShurikenX = -4;
                    }
                    if (puntos >= 10){
                        movShurikenX = -5;
                    }
                    if (puntos >= 15){
                        movShurikenX = -6;
                    }
                }else{
                    movShurikenX = 0;
                    ninjaMuerto.setVisible(true);
                    groupNinja.setVisible(false);
                    gameOver.setVisible(true);
                    sonidoAmbiente.stop();
                }  
    }
    
    void imagenesAñadidas(){
      //Añadir imagen
        root.getChildren().add(fondo);
        root.getChildren().add(fondo2);
        root.getChildren().add(groupNinja);
        root.getChildren().add(groupShuriken1);
        root.getChildren().add(groupShuriken2);
        root.getChildren().add(groupShuriken3);
        root.getChildren().add(paneScores);
        root.getChildren().add(groupCoin);
        root.getChildren().add(ninjaMuerto);
        root.getChildren().add(gameOver);  
    }
    
    void cargaImagenes(){
        //Imagenes de fondo
        Image img = new Image(getClass().getResourceAsStream("/images/fondo.png"));
        fondo = new ImageView(img);
        Image img3 = new Image(getClass().getResourceAsStream("/images/fondo2.png"));
        fondo2 = new ImageView(img3);
        img4 = new Image (getClass().getResourceAsStream("/images/coinnn_1.png"));
        
        //Imagen ninja
        img2 = new Image (getClass().getResourceAsStream("/images/ninjaBueno.png"));
        Image img5 = new Image (getClass().getResourceAsStream("/images/ninaMuerto.png"));
        ninjaMuerto = new ImageView (img5);
        ninjaMuerto.setVisible(false);
        
        //Imagen GameOver
        Image img6 = new Image(getClass().getResourceAsStream("/images/gameOver.png"));
        gameOver = new ImageView (img6);
        gameOver.setVisible(false);
        
    }
    
    void marcos(){
        //Agregando marco al ninja
        groupNinja = new Group();
        ImageView ninja2 = new ImageView (img2);
        zonaContacto = new Rectangle (45, 55);
        groupNinja.getChildren().add(ninja2);
        groupNinja.getChildren().add(zonaContacto);
        groupNinja.setLayoutY(posNinjaY);
        zonaContacto.setVisible(false);
        
        //Agregando marco a la moneda
        groupCoin = new Group();
        ImageView coin = new ImageView (img4);
        zonaCoin = new Rectangle (30,30);
        groupCoin.getChildren().add(coin);
        groupCoin.getChildren().add(zonaCoin);
        zonaCoin.setVisible(false);
        groupCoin.setVisible(false);
        posCoinX = (int)(Math.random()*901);
        groupCoin.setLayoutY(posCoinY);
        groupCoin.setLayoutX (posCoinX);
    }
    
    void posShuriken(){
        //Posicionamiento de los shurikens
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
    }
}
    
    

