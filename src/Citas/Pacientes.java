
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Miguel
 */

public class Pacientes extends JPanel {

private int id;
private JLabel Cedula;
private JLabel Nombre;
private JLabel Apellido;
private JLabel Direccion;
private JLabel Correo;
private JLabel TlfCasa;
private JLabel TlfCelular;

private JLabel CedulaL;
private JLabel NombreL;
private JLabel ApellidoL;
private JLabel DireccionL;
private JLabel CorreoL;
private JLabel TlfCasaL;
private JLabel TlfCelularL;

Font font;

    public Pacientes(Integer id, String Cedula, String Nombre, String Apellido, String Direccion, String Correo, String TlfCasa, String TlfCelular) throws IOException {
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Sertig.otf"));
            font  = font.deriveFont(Font.BOLD, 11);
        } catch (FontFormatException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.ApellidoL = new JLabel();
        this.Apellido = new JLabel();
        this.Cedula = new JLabel();
        this.CedulaL = new JLabel();
        this.Correo = new JLabel();
        this.CorreoL = new JLabel();
        this.Direccion = new JLabel();
        this.DireccionL = new JLabel();
        this.Nombre = new JLabel();
        this.NombreL = new JLabel();
        this.TlfCasa = new JLabel();
        this.TlfCasaL = new JLabel();
        this.TlfCelular = new JLabel();
        this.TlfCelularL = new JLabel();
        
        this.id = id;
        this.Cedula.setText(Cedula);
        this.Nombre.setText(Nombre);
        this.Apellido.setText(Apellido);
        this.Direccion.setText(Direccion);
        this.Correo.setText(Correo);
        this.TlfCasa.setText(TlfCasa);
        this.TlfCelular.setText(TlfCelular);
        
        this.CedulaL.setText("<HTML> Cedula &nbsp &nbsp </HTML>");
        this.NombreL.setText("<HTML> Nombre &nbsp &nbsp </HTML>");
        this.ApellidoL.setText("<HTML> Apellido &nbsp &nbsp </HTML>");
        this.DireccionL.setText("<HTML> Direccion &nbsp &nbsp </HTML>");
        this.CorreoL.setText("<HTML> Correo &nbsp &nbsp </HTML>");
        this.TlfCasaL.setText("<HTML> Tlf. Casa &nbsp &nbsp </HTML>");
        this.TlfCelularL.setText("<HTML> Tlf. Celular &nbsp &nbsp </HTML>");
        
        this.Cedula.setText("25266350");
        this.Nombre.setText("Miguel");
        this.Apellido.setText("Boscan");
        this.Direccion.setText("P Sherman, Calle Walady, 42, Sydney");
        this.Correo.setText("miguelboscan18@gmail.com");
        this.TlfCasa.setText("0286-9615193");
        this.TlfCelular.setText("0424-9117263");
        
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
              
        disenoLabel(CedulaL);
        disenoLabel(this.Cedula);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.CedulaL, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.Cedula, gbc);
      
        
    }
    
    public Pacientes() throws IOException {
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Sertig.otf"));
            font  = font.deriveFont(Font.BOLD, 11);
        } catch (FontFormatException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.ApellidoL = new JLabel();
        this.Apellido = new JLabel();
        this.Cedula = new JLabel();
        this.CedulaL = new JLabel();
        this.Correo = new JLabel();
        this.CorreoL = new JLabel();
        this.Direccion = new JLabel();
        this.DireccionL = new JLabel();
        this.Nombre = new JLabel();
        this.NombreL = new JLabel();
        this.TlfCasa = new JLabel();
        this.TlfCasaL = new JLabel();
        this.TlfCelular = new JLabel();
        this.TlfCelularL = new JLabel();
        
        
                
        this.CedulaL.setText("<HTML> Cedula: &nbsp &nbsp </HTML>");
        this.NombreL.setText("<HTML> Nombre: &nbsp &nbsp </HTML>");
        this.ApellidoL.setText("<HTML> Apellido: &nbsp &nbsp </HTML>");
        this.DireccionL.setText("<HTML> Direccion: &nbsp &nbsp </HTML>");
        this.CorreoL.setText("<HTML> Correo: &nbsp &nbsp </HTML>");
        this.TlfCasaL.setText("<HTML> Tlf. Casa: &nbsp &nbsp </HTML>");
        this.TlfCelularL.setText("<HTML> Tlf. Celular: &nbsp &nbsp </HTML>");
        
        this.Cedula.setText("<HTML>"+"25266350"+"</HTML>");
        this.Nombre.setText("<HTML>"+"Miguel"+"</HTML>");
        this.Apellido.setText("<HTML>"+"Boscan"+"</HTML>");
        this.Direccion.setText("<HTML>"+"P Sherman, Calle Walady, 42, Sydney"+"</HTML>");
        this.Correo.setText("<HTML>"+"miguelboscan18@gmail.com"+"</HTML>");
        this.TlfCasa.setText("<HTML>"+"0286-9615193"+"</HTML>");
        this.TlfCelular.setText("<HTML>"+"0424-9117263"+"</HTML>");
        
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        disenoLabel(CedulaL);
        disenoLabel(NombreL);
        disenoLabel(ApellidoL);
        disenoLabel(DireccionL);
        disenoLabel(CorreoL);
        disenoLabel(TlfCasaL);
        disenoLabel(TlfCelularL);
        
        
        // CEDULA
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.CedulaL, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.Cedula, gbc);
        // FIN CEDULA    
                    
        // NOMBRE    
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.NombreL, gbc);

            gbc.gridx = 5;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.Nombre, gbc);
        // FIN NOMBRE
            
        // APELLIDO    
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.ApellidoL, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.Apellido, gbc);
        // FIN APELLIDO    
      
        // CORREO    
            gbc.gridx = 4;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.CorreoL, gbc);

            gbc.gridx = 5;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.Correo, gbc);
        // FIN CORREO
            
        // TLF CASA    
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.TlfCasaL, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.TlfCasa, gbc);
        // FIN TLF CASA    
      
        // TLF CELULAR    
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.TlfCelularL, gbc);

            gbc.gridx = 5;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.TlfCelular, gbc);
        // FIN TLF CELULAR  
            
        // DIRECCION    
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            this.add(this.DireccionL, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 4;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(this.Direccion, gbc);
        // FIN DIRECCION     
    }
   
    
    private void disenoLabel(JLabel actual){
        actual.setFont(font);
    }

}
