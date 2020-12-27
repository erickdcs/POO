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
	private static int rondas;
	public static void instanciarSala(Sala sala) {
		salas[contSalas] = sala;
		contSalas++;
	}
	
	public static void instanciarJugador(Jugador jugador) {
		jugadores[contSalas] = jugador;
		contJugadores++;
	}

	public static void gestorRondas() {
		//int turnos =0;
		Scanner eleccion = new Scanner(System.in);
		while(true) {
			for(int i =0; i < GestorPartida.getContJugadores(); i++) {
				System.out.println("Turno del jugador: " + GestorPartida.getJugadores()[i]);
				
				System.out.println("Elige una accion: ");
				//Moverse
				System.out.println("1- Cambiar de Sala");
				
				//Coger objeto
				if(objetosEnSala(GestorPartida.getJugadores()[i]) && !objetosEnJugador(GestorPartida.getJugadores()[i])) {
					
				}
				//Dejar objeto
				if(objetosEnJugador(GestorPartida.getJugadores()[i])) {
					
				}
				//Pedir objeto
				if(jugadorEnSala(GestorPartida.getJugadores()[i])) {
					
				}
				//Dar objeto
				if(dar(GestorPartida.getJugadores()[i])) {
					
				}
				//Nada
				System.out.println("6- Pasar");
				
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
			if(GestorPartida.getObjetoSala()[i].getNombre().equalsIgnoreCase(jugador.getSala())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
	}
	public static boolean objetosEnJugador(Jugador jugador) {
		boolean hayObjetos =false;
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getNombre().equalsIgnoreCase(jugador.getNombre())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
	}
	public static boolean jugadorEnSala(Jugador jugador) {
		boolean hayJugador = false;
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getId() != jugador.getId() && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				hayJugador = true;
			}
			
		}
		return hayJugador;
	}
	public static boolean dar(Jugador jugador) {
		boolean puedeDar = false;
		boolean hayJugador = jugadorEnSala(jugador);
		
		
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
			if(GestorPartida.getObjetoJugador()[i].getNombre().equalsIgnoreCase(jugador.getNombre())) {
				return GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		return "";
	}
	
	
	public static void imprimirSala(Jugador jugador) {
		System.out.println("Sala: " + jugador.getSala());
		System.out.printf("Objetos en la sala: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getNombre().equalsIgnoreCase(jugador.getSala())) {
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
			if(GestorPartida.getObjetoJugador()[i].getNombre().equalsIgnoreCase(jugador.getNombre())) {
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
					if(GestorPartida.getObjetoJugador()[j].getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						System.out.printf("%s", GestorPartida.getObjetoJugador()[j].getNombreObjeto());
					}
				}
				System.out.printf("\n");
			}
			
		}
	}
	public static String[] verSalasVecinas(int a) {
		int sala =0;
		int b =a;
		String[] salasVecinas;
		//System.out.println(GestorPartida.getJugadores()[b].getNombre());
		//System.out.printf("Esta en:\n");
		//System.out.println(GestorPartida.getJugadores()[b].getSala());
		for( sala = 0;sala< GestorPartida.getContSalas(); sala++) {
			if(GestorPartida.getJugadores()[b].getSala().equalsIgnoreCase(GestorPartida.getSalas()[sala].getNombre() )) {
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
	
	

}
