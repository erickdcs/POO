
public class ObjetoSala extends Objetos{
	private Sala sala;
	
	
	public ObjetoSala(String nombre, Sala salas) {
		super(nombre);
		setSala(salas);
		
		
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}


	
}
