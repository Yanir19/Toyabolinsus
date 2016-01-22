
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import javax.swing.JLabel;

/**
 *
 * @author Miguel
 */

public class Pacientes extends JLabel {
private Integer id;
private String Cedula;
private String Nombre;
private String Apellido;
private String Direccion;
private String Correo;
private String TlfCasa;
private String TlfCelular;

    public Pacientes(Integer id, String Cedula, String Nombre, String Apellido, String Direccion, String Correo, String TlfCasa, String TlfCelular) {
        this.id = id;
        this.Cedula = Cedula;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.TlfCasa = TlfCasa;
        this.TlfCelular = TlfCelular;
    }
    
    Pacientes() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public String getCedula() {
        return Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getTlfCasa() {
        return TlfCasa;
    }

    public String getTlfCelular() {
        return TlfCelular;
    }    
}
