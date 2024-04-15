package br.univesp.PJI1102024G02.helper;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rubens
 */
public class TimeHelper {
    
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    public static int[] getDiasDeTrabalho() {
        int[] dias = {2,3,4,5,6,7};
        return dias;
    }
    
    public static String getHorarioAlmoco() {
        return "13:00";
    }
    
    public static String[] getHorariosAtendimento() {
        String[] horarios = {
            "08:00","09:00","10:00","11:00",
            "12:00","13:00","14:00","15:00",
            "16:00","17:00","18:00"
        };
        
        return horarios;
    }
    
        public static int[] getHorariosAtendimentoInt() {
        int[] horarios = {
            8,9,10,11,12,13,14,15,16,17,18
        };
        
        return horarios;
    }
}
