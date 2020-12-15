
public class Sala{
	private String nombre;
	private String salasVecinas[];
	
	public Sala(String nombre, String salasVecinas[]) {
		this.setNombre(nombre);
		this.setSalasVecinas(salasVecinas);
		GestorPartida.instanciarSala(this);
	}
	
	
	
	
	
	
	//Devolver 1 String
		
	//Nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//Objetos
	
	public String[] getSalasVecinas() {
		return salasVecinas;
	}


	public void setSalasVecinas(String[] salasVecinas) {
		this.salasVecinas = salasVecinas;
	}

}
