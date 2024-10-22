
package gp8_traveltogether.entidades;


public class Ciudad {
    private int codCiudad;
    private String nombre;
    private boolean estado;

    public Ciudad(int codCiudad, String nombre, boolean estado) {
        this.codCiudad = codCiudad;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
