/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical;

/**
 *
 * @author LjnozZ
 */
public class Paciente {
    String id;
    String cedula;
    String nombre;
    String apellido;
    String direccion;
    String correo;
    String tflncasa;
    String tlfncelular;

    public Paciente(String id, String cedula, String nombre, String apellido, String direccion, String correo, String tflncasa, String tlfncelular) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.tflncasa = tflncasa;
        this.tlfncelular = tlfncelular;
    }
    
    
    
}
