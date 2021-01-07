
public class Creencias {
	public String[] nombrePersona = new String[10];
	public String[] salaPersona = new String[10];
	
	public String[] nombreObjeto = new String[10];
	public String[] lugarObjeto = new String[10];
	
	public Creencias(Jugador[] personas, ObjetoJugador[] objetosJugador, ObjetoSala[] objetosSala, int jugadorId) {
		
		int i =0;
		int j = 0;
		
		for(i =0; i < personas.length; i++) {
			if(personas[i].getId() != jugadorId) {
				nombrePersona[j] = personas[i].getNombre();
				j++;
			}
		}
		
		for(i =0; i < objetosJugador.length; i++) {
			nombreObjeto[i] = objetosJugador[i].getNombreObjeto();
		}
		for(j = 0; j <  objetosSala.length; j++) {			
			nombreObjeto[i] = objetosSala[j].getNombreObjeto();
			i++;
		}
	}
}
