package ec.edu.uce.erpmunicipal.util;

public class OrmUtil {

	
	public static String getMonthName(int month)
	{
		String name="";
		switch (month) {
		case 1:
			name="Enero";
			break;
		case 2:
			name="Febrero";
			break;
		case 3:
			name="Marzo";
			break;
		case 4:
			name="Abril";
			break;
		case 5:
			name="Mayo";
			break;
		case 6:
			name="Junio";
			break;
		case 7:
			name="Julio";
			break;
		case 8:
			name="Agosto";
			break;
		case 9:
			name="Septiembre";
			break;
		case 10:
			name="Octubre";
			break;
		case 11:
			name="Noviembre";
			break;
		case 12:
			name="Diciembre";
			break;
		}
		return name;
	}
	
}
