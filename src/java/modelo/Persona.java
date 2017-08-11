/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Rules.Format;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Ivan Peters
 */
public class Persona {
      public static boolean validarRut(String rut){
        int dvR,dvT,suma=0;
        int[] serie = {2,3,4,5,6,7};
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        dvR = Integer.valueOf(rut.substring(rut.length()-1));
        for(int i = rut.length()-2;i>=0; i--){
            suma +=  Integer.valueOf(rut.substring(i, i+1))*serie[(rut.length()-2-i)%6];
        }
        dvT=11-suma%11;
        if(dvT == dvR){
            return true;
        }else{
            return false;
        }
    }
    public static String agregaDigitoVerificador(int run){
        String rut = String.valueOf(run);
        
        if( rut.equals("") || rut == null){
            return "";
        }else{
            int dvT,suma=0;
            int[] serie = {2,3,4,5,6,7};
 
            for(int i = rut.length()-1;i>=0; i--){ 
               suma +=  Integer.valueOf(rut.substring(i, i+1))*serie[(rut.length()-1-i)%6];
            }
            dvT=11-suma%11;

            String dvString ="";
            switch (dvT) {
                case 10:
                    dvString = "K";
                    break;
                case 11:
                    dvString ="0";
                    break;
                default:
                    dvString = String.valueOf(dvT);
                    break;
            }
            return Format.separaMiles( run )+"-"+dvString;
        }
    }

    public static int rutNumerico(String rut){
        if(rut.equals("")){
            return 0;
        }else{
            rut = rut.replace(".", "");
            String[] ruts = rut.split("-");
            if(ruts.length>0){
                return  Integer.parseInt( ruts[0].toString() );
            }else{
                return Integer.parseInt( rut );
            }
            
        }
      
    }
    
  
}
