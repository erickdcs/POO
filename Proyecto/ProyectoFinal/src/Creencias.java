
public class Creencias {
	public String[] nombrePersona = new String[10];
	public String[] salaPersona = new String[10];
	
	public String[] nombreObjeto = new String[10];
	public String[] lugarObjeto = new String[10];
	
	public Creencias(Jugador[] personas, ObjetoJugador[] objetosJugador, ObjetoSala[] objetosSala, int jugadorId) {
		
		int i =0;
		int j = 0;
		
		for(i =0; i < GestorPartida.getContJugadores(); i++) {
			if(personas[i].getId() != jugadorId) {
				nombrePersona[j] = personas[i].getNombre();
				j++;
			}
		}
		
		for(i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(objetosJugador[i].getNombreObjeto()!= null) {
				nombreObjeto[i] = objetosJugador[i].getNombreObjeto();
			}
			
		}
		for(j = 0; j <  GestorPartida.getContObjetosSala(); j++) {		
			if(objetosSala[j].getNombreObjeto() != null) {
				nombreObjeto[i] = objetosSala[j].getNombreObjeto();
				i++;
			}
			
		}
	}
	public String[] getNombrePersona() {
		return nombrePersona;
	}
	
	public void setNombrePersona(String nombrePersona, int pos) {
		this.nombrePersona[pos] = nombrePersona;
	}
	public String[] getSalaPersona() {
		return salaPersona;
	}
	
	public void setSalaPersona(String SalaPersona, int pos) {
		this.salaPersona[pos] = SalaPersona;
	}
	
	
	public String[] getNombreObjeto() {
		return nombreObjeto;
	}
	
	public void setNombreObjeto(String nombreObjeto, int pos) {
		this.nombreObjeto[pos] = nombreObjeto;
	}
	public String[] getLugarObjeto() {
		return lugarObjeto;
	}
	
	public void setLugarObjeto(String lugarObjeto, int pos) {
		this.lugarObjeto[pos] = lugarObjeto;
	}
}
