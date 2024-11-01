/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gp8_traveltogether.entidades;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;


public class Paquete {
    private int codigoPaquete;
    private Ciudad origen;
    private Ciudad destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String temporada;
    private Alojamiento estadia;
    private Pasaje boleto;
    private Pension pension;
    private boolean traslado;
    private ArrayList<Turista> turistas;
    private double montoFinal;
    private boolean estado;

    public Paquete(Ciudad origen, Ciudad destino, LocalDate fechaInicio, LocalDate fechaFin) {
        this.origen = origen;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.temporada = calcularTemporada(fechaInicio);
    }
    
    public Paquete(int codigoPaquete, Ciudad origen, Ciudad destino, LocalDate fechaInicio, LocalDate fechaFin, String temporada, Alojamiento estadia, Pasaje boleto, Pension pension, boolean traslado, ArrayList<Turista> turistas, double montoFinal, boolean estado) {
        this.codigoPaquete = codigoPaquete;
        this.origen = origen;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.temporada = calcularTemporada(fechaInicio);
        this.estadia = estadia;
        this.boleto = boleto;
        this.pension = pension;
        this.traslado = traslado;
        this.turistas = new ArrayList<>();
        this.montoFinal = calcularMontoFinal();
        this.estado = estado;
    }

    public Paquete(Ciudad origen, Ciudad destino, LocalDate fechaInicio, LocalDate fechaFin, String temporada, Alojamiento estadia, Pasaje boleto, Pension pension, boolean traslado, ArrayList<Turista> turistas, double montoFinal, boolean estado) {
        this.origen = origen;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.temporada = calcularTemporada(fechaInicio);
        this.estadia = estadia;
        this.boleto = boleto;
        this.pension = pension;
        this.traslado = traslado;
        this.turistas = new ArrayList<>();
        this.montoFinal = calcularMontoFinal();
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

    public Pension getPension() {
        return pension;
    }

    public boolean isTraslado() {
        return traslado;
    }

    public ArrayList<Turista> getTuristas() {
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

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public void setTraslado(boolean traslado) {
        this.traslado = traslado;
    }

    public void setTuristas(ArrayList<Turista> turistas) {
        this.turistas = turistas;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public void setEstado(boolean estado) {
        this.estado = estado; 
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
    
    public String calcularTemporada(LocalDate ida){
        LocalDate inicioTAlta = LocalDate.of(2024, 12, 15);
        LocalDate finTAlta = LocalDate.of(2025, 2, 28);
        LocalDate inicioTMedia = LocalDate.of(2024,9,1);
        LocalDate finTMedia = LocalDate.of(2024, 12, 14);
        
        if (!ida.isBefore(inicioTAlta) && !ida.isAfter(finTAlta)) {
            return "alta";
        } else if (!ida.isBefore(inicioTMedia) && (!ida.isAfter(finTMedia)) || semanaSanta2025(ida)) {
            return "media";
        } else {
            return "baja";
        }
    }
    
    public boolean semanaSanta2025(LocalDate ida){
        LocalDate inicioSS = LocalDate.of(2025, 4, 17);
        LocalDate finSS = LocalDate.of(2025,4,19);
        return ida.isAfter(inicioSS)&& ida.isBefore(ida);
    }
    
    public long totalDias(){
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
    
    public Double calcularMontoFinal(){
        double costoFinal = 0.0;
        double costoAlojamiento = 0.0;
        double costoPasajes = 0.0;
        double costoTraslado = 0.0;
        double costoPension = 0.0; 
        
        if(estadia!=null){
            costoAlojamiento = estadia.getPrecioNoche()*totalDias();
            costoFinal += costoAlojamiento;
        }
        
        for (Turista t:turistas){
            double costoBoleto = 0.0;
            if(boleto!=null){
                costoBoleto = boleto.getPrecioPasaje();
            }else {
                costoBoleto = 0.0;
            }
            
            double tieneTraslado = 0.0;
            if(traslado){
                tieneTraslado = costoFinal * 0.01;
            }else{
                tieneTraslado = 0.0;
            }
            
            if (pension != null) {
                double porcentajePension = pension.getPorcentaje();
                costoPension = costoAlojamiento * (porcentajePension / 100);
                costoFinal += costoPension; 
        }
            
            if (t.getEdad()<10){
               costoBoleto *=0.5;
               tieneTraslado *=0.5;
               costoPension*=0.5;
           }
            
            costoPasajes += costoBoleto;
            costoTraslado += tieneTraslado;
            costoFinal += costoBoleto + tieneTraslado;      
        }
        
        if ("Alta".equals(temporada)) {
            costoFinal *= 1.30;
        } else if ("Media".equals(temporada)) {
            costoFinal *= 1.15;
        }
        return costoFinal;
    }
    
    public void agregarTurista(Turista turista){
         turistas.add(turista);
    }
           
} //cierre de Clase Paquete
    
   
