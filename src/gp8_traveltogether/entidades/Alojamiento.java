package gp8_traveltogether.entidades;

public class Alojamiento {
    private int codAlojam;
    private String nombre;
    private Ciudad ciudad;
    private String tipoAlojam;
    private int capacidad;
    private double precioNoche;
    private boolean estado;

    public Alojamiento(int codAlojam, String nombre, Ciudad ciudad, String tipoAlojam, int capacidad, double precioNoche, boolean estado) {
        this.codAlojam = codAlojam;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.tipoAlojam = tipoAlojam;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.estado = estado;
    }

    public Alojamiento(String nombre, Ciudad ciudad, String tipoAlojam, int capacidad, double precioNoche, boolean estado) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.tipoAlojam = tipoAlojam;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.estado = estado;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipoAlojam() {
        return tipoAlojam;
    }

    public void setTipoAlojam(String tipoAlojam) {
        this.tipoAlojam = tipoAlojam;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }       
}

    