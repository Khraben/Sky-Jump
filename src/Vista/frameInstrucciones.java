package Vista;

public class frameInstrucciones extends javax.swing.JFrame {

    public frameInstrucciones() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlInstrucciones3 = new javax.swing.JLabel();
        jlInstrucciones2 = new javax.swing.JLabel();
        jlInstrucciones1 = new javax.swing.JLabel();
        jbSalir = new javax.swing.JButton();
        jlPersonaje2 = new javax.swing.JLabel();
        jlPersonaje1 = new javax.swing.JLabel();
        jlFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlInstrucciones3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/instrucciones3.png"))); // NOI18N
        getContentPane().add(jlInstrucciones3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 360, -1));

        jlInstrucciones2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/instrucciones2.png"))); // NOI18N
        getContentPane().add(jlInstrucciones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, -1, -1));

        jlInstrucciones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/instrucciones1.png"))); // NOI18N
        getContentPane().add(jlInstrucciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jbSalir.setFont(new java.awt.Font("Berlin Sans FB", 1, 14)); // NOI18N
        jbSalir.setForeground(new java.awt.Color(0, 153, 153));
        jbSalir.setText("SALIR");
        jbSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 500, 150, 40));

        jlPersonaje2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje2.gif"))); // NOI18N
        getContentPane().add(jlPersonaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, -1, -1));

        jlPersonaje1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/personaje1.gif"))); // NOI18N
        getContentPane().add(jlPersonaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jlFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoInstrucciones.png"))); // NOI18N
        getContentPane().add(jlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlFondo;
    private javax.swing.JLabel jlInstrucciones1;
    private javax.swing.JLabel jlInstrucciones2;
    private javax.swing.JLabel jlInstrucciones3;
    private javax.swing.JLabel jlPersonaje1;
    private javax.swing.JLabel jlPersonaje2;
    // End of variables declaration//GEN-END:variables
}
