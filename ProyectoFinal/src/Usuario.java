
public class Usuario extends Jugador{

	public Usuario(String nombre, String sala) {
		super(nombre, sala);
		GestorPartida.instanciarPersona(this);
	}

	//Retorna un int que resuelve la accionn que el usuario desea realizar
	public int dameAccion(String accion) {
		switch(accion) {
			case "Moverse": return 1;
			case "Coger": return 2;
			case "Dejar": return 3;
			case "Pedir": return 4;
			case "Dar": return 5;
			case "Info": return 0;
			case "Pasar": return 7;
		}
		return 0;
		
	}

}
