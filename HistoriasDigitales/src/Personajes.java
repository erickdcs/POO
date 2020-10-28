import java.util.Scanner;
public class Personajes {
	private String nombre;
	private int pos;
	private String Localizacion;
	public Objetos objeto;
	
	public Objetivos objetivo = new Objetivos();
	
	
	
	private int characterPos[];
	private int characterNumber;
	
	
	
	public Objetos objects[] = new Objetos[10];
	private Peticiones petitions[] = new Peticiones[10];
	private static int numTotalPlayers = 0;
	
	public Personajes(String nombre, int pos) {
		this.setNombre(nombre);
		this.setCharacterNumber(1);
		this.setPos(pos);
		setNumTotalPlayers(getNumTotalPlayers() + 1);
	}
	
	
	public Personajes(String nombre, String Localizacion) {
		this.setNombre(nombre);
		//this.setPos(pos);
		setNumTotalPlayers(getNumTotalPlayers() + 1);
		this.setCharacterNumber(getNumTotalPlayers());
		this.Localizacion = Localizacion;
		GestorPartida.rellenadorJugadores(this);
	}
	
	
	public Personajes(String nombre, int pos, int characterNumber, int characterPos[]){
		this.setNombre(nombre);
		this.setPos(pos);
		this.setCharacterNumber(characterNumber);
		this.setCharacterPos(characterPos);
		setNumTotalPlayers(getNumTotalPlayers() + 1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void PedirObjecto(Personajes allPlayers[]) {
		int posName = posJugadorSeleccionado();
		if(posName <= numTotalPlayers) {
			String objectName = nombreObjetoSeleccionado();
			System.out.println(allPlayers[posName-1].nombre + " me das el/la " + objectName);
			int i = 0;
			if(this.petitions == null) {
				
			}
			else {
				for(; this.petitions[i] != null ;i++) {
				}
			}
			this.petitions[i] = new Peticiones(objectName, posName);
		}
		else {
			System.out.println("No existe ese jugador");
		}
		
	}
	public void darObjeto(Personajes allPlayers[]) {
		int posName = posJugadorSeleccionado()-1;
		String objectName = nombreObjetoSeleccionado();
		int contadorPosObjeto = 0;
		int contadorPosObjeto2 = 0;
		if(allPlayers[posName] != null) {
			if(allPlayers[posName].pos == this.pos) {
				if(comprobarSiTienexObjeto(objectName) == true) {
					for (int i = 0; allPlayers[posName].petitions[i] != null;i++) {
						if(allPlayers[posName].petitions[i].getObjeto().equalsIgnoreCase(objectName) && allPlayers[posName].petitions[i].getNumJugador() == this.characterNumber) {
							for(;allPlayers[posName].objects[contadorPosObjeto] != null; contadorPosObjeto++) {
							}
							for(;!this.objects[contadorPosObjeto2].getNombre().equalsIgnoreCase(objectName);contadorPosObjeto2++) {
								
							}		
							allPlayers[posName].objects[contadorPosObjeto] = this.objects[contadorPosObjeto2];
							EliminarUnaPosArray(contadorPosObjeto2, this.objects);
							EliminarPeticiones(i,this.petitions);
						}
						else if(petitions[i+1] == null) {
							System.out.println("No existe una peticion de este objeto");
						}
						
					}
				}
				else {
					System.out.println("No posees ese objeto");
				}
			}
			else {
				System.out.println("No estais en la misma posicion");
			}
			
		}
		else {
			System.out.println("Este numero de usuario no existe en esta partida");
		}
		int a =0;
	}
	
	
	
	
	public void CojerObjeto(int pos, Localizaciones place) {
		String objetoAcoger = nombreObjetoSeleccionado();
		int contadorObjetos = 0;
		int contadorObjetosPlace = 0;
		int placePos = 0;
		for(contadorObjetos = 0; objects[contadorObjetos] != null;contadorObjetos++) {
		}
		for(contadorObjetosPlace = 0; place.getObject() != null ;contadorObjetosPlace++) {
			if(place.getObjectNom().equalsIgnoreCase(objetoAcoger)) {
				placePos = contadorObjetosPlace;
			}
		}
		this.objeto = place.getObject();
		//EliminarUnaPosArray(placePos, place.objects);
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
	
	public boolean comprobarSiTienexObjeto(String objeto) {
		int i = 0;
		boolean retorno = true;
		for(; (this.objects[i].getNombre() != null) && !this.objects[i].getNombre().equalsIgnoreCase(objeto);i++) {
			
		}
		if(this.objects[i] == null) {
			retorno = false;
		}

		return retorno;
		
	}
	public void EliminarUnaPosArray(int posEliminada, Objetos objects[]) {
		int i = posEliminada;
		for(; objects[i] != null; i++) {
			objects[i] = objects[i+1];
		}
	}
	
	public void EliminarPeticiones(int posEliminada, Peticiones petition[]) {
		int i = posEliminada;
		for(; petition[i] != null; i++) {
			petition[i] = petition[i+1];
		}
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
	//Posicion del personaje
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	// Array de posiciones de los jugadores
	public int[] getCharacterPos() {
		return characterPos;
	}
	public void setCharacterPos(int characterPos[]) {
		this.characterPos = characterPos;
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
