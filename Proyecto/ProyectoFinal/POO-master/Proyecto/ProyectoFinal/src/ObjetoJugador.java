
public class ObjetoJugador extends Objetos{
	private Jugador jugador;
	
	
	public ObjetoJugador(String nombre, Jugador jugador) {
		super(nombre);
		setJugador(jugador);
		
	}


	public Jugador getJugador() {
		return jugador;
	}


	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	
}
