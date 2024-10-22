
package gp8_traveltogether.vistas;

import gp8_traveltogether.entidades.Ciudad;
import gp8_traveltogether.persistencia.CiudadData;


public class GP8_TravelTogether {

  
    public static void main(String[] args) {
        //Ciudad ciudad = new Ciudad ("San Rafael", true);
        
        CiudadData cData= new CiudadData();
        
        //cData.agregarCiudad(ciudad);
        //cData.bajaLogicaCiudad(1);
        //cData.altaLogicaCiudad(1);
        
        for (Ciudad city: cData.mostrarCiudades()){
            System.out.println(city.toString());
        }
    }
    
}
