package gp8_traveltogether.entidades;

public class Alojamiento {
     private int codAlojam;
    private String nombre;
    private boolean estado;
    private int capacidad;
    private double precioNoche;
    private int codCiudad; // Agregado el campo codCiudad para enlazar con la Ciudad

    public Alojamiento(int codAlojam, String nombre, boolean estado, int capacidad, double precioNoche, int codCiudad) {
        this.codAlojam = codAlojam;
        this.nombre = nombre;
        this.estado = estado;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.codCiudad = codCiudad;
    }

    public Alojamiento() {
    }

    public int getCodAlojam() {
        return codAlojam;
    }

    public void setCodAlojam(int codAlojam) {
        this.codAlojam = codAlojam;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }
        
}

    