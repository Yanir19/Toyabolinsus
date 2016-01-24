/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import javax.swing.JLabel;

/**
 *
 * @author Yanir
 */


public class Citas extends JLabel {
String Hora;
String Paciente;
String Cedula;
String fecha;

    public Citas(String noCita, String hora){
        this.Hora=hora;
        this.setText(noCita);
        System.out.println("Esta es la hora: "+Hora);
        //HOLA
        
    }
    


    public Citas(String Hora, String Paciente, String Dia) {
        this.Hora = Hora;
        this.Paciente = Paciente;
        this.fecha = Dia;
        
        this.setText( Hora +": "+ this.Paciente);
    }
    
    

    public String getHora() {
        return Hora;
    }

    Citas() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
