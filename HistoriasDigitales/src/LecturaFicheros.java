import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaFicheros {

	//AQUI SEPARAMOS LAS STRING DE LAS LOCALIZACIONES, Y DE SUS VECINAS, Y LAS ALMACENAMOS EN LAS LOCALIZACIONES
	public static void creadorLocalizaciones(String Localizacion) {
		String nombre = Localizacion.substring(0, Localizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String vecinos = Localizacion.substring(Localizacion.indexOf("(")+1,Localizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		String vecinosArray[] = vecinos.split(", "); // los separo por cada ", " para sacar lsolo el nombre de las localizaciones
		Localizaciones zona = new Localizaciones(nombre, vecinosArray); //creo la localizacion, instaciandola en el gestor de partida
	}
	

	//SEPARAMOS LOS PERSONAJES DE SU LOCALIZACION, E INSTANCIAMOS PERSONAJES CON NOMBRE Y LOCALIZACION
	public static void creadorPersonajes(String Personaje) {
		String nombre = Personaje.substring(0, Personaje.indexOf("("));	
		String vecinos = Personaje.substring(Personaje.indexOf("(")+1,Personaje.indexOf(")"));
		Personajes persona = new Personajes(nombre, vecinos); 
	}
	

	//SEPARAMOS LOS OBJETOS Y SU LOCALIZACION/PORTADOR 			PD: SE IDENTIFICARA SI ES LOCALIZACION O PORTADOR DENTRO DE LA CLASE OBJETO
	public static void creadorObjetos(String Objeto) {
		String nombre = Objeto.substring(0, Objeto.indexOf("("));
		String perLoc = Objeto.substring(Objeto.indexOf("(")+1,Objeto.indexOf(")")); 
		Objetos object = new Objetos(nombre, perLoc);
	}

	//SEPARAMOS AL PORTADOR DE LA LOCALIZACION
	public static void crearObjetivosLocalizacion(String ObjetivoLocalizacion) {
		String nombre = ObjetivoLocalizacion.substring(0, ObjetivoLocalizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String localizacion = ObjetivoLocalizacion.substring(ObjetivoLocalizacion.indexOf("(")+1,ObjetivoLocalizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
			if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(nombre)) {
				GestorPartida.jugadores[i].objetivo.setLocalizacion(localizacion);
			}
		}
		
		
	}

	//SEPARAMOS LOS OBJETOS DE SU PORTADOR (EN LOS OBJETIVOS)
	public static void crearObjetivosObjetos(String ObjetivoObjeto) {
		if(ObjetivoObjeto.indexOf("(")!= -1) {
			String objeto = ObjetivoObjeto.substring(0, ObjetivoObjeto.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
			String portador = ObjetivoObjeto.substring(ObjetivoObjeto.indexOf("(")+1,ObjetivoObjeto.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
			for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
				if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(portador)) {
					GestorPartida.jugadores[i].objetivo.setObjeto(objeto);
				}
			}
		}
		
		
	}
	public static void leerTodo(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String[] prueba;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Personajes>")) {
        	       	creadorLocalizaciones(cadena);
        	cadena = b.readLine();
            
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Objetos>")) {
            creadorPersonajes(cadena);
            cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Localizacion Personajes>")) {
            creadorObjetos(cadena);
            cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Posesion Objetos>")) {
        	
        	crearObjetivosLocalizacion(cadena);
            cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null ) {
        	//System.out.println(cadena);
        	crearObjetivosObjetos(cadena);
            cadena = b.readLine();
        }
        b.close();
    }
	
	//LLAMAMOS A LA FUNCION QUE LEE TODAS LAS PARTES
	
	public static void AllLecture(String route) throws FileNotFoundException, IOException {
		leerTodo(route);
	}
}
