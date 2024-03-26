package appdashboard3b.utelerias;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class RT {
	private static TimeZone miZonaGMT=TimeZone.getTimeZone("GMT");
	private  static Calendar calendarioGMT=new GregorianCalendar(miZonaGMT);

	public RT() {
	
	}


    public String dateFechaToStringHoraDMYhms(Date fecha)
    {
      if(fecha==null)return "";
      return this.dateToString(fecha);
    }

    public static String dateFechaToStringHora(Date fecha)
    {
      if(fecha!=null) {
        SimpleDateFormat formatoSQL  = new SimpleDateFormat ("yyyyMMddHHmmss",new Locale("es","MX"));
        return formatoSQL.format(fecha);
      }else {
        return "";
      }
    }

	public static String horaToString(Date fecha){
		if(fecha!=null) {
			SimpleDateFormat formatoSQL  = new SimpleDateFormat ("HH:mm");
			return formatoSQL.format(fecha);
		}else {
			return "";
		}
	}

	 public String dateFechaToStringORA(Date fecha) {
	 if ( fecha!=null )    {
	     String lSFecha= dateFechaToStringHora(fecha) ;
	     return "TO_DATE('"+lSFecha+"','YYYYMMDDHH24MISS')";
	    }
	    else
	        return "NULL";
	
	 }

    
	public static int dateFechaToIntDay(Date fecha)
    {
        int day;
        calendarioGMT.setTime(fecha);
        day=calendarioGMT.get(Calendar.DATE);
        return day;
    }
	
    public static int dateFechaToIntMonth(Date fecha)
    {
        int month;
        calendarioGMT.setTime(fecha);
        month=calendarioGMT.get(Calendar.MONTH)+1;
        return month;
    }
    
	public static int dateFechaToIntYear(Date fecha){
	    int year;
	    calendarioGMT.setTime(fecha);
	    year=calendarioGMT.get(Calendar.YEAR);
	    return year;
	}

    public Date stringToDateTime(String pSFecha){
	    if (pSFecha!="" && pSFecha!=null ){
	       String myFecha = pSFecha.substring(6,8)+pSFecha.substring(4,6)+pSFecha.substring(0,4)+pSFecha.substring(8,14);
	       return this.stringToDate(myFecha);
	    }
	    return null;   
    }


    public boolean esFechaMayorOIgual(Date fecha1,Date fecha2){
	    boolean lbEsFechaMayor = false;
	    if (fecha1.getTime()>=fecha2.getTime() )
	    {
	       lbEsFechaMayor = true;
	    }
	    return lbEsFechaMayor;
    }

  public Timestamp sumaDiasTs(Timestamp pOFecha,int piDias)
	{
	  Timestamp fechaDias= new Timestamp((pOFecha.getTime()+ ((long)piDias)*(86400000L)));
	  return fechaDias;
	}
		
  public static Date sumaDias(Date pOFecha,int piDias)
  {
    Date fechaDias= new Date((pOFecha.getTime()+ ((long)piDias)*(86400000L)));
    return fechaDias;
  }
  
  public static Date restaDias(Date pOFecha,int piDias)
	{
	  Date fechaDias= new Date((pOFecha.getTime()- ((long)piDias)*(86400000L)));
	  return fechaDias;
	}
	
  public Date sumaHoras(Date pOFecha,double piHoras)
  {
    long myHoras = (long)(piHoras *(3600000L));
    Date fechaDias= new Date((pOFecha.getTime()+ myHoras));
    return fechaDias;
  }

    
	public static boolean esUltimoDiaMes(Date fecha)
    {
      boolean valor = false;
      
      Date myFecha =(Date) fecha.clone();
      int myDia, mySiguienteDia;

      myDia = dateFechaToIntDay(myFecha);
      myFecha = sumaDias(myFecha,1);
      mySiguienteDia = dateFechaToIntDay(myFecha);
      if (mySiguienteDia < myDia)
        valor = true;

      return valor;
    }
	
	public static int getUltimoDiaDelMes(int anio, int mes) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(anio, mes, 1); 		 
		
        return cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}
	

    public static String stringGetDDMMYYYYFromDate(Date fecha)
    {
        SimpleDateFormat formatoSQL  = new SimpleDateFormat ("HHmmssyyyyMMdd",new Locale("es","MX"));
        formatoSQL.setTimeZone(miZonaGMT);
        String lSFecha=formatoSQL.format(fecha);
        String lSFecha2=lSFecha.substring(12,14);
        lSFecha2+=lSFecha.substring(10,12);
        lSFecha2+=lSFecha.substring(6,10);
        return lSFecha2;
    }


  

  public static int obtieneDiferenciaDias(Date pOFecha1,Date pOFecha2)
  {
    int myDias = (int)((pOFecha1.getTime()-pOFecha2.getTime())/86400000L);
    return myDias;
  }

  public float diferenciaFechas(Date pOFecha1,Date pOFecha2,int aTipo)//throws SesionException
  {
    long  dif= ((pOFecha1.getTime()-pOFecha2.getTime()));
    float res=0;
    switch(aTipo)
    {
      case 2: 
        res=(dif/(1000*60*60*24));
      break;
      case 1:
        res=(dif/(1000*60*60));
      break;
      default:
        res= Float.NaN;
      break;
    }
    return res;
  }



    public String dateFechaToStringEspanol(Date fecha){
        DateFormat formatoFecha=DateFormat.getDateInstance(DateFormat.LONG,new Locale("es","MX"));
	    DateFormat formatoHora=DateFormat.getTimeInstance(DateFormat.MEDIUM,new Locale("es","MX"));
  		formatoFecha.setTimeZone(miZonaGMT);
		formatoHora.setTimeZone(miZonaGMT);

        String miFecha=formatoFecha.format(fecha)+" "+formatoHora.format(fecha);
        return miFecha;
        }

    public String dateToStringDDMMYYYYHHMM(Date pOFecha)
    {
      String lSFecha;
      if (pOFecha == null)
      {
        lSFecha="";
      }
      else
      {
        SimpleDateFormat formatoSQL=new SimpleDateFormat("dd/MM/yyyy HH:mm",new Locale("es","MX"));
        formatoSQL.setTimeZone(miZonaGMT);
        lSFecha=formatoSQL.format(pOFecha);
      }
      return lSFecha;
    }

    public String dateToStringDDMMYYYY(Date pOFecha)
    {
      String lSFecha;
      if (pOFecha == null)
      {
        lSFecha="";
      }
      else
      {
        SimpleDateFormat formatoSQL=new SimpleDateFormat("ddMMyyyy",new Locale("es","MX"));
        formatoSQL.setTimeZone(miZonaGMT);
        lSFecha=formatoSQL.format(pOFecha);
      }
      return lSFecha;
    }


	public Date getDateGregorianCalendar(Date aFecha)
	{
	  GregorianCalendar myCalendar = new GregorianCalendar();
	  myCalendar.setTime(aFecha);
	  myCalendar.setGregorianChange(aFecha);
	  return myCalendar.getTime();
	}




