
public class IA extends Jugador{

	public IA(String nombre, String sala) {
		super(nombre, sala);
		
		GestorPartida.instanciarIA(this);
	}
	
	
	
	//Se elige la acción de la IA
	public int dameAccion(){
		boolean objetoPosesion=false;
		boolean objetoObjetivoSala=false;
		boolean objetoObjetivoJugador=false;
		boolean turno=false;
		int pasarTurno= (int) (Math.random()*10);
		int j;
		String[] objetosenSala;
		String[] objetosenJugadores;
		
		objetosenSala=verObjetosEnSalaIa(this);
		objetosenJugadores=verObjetosEnJugadoresIa(this);
		turno=false;
		
		if(pasarTurno<2) {
			GestorPartida.getInterfaz().getRondasAnteriores().append(this.getNombre() + " ha saltado su Turno.\n");
			GestorPartida.getInterfaz().setHistoriaCompleta(GestorPartida.getInterfaz().getHistoriaCompleta()+ this.getNombre() + " ha saltado su Turno.\n");
			GestorPartida.getInterfaz().setTurnosSaltados(GestorPartida.getInterfaz().getTurnosSaltados() + 1);
		}
			
		else {
			GestorPartida.getInterfaz().setTurnosSaltados(0);
			for(j=0;j < GestorPartida.getContObjetosJugador();j++) {
				if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(this.getNombre())) {
					if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
		                   break;
		               }
		               else {
		                   objetoPosesion=true;
		               }
				}
			}
				
			for(int i=0; objetosenSala[i]!=null ;i++) {
				if(objetosenSala[i].equalsIgnoreCase(this.objetivoObjeto)) {
					objetoObjetivoSala=true;
					break;
				}
			}
				
			for(int i=0; i < objetosenJugadores.length && objetosenJugadores[i]!=null;i++) {
				if(objetosenJugadores[i].equalsIgnoreCase(this.objetivoObjeto)) {
					objetoObjetivoJugador=true;
					break;
				}
			}
			//la IA deja su objeto
			if(objetoPosesion ==true && turno==false) {
				for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
					if(this == GestorPartida.getObjetoJugador()[i].getJugador()) {
						GestorPartida.getInterfaz().getRondasAnteriores().append(this.getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + this.getSala() + ".\n");
						dejarObjeto(this.getId(), GestorPartida.getObjetoJugador()[i]);					
						objetoPosesion=false;
						turno=true;
						break;
					}
				}
			}
			//la IA coge un objeto
			if(objetoObjetivoSala ==true && turno==false) {
				for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
					if(GestorPartida.getObjetoSala()[i].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
						GestorPartida.getInterfaz().getRondasAnteriores().append(this.getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + this.getSala() + ".\n");
						cogerObjeto(this.getId(), GestorPartida.getObjetoSala()[i]);
						objetoObjetivoSala=false;
						turno=true;
						break;
					}
				}
			}
				
			//la IA pide un objeto
			if(objetoObjetivoJugador == true && turno==false) {
				for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
					if(GestorPartida.getObjetoJugador()[i].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
						hacerPeticionIa(this,GestorPartida.getObjetoJugador()[i].getJugador().getNombre(),this.objetivoObjeto);
						objetoObjetivoJugador=false;
						turno=true;
						break;
					}
				}
			}
			//la IA se cambia de sala
			if(objetoObjetivoSala==false && objetoObjetivoJugador==false && objetoPosesion==false && turno==false) {
				cambiarSalaIa(this);
				turno=true;
			}
		}
		return 0;
	}
		
		//Devuelve un array con los objetos que hay en la sala
		private String[] verObjetosEnSalaIa(Jugador jugador) {
			int contObjetos;
			contObjetos=GestorPartida.getContObjetosSala();
			int pos = 0;
			String objetosenSala[] = new String[contObjetos];
			
			for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
				if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
					objetosenSala[pos]=GestorPartida.getObjetoSala()[i].getNombreObjeto();
					pos++;
				}
			}
			return objetosenSala;
		}
		
		//Devuelve un array con los objetos que tienen los jugadores de la misma sala
		private String[] verObjetosEnJugadoresIa(Jugador jugador) {
			int contObjetos;
			contObjetos=GestorPartida.getContObjetosJugador();
			int pos = 0;
			String objetosenJugadores[] = new String[contObjetos];
			
			for(int i = 0; i < GestorPartida.getContObjetosJugador();i++) {
				if(GestorPartida.getObjetoJugador()[i].getJugador().getSala().equalsIgnoreCase(jugador.getSala())) {
					if(!GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
						objetosenJugadores[pos]= GestorPartida.getObjetoJugador()[i].getNombreObjeto();
						pos++;
					}
				}
			}
			return objetosenJugadores;
		}
		
		//Funcion que permite a la ia hacer peticiones
		private void hacerPeticionIa(Jugador jugadorHace, String jugadorRecibe, String objeto) {
			int jugador = posJugadorObjetivo(jugadorRecibe);
			int posPeticion = 0;
		
			for(posPeticion = 0; GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion] != null; posPeticion++) {
				if(GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion].getJugadorPide().equalsIgnoreCase(jugadorHace.getNombre()) ) {
					break;
				}
				if(posPeticion == 9) {
					posPeticion =0;
					break;
				}
			}
			GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion] =  new Peticiones(objeto,jugadorHace.getNombre());
			GestorPartida.getInterfaz().getRondasAnteriores().append(jugadorHace.getNombre() + " ha pedido el Objeto " + objeto  + " a " + GestorPartida.getJugadores()[jugador].getNombre() + ".\n");
			GestorPartida.getInterfaz().setHistoriaCompleta(GestorPartida.getInterfaz().getHistoriaCompleta() + jugadorHace.getNombre() + " ha pedido el Objeto " + objeto  + " a " + GestorPartida.getJugadores()[jugador].getNombre() + ".\n");
		}
		
		//Funcion que cambia a la de sala
		private void cambiarSalaIa(Jugador jugador) {
			String[] salasVecinas;
			salasVecinas= GestorPartida.verSalasVecinas(jugador.getId());
			int contVecinas;
			boolean salaObjetivo = false;		
			contVecinas=salasVecinas.length;
			int numeroAleatorio = (int) (Math.random()*contVecinas);
			
			for(int i=0;i<contVecinas;i++) {
				if(salasVecinas[i].equalsIgnoreCase(jugador.getObjetivoSala())) {
					GestorPartida.getJugadores()[jugador.getId()].setSala(salasVecinas[i]);
					GestorPartida.getInterfaz().getRondasAnteriores().append(jugador.getNombre()+" se ha desplazado a la Sala "+ jugador.getSala()+".\n");
					GestorPartida.getInterfaz().setHistoriaCompleta(GestorPartida.getInterfaz().getHistoriaCompleta() + jugador.getNombre()+" se ha desplazado a la Sala "+ jugador.getSala()+".\n");
					salaObjetivo = true;
					break;
				}
			}
			if(!salaObjetivo) {
				GestorPartida.getJugadores()[jugador.getId()].setSala(salasVecinas[numeroAleatorio]);
				GestorPartida.getInterfaz().getRondasAnteriores().append(jugador.getNombre()+" se ha desplazado a la Sala "+ jugador.getSala()+".\n");
				GestorPartida.getInterfaz().setHistoriaCompleta(GestorPartida.getInterfaz().getHistoriaCompleta() + jugador.getNombre()+" se ha desplazado a la Sala "+ jugador.getSala()+".\n");
			}
		}



		
}
