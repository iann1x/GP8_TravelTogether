package gp8_traveltogether.entidades;

public class Pension {
    private int codAdicional;
    private String nombre;
    private double porcentaje;

    public Pension(int codAdicional, String nombre, double porcentaje) {
        this.codAdicional = codAdicional;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
    
    public Pension(){
    }
    public int getCodAdicional() {
        return codAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setCodAdicional(int codAdicional) {
        this.codAdicional = codAdicional;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
