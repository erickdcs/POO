
public abstract class Jugador implements Accionable{
	
	private String nombre;
	private static int contId;
	private String sala;
	private int id;
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
	public int dameAccion(String accion) {
		return 0;
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


}
