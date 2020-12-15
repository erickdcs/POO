
public class ObjetoJugador extends Jugador{
	private String nombre;
	
	
	public ObjetoJugador(String nombre, Jugador jugador) {
		super(jugador.getNombre(), jugador.getSala());
		
		setNombreObjeto(nombre);
	}

	
	
	
	
	public String getNombreObjeto() {
		return nombre;
	}

	public void setNombreObjeto(String nombre) {
		this.nombre = nombre;
	}
}
