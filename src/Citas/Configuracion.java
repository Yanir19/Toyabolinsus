/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import Services.Add;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Yanir
 */
public class Configuracion extends javax.swing.JFrame {
    Color colorDelPapa;
    Color colorBotones;
    Font font;
    /**
     * Creates new form Configuracion
     */
    
    public Configuracion() throws IOException {
        initComponents();
        Image icon = new ImageIcon(getClass().getResource("/Iconos/medicos-de-tampico.png")).getImage();
        setIconImage(icon);
        colorBotones = new Color (hex ("2C3E50"));//Color d elos botonte
        colorDelPapa = new Color (hex("A9D0F5"));//Color del backgorud del papa
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Sertig.otf"));
            font  = font.deriveFont(Font.BOLD, 11);
        } catch (FontFormatException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        disenoLabel(jLabel1);
        disenoLabel(jLabel2);
        disenoLabel(jLabel3);
        disenoLabel(jLabel4);
        disenoLabel(jLabel5);
        disenoLabel(jLabel6);
        disenoLabel(jLabel11);
        disenoBotones(EditDatBtn);
        disenoBotones(AceptarBtn);
        disenoBotones(SalirBtn);
        this.getContentPane().setBackground(colorDelPapa);
        
        
        setTitle("Configuracion");
    }
    
    private int hex( String color_hex )
        {
            return Integer.parseInt(color_hex,  16 );
        }
        private void disenoLabel(JLabel actual){
            font=font  = font.deriveFont(Font.ROMAN_BASELINE, 14);
            actual.setFont(font);
    }
    
 private void disenoBotones(JButton actual){
       actual.setBackground(colorBotones);
       font  = font.deriveFont(Font.TYPE1_FONT, 13);
       actual.setFont(font);
       actual.setBorderPainted(false);
       actual.setFocusPainted(false);
        //actual.setContentAreaFilled(false);
        
       actual.setOpaque(false);
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        EditDatBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        AtencionSpin = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        HorarioTxtF1 = new javax.swing.JTextField();
        HoraCmbBox1 = new javax.swing.JComboBox<>();
        HorarioTxtF2 = new javax.swing.JTextField();
        HoraCmbBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        CompartirChckBox = new javax.swing.JCheckBox();
        AceptarBtn = new javax.swing.JButton();
        SalirBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Lunes = new javax.swing.JCheckBox();
        Martes = new javax.swing.JCheckBox();
        Miercoles = new javax.swing.JCheckBox();
        Jueves = new javax.swing.JCheckBox();
        Viernes = new javax.swing.JCheckBox();
        Sabado = new javax.swing.JCheckBox();
        Domingo = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario:");

        EditDatBtn.setText("Editar Datos");
        EditDatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditDatBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Tiempo de atención:");

        jLabel3.setText("Minutos");

        jLabel4.setText("Horario de atención:");

        HorarioTxtF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorarioTxtF1ActionPerformed(evt);
            }
        });

        HoraCmbBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "a.m", "p.m" }));

        HoraCmbBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "a.m", "p.m" }));

        jLabel6.setText("Compartir información:");

        AceptarBtn.setText("Aceptar");
        AceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarBtnActionPerformed(evt);
            }
        });

        SalirBtn.setText("Salir");
        SalirBtn.setMaximumSize(new java.awt.Dimension(71, 23));
        SalirBtn.setMinimumSize(new java.awt.Dimension(71, 23));
        SalirBtn.setName(""); // NOI18N
        SalirBtn.setPreferredSize(new java.awt.Dimension(71, 23));

        jLabel11.setText("Días de atención:");

        Lunes.setText("L");

        Martes.setText("M");

        Miercoles.setText("M");

        Jueves.setText("J");

        Viernes.setText("V");

        Sabado.setText("S");

        Domingo.setText("D");

        jMenu1.setText("Citas");

        jMenuItem4.setText("Ir a panel de citas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Configuración");

        jMenuItem3.setText("Ir a configuración");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Usuario");

        jMenuItem1.setText("Login");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Crear usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SalirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AceptarBtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EditDatBtn)
                                .addComponent(CompartirChckBox)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(AtencionSpin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(HorarioTxtF1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(HoraCmbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(HorarioTxtF2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(HoraCmbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(36, 36, 36)
                            .addComponent(Lunes)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Martes)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Miercoles)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Jueves)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Viernes)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Sabado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Domingo))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(AtencionSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(HorarioTxtF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoraCmbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HorarioTxtF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoraCmbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CompartirChckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(EditDatBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Lunes)
                    .addComponent(Martes)
                    .addComponent(Miercoles)
                    .addComponent(Jueves)
                    .addComponent(Viernes)
                    .addComponent(Sabado)
                    .addComponent(Domingo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AceptarBtn)
                    .addComponent(SalirBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HorarioTxtF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorarioTxtF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HorarioTxtF1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            FrameCita PanelCitas = new FrameCita();
            PanelCitas.setVisible(true);
        } catch (IOException | JSONException | ParseException | java.text.ParseException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Configuracion configuracion = null;
        try {
            configuracion = new Configuracion();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        configuracion.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Login login = null;
        try {
            login = new Login();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Usuario user = null;
        try {
            user = new Usuario();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void EditDatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditDatBtnActionPerformed
        Usuario user = null;
        try {
            user = new Usuario();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setVisible(true);
        user.setLocationRelativeTo(null);
    }//GEN-LAST:event_EditDatBtnActionPerformed

    private void AceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarBtnActionPerformed
        try {
            
            JSONObject configuracion=new JSONObject();
            configuracion.put("username", CompartirChckBox.isSelected());
            configuracion.put("comparte", CompartirChckBox.isSelected());
            configuracion.put("tiempoatencion", AtencionSpin.getValue());
            configuracion.put("lunes",Lunes.isSelected());
            configuracion.put("martes",Martes.isSelected());
            configuracion.put("miercoles",Miercoles.isSelected());
            configuracion.put("jueves",Jueves.isSelected());
            configuracion.put("viernes",Viernes.isSelected());
            configuracion.put("sabado",Sabado.isSelected());
            configuracion.put("domingo",Domingo.isSelected());
            configuracion.put("horainicio", HorarioTxtF1.getText() + " " + HoraCmbBox1.getSelectedItem());
            configuracion.put("horafin", HorarioTxtF2.getText() + " " + HoraCmbBox2.getSelectedItem());
            new Add().add("http://localhost/API_Citas/public/Medicos/edit/" + Login.username, configuracion);
        } catch (JSONException | IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
                                
    }//GEN-LAST:event_AceptarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Configuracion().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AceptarBtn;
    private javax.swing.JSpinner AtencionSpin;
    private javax.swing.JCheckBox CompartirChckBox;
    private javax.swing.JCheckBox Domingo;
    private javax.swing.JButton EditDatBtn;
    private javax.swing.JComboBox<String> HoraCmbBox1;
    private javax.swing.JComboBox<String> HoraCmbBox2;
    private javax.swing.JTextField HorarioTxtF1;
    private javax.swing.JTextField HorarioTxtF2;
    private javax.swing.JCheckBox Jueves;
    private javax.swing.JCheckBox Lunes;
    private javax.swing.JCheckBox Martes;
    private javax.swing.JCheckBox Miercoles;
    private javax.swing.JCheckBox Sabado;
    private javax.swing.JButton SalirBtn;
    private javax.swing.JCheckBox Viernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    // End of variables declaration//GEN-END:variables
}
