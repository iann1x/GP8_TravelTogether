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

    public Pasaje(int codPasaje, Ciudad origen, Ciudad destino, double precioPasaje, String tipoViaje) {
        this.codPasaje = codPasaje;
        this.origen = origen;
        this.destino = destino;
        this.precioPasaje = precioPasaje;
        this.tipoViaje = tipoViaje;
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

    @Override
    public String toString() {
        return origen + "-" + destino;
    }
    
    
}
