
public class Jugador {

	
	private String nombre;
	private static int contId;
	private String sala;
	private int id;
	private String objetivoObjeto;
	private String objetivoSala;
	//private String memoriaPosiciones[];
	// String memoriaObjetos[];
	
	
	public Jugador(String nombre, String sala) {
		setNombre(nombre);
		setSala(sala);
		setId(contId);
		
		contId++;
		GestorPartida.instanciarJugador(this);
	}
	
	public void comprobarObjetivos() {
		
	}
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	


	public String getSala() {
		return sala;
	}



	public void setSala(String sala) {
		this.sala = sala;
	}





	public String getObjetivoObjeto() {
		return objetivoObjeto;
	}





	public void setObjetivoObjeto(String objetivoObjeto) {
		this.objetivoObjeto = objetivoObjeto;
	}





	public String getObjetivoSala() {
		return objetivoSala;
	}





	public void setObjetivoSala(String objetivoSala) {
		this.objetivoSala = objetivoSala;
	}

	public static int getContId() {
		return contId;
	}

	public static void setContId(int contId) {
		Jugador.contId = contId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
