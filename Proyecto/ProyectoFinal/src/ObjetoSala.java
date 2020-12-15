
public class ObjetoSala extends Sala{
	private String nombre;
	
	
	public ObjetoSala(String nombre, Sala salas) {
		super(salas.getNombre(), salas.getSalasVecinas());
		setNombreObjeto(nombre);
	}

	
	
	
	
	public String getNombreObjeto() {
		return nombre;
	}

	public void setNombreObjeto(String nombre) {
		this.nombre = nombre;
	}

	
}
