package Vista;

import Controlador.gameThread;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class frameGame extends javax.swing.JFrame {
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public Random random = new Random();

    gameThread hiloSkyJump;

    public String estadoPersonajeH = "Estatico";
    public String estadoPersonajeV = "Estatico";
    public int plataformaActual = 1;
    public int plataformaAnterior = 1;                                                 //Para validar si en el uso del powerUp solo avanzo una plataforma
    public int velocidadFondo = 1;                                                     //Para cambiar la velocidad con la que se mueve el fondo
    public int puntuacion = 0;
    public int evento = 0;
    public int cantidadSaltos = 0;
    public int posicionBajada = 350;
    public int velocidadObstaculoH = 0;                                                //Para la velocidad de movimiento horizontal del obstaculo
    public int velocidadObstaculoV = 0;                                                //Para la velocidad de movimiento vertical del obstaculo
    public boolean powerUp = false;
    public boolean powerUpDisponible = false;
    public boolean obstaculoActivo = false;

    JLabel jlPersonajeActivo;

    private Clip musicaFondo;
    private Clip FX;

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public frameGame(int personajeSeleccionado) {
        initComponents();
        setLocationRelativeTo(null);
        jtPuntuacion.setEnabled(false);
        hiloSkyJump = new gameThread(this);
        hiloSkyJump.start();
        if (personajeSeleccionado == 2) {
            jlPersonajeActivo = jlPersonaje2;
            jlPersonaje1.setIcon(null);
        } else {
            jlPersonajeActivo = jlPersonaje1;
            jlPersonaje2.setIcon(null);
        }
    }//Metodo Constructor
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void reproducirMusicaFondo() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sonido/musicaFondo.wav")); // Cambia la ruta según la ubicación de tu archivo de audio
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioInputStream);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFondo.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//Metodo para iniciar la reproduccion de la musica de fondo en loop

    public void reproducirFX(String rutaFX) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource(rutaFX));
            FX = AudioSystem.getClip();
            FX.open(audioInputStream);
            FX.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//Metodo para reproducir efectos de sonido, recibe la ruta del archivo con el efecto de sonido

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void moverFondo() {
        if (jlFondo.getY() < 0) {
            //Esto es para correr el fondo
            jlFondo.setLocation(jlFondo.getX(), jlFondo.getY() + velocidadFondo);
        } else {
            jlFondo.setLocation(0, -1890);
        }

    }//Metodo que mueve el fondo y cuando es necesario reestablece la posicion para simular fondo infito

    public void moverPersonaje() {
        if (estadoPersonajeH.equalsIgnoreCase("Estatico")) {
            jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY());
        }
        if (estadoPersonajeV.equalsIgnoreCase("Subiendo")) {
            jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() - 10);
            if (powerUp) {
                if (jlPersonajeActivo == jlPersonaje1) {
                    if (jlPersonajeActivo.getY() < 110) {
                        estadoPersonajeV = "Bajando";
                    }

                } else {                                                               //En este caso estaria PowerUp pero seria el personaje 2
                    if ((jlPersonajeActivo.getY() < posicionBajada)) {
                        estadoPersonajeV = "Bajando";
                        posicionBajada = 350;
                    }
                }
            } else {
                if (jlPersonajeActivo.getY() < 350) {
                    estadoPersonajeV = "Bajando";
                }
            }
        }
        if (estadoPersonajeV.equalsIgnoreCase("Bajando")) {
            jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 10);
            detectarPlataforma();
            if (jlPersonajeActivo.getY() == 610) {
                if (jlPersonajeActivo == jlPersonaje1) {
                    reproducirFX("/sonido/fxCaidaPJ1.wav");
                } else {
                    reproducirFX("/sonido/fxCaidaPJ2.wav");
                }

            }
            if (jlPersonajeActivo.getY() > 940) {                                       //El personaje cae de la pantalla, pierde
                gameOver();
            }
        }
        if (jlPersonajeActivo.getY() == 590) {
            cantidadSaltos = 0;
        }
        if (estadoPersonajeH.equalsIgnoreCase("Derecha")) {
            jlPersonajeActivo.setLocation(jlPersonajeActivo.getX() + 13, jlPersonajeActivo.getY());
            if (detectarCaida()) {
                estadoPersonajeV = "Bajando";
            }
            if (jlPersonajeActivo.getX() > 1014) {
                jlPersonajeActivo.setLocation(-30, jlPersonajeActivo.getY());
            }
        }
        if (estadoPersonajeH.equalsIgnoreCase("Izquierda")) {
            jlPersonajeActivo.setLocation(jlPersonajeActivo.getX() - 13, jlPersonajeActivo.getY());
            if (detectarCaida()) {
                estadoPersonajeV = "Bajando";
            }
            if (jlPersonajeActivo.getX() < -30) {
                jlPersonajeActivo.setLocation(1014, jlPersonajeActivo.getY());
            }

        }
    }//Metodo que establece el movimiento del personaje en todo el juego

    public void detectarPlataforma() {
        switch (plataformaActual) {
            case 1:
                if ((jlPlataforma1.getY() - 193 == jlPersonajeActivo.getY())
                        && (jlPlataforma1.getX() + jlPlataforma1.getWidth() > jlPersonajeActivo.getX())
                        && (jlPlataforma1.getX() < jlPersonajeActivo.getX() + 112)) {
                    estadoPersonajeV = "Estatico";
                } else {
                    if ((jlPlataforma2.getY() - 193 == jlPersonajeActivo.getY())
                            && (jlPlataforma2.getX() + jlPlataforma2.getWidth() > jlPersonajeActivo.getX())
                            && (jlPlataforma2.getX() < jlPersonajeActivo.getX() + 112)) {
                        estadoPersonajeV = "Estatico";
                        plataformaActual = 2;
                        plataformaAnterior = 1;
                        reubicarLabels();
                        puntuacion += 1;
                        velocidadFondo = random.nextInt(8) + 1;
                        eventosRandom();
                    } else {
                        if ((jlPlataforma3.getY() - 193 == jlPersonajeActivo.getY())
                                && (jlPlataforma3.getX() + jlPlataforma3.getWidth() > jlPersonajeActivo.getX())
                                && (jlPlataforma3.getX() < jlPersonajeActivo.getX() + 112)) {
                            estadoPersonajeV = "Estatico";
                            plataformaActual = 3;
                            plataformaAnterior = 1;
                            reubicarLabels();
                            puntuacion += 5;
                            velocidadFondo = random.nextInt(8) + 1;
                            eventosRandom();
                        }
                    }
                }
                powerUp = false;
                break;
            case 2:
                if ((jlPlataforma2.getY() - 193 == jlPersonajeActivo.getY())
                        && (jlPlataforma2.getX() + jlPlataforma2.getWidth() > jlPersonajeActivo.getX())
                        && (jlPlataforma2.getX() < jlPersonajeActivo.getX() + 112)) {
                    estadoPersonajeV = "Estatico";
                } else {
                    if ((jlPlataforma3.getY() - 193 == jlPersonajeActivo.getY())
                            && (jlPlataforma3.getX() + jlPlataforma3.getWidth() > jlPersonajeActivo.getX())
                            && (jlPlataforma3.getX() < jlPersonajeActivo.getX() + 112)) {
                        estadoPersonajeV = "Estatico";
                        plataformaActual = 3;
                        plataformaAnterior = 2;
                        reubicarLabels();
                        puntuacion += 1;
                        velocidadFondo = random.nextInt(8) + 1;
                        eventosRandom();
                    } else {
                        if ((jlPlataforma1.getY() - 193 == jlPersonajeActivo.getY())
                                && (jlPlataforma1.getX() + jlPlataforma1.getWidth() > jlPersonajeActivo.getX())
                                && (jlPlataforma1.getX() < jlPersonajeActivo.getX() + 112)) {
                            estadoPersonajeV = "Estatico";
                            plataformaActual = 1;
                            plataformaAnterior = 2;
                            reubicarLabels();
                            puntuacion += 5;
                            velocidadFondo = random.nextInt(8) + 1;
                            eventosRandom();
                        }
                    }
                }
                powerUp = false;
                break;
            case 3:
                if ((jlPlataforma3.getY() - 193 == jlPersonajeActivo.getY())
                        && (jlPlataforma3.getX() + jlPlataforma3.getWidth() > jlPersonajeActivo.getX())
                        && (jlPlataforma3.getX() < jlPersonajeActivo.getX() + 112)) {
                    estadoPersonajeV = "Estatico";
                } else {
                    if ((jlPlataforma1.getY() - 193 == jlPersonajeActivo.getY())
                            && (jlPlataforma1.getX() + jlPlataforma1.getWidth() > jlPersonajeActivo.getX())
                            && (jlPlataforma1.getX() < jlPersonajeActivo.getX() + 112)) {
                        estadoPersonajeV = "Estatico";
                        plataformaActual = 1;
                        plataformaAnterior = 3;
                        reubicarLabels();
                        puntuacion += 1;
                        velocidadFondo = random.nextInt(8) + 1;
                        eventosRandom();
                    } else {
                        if ((jlPlataforma2.getY() - 193 == jlPersonajeActivo.getY())
                                && (jlPlataforma2.getX() + jlPlataforma2.getWidth() > jlPersonajeActivo.getX())
                                && (jlPlataforma2.getX() < jlPersonajeActivo.getX() + 112)) {
                            estadoPersonajeV = "Estatico";
                            plataformaActual = 2;
                            plataformaAnterior = 3;
                            reubicarLabels();
                            puntuacion += 5;
                            velocidadFondo = random.nextInt(8) + 1;
                            eventosRandom();
                        }
                    }
                }
                powerUp = false;
                break;
        }
    }//Metodo que detecta si el personaje cae sobre alguna plataforma de juego

    public boolean detectarCaida() {
        if (estadoPersonajeV.equalsIgnoreCase("Estatico")) {
            if (plataformaActual == 1) {
                if ((jlPlataforma1.getX() > jlPersonajeActivo.getX() + 80)
                        || (jlPlataforma1.getX() + jlPlataforma1.getWidth() < jlPersonajeActivo.getX())) {
                    return true;
                }

            } else {
                if (plataformaActual == 2) {
                    if ((jlPlataforma2.getX() > jlPersonajeActivo.getX() + 80)
                            || (jlPlataforma2.getX() + jlPlataforma2.getWidth() < jlPersonajeActivo.getX())) {
                        return true;
                    }
                } else {
                    if (plataformaActual == 3) {

                        if ((jlPlataforma3.getX() > jlPersonajeActivo.getX() + 80)
                                || (jlPlataforma3.getX() + jlPlataforma3.getWidth() < jlPersonajeActivo.getX())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }//Metodo que detecta si el personaje deja una plataforme durante movimiento horizontal

    public void reubicarLabels() {
        if (plataformaActual == 1) {
            if (plataformaAnterior == 2) {
                jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 400);
                jlPlataforma1.setLocation(jlPlataforma1.getX(), jlPlataforma1.getY() + 400);
                jlPlataforma2.setLocation(random.nextInt(831) + 20, 583);
            } else {
                jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 200);
                jlPlataforma1.setLocation(jlPlataforma1.getX(), jlPlataforma1.getY() + 200);
                jlPlataforma2.setLocation(jlPlataforma2.getX(), jlPlataforma2.getY() + 200);
            }
            jlPlataforma3.setLocation(random.nextInt(831) + 20, 383);
        } else {
            if (plataformaActual == 2) {
                if (plataformaAnterior == 3) {
                    jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 400);
                    jlPlataforma2.setLocation(jlPlataforma2.getX(), jlPlataforma2.getY() + 400);
                    jlPlataforma3.setLocation(random.nextInt(831) + 20, 583);
                } else {
                    jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 200);
                    jlPlataforma2.setLocation(jlPlataforma2.getX(), jlPlataforma2.getY() + 200);
                    jlPlataforma3.setLocation(jlPlataforma3.getX(), jlPlataforma3.getY() + 200);
                }
                jlPlataforma1.setLocation(random.nextInt(831) + 20, 383);
            } else {
                if (plataformaAnterior == 1) {
                    jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 400);
                    jlPlataforma1.setLocation(random.nextInt(831) + 20, 583);
                    jlPlataforma3.setLocation(jlPlataforma3.getX(), jlPlataforma3.getY() + 400);
                } else {
                    jlPersonajeActivo.setLocation(jlPersonajeActivo.getX(), jlPersonajeActivo.getY() + 200);
                    jlPlataforma1.setLocation(jlPlataforma1.getX(), jlPlataforma1.getY() + 200);
                    jlPlataforma3.setLocation(jlPlataforma3.getX(), jlPlataforma3.getY() + 200);
                }
                jlPlataforma2.setLocation(random.nextInt(831) + 20, 383);
            }
        }
        if (powerUpDisponible) {
            jlPowerUp.setLocation(jlPowerUp.getX(), jlPowerUp.getY() + 200);
            if (jlPowerUp.getY() > 750) {
                jlPowerUp.setLocation(-40, -0);
                powerUpDisponible = false;
            }
        }
        if (obstaculoActivo) {
            jlObstaculo.setLocation(jlPowerUp.getX(), jlPowerUp.getY() + 200);
            if ((jlObstaculo.getX() > 1096)
                    || (jlObstaculo.getX() > -41)
                    || (jlObstaculo.getY() > 951)) {
                obstaculoActivo = false;
                jlObstaculo.setLocation(0, -41);

            }
        }
    }//Metodo que mueve las labels hacia abajo, incluido el personaje

    public void detectarPowerUp() {
        if (powerUpDisponible) {
            if ((jlPersonajeActivo.getBounds().intersects(jlPowerUp.getBounds()))
                    && (jlPersonajeActivo.getY() == jlPowerUp.getY() - 160)
                    && ((estadoPersonajeV.equalsIgnoreCase("Bajando"))
                    || (estadoPersonajeV.equalsIgnoreCase("Estatico")))) {
                powerUp = true;
                powerUpDisponible = false;
                jlPowerUp.setLocation(-40, -0);
            }
        }
    }//Metodo que detecta que se "obtiene" powerUp por parte del personaje

    public void moverObstaculo() {
        if (obstaculoActivo) {
            jlObstaculo.setLocation(jlObstaculo.getX() + velocidadObstaculoH, jlObstaculo.getY() + velocidadObstaculoV);
        }
    }//Metodo que mueve el obstaculo si se encuentra activo

    public void detectarObstaculo() {
        if (obstaculoActivo) {
            if (jlPersonajeActivo.getBounds().intersects(jlObstaculo.getBounds())) {
                gameOver();
            }
        }
    }//Metodo que detecta la colision del personaje con un obstaculo
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void eventosRandom() {
        evento = random.nextInt(12) + 1;
        if (evento == 7) {                                                              //Si el random es 7, se genera un powerUp
            if (!powerUpDisponible) {                                                   //Verificar que no se encuentre ya un powerUp Disponible
                powerUpDisponible = true;
                if (plataformaActual == 1) {
                    jlPowerUp.setLocation(random.nextInt(jlPlataforma3.getWidth()) + jlPlataforma3.getX() - 20, jlPlataforma3.getY() - 33);
                } else {
                    if (plataformaActual == 2) {
                        jlPowerUp.setLocation(random.nextInt(jlPlataforma1.getWidth()) + jlPlataforma1.getX(), jlPlataforma1.getY() - 33);
                    } else {
                        jlPowerUp.setLocation(random.nextInt(jlPlataforma2.getWidth()) + jlPlataforma2.getX(), jlPlataforma2.getY() - 33);
                    }
                }
            }
            evento = 0;
        } else {
            if ((evento == 5)
                    || (evento == 6)) {                                                     //Si el random es 5 ó 6, se genera un obstaculo
                if (!obstaculoActivo) {
                    obstaculoActivo = true;
                    velocidadObstaculoH = random.nextInt(14) + 4;
                    velocidadObstaculoV = random.nextInt(10) + 2;
                    evento = random.nextInt(2) + 1;
                    if (evento == 1) {                                                      //Si el random es 1, aparece por la izquierda
                        jlObstaculo.setLocation(-18, random.nextInt(400));

                    }
                    if (evento == 2) {                                                      //Si el random es 2, aparece por la derecha
                        velocidadObstaculoH = velocidadObstaculoH * -1;
                        jlObstaculo.setLocation(1072, random.nextInt(400));
                    }
                }
            }
        }
    }//Metodo para la generación de eventos random, como powerUps y obstaculos

    public void actualizarPuntaje() {
        jtPuntuacion.setText("" + puntuacion);
    }//Metodo que actualiza el puntaje que se muestra al usuario

    public void gameOver() {
        musicaFondo.stop();
        Icon icono = new ImageIcon(getClass().getResource("/img/fondoGameOver.png")); //Se crea un nuevo icono y se muestra
        JOptionPane.showMessageDialog(null, "", "", 0, icono);
        this.dispose();
        hiloSkyJump.stop();
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------- 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlObstaculo = new javax.swing.JLabel();
        jlPausa = new javax.swing.JLabel();
        jtPuntuacion = new javax.swing.JTextField();
        jlPuntacionTxt = new javax.swing.JLabel();
        jlPersonaje1 = new javax.swing.JLabel();
        jlPersonaje2 = new javax.swing.JLabel();
        jlPowerUp = new javax.swing.JLabel();
        jlPlataforma1 = new javax.swing.JLabel();
        jlPlataforma2 = new javax.swing.JLabel();
        jlPlataforma3 = new javax.swing.JLabel();
        jlFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(1090, 986));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlObstaculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/obstaculo.gif"))); // NOI18N
        getContentPane().add(jlObstaculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-41, 0, -1, -1));

        jlPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonPausa.png"))); // NOI18N
        jlPausa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlPausaMouseClicked(evt);
            }
        });
        getContentPane().add(jlPausa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jtPuntuacion.setFont(new java.awt.Font("Berlin Sans FB", 1, 45)); // NOI18N
        jtPuntuacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPuntuacion.setText("0");
        jtPuntuacion.setBorder(null);
        jtPuntuacion.setOpaque(false);
        getContentPane().add(jtPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 130, 60));

        jlPuntacionTxt.setFont(new java.awt.Font("Berlin Sans FB", 1, 38)); // NOI18N
        jlPuntacionTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPuntacionTxt.setText("Puntuación:");
        getContentPane().add(jlPuntacionTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 230, -1));

        jlPersonaje1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje1.gif"))); // NOI18N
        getContentPane().add(jlPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 590, 110, -1));

        jlPersonaje2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje2.gif"))); // NOI18N
        getContentPane().add(jlPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 590, 110, -1));

        jlPowerUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPowerUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/powerUp.png"))); // NOI18N
        getContentPane().add(jlPowerUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, -1, -1));

        jlPlataforma1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plataforma1.png"))); // NOI18N
        getContentPane().add(jlPlataforma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 783, -1, -1));

        jlPlataforma2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plataforma2.png"))); // NOI18N
        getContentPane().add(jlPlataforma2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 583, -1, -1));

        jlPlataforma3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plataforma3.png"))); // NOI18N
        getContentPane().add(jlPlataforma3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 383, -1, -1));

        jlFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoJuego.png"))); // NOI18N
        jlFondo.setText("Puntuación:");
        getContentPane().add(jlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1890, 1090, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == 37) {
            estadoPersonajeH = "Izquierda";
        }
        if (evt.getKeyCode() == 39) {
            estadoPersonajeH = "Derecha";
        }
        if (evt.getKeyCode() == 32) {
            if (jlPersonajeActivo == jlPersonaje2 && powerUp) {
                if (cantidadSaltos < 2) {
                    reproducirFX("/sonido/fxSaltoPJ2.wav");
                    estadoPersonajeV = "Subiendo";
                    cantidadSaltos += 1;
                    if (cantidadSaltos == 2) {
                        posicionBajada = jlPersonajeActivo.getY() - 240;
                    }
                }
            } else {
                if ((estadoPersonajeV.equalsIgnoreCase("Bajando")) //evitar doble salto
                        || (estadoPersonajeV.equalsIgnoreCase("Subiendo"))) {

                } else {
                    if (jlPersonajeActivo == jlPersonaje1) {
                        reproducirFX("/sonido/fxSaltoPJ1.wav");
                        estadoPersonajeV = "Subiendo";
                    } else {
                        reproducirFX("/sonido/fxSaltoPJ2.wav");
                        estadoPersonajeV = "Subiendo";
                    }

                }
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == 37) {
            estadoPersonajeH = "Estatico";
        }
        if (evt.getKeyCode() == 39) {
            estadoPersonajeH = "Estatico";
        }
    }//GEN-LAST:event_formKeyReleased

    private void jlPausaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlPausaMouseClicked
        int vel = velocidadFondo;
        musicaFondo.stop();
        velocidadFondo = 0;
        Icon icono = new ImageIcon(getClass().getResource("/img/fondoPausa.png"));
        JOptionPane.showMessageDialog(null, "", "", 0, icono);
        musicaFondo.start();
        velocidadFondo = vel;
    }//GEN-LAST:event_jlPausaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlFondo;
    private javax.swing.JLabel jlObstaculo;
    private javax.swing.JLabel jlPausa;
    private javax.swing.JLabel jlPersonaje1;
    private javax.swing.JLabel jlPersonaje2;
    private javax.swing.JLabel jlPlataforma1;
    private javax.swing.JLabel jlPlataforma2;
    private javax.swing.JLabel jlPlataforma3;
    private javax.swing.JLabel jlPowerUp;
    private javax.swing.JLabel jlPuntacionTxt;
    private javax.swing.JTextField jtPuntuacion;
    // End of variables declaration//GEN-END:variables
}
