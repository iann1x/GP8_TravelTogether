/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gp8_traveltogether.entidades;

/**
 *
 * @author Administrator
 */
public class Turista {
    
    private int dni;
    private String nombre;
    private int edad;
    private boolean estado;

    public Turista(int dni, String nombre, int edad, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.estado = estado;
    }

    public Turista() {
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
