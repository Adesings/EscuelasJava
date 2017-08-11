/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rules;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Ivan Peters
 */
public class Format {
    
    public static String separaMiles(int numero){
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
        
        
        return formateador.format(numero);
    }
}
