import java.util.Scanner;
public class Personajes {
	private String nombre;
	private String Localizacion;
	public Objetos objeto;
	public Objetivos objetivo = new Objetivos();
	private int characterNumber;
	Personajes creencias[] = new Personajes [10];
	
	private Peticiones petitions;
	private static int numTotalPlayers = 0;
	
	public Personajes(String nombre, String Localizacion) {
		this.setNombre(nombre);
		setNumTotalPlayers(getNumTotalPlayers() + 1);
		this.setCharacterNumber(getNumTotalPlayers());
		this.Localizacion = Localizacion;
		GestorPartida.rellenadorJugadores(this);
	}
		
	
	//Funcion para crear una peticion de un objeto
	public void PedirObjecto() {
		String nombreSeleccionado = nombreJugadorSeleccionado();	//Se el nombre del usuario al que le va a pedir el objeto
		int contadorJugador = 0;	//Contador para ver la posicion del jugador dentro del gestor de partida
		int contadorObjetos = 0;	//Contador para ver la posicion del objeto dentro del gestor de partida
		
		//Localizamos si existe el jugador, y en caso de existir, su posicion
		for(;GestorPartida.jugadores[contadorJugador] != null && GestorPartida.jugadores[contadorJugador].nombre.equalsIgnoreCase(nombreSeleccionado); contadorJugador++) {	
		}
		if(GestorPartida.jugadores[contadorJugador] != null) {
			if(GestorPartida.jugadores[contadorJugador].Localizacion.equalsIgnoreCase(this.Localizacion)) {
				String nombreObjeto = nombreObjetoSeleccionado();
				for(; GestorPartida.objects[contadorObjetos] != null && GestorPartida.objects[contadorObjetos].getNombre().equalsIgnoreCase(nombreObjeto); contadorObjetos++) {
				}
				if(GestorPartida.objects[contadorObjetos] != null ) {
					this.petitions.setNumJugador(nombreSeleccionado);
					this.petitions.setObjeto(nombreObjeto);
				}
				else {
					System.out.println("No existe un objeto con ese nombre");
				}
			}
			else {
				System.out.println("Ese jugador no esta en tu misma localizacion");
			}
		}
		else {
			System.out.println("No existe un jugador con ese nombre");
		}
	}
	
	public void darObjeto() {
		String nombreSeleccionado = nombreJugadorSeleccionado();
		String objectName = nombreObjetoSeleccionado();
		int contadorPersonajes = 0;
		if(this.objeto != null) {
			if(this.objeto.getNombre().equalsIgnoreCase(objectName)) {
				for(; GestorPartida.jugadores[contadorPersonajes] != null && !GestorPartida.jugadores[contadorPersonajes].getNombre().equalsIgnoreCase(nombreSeleccionado); contadorPersonajes++) {
				}
				if(GestorPartida.jugadores[contadorPersonajes] != null) {
					if(!GestorPartida.jugadores[contadorPersonajes].petitions.getNumJugador().equalsIgnoreCase(this.nombre)) {
						
						if(!GestorPartida.jugadores[contadorPersonajes].petitions.getObjeto().equalsIgnoreCase(objectName)){
							
							if(GestorPartida.jugadores[contadorPersonajes].Localizacion.equalsIgnoreCase(this.Localizacion)) {
								
								if(GestorPartida.jugadores[contadorPersonajes].objeto == null) {
									GestorPartida.jugadores[contadorPersonajes].objeto = this.objeto;
									this.objeto = null;
								}
								else {
									System.out.println("El jugador ya tiene un objeto");
								}
								
							}
							else {
								System.out.println("No estais en la misma posicion");
							}
							
						}
						else {
							System.out.println("No existe una peticion de ese usuario por ese objeto");
						}
						
					}
					else {
						System.out.println("No existe una peticion de ese usuario");
					}
					
				}
				else {
					System.out.println("Ese jugador no existe");
				}
			}
			else {
				System.out.println("No tienes ese objeto");
			}
		}
		else {
			System.out.println("No tienes ningun objeto");
		}
		int a =0;
	}
	
	
	
	
	public void CojerObjeto() {
		//String objetoAcoger = nombreObjetoSeleccionado();
		int contadorZonas = 0;
		for(; GestorPartida.zonas[contadorZonas]!= null  && !GestorPartida.zonas[contadorZonas].getNombre().equalsIgnoreCase(this.Localizacion);contadorZonas++) {
		}
		
		if(GestorPartida.zonas[contadorZonas].getObject() != null) {
			if(this.objeto != null) {
				this.objeto = GestorPartida.zonas[contadorZonas].getObject();
			}
			else {
				System.out.println("Ya tienes un objeto, no puedes tener 2");
			}
		}
		else {
			System.out.println("Error no hay objeto en la localizacion");
		}
	}
	
	
	
	
	public String nombreObjetoSeleccionado() {
		Scanner nameObject = new Scanner(System.in);
		System.out.println("Indica el nombre del objeto que quieres seleccionar");
		String nombreObjeto = nameObject.next();
		return nombreObjeto;
	}
	public String nombreJugadorSeleccionado() {
		Scanner namePlayer = new Scanner(System.in);
		System.out.println("Indica el nombre del jugador que quieres seleccionar");
		String nombreJugador = namePlayer.nextLine();
		return nombreJugador;
	}
	public int posJugadorSeleccionado() {
		Scanner posPlayer = new Scanner(System.in);
		System.out.println("Indica el numero de jugador que quieres seleccionar");
		int posJugador = posPlayer.nextInt();
		return posJugador;
	}
	
	public void numeroJugadores(Personajes allPlayers[]) {
		int i = 0;
		
	}
		
	
	//Nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// Numero del personaje, ej: personaje1 - personaje2
	public int getCharacterNumber() {
		return characterNumber;
	}
	public void setCharacterNumber(int characterNumber) {
		this.characterNumber = characterNumber;
	}
	public int getNumTotalPlayers() {
		return numTotalPlayers;
	}
	public void setNumTotalPlayers(int numTotalPlayers) {
		this.numTotalPlayers = numTotalPlayers;
	}
	public String getLocalizacion() {
		return Localizacion;
	}
	public void setLocalizacion(String localizacion) {
		Localizacion = localizacion;
	}
	
	public Objetos getObjeto() {
		return objeto;
	}
	public void setObjeto(Objetos objeto) {
		this.objeto = objeto;
	}
	public Objetivos getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Objetivos objetivos) {
		this.objetivo = objetivos;
	}
}


