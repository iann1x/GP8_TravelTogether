package gp8_traveltogether.entidades;

public class Alojamiento {
    private int codAlojam;
    private String nombre;
    private boolean estado;
    private int capacidad;
    private double precioNoche;

    public Alojamiento(int codAlojam, String nombre, boolean estado, int capacidad, double precioNoche) {
        this.codAlojam = codAlojam;
        this.nombre = nombre;
        this.estado = estado;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
    }
    
    public Alojamiento(){
    }
    

    public int getCodAlojam() {
        return codAlojam;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setCodAlojam(int codAlojam) {
        this.codAlojam = codAlojam;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }
        
}

    