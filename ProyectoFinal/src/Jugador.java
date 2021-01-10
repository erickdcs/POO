import java.util.Scanner;
public class Jugador {
	
	private String nombre;
	private static int contId;
	private String sala;
	private int id;
	private boolean IA = true;
	private String objetivoObjeto;
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
    
	public void cambiarSala(String salaVecina) {
		this.setSala(salaVecina);
	}
	
	public static void comprobarVictoria() {
		int puntoSala=0;
		int puntoObjeto=0;
		for(int i=0; GestorPartida.getJugadores()[i]!=null; i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[i].getObjetivoSala())) {
				puntoSala++;
			}
			if(GestorPartida.getObjetoJugador()[i]!=null) {
				if(GestorPartida.getObjetoJugador()[i].getNombreObjeto().equalsIgnoreCase(GestorPartida.getJugadores()[i].getObjetivoObjeto())){
					puntoObjeto++;
				}
			}
			if(puntoSala>=1&&puntoObjeto>=1) {
				System.out.println("Ha ganado"+ GestorPartida.getJugadores()[i].getNombre());
				break;
			}
		}	
	}
	
	public void verObjetosEnSala() {
		System.out.println("Los objetos disponibles de la sala son: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(this.sala)) {
				System.out.println(GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getSala().equalsIgnoreCase(this.sala)) {
				System.out.println("el jugador "+ GestorPartida.getObjetoJugador()[i].getJugador().getNombre() + " tiene el objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() );
			}
		}
		
	}

	public boolean comprobarObjetoenSala(String objeto) {
		boolean objetoenSala = false;
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(this.sala)) {
				System.out.println("El objeto " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " esta en tu sala");
				objetoenSala = true;
			}
		}
		return objetoenSala;
	}
	
	public boolean comprobarObjetoenJugador(String objeto) {
		boolean objetoenJugador = false;
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(this.sala)) {
				System.out.println("el jugador "+ GestorPartida.getObjetoJugador()[i].getJugador().getNombre() + " tiene el objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() );
				objetoenJugador = true;
			}
		}
		return objetoenJugador;
	}

	//Funcion que pide un nombre para que sea objetivo de una accion,y ademas comprueba que existe un juador con ese nombre
	private String jugadorObjetivo() {
		Scanner nombre = new Scanner(System.in);
		System.out.println("Indica el nombre del jugador que quieres seleccionar");
		String nombreJugador = nombre.nextLine();
		int jugador;
		for( jugador =0; jugador < GestorPartida.getContJugadores(); jugador++) {
			if(GestorPartida.getJugadores()[jugador].getNombre().equalsIgnoreCase(nombreJugador) && !GestorPartida.getJugadores()[jugador].getNombre().equalsIgnoreCase(this.nombre) ) {
				if(!GestorPartida.getJugadores()[jugador].getSala().equalsIgnoreCase(this.sala)){
					System.out.println("No puede seleccionar a alguien que no esta en la misma sala, seleccione a otro");
					nombreJugador = nombre.nextLine();
					jugador =-1;
				}
				else {
					break;
				}
				
			}
			if(nombreJugador.equalsIgnoreCase(this.nombre)) {
				System.out.println("No puede seleccionarse a si mismo/a, seleccione otro");
				nombreJugador = nombre.nextLine();
				jugador =-1;
			}
			else if(jugador == GestorPartida.getContJugadores()-1) {
				
				System.out.println("Ese jugador no existe, seleccione otro");
				nombreJugador = nombre.nextLine();
				jugador =-1;
				
			}
			
		}
		return nombreJugador;
	}
	
	//Funcion que retorna la posion del array de GestorPartida en la que se encuentra el jugador seleccionado
	private int posJugadorObjetivo(String jugadorSeleccionado) {
		int jugador = 0;
		
		for(jugador =0; jugador < GestorPartida.getContJugadores(); jugador++) {
			if(GestorPartida.getJugadores()[jugador].getNombre().equalsIgnoreCase(jugadorSeleccionado)) {
				break;
			}
		}
		return jugador;
	}
	
	//Con esto seleccionas un objeto, y ademas comprueba que existe un objeto con ese nombre
	/*private String objetoObjetivo() {
		Scanner objetos = new Scanner(System.in);
		System.out.println("Indica el nombre del objeto que quieres seleccionar");
		String nombreObjeto = objetos.nextLine();
		int objeto =0;
		
		for( int salida = 0; salida == 0;) {
			for(objeto=0; objeto < GestorPartida.getContObjetosJugador(); objeto++)
			{
				if(GestorPartida.getObjetoJugador()[objeto].getNombreObjeto().equalsIgnoreCase(nombreObjeto)) {
					salida = 1;
					break;
				}
			}
			if(salida == 0) {
				for(objeto=0; objeto < GestorPartida.getContObjetosSala(); objeto++)
				{
					if(GestorPartida.getObjetoSala()[objeto].getNombreObjeto().equalsIgnoreCase(nombreObjeto)) {
						salida = 1;
						System.out.println("hola1");
						break;
					}
				}
			}
			if(salida ==0) {
				System.out.println("No existe un objeto con ese nombre, elige uno nuevo");
				nombreObjeto = objetos.nextLine();
			}
			
		}
		return nombreObjeto;
	}*/
	
	
	public String accionIa(Jugador this) {
		int objetoPosesion=0;
		int objetoObjetivoSala=0;
		int objetoObjetivoJugador=0;
		int salaObjetivo=0;
		int j;
		String[] objetosenSala;
		String[] objetosenJugadores;
		objetosenSala=verObjetosEnSalaIa(this);
		objetosenJugadores=verObjetosEnJugadoresIa(this);
		
		for(j=0;j < GestorPartida.getContObjetosJugador();j++) {
			if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(this.getNombre())) {
				if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
                    break;
                }
                else {
                    objetoPosesion++;
                }
			}
		}
		
		for(int i=0; objetosenSala[i]!=null ;i++) {
			if(objetosenSala[i].equalsIgnoreCase(this.objetivoObjeto)) {
				objetoObjetivoSala++;
			}
		}
		
		for(int i=0; i < objetosenJugadores.length && objetosenJugadores[i]!=null;i++) {
			if(objetosenJugadores[i].equalsIgnoreCase(this.objetivoObjeto)) {
				objetoObjetivoJugador++;
			}
		}
		
		if(objetoPosesion!=0) {
			for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
				if(this == GestorPartida.getObjetoJugador()[i].getJugador()) {
					dejarObjeto(this.getId() ,GestorPartida.getObjetoJugador()[i]);
				}
			}
		}
		
		if(objetoObjetivoSala !=0) {
			for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
				if(GestorPartida.getObjetoSala()[i].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
					cogerObjeto(this.getId(), GestorPartida.getObjetoSala()[i]);
				}
			}
		}
		
		if (this.getSala().equalsIgnoreCase(this.getObjetivoSala())) {
			salaObjetivo++;
		}
		
		if(objetoObjetivoJugador !=0) {
			for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
				if(GestorPartida.getObjetoJugador()[i].getNombreObjeto().equalsIgnoreCase(this.objetivoObjeto)) {
					hacerPeticionIa(this,GestorPartida.getObjetoJugador()[i].getJugador().getNombre(),this.objetivoObjeto);
				}
			}
		}
		
		if(objetoObjetivoSala==0 && salaObjetivo==0 && objetoObjetivoJugador==0 && objetoPosesion==0) {
			cambiarSalaIa(this);
			System.out.println(GestorPartida.getJugadores()[id].getNombre()+ "   " + GestorPartida.getJugadores()[id].getSala() + "\n");
		}
		return "Hola";
	}
	
	private String[] verObjetosEnSalaIa(Jugador jugador) {
		int contObjetos;
		contObjetos=GestorPartida.getContObjetosSala();
		int pos =0;
		String objetosenSala[] = new String[contObjetos];
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
				objetosenSala[pos]=GestorPartida.getObjetoSala()[i].getNombreObjeto();
				pos++;
			}
		}
		return objetosenSala;
	}
	
	private String[] verObjetosEnJugadoresIa(Jugador jugador) {
		int contObjetos;
		contObjetos=GestorPartida.getContObjetosJugador();
		int pos =0;
		String objetosenJugadores[] = new String[contObjetos];
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getSala().equalsIgnoreCase(jugador.getSala())) {
				objetosenJugadores[pos]= GestorPartida.getObjetoJugador()[i].getNombreObjeto();
				pos++;
			}
		}
		return objetosenJugadores;
	}
	
	private void hacerPeticionIa(Jugador jugadorHace, String jugadorRecibe, String objeto ) {
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
		
	}
	
	private void cambiarSalaIa(Jugador jugador) {
		String[] salasVecinas;
		salasVecinas= GestorPartida.verSalasVecinas(jugador.getId());
		int contVecinas;
		int salaObjetivo=0;
		contVecinas=salasVecinas.length;
		int numeroAleatorio = (int) (Math.random()*contVecinas);
		for(int i=0;i<contVecinas;i++) {
			if(salasVecinas[i].equalsIgnoreCase(jugador.getObjetivoSala())) {
				GestorPartida.getJugadores()[jugador.getId()].setSala(salasVecinas[i]);
				salaObjetivo++;
				break;
			}
		}
		if(salaObjetivo==0) {
			GestorPartida.getJugadores()[jugador.getId()].setSala(salasVecinas[numeroAleatorio]);
		}
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

	public boolean isIA() {
		return IA;
	}

	public void setIA(boolean iA) {
		IA = iA;
	}
}
