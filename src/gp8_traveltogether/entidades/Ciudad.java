
package gp8_traveltogether.entidades;


public class Ciudad {
    private int codCiudad;
    private String nombre;
    private boolean estado;
     private int frecuencia; 
    
  
   

    public Ciudad(int codCiudad, String nombre, boolean estado) {
        this.codCiudad = codCiudad;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Ciudad(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
    
    public Ciudad() {
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
     public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Ciudad ciudad = (Ciudad) obj;
    return codCiudad == ciudad.codCiudad;
    }
<<<<<<< HEAD

  
=======
    
    public Object getFrecuencia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
>>>>>>> 0f18b5f4b2bbb6714dbb704a73609a5d74852b05
    }
    
    
    
    

