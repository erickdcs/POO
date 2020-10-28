import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaFicheros {
	public static void muestraLocalizaciones(String archivo) throws FileNotFoundException, IOException {
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
        
        b.close();
    }
	
	public static void creadorLocalizaciones(String Localizacion) {
		String aux = Localizacion.substring(0, Localizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String vecinos = Localizacion.substring(Localizacion.indexOf("(")+1,Localizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		String vecinosArray[] = vecinos.split(", "); // los separo por cada ", " para sacar lsolo el nombre de las localizaciones
		Localizaciones zona = new Localizaciones(aux, vecinosArray); //creo la localizacion, instaciandola en el gestor de partida
	}
	
	
	
	public static void muestraPersonajes(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        while(!cadena.equals("<Personajes>")) {
        	cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Objetos>")) {
            creadorPersonajes(cadena);
            cadena = b.readLine();
        }
        b.close();
    }
	public static void creadorPersonajes(String Personaje) {
		String aux = Personaje.substring(0, Personaje.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String vecinos = Personaje.substring(Personaje.indexOf("(")+1,Personaje.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		//String vecinosArray[] = vecinos.split(", "); // los separo por cada ", " para sacar lsolo el nombre de las localizaciones
		Personajes persona = new Personajes(aux, vecinos); //creo la localizacion, instaciandola en el gestor de partida
	}
	
	
	
	
	public static void muestraObjetos(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        while(!cadena.equals("<Objetos>")) {
        	cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Localizacion Personajes>")) {
            creadorObjetos(cadena);
            cadena = b.readLine();
        }
        b.close();
    }
	
	public static void creadorObjetos(String Objeto) {
		String aux = Objeto.substring(0, Objeto.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String vecinos = Objeto.substring(Objeto.indexOf("(")+1,Objeto.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		//String vecinosArray[] = vecinos.split(", "); // los separo por cada ", " para sacar lsolo el nombre de las localizaciones
		Objetos object = new Objetos(aux, vecinos); //creo la localizacion, instaciandola en el gestor de partida
	}
	
	
	
	public static void muestraObjetivoLocalizacion(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        while(!cadena.equals("<Localizacion Personajes>")) {
        	cadena = b.readLine();
        }
        cadena = b.readLine();
        while(cadena !=null && !cadena.equals("<Posesion Objetos>")) {
        	
        	crearObjetivosLocalizacion(cadena);
            cadena = b.readLine();
        }
        b.close();
    }
	
	public static void crearObjetivosLocalizacion(String ObjetivoLocalizacion) {
		String aux = ObjetivoLocalizacion.substring(0, ObjetivoLocalizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
		String portador = ObjetivoLocalizacion.substring(ObjetivoLocalizacion.indexOf("(")+1,ObjetivoLocalizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
		for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
			if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(aux)) {
				GestorPartida.jugadores[i].objetivo.setLocalizacion(portador);
			}
		}
		
		
	}
	
	
	
	public static void muestraObjetivosObjetos(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        while(!cadena.equals("<Posesion Objetos>")) {
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
	public static void crearObjetivosObjetos(String ObjetivoObjeto) {
		if(ObjetivoObjeto.indexOf("(")!= -1) {
			String aux = ObjetivoObjeto.substring(0, ObjetivoObjeto.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
			String portador = ObjetivoObjeto.substring(ObjetivoObjeto.indexOf("(")+1,ObjetivoObjeto.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
			for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
				if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(portador)) {
					GestorPartida.jugadores[i].objetivo.setObjeto(aux);
				}
			}
		}
		
		
	}
	public static void AllLecture(String route) throws FileNotFoundException, IOException {
		muestraLocalizaciones(route);
		muestraPersonajes(route);
		muestraObjetos(route);
		muestraObjetivoLocalizacion(route);
		muestraObjetivosObjetos(route);
		
	}
}
