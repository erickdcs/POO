
public class Peticiones {
	private String objeto;
	private String jugadorPide;
	public Peticiones(String objeto, String jugadorPide) {
		setObjeto(objeto);
		setJugadorPide(jugadorPide);
	}
	
	
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getJugadorPide() {
		return jugadorPide;
	}
	public void setJugadorPide(String jugadorPide) {
		this.jugadorPide = jugadorPide;
	}
}
