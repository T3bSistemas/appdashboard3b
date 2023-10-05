package appdashboard3b.beans;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Regiones {
	Map<String, Region> regiones =  null;
	
	public Map<String, Region> getRegiones() {
		regiones =Stream.of(
		        new AbstractMap.SimpleEntry<String, Region>( "1000" ,new Region("1000", "Cuatitlan", 	"192.168.10.7", 	"2638", "abastex")), 
		        new AbstractMap.SimpleEntry<String, Region>( "1001" ,new Region("1001", "Reyes", 		"192.168.20.7", 	"2638", "abastex_reyes")), 
		        new AbstractMap.SimpleEntry<String, Region>( "1002" ,new Region("1002", "Barrientos", 	"192.168.30.7", 	"2638", "abastex_barrientos")), 
		        new AbstractMap.SimpleEntry<String, Region>( "1003" ,new Region("1003", "Metepec", 		"192.168.40.7", 	"2638", "abastex_metepec")),
		        new AbstractMap.SimpleEntry<String, Region>( "1004" ,new Region("1004", "Yautepec", 	"192.168.50.7", 	"2638", "abastex_yautepec")),
		        new AbstractMap.SimpleEntry<String, Region>( "1005" ,new Region("1005", "Texcoco", 		"192.168.60.7", 	"2638", "abastex_texcoco")),
		        new AbstractMap.SimpleEntry<String, Region>( "1006" ,new Region("1006", "Tlahuac", 		"192.168.70.7", 	"2638", "abastex_tlahuac")),
		        new AbstractMap.SimpleEntry<String, Region>( "1007" ,new Region("1007", "Celaya", 		"192.168.80.7", 	"2638", "abastex_celaya")),
		        new AbstractMap.SimpleEntry<String, Region>( "1008" ,new Region("1008", "Puebla", 		"192.168.90.7", 	"2638", "abastex_puebla")),
		        new AbstractMap.SimpleEntry<String, Region>( "1009" ,new Region("1009", "Ecatepec", 	"192.168.100.7",	"2638", "abastex_ecatepec")),
		        new AbstractMap.SimpleEntry<String, Region>( "1010" ,new Region("1010", "Tulancingo",	"192.168.110.7", 	"2638", "abastex_tulancingo")),
		        new AbstractMap.SimpleEntry<String, Region>( "1011" ,new Region("1011", "Morelia", 		"192.168.120.7", 	"2638", "abastex_morelia")),
		        new AbstractMap.SimpleEntry<String, Region>( "1012" ,new Region("1012", "Vallejo", 		"192.168.130.7", 	"2638", "abastex_vallejo")),
		        new AbstractMap.SimpleEntry<String, Region>( "1014" ,new Region("1014", "Tultitlan",    "192.168.150.7", 	"2638", "abastex_tultitlan")),
		        new AbstractMap.SimpleEntry<String, Region>( "1015" ,new Region("1015", "Tepozotlan",    	"192.200.7.200", 	"2638", "abastex_tep")),
		        new AbstractMap.SimpleEntry<String, Region>( "1098" ,new Region("1098", "Yema",         "192.202.100.7", 	"2638", "yema_alm")),
		        new AbstractMap.SimpleEntry<String, Region>( "1099" ,new Region("1099", "Yema-Lab",     "192.200.7.60", 	"2638", "lab_alm_yema"))
		    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return regiones;
	}

	public void setRegiones(Map<String, Region> regiones) {
		this.regiones = regiones;
	}
}
