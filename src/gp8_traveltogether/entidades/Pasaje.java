package gp8_traveltogether.entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Administrator
 */
public class Pasaje {
    
    private int codPasaje;
    private Ciudad origen;
    private Ciudad destino;
    private double precioPasaje;
    private String tipoViaje;
    private boolean estado;

    public Pasaje(int codPasaje, Ciudad origen, Ciudad destino, double precioPasaje, String tipoViaje, boolean estado) {
        this.codPasaje = codPasaje;
        this.origen = origen;
        this.destino = destino;
        this.precioPasaje = precioPasaje;
        this.tipoViaje = tipoViaje;
        this.estado = estado;
    }

    public Pasaje(Ciudad origen, Ciudad destino, double precioPasaje, String tipoViaje, boolean estado) {
        this.origen = origen;
        this.destino = destino;
        this.precioPasaje = precioPasaje;
        this.tipoViaje = tipoViaje;
        this.estado = estado;
    }

    public Pasaje() {
    }

    public int getCodPasaje() {
        return codPasaje;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public double getPrecioPasaje() {
        return precioPasaje;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setCodPasaje(int codPasaje) {
        this.codPasaje = codPasaje;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public void setPrecioPasaje(double precioPasaje) {
        this.precioPasaje = precioPasaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return origen +"-" + destino;
    }
    
    
}
