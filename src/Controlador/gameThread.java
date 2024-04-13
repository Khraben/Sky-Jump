package Controlador;

import Vista.frameGame;

public class gameThread extends Thread {

    frameGame frmSky;
    public boolean gameOver = false;

    public gameThread(frameGame frmSky) {
        this.frmSky = frmSky;
        frmSky.reproducirMusicaFondo();
    }//Fin del constructor

    public void run() {
        try {
            while (!gameOver) {
                frmSky.moverFondo();
                frmSky.moverPersonaje();
                frmSky.detectarPowerUp();
                frmSky.actualizarPuntaje();
                frmSky.moverObstaculo();
                frmSky.detectarObstaculo();
                sleep(30);
            }
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Error en la ejecucion del juego");
        }
    }//Fin del metodo run

}//Fin de la clase 
