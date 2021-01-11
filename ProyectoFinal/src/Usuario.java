
public class Usuario extends Jugador{

	public Usuario(String nombre, String sala) {
		super(nombre, sala);
		GestorPartida.instanciarPersona(this);
	}

}