public String campoDateToString(String pNombreCampo)
{
    return "TO_CHAR("+pNombreCampo+",'YYYYMMDDHH24MISS')";
}


    public String dateToString(Date fecha)
    {
      if(fecha==null)return "";

      SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
      String stringDate = dateFormat.format(fecha);
      return stringDate;
    }

    public Date stringToDate(String aFechaDD_SS)
    {
      try
      {
        SimpleDateFormat myFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date myDate = myFormat.parse( aFechaDD_SS );
        return myDate;
      }
      catch(Exception e)
      {
      }
      return null;
    }

	public Date stringToDateTimeDD_MM_YYYY_HH_mm(String aFechaDD_SS)
	{
	  try
	  {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		myFormat.setLenient(false);
		Date myDate = myFormat.parse( aFechaDD_SS );
		return myDate;
	  }
	  catch(Exception e)
	  {
	  }
	  return null;
	}

	public static Date stringToDateTimeYYYY_MM_DD(String aFechaDD_SS)
	{
	  try
	  {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");		
		Date myDate = myFormat.parse(aFechaDD_SS);
		return myDate;
	  }
	  catch(Exception e)
	  {
	  }
	  return null;
	}
	
	public static Date stringToDateTimeDD_MM_YYYY(String aFechaDD_SS)
	{
	  try
	  {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");		
		Date myDate = myFormat.parse(aFechaDD_SS);
		return myDate;
	  }
	  catch(Exception e)
	  {
	  }
	  return null;
	}

	public Date retornaFechaCeroHoras(Date fecha){
		if(fecha==null)return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = dateFormat.format(fecha);
		stringDate += " 00:00:00";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		myFormat.setLenient(false);
		Date myDate = null;
		try {
			myDate = myFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}

	public Date retornaFecha24Horas(Date fecha){
		if(fecha==null)return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = dateFormat.format(fecha);
		stringDate += " 23:59:59";
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		myFormat.setLenient(false);
		Date myDate = null;
		try {
			myDate = myFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate;
	}


    public String dateToStringMascara(Date fecha)
    {
      if(fecha==null)return "";
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      String stringDate = dateFormat.format(fecha);
      return stringDate;
    }

	public String dateToStringDD_MM_YYYY(Date fecha){
	  if(fecha==null)return "";
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	  String stringDate = dateFormat.format(fecha);
	  return stringDate;
	}

	public String dateToStringRptCeroH(Date fecha){
	  if(fecha==null)return "";
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  String stringDate = dateFormat.format(fecha);
	  stringDate = "{ts '"+ stringDate +" 00:00:00'}";
	  return stringDate;
	}
	public String dateToStringRpt24H(Date fecha){
	  if(fecha==null)return "";
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  String stringDate = dateFormat.format(fecha);
	  stringDate = "{ts '"+ stringDate +" 23:59:59'}";
	  return stringDate;
	}
	
	public static String dateToStringYMD(Date fecha){
		  if(fecha==null)return "";
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  String stringDate = dateFormat.format(fecha);
		  return stringDate;
		}
	
	public static String dateToStringDMY(Date fecha){
		  if(fecha==null)return "";
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		  String stringDate = dateFormat.format(fecha);
		  return stringDate;
		}

	public static String formatoHoraReferidaA1970GMT(Date unaFecha){
		SimpleTimeZone st = new SimpleTimeZone(0, "GMT");

		java.text.SimpleDateFormat miS = new java.text.SimpleDateFormat("kk:mm");
		miS.setTimeZone(st);
		String miFS = miS.format(unaFecha);
		return miFS;
	}

	public static int getDayOfTheWeek(Date d){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);		
	}
	
	public String dateToStringDDMMYYYYHHMMss(Date pOFecha)
		{
		  String lSFecha;
		  if (pOFecha == null)
		  {
			lSFecha="";
		  }
		  else
		  {
			SimpleDateFormat formatoSQL=new SimpleDateFormat("ddMMyyyyHHmmss",new Locale("es","MX"));			
			formatoSQL.setTimeZone(TimeZone.getDefault());
			lSFecha=formatoSQL.format(pOFecha);
		  }
		  return lSFecha;
		}

	public String formatoFechaDB2ConHrs(Date fecha,String hrs){
	  if(fecha!=null) {
		SimpleDateFormat formatoSQL  = new SimpleDateFormat ("yyyy-MM-dd");
			return formatoSQL.format(fecha) + "-"+hrs+""+".00.00.000";
	  }
	  else {
		return "";
	  }
	}
}
