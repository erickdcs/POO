import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class GestorPartida {
	private static Sala salas[] = new Sala [10];
	private static int contSalas;
	private static Jugador jugadores[] = new Jugador[10];
	private static int contJugadores;
	
	
	private static ObjetoSala objetoSala[] = new ObjetoSala[10];
	private static int contObjetosSala;
	private static ObjetoJugador objetoJugador[] = new ObjetoJugador[10];
	private static int contObjetosJugador; 
	
	private static int contPasarTurnos = 0;
	
	private static int jugadorActivo;
	private static int rondas;
	
	private static GUI interfaz;
	
	public static void crearPartida() throws FileNotFoundException, IOException {
		LecturaFicheros.AllLecture("C:\\Users\\jorge\\pruebaPOO.txt");
		int jugadorPersona = 0;
				//(int) (Math.random()*GestorPartida.getContJugadores());		
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

	public static void hud(Jugador jugador) {
		imprimirObjetos(jugador);
		imprimirSala(jugador);
		imprimirSalasVecinas(jugador);
		imprimirPeticiones(jugador);
		imprimirJugadores(jugador);
	}
	public static void imprimirPeticiones(Jugador jugador) {
		for(int i =0; jugador.getPeticiones()[i]!=null; i++) {
			System.out.printf("Peticion de %s de %s\n", jugador.getPeticiones()[i].getJugadorPide(), jugador.getPeticiones()[i].getObjeto());
		}
		System.out.printf("\n");
	}
		
	public static boolean objetosEnSala(Jugador jugador) {
		boolean hayObjetos =false;
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
	}
	public static boolean objetosEnJugador(Jugador jugador) {
		boolean hayObjetos =false;
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
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
	public static void imprimirObjetosEnSala(Jugador jugador) {
		System.out.printf("Objetos en la sala: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getSala())) {
				System.out.printf("%s  ", GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
	}
	public static boolean dar(Jugador jugador) {
		boolean puedeDar = false;
		boolean hayJugador = jugadorEnSala(jugador.getId());
		
		
		if(hayJugador) {
			String []PersonasEnSala = jugadoresEnSala(jugador);
			for(int i =0; jugador.getPeticiones()[i]!=null;i++) {
				for(int j =0; j < PersonasEnSala.length; j++) {
					if(jugador.getPeticiones()[i].getJugadorPide().equalsIgnoreCase(PersonasEnSala[j])) {
						if(objetoDelJugador(jugador).equalsIgnoreCase(jugador.getPeticiones()[i].getObjeto())) {
							puedeDar = true;
						}
					}
				
				}
			}
		}
		
		return puedeDar;
	}
	public static String [] jugadoresEnSala(Jugador jugador) {
		String []personas = new String[GestorPartida.getContJugadores()];
		int contPersonas = 0;
		for(int i =0; i < GestorPartida.contJugadores; i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				personas[contPersonas] = GestorPartida.getJugadores()[i].getNombre();
				contPersonas++;
			}
		}
		return personas;
	}
	public static String objetoDelJugador(Jugador jugador) {
		
		for(int i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				return GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		return "";
	}
	
	
	public static void imprimirSala(Jugador jugador) {
		System.out.println("Sala: " + jugador.getSala());
		System.out.printf("Objetos en la sala: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
				System.out.printf("%s  ", GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
		System.out.printf("\n");
	}
	public static void imprimirSalasVecinas(Jugador jugador) {
		String salas[] =  verSalasVecinas(jugador.getId());
		System.out.printf("Salas vecinas: ");
		for(int i =0; i< salas.length; i++) {
			System.out.printf("%s  ", salas[i]);
		}
		System.out.printf("\n");
	}
	public static void imprimirObjetos(Jugador jugador) {
		System.out.printf("Objeto: ");
		for(int i =0; i < GestorPartida.contObjetosJugador; i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				System.out.printf("%s\t",GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			}
		}
		System.out.printf("\n");
	}
	public static void imprimirJugadores(Jugador jugador) {
		System.out.printf("Jugadores en la misma sala:\n");
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getId() != jugador.getId() && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				System.out.printf("%s\t\tObjetos: ",GestorPartida.getJugadores()[i].getNombre());
				for(int j =0; j< GestorPartida.getContObjetosJugador();j++) {
					if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						System.out.printf("%s", GestorPartida.getObjetoJugador()[j].getNombreObjeto());
					}
				}
				System.out.printf("\n");
			}
			
		}
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