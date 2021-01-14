import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaFicheros {
	
	//AQUI SEPARAMOS LAS STRING DE LAS LOCALIZACIONES, Y DE SUS VECINAS, Y LAS ALMACENAMOS EN LAS LOCALIZACIONES
	public static void creadorLocalizaciones(String Localizacion) {
		try {
			if(Localizacion.indexOf("(")!= -1 && Localizacion.indexOf(")")!= -1) {
				String nombre = Localizacion.substring(0, Localizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
				String vecinos = Localizacion.substring(Localizacion.indexOf("(")+1,Localizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
				String vecinosArray[] = vecinos.split(", "); // los separo por cada ", " para sacar lsolo el nombre de las localizaciones
				Sala sala = new Sala(nombre, vecinosArray); //creo la localizacion, instaciandola en el gestor de partida
			}else {
				throw new ExcepcionesFich("Error en el formato de entrada de datos de Localizacion\nEjemplo correcto: 'Jardin(Comedor, Salon)'");
			}
		}catch(ExcepcionesFich e) {
			e.printStackTrace();
		}
	}
	

	//SEPARAMOS LOS PERSONAJES DE SU LOCALIZACION, E INSTANCIAMOS PERSONAJES CON NOMBRE Y LOCALIZACION
	public static void creadorPersonajes(String Personaje) {
		boolean localizacionExiste = false;
		try {
			if(Personaje.indexOf("(")!= -1 && Personaje.indexOf(")")!= -1) {
				String nombre = Personaje.substring(0, Personaje.indexOf("("));	
				String sala = Personaje.substring(Personaje.indexOf("(")+1,Personaje.indexOf(")"));
				for(int i = 0; i <  GestorPartida.getContSalas(); i++) {
					if(sala.equalsIgnoreCase(GestorPartida.getSalas()[i].getNombre())) {
						localizacionExiste = true;
						break;
					}
				}
				if(localizacionExiste) {
					if(GestorPartida.getContJugadores() <1) {
						GestorPartida.getJugadores()[GestorPartida.getContJugadores()] = new Usuario(nombre, sala);
						
					}else {
						GestorPartida.getJugadores()[GestorPartida.getContJugadores()] = new IA(nombre, sala);
						
					}
				}else {
					throw new ExcepcionesFich("La sala donde se quiere instanciar un jugador, no existe");
				}
				
			}else {
				throw new ExcepcionesFich("Error en el formato de entrada de datos de Personaje\nEjemplo correcto: 'Erick(Jardin)'");
			}
		}catch(ExcepcionesFich e) {
			e.printStackTrace();
		}
		
	}
	

	//SEPARAMOS LOS OBJETOS Y SU LOCALIZACION/PORTADOR 			PD: SE IDENTIFICARA SI ES LOCALIZACION O PORTADOR DENTRO DE LA CLASE OBJETO
	public static void creadorObjetos(String Objeto) {
		try {
			if(Objeto.indexOf("(")!= -1 && Objeto.indexOf(")")!= -1) {	
				String nombre = Objeto.substring(0, Objeto.indexOf("("));
				String localizacion = Objeto.substring(Objeto.indexOf("(")+1,Objeto.indexOf(")")); 
				comparadorObjetos(nombre, localizacion);
			}
			else {
				throw new ExcepcionesFich("Error en el formato de entrada de datos de Objetos\nEjemplo correcto: 'Cajon(Jardin)'");
			}
		}catch(ExcepcionesFich e) {
			e.printStackTrace();
		}
		//Objetos object = new Objetos(nombre, localizacion);
	}

	public static void comparadorObjetos(String nombre, String localizacion) {
		boolean objetoExiste = false;
		
		try{
			
			for(int i = 0; i <  GestorPartida.getContSalas(); i++) {
				if(localizacion.equalsIgnoreCase(GestorPartida.getSalas()[i].getNombre())) {
					GestorPartida.getObjetoSala()[GestorPartida.getContObjetosSala()] = new ObjetoSala(nombre, GestorPartida.getSalas()[i]);
					GestorPartida.setContObjetosSala(GestorPartida.getContObjetosSala()+1);
					objetoExiste = true;
					break;
				}
			}
			for(int i = 0; i < GestorPartida.getContJugadores() && objetoExiste == false; i++) {
				if(localizacion.equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
					ObjetoJugador objeto = new ObjetoJugador(nombre, GestorPartida.getJugadores()[i]);
					GestorPartida.getObjetoJugador()[GestorPartida.getContObjetosJugador()] = new ObjetoJugador(nombre, GestorPartida.getJugadores()[i]);
					GestorPartida.setContObjetosJugador(GestorPartida.getContObjetosJugador()+1);
					objetoExiste = true;
					break;
				}
			}
			if(objetoExiste == false) {
				throw new ExcepcionesFich("No existe el localizacion donde se quiere instanciar el objeto");
			}
				
		}catch(ExcepcionesFich e) {
				e.printStackTrace();
		}
			
	}
		
		
	
	
	//SEPARAMOS AL PORTADOR DE LA LOCALIZACION
	public static void crearObjetivosLocalizacion(String ObjetivoLocalizacion) {
		boolean localizacionExiste = false;
		boolean jugadorExiste = false;
		try {
			if(ObjetivoLocalizacion.indexOf("(")!= -1 && ObjetivoLocalizacion.indexOf(")")!= -1) {	
				String nombre = ObjetivoLocalizacion.substring(0, ObjetivoLocalizacion.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
				String localizacion = ObjetivoLocalizacion.substring(ObjetivoLocalizacion.indexOf("(")+1,ObjetivoLocalizacion.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
				for(int i = 0; i <  GestorPartida.getContSalas(); i++) {
					if(localizacion.equalsIgnoreCase(GestorPartida.getSalas()[i].getNombre())) {
						localizacionExiste = true;
						break;
					}
				}
				for(int i = 0; i < GestorPartida.getContJugadores() && localizacionExiste == true; i++) {
					if(nombre.equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						jugadorExiste = true;
						break;
					}
				}
				if(jugadorExiste ==  true && localizacionExiste == true) {
					for(int i = 0; GestorPartida.getJugadores()[i] != null; i++) {
						if(GestorPartida.getJugadores()[i].getNombre().equalsIgnoreCase(nombre)) {
							GestorPartida.getJugadores()[i].setObjetivoSala(localizacion);
						}
					}
				}
				else if(jugadorExiste ==  false) {
					throw new ExcepcionesFich("Error el jugador al que se le asigna el objetivo no existe");
				}
				else if(localizacionExiste == false) {
					throw new ExcepcionesFich("Error la sala que se le asigna como objetivo a un jugador no existe");
				}
			}else {
				throw new ExcepcionesFich("La localizacion que se esta asigando como objetivo a un jugador, no existe");
			}
		}catch(ExcepcionesFich e) {
			e.printStackTrace();
		}
		
	}

	//SEPARAMOS LOS OBJETOS DE SU PORTADOR (EN LOS OBJETIVOS)
	public static void crearObjetivosObjetos(String ObjetivoObjeto) {
		boolean objetoExiste = false;
		boolean jugadorExiste = false;
		
		if(ObjetivoObjeto.indexOf("(")!= -1) {
			try {
				String objeto = ObjetivoObjeto.substring(0, ObjetivoObjeto.indexOf("("));	//Saco el nombre de la localizacion, leyendo solo hasta el (
				String portador = ObjetivoObjeto.substring(ObjetivoObjeto.indexOf("(")+1,ObjetivoObjeto.indexOf(")")); //saco un string con las localizaciones vecinas, leyendo desde el ( hasta el ) sin incluir
				for(int i = 0; i <  GestorPartida.getContObjetosJugador(); i++) {
					if(objeto.equalsIgnoreCase(GestorPartida.getObjetoJugador()[i].getNombreObjeto())) {
						objetoExiste = true;
						break;
					}
				}
				for(int i = 0; i <  GestorPartida.getContObjetosSala(); i++) {
					if(objeto.equalsIgnoreCase(GestorPartida.getObjetoSala()[i].getNombreObjeto())) {
						objetoExiste = true;
						break;
					}
				}
				
				for(int i = 0; i < GestorPartida.getContJugadores() && objetoExiste == true; i++) {
					if(portador.equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						jugadorExiste = true;
						break;
					}
				}
				for(int i = 0; GestorPartida.getJugadores()[i] != null; i++) {
					if(GestorPartida.getJugadores()[i].getNombre().equalsIgnoreCase(portador)) {
						GestorPartida.getJugadores()[i].setObjetivoObjeto(objeto);
					}
				}
				if(!objetoExiste) {
					throw new ExcepcionesFich("Error el objeto que se le asigna como objetivo no existe");
				}
				if(!jugadorExiste) {
					throw new ExcepcionesFich("Error el jugador al que se le asigna el objetivo no existe");
				}
			}catch(ExcepcionesFich e) {
				e.printStackTrace();
			}
		}	
	}
	
	public static void leerTodo(String archivo) throws FileNotFoundException, IOException {
        String cadena;
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

