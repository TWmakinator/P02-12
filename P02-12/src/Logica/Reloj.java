package Logica;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Reloj {
	private String hora;
	private String minuto;
	private String segundo;
	private int meridiem;
	
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMinuto() {
		return minuto;
	}
	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	public String getSegundo() {
		return segundo;
	}
	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
	public int getMeridiem() {
		return meridiem;
	}
	public void setMeridiem(int meridiem) {
		this.meridiem = meridiem;
	}

	
	public void establecerHora(){
		 
        int h;
        int m;
        int s;
 
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
 
        calendario.setTime(fechaHoraActual);
 
        meridiem = calendario.get(Calendar.AM_PM);
 
        if(meridiem == 1){
            h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            if(h > 9){
                this.hora = h + "";
            }else
                this.hora = "0" + h;
        }else{
            h = calendario.get(Calendar.HOUR_OF_DAY);
            if(h > 9){
                this.hora = h + "";
            }else{
                this.hora = "0" + h;
            }
        }
 
        m = calendario.get(Calendar.MINUTE);
        if(m > 9){
            minuto = m + "";
        }else{
            minuto = "0" + m;
        }
 
        s = calendario.get(Calendar.SECOND);
        if(s > 9){
            segundo = s + "";
        }else{
            segundo = "0" + s;
        }
 
    }
}
