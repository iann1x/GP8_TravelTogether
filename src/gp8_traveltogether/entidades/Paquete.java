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

    public Paquete(Ciudad origen, Ciudad destino, LocalDate fechaInicio, LocalDate fechaFin, String temporada) {
        this.origen = origen;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.temporada = temporada;
        this.turistas = new ArrayList<>();
    }
    
    public Paquete(int codigoPaquete, Ciudad origen, Ciudad destino, LocalDate fechaInicio, LocalDate fechaFin, String temporada, Alojamiento estadia, Pasaje boleto, Pension pension, boolean traslado, ArrayList<Turista> turistas, boolean estado) {
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
        
        int mes = ida.getMonthValue();
        int dia = ida.getDayOfMonth();
        
        if (dia > 15 && mes == 12 || mes == 1 || mes == 2 || mes ==7){
            return "alta";
        } else if (mes ==9|| mes ==10 || mes == 11 || semanaSanta2025(ida)){
            return "media";
        } else{
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
    
    public double calcularMontoFinal() {
        double costoFinal = 0.0;

        if (turistas != null) {
            for (Turista turista : turistas) {
                boolean menor = turista.getEdad() <10;
                double precioBase = getCostoAlojamiento(menor) + getCostoPension(menor) + getCostoPasaje(menor);
                costoFinal += precioBase + getCostoTraslado(menor, precioBase);
 
                } 
            }

        costoFinal+= getRecargoTemporada(costoFinal);
        return Math.round(costoFinal*100.0)/100.0;   
    }
    
    public double calcularMontoFinalConRecargo() {
        double montoTotal = calcularMontoFinal();
    
        double recargoPorPasajero = montoTotal * 0.1;
        montoTotal += recargoPorPasajero;
    
        return Math.round(montoTotal*100.0)/100.0;
    }

    public double costoAdulto(){
        double precioBaseAdulto = getCostoAlojamiento(false)+getCostoPasaje(false)+getCostoPension(false);
        double costoTraslado = getCostoTraslado(false, precioBaseAdulto);
        
        return Math.round((precioBaseAdulto+costoTraslado)*100.0)/100.0; 
    }
    
    public double costoMenor(){
        double precioBaseMenor = getCostoAlojamiento(true)+getCostoPasaje(true)+getCostoPension(true);
        double costoTraslado = getCostoTraslado(true, precioBaseMenor);
        return Math.round((precioBaseMenor+costoTraslado)*100.0)/100.0; 
    }
    
    public double getCostoAlojamiento(boolean menor){
        double costoAlojamiento = 0.0;
        
        if (estadia != null) {
            costoAlojamiento = estadia.getPrecioNoche() * totalDias();
        }
        return Math.round(costoAlojamiento*100.0)/100.0;
    
    }
    
    public double getCostoPension(boolean menor){
        double costoPension = 0.0;
        
        if (pension != null && estadia != null) {
            double porcentajePension = pension.getPorcentaje();
            costoPension = estadia.getPrecioNoche() * totalDias() * (porcentajePension / 100);
            if (menor) {
                costoPension *= 0.5;
            }
        }
        return Math.round(costoPension*100.0)/100.0;
    }
    
    public double getCostoPasaje(boolean menor) {
        double costoPasaje = 0.0;
        if (boleto != null) {
            costoPasaje = boleto.getPrecioPasaje();
            if (menor) {
                costoPasaje *= 0.5; 
            }
        } 
        return Math.round(costoPasaje*100.0)/100.0;
    }
    
    public double getCostoTraslado(boolean menor, double precioBase) {
        double costoTraslado = 0.0;
        if (traslado) {
            costoTraslado = precioBase * 0.01;
            if (menor) {
            costoTraslado *= 0.5; 
            }
        }
        return Math.round(costoTraslado*100.0)/100.0;
    }
    
    public double getRecargoTemporada(double costoBase) {
        double recargo = 0.0;

        if ("alta".equals(temporada)) {
            recargo = costoBase * 0.30;  
        } else if ("media".equals(temporada)) {
            recargo = costoBase * 0.15;  
        } else if ("baja".equals(temporada)) {
            recargo = 0.0; 
        }
    return Math.round(recargo*100.0)/100.0;
    }
    
    
    public void agregarTurista(Turista turista){
        if (turistas == null) {
            turistas = new ArrayList<>();
        }
        turistas.add(turista);
    }
    
    public void eliminarTurista(Turista turista){
        if (turistas == null) {
            turistas = new ArrayList<>();
        }
        turistas.remove(turista);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "codigoPaquete=" + codigoPaquete + 
                ", origen=" + origen +
                ", destino=" + destino +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", temporada=" + temporada +
                ", estadia=" + estadia +
                ", boleto=" + boleto +
                ", pension=" + pension +
                ", traslado=" + traslado +
                ", turistas=" + turistas +
                ", montoFinal=" + montoFinal +
                ", estado=" + estado + '}';
    }
  
} //cierre de Clase Paquete
    
   
