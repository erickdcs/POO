import java.io.FileNotFoundException;
import java.io.IOException;

public class GestorPartida {
	private static Sala salas[] = new Sala [10];
	private static int contSalas;
	private static Jugador jugadores[] = new Jugador[10];
	private static int contJugadores;
		
	private static ObjetoSala objetoSala[] = new ObjetoSala[10];
	private static int contObjetosSala;
	private static ObjetoJugador objetoJugador[] = new ObjetoJugador[10];
	private static int contObjetosJugador; 
	
	private static int jugadorActivo;
	private static int rondas;
	
	private static GUI interfaz;
	
	public static void crearPartida() throws FileNotFoundException, IOException {
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		
		int jugadorPersona = (int) (Math.random()*GestorPartida.getContJugadores());	
		GestorPartida.getJugadores()[jugadorPersona].setIA(false);	
		interfaz = new GUI(jugadorPersona);		
	}
	
	public static void instanciarSala(Sala sala) {
		salas[contSalas] = sala;
		contSalas++;
	}
	
	public static void instanciarJugador(Jugador jugador) {
		jugadores[contSalas] = jugador;
		contJugadores++;
	}
	
	public static void instanciarCreencias() {
		for(int i =0; i < GestorPartida.getContJugadores(); i++) {
			GestorPartida.jugadores[i].setCreencias(new  Creencias(GestorPartida.jugadores,  GestorPartida.objetoJugador, GestorPartida.objetoSala, i)); 
		}
	}
	
	public static void actualizarCreencias(int id) {
		int j =0;		
		//Actualizamos las creecias del jugador 
		int i =0;
		for(j =0; j < GestorPartida.getContJugadores();j++) {
			
			
			if(GestorPartida.getJugadores()[j].getId() != id) {
				//Filtramos que los jugadores que vayan a actualizar sus creencias sean los de la sala del jugador que acaba de realizar una accion, excepto las suyas propias			
				if(GestorPartida.getJugadores()[j].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala()) &&  GestorPartida.getJugadores()[j].getId() != id) {
					//Actualizamos la sala por si el jugador se acaba de mover
					GestorPartida.getJugadores()[id].getCreencias().setSalaPersona(GestorPartida.getJugadores()[j].getSala(), i);
					
					
				}
				i++;
			}
		}
			
		for(j = 0;  j < GestorPartida.getContObjetosJugador();j++) {
			//Comprobamos que el jugador coincida con un poseedor de un objeto
			if(GestorPartida.getObjetoJugador()[j].getJugador().getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				for(int x =0; GestorPartida.getJugadores()[id].getCreencias().getNombreObjeto()[x]!=null ; x++) {
					//Cogemos la posicion del objeto donde esta el objeto dentro de las creencias
					if(GestorPartida.getJugadores()[id].getCreencias().getNombreObjeto()[x].equalsIgnoreCase(GestorPartida.getObjetoJugador()[j].getNombreObjeto())) {
						GestorPartida.getJugadores()[id].getCreencias().setLugarObjeto(GestorPartida.getObjetoJugador()[j].getJugador().getNombre(), x);
					}
					
				}
			}
		}
		
		for(j = 0;  j < GestorPartida.getContObjetosSala();j++) {
			//Comprobamos si hay objetos en la sala del jugador
			if(GestorPartida.getObjetoSala()[j].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				for(int x =0; GestorPartida.getJugadores()[id].getCreencias().getNombreObjeto()[x]!=null ; x++) {
					//Cogemos la posicion del objeto donde esta el objeto dentro de las creencias
					if(GestorPartida.getJugadores()[id].getCreencias().getNombreObjeto()[x].equalsIgnoreCase(GestorPartida.getObjetoSala()[j].getNombreObjeto())) {
						GestorPartida.getJugadores()[id].getCreencias().setLugarObjeto(GestorPartida.getObjetoSala()[j].getSala().getNombre(), x);
					}
						
				}
			}
		}
		
		
	}

	public static boolean jugadorEnSala(int id) {
		boolean hayJugador = false;
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getId() != id && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				hayJugador = true;
			}
			
		}
		return hayJugador;
	}

	public static String[] verSalasVecinas(int a) {
		int sala =0;
		String[] salasVecinas;
		for( sala = 0;sala< GestorPartida.getContSalas(); sala++) {
			if(GestorPartida.getJugadores()[a].getSala().equalsIgnoreCase(GestorPartida.getSalas()[sala].getNombre() )) {
				break;
			}
		}
		salasVecinas=GestorPartida.getSalas()[sala].getSalasVecinas();
		return salasVecinas;
	}

	public static Sala[] getSalas() {
		return salas;
	}

	public static void setSalas(Sala[] salas) {
		GestorPartida.salas = salas;
	}

	public static int getContSalas() {
		return contSalas;
	}

	public static void setContSalas(int contSalas) {
		GestorPartida.contSalas = contSalas;
	}

	public static Jugador[] getJugadores() {
		return jugadores;
	}

	public static void setJugadores(Jugador[] jugadores) {
		GestorPartida.jugadores = jugadores;
	}

	public static int getContJugadores() {
		return contJugadores;
	}

	public static void setContJugadores(int contJugadores) {
		GestorPartida.contJugadores = contJugadores;
	}

	public static ObjetoSala[] getObjetoSala() {
		return objetoSala;
	}

	public static void setObjetoSala(ObjetoSala objetoSala[]) {
		GestorPartida.objetoSala = objetoSala;
	}

	public static ObjetoJugador[] getObjetoJugador() {
		return objetoJugador;
	}

	public static void setObjetoJugador(ObjetoJugador objetoJugador[]) {
		GestorPartida.objetoJugador = objetoJugador;
	}

	public static int getContObjetosSala() {
		return contObjetosSala;
	}

	public static void setContObjetosSala(int contObjetosSala) {
		GestorPartida.contObjetosSala = contObjetosSala;
	}

	public static int getContObjetosJugador() {
		return contObjetosJugador;
	}

	public static void setContObjetosJugador(int contObjetosJugador) {
		GestorPartida.contObjetosJugador = contObjetosJugador;
	}

	public static int getJugadorActivo() {
		return jugadorActivo;
	}

	public static void setJugadorActivo(int jugadorActivo) {
		GestorPartida.jugadorActivo = jugadorActivo;
	}

	public static int getRondas() {
		return rondas;
	}

	public static void setRondas(int rondas) {
		GestorPartida.rondas = rondas;
	}

	public static GUI getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(GUI interfaz) {
		GestorPartida.interfaz = interfaz;
	}
}