
public class Localizaciones {
	private String nombre;
	private int pos;
	public Objetos objects[];
	public Localizaciones(String nombre, int pos, Objetos objects[]) {
		this.setNombre(nombre);
		this.pos = pos;
		this.setObjects(objects);
	}
	
	
	//Devolver 1 String
	public String getOneName(int cont) {
		return this.objects[cont].getNombre();
	}
	//Devolver 1 Objetos
	public Objetos getOneObject(int cont) {
		return this.objects[cont];
	}
		
	//Nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//Pos
	public int getPos() {
		return pos;
	}
	public void setInt(int pos) {
		this.pos = pos;
	}
	//Objetos
	public Objetos[] getObjects() {
		return objects;
	}
	public void setObjects(Objetos objects[]) {
		this.objects = objects;
	}
	
}
