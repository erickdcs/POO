
public class Jugador {
	
	private String nombre;
	private static int contId;
	private String sala;
	private int id;
	private boolean IA = true;
	protected String objetivoObjeto;
	private String objetivoSala;
	private Peticiones peticiones[] =  new Peticiones[10];
	private Creencias creencias;
	
	public Jugador(String nombre, String sala) {
		setNombre(nombre);
		setSala(sala);
		setId(contId);
		
		contId++;
		GestorPartida.instanciarJugador(this);
	}
	
	//Funcion que permite hacer peticiones
	public void hacerPeticion(String jugadorSeleccionado, String objetoSeleccionado) {
		int jugador = posJugadorObjetivo(jugadorSeleccionado);//COnseguimos la posicion del jugador seleccionado dentro del array de GestorPartida
		int posPeticion = 0;
		
		
		//Con este bucle vemos si ya existe algun peticion del jugador anteriomente, o la primera posicon vacia en su array de peticiones
		for(posPeticion = 0; GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion] != null; posPeticion++) {
			if(GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion].getJugadorPide().equalsIgnoreCase(this.nombre) ) {
				break;
			}
			if(posPeticion == 9) {
				posPeticion =0;
				break;
			}
		}
		//Ya encontrando una posicon vacia, o una peticion anterior de este jugador, la modificamos o la anadimos
		
		GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion] =  new Peticiones(objetoSeleccionado,this.nombre);
		
	}
	
	//Funcion que permite dar objetos (aceptar peticiones)
	public void darObjeto(String nombre, String objeto) {
		int idJugador =0;
		for(idJugador =0; idJugador < GestorPartida.getContJugadores(); idJugador++) {
			if(GestorPartida.getJugadores()[idJugador].getNombre().equalsIgnoreCase(nombre)) {
				break;
			}
		}
		for(int i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			
			if(GestorPartida.getObjetoJugador()[i].getNombreObjeto().equalsIgnoreCase(objeto)) {
				GestorPartida.getObjetoJugador()[i].setJugador(GestorPartida.getJugadores()[idJugador]);
				
			}
			
		}
		int peticion = 0;
		for(peticion =0; peticion <10 && this.peticiones[peticion] != null; peticion++) {
			if(this.peticiones[peticion].getJugadorPide().equalsIgnoreCase(nombre)) {
				this.peticiones[peticion] = null;
			}
		}
		for(int i =peticion+1; i<10 && this.peticiones[i] !=null; i++) {
			this.peticiones[i] = this.peticiones[i-1];
		}
	}
	
    //Funcion que permite coger objetos
    public static void cogerObjeto(int id, ObjetoSala objeto){
    	
    	GestorPartida.getObjetoJugador()[GestorPartida.getContObjetosJugador()] = new ObjetoJugador(objeto.getNombreObjeto(), GestorPartida.getJugadores()[id]);
        GestorPartida.setContObjetosJugador(GestorPartida.getContObjetosJugador()+1);
        
        for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
            if(GestorPartida.getObjetoSala()[i] == objeto) {
                for(int j = i; j < (GestorPartida.getContObjetosSala()-1); j++) {
                    GestorPartida.getObjetoSala()[j] = GestorPartida.getObjetoSala()[j+1];                    
                }
                GestorPartida.getObjetoSala()[GestorPartida.getContObjetosSala()] = null;
                GestorPartida.setContObjetosSala(GestorPartida.getContObjetosSala()-1);
                break;
            }
        }
    }
    
    //Funcion que permite dejar objetos
    public static void dejarObjeto(int id, ObjetoJugador objeto) {
    	
	  	for(int i = 0; i < GestorPartida.getContSalas(); i++) {
	    	if(GestorPartida.getJugadores()[id].getSala().equalsIgnoreCase(GestorPartida.getSalas()[i].getNombre())) {
	    		GestorPartida.getObjetoSala()[GestorPartida.getContObjetosSala()] = new ObjetoSala(objeto.getNombreObjeto(), GestorPartida.getSalas()[i]);
	    		break;
	    	}

    	}        
        GestorPartida.setContObjetosSala(GestorPartida.getContObjetosSala()+1);
        
        for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
            if(GestorPartida.getObjetoJugador()[i] == objeto) {
                for(int j = i; j < (GestorPartida.getContObjetosJugador()-1); j++) {
                    GestorPartida.getObjetoJugador()[j] = GestorPartida.getObjetoJugador()[j+1];                    
                }
                GestorPartida.getObjetoJugador()[GestorPartida.getContObjetosJugador()] = null;
                GestorPartida.setContObjetosJugador(GestorPartida.getContObjetosJugador()-1);
                break;
            }
        }
    }
 
	//Funcion que retorna la posion del array de GestorPartida en la que se encuentra el jugador seleccionado
	protected int posJugadorObjetivo(String jugadorSeleccionado) {
		int jugador = 0;
		
		for(jugador =0; jugador < GestorPartida.getContJugadores(); jugador++) {
			if(GestorPartida.getJugadores()[jugador].getNombre().equalsIgnoreCase(jugadorSeleccionado)) {
				break;
			}
		}
		return jugador;
	}
	
	/*
	//Se elige la acci�n de la IA
	public void accionIa(Jugador this){
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
		}
		
		else {
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
		int pos =0;
		String objetosenJugadores[] = new String[contObjetos];
		
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
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
	*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public String getObjetivoObjeto() {
		return objetivoObjeto;
	}
	public void setObjetivoObjeto(String objetivoObjeto) {
		this.objetivoObjeto = objetivoObjeto;
	}

	public String getObjetivoSala() {
		return objetivoSala;
	}
	
	public void setObjetivoSala(String objetivoSala) {
		this.objetivoSala = objetivoSala;
	}

	public static int getContId() {
		return contId;
	}

	public static void setContId(int contId) {
		Jugador.contId = contId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Peticiones[] getPeticiones() {
		return peticiones;
	}
	public void setPeticiones(Peticiones[] peticiones) {
		this.peticiones = peticiones;
	}

	public Creencias getCreencias() {
		return creencias;
	}

	public void setCreencias(Creencias creencias) {
		this.creencias = creencias;
	}

	public boolean isIA() {
		return IA;
	}

	public void setIA(boolean iA) {
		IA = iA;
	}
}
