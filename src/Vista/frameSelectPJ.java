package Vista;

public class frameSelectPJ extends javax.swing.JFrame {

    public frameSelectPJ() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlPowerIcon4 = new javax.swing.JLabel();
        jlPowerIcon3 = new javax.swing.JLabel();
        jlPowerIcon2 = new javax.swing.JLabel();
        jlPowerIcon1 = new javax.swing.JLabel();
        jbSeleccionarPJ2 = new javax.swing.JButton();
        jbSeleccionarPJ1 = new javax.swing.JButton();
        jlPersonaje2 = new javax.swing.JLabel();
        jlPersonaje1 = new javax.swing.JLabel();
        jlFondoMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(920, 575));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Salto Doble");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, 100, 20));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Megasalto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 406, 100, 20));

        jlPowerIcon4.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jlPowerIcon4.setForeground(new java.awt.Color(0, 153, 153));
        jlPowerIcon4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPowerIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/powerUp.png"))); // NOI18N
        getContentPane().add(jlPowerIcon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 395, 30, -1));

        jlPowerIcon3.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jlPowerIcon3.setForeground(new java.awt.Color(0, 153, 153));
        jlPowerIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPowerIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/powerUp.png"))); // NOI18N
        getContentPane().add(jlPowerIcon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 395, -1, -1));

        jlPowerIcon2.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jlPowerIcon2.setForeground(new java.awt.Color(0, 153, 153));
        jlPowerIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPowerIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/powerUp.png"))); // NOI18N
        getContentPane().add(jlPowerIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 395, 30, -1));

        jlPowerIcon1.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jlPowerIcon1.setForeground(new java.awt.Color(0, 153, 153));
        jlPowerIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPowerIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/powerUp.png"))); // NOI18N
        getContentPane().add(jlPowerIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 395, -1, -1));

        jbSeleccionarPJ2.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jbSeleccionarPJ2.setForeground(new java.awt.Color(0, 153, 153));
        jbSeleccionarPJ2.setText("Seleccionar PJ 2");
        jbSeleccionarPJ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSeleccionarPJ2ActionPerformed(evt);
            }
        });
        getContentPane().add(jbSeleccionarPJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 150, 40));

        jbSeleccionarPJ1.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jbSeleccionarPJ1.setForeground(new java.awt.Color(0, 153, 153));
        jbSeleccionarPJ1.setText("Seleccionar PJ 1");
        jbSeleccionarPJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSeleccionarPJ1ActionPerformed(evt);
            }
        });
        getContentPane().add(jbSeleccionarPJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 150, 40));

        jlPersonaje2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje2.gif"))); // NOI18N
        getContentPane().add(jlPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, -1, -1));

        jlPersonaje1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje1.gif"))); // NOI18N
        getContentPane().add(jlPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        jlFondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoSeleccionPJ.png"))); // NOI18N
        jlFondoMenu.setText("..");
        getContentPane().add(jlFondoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 575));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSeleccionarPJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSeleccionarPJ1ActionPerformed
        jugar(1);
    }//GEN-LAST:event_jbSeleccionarPJ1ActionPerformed

    private void jbSeleccionarPJ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSeleccionarPJ2ActionPerformed
        jugar(2);
    }//GEN-LAST:event_jbSeleccionarPJ2ActionPerformed

    public void jugar(int personajeSeleccionado) {
        new frameGame(personajeSeleccionado).setVisible(true);
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbSeleccionarPJ1;
    private javax.swing.JButton jbSeleccionarPJ2;
    private javax.swing.JLabel jlFondoMenu;
    private javax.swing.JLabel jlPersonaje1;
    private javax.swing.JLabel jlPersonaje2;
    private javax.swing.JLabel jlPowerIcon1;
    private javax.swing.JLabel jlPowerIcon2;
    private javax.swing.JLabel jlPowerIcon3;
    private javax.swing.JLabel jlPowerIcon4;
    // End of variables declaration//GEN-END:variables
}
