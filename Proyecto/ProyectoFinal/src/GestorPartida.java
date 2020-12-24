
public class GestorPartida {
	private static Sala salas[] = new Sala [10];
	private static int contSalas;
	private static Jugador jugadores[] = new Jugador[10];
	private static int contJugadores;
	private static ObjetoSala objetoSala[] = new ObjetoSala[10];
	private static int contObjetosSala;
	private static ObjetoJugador objetoJugador[] = new ObjetoJugador[10];
	private static int contObjetosJugador;
	
	public static void instanciarSala(Sala sala) {
		salas[contSalas] = sala;
		contSalas++;
	}
	
	public static void instanciarJugador(Jugador jugador) {
		jugadores[contSalas] = jugador;
		contJugadores++;
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
