
public class Objetos {
	private String nombre;
	private String jugadorLocalizacion;
	private String jugador;
	private String localizacion;
	public Objetos (String nombre, String jugadorLocalizacion) {
		this.setNombre(nombre);
		this.setJugadorLocalizacion(jugadorLocalizacion);
		asignarObjeto(jugadorLocalizacion);
	}
	
	//Nombre
	public void asignarObjeto(String jugadorLocalizacion) {
		if(comprobarJug(jugadorLocalizacion) == false) {
			this.localizacion = jugadorLocalizacion;
			asiganrObjetoaLocalizacion(jugadorLocalizacion);
		}
		else {
			this.jugador = jugadorLocalizacion;
			asiganrObjetoaPersonaje(jugadorLocalizacion);
		}
	}
	
	
	
	public void asiganrObjetoaPersonaje(String jugadorLocalizacion) {
		for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
			if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(jugadorLocalizacion)){
				GestorPartida.jugadores[i].objeto = this;
			}
		}
	}
	
	public void asiganrObjetoaLocalizacion(String jugadorLocalizacion) {
		for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
			if(GestorPartida.zonas[i].getNombre().equalsIgnoreCase(jugadorLocalizacion)){
				GestorPartida.zonas[i].objeto = this;
			}
		}
	}
	
	public boolean comprobarJug(String jugadorLocalizacion) {
		boolean retorno = false;
		for(int i = 0; GestorPartida.jugadores[i] != null; i++) {
			if(GestorPartida.jugadores[i].getNombre().equalsIgnoreCase(jugadorLocalizacion)){
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getJugadorLocalizacion() {
		return jugadorLocalizacion;
	}

	public void setJugadorLocalizacion(String jugadorLocalizacion) {
		this.jugadorLocalizacion = jugadorLocalizacion;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	
}
