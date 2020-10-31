
public class Localizaciones {
	private String nombre;
	private String salasVecinas[];
	
	public Objetos objeto;
	
	public Localizaciones(String nombre, String salasVecinas[]) {
		this.setNombre(nombre);
		this.setSalasVecinas(salasVecinas);
		GestorPartida.rellenadorZonas(this);
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
	public Objetos getObject() {
		return objeto;
	}
	public String getObjectNom() {
		return this.objeto.getNombre();
	}
	public void setObject(Objetos object) {
		this.objeto = object;
	}


	public String[] getSalasVecinas() {
		return salasVecinas;
	}


	public void setSalasVecinas(String[] salasVecinas) {
		this.salasVecinas = salasVecinas;
	}

}
