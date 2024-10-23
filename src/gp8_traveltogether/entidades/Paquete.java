/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gp8_traveltogether.entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Paquete {
    
    private int codigoPaquete;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Ciudad origen;
    private Ciudad destino;
    private Pasaje boleto;
    private Alojamiento estadia;
    private Pension regimen;
    private boolean traslado;
    private List<Turista> turistas;
    private double montoFinal;
    private boolean estado;

    public Paquete(int codigoPaquete, LocalDate fechaInicio, LocalDate fechaFin, Ciudad origen, Ciudad destino, Pasaje boleto, Alojamiento estadia, Pension regimen, boolean traslado, List<Turista> turistas, double montoFinal, boolean estado) {
        this.codigoPaquete = codigoPaquete;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.origen = origen;
        this.destino = destino;
        this.boleto = boleto;
        this.estadia = estadia;
        this.regimen = regimen;
        this.traslado = traslado;
        this.turistas = turistas;
        this.montoFinal = montoFinal;
        this.estado = estado;
    }

    public Paquete() {
    }

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public Pasaje getBoleto() {
        return boleto;
    }

    public Alojamiento getEstadia() {
        return estadia;
    }

    public Pension getRegimen() {
        return regimen;
    }

    public boolean isTraslado() {
        return traslado;
    }

    public List<Turista> getTuristas() {
        return turistas;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public void setBoleto(Pasaje boleto) {
        this.boleto = boleto;
    }

    public void setEstadia(Alojamiento estadia) {
        this.estadia = estadia;
    }

    public void setRegimen(Pension regimen) {
        this.regimen = regimen;
    }

    public void setTraslado(boolean traslado) {
        this.traslado = traslado;
    }

    public void setTuristas(List<Turista> turistas) {
        this.turistas = turistas;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
