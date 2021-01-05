import java.util.Scanner;
public class Jugador {

	
	private String nombre;
	private static int contId;
	private String sala;
	private int id;
	private String objetivoObjeto;
	private String objetivoSala;
	private Peticiones peticiones[] =  new Peticiones[10];
	
	//private String memoriaPosiciones[];
	// String memoriaObjetos[];
	
	
	public Jugador(String nombre, String sala) {
		setNombre(nombre);
		setSala(sala);
		setId(contId);
		
		contId++;
		GestorPartida.instanciarJugador(this);
	}
	public void hacerPeticion() {
		String jugadorSeleccionado = jugadorObjetivo();//Pedimos el nombre del jugador al que vamos a realizar la peticion
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
		String objetoSeleccionado = objetoObjetivo();
		GestorPartida.getJugadores()[jugador].getPeticiones()[posPeticion] =  new Peticiones(objetoSeleccionado,this.nombre);
		
	}
	public void cambiarObjeto() {
		String jugadorSeleccionado = jugadorObjetivo();//Pedimos el nombre del jugador al que vamos a realizar la peticion
		String objetoSeleccionado = objetoObjetivo();
		boolean existePeticion = false;
		boolean poseeObjeto =false;
		for(int i =0; GestorPartida.getObjetoJugador()[i] !=null ;i++) {
			if(GestorPartida.getObjetoJugador()[i].getNombre().equalsIgnoreCase(this.nombre) && GestorPartida.getObjetoJugador()[i].getNombreObjeto().equalsIgnoreCase(objetoSeleccionado)) {
				poseeObjeto =true;
				break;
			}
		}
		
		if(poseeObjeto == true) {
			for(int i =0; this.peticiones[i] !=null; i++) {
				if(this.peticiones[i].getJugadorPide().equalsIgnoreCase(jugadorSeleccionado) && this.peticiones[i].getObjeto().equalsIgnoreCase(objetoSeleccionado)) {
					existePeticion = true;
					if(GestorPartida.getObjetoJugador()[i].getSala().equalsIgnoreCase(this.sala)) {
						GestorPartida.getObjetoJugador()[i].setNombre(jugadorSeleccionado);
						break;
					}
					else {
						System.out.println("Accion imposible, no estais en la misma sala");
					}
				}
				
			}
			
			if(existePeticion == false ) {
				System.out.printf("Accion imposible, no existe una peticion de %s del jugador %/n",jugadorSeleccionado,objetoSeleccionado );
			}
		}
		else{
			System.out.println("Accion imposible, no posees el objeto que quieres dar");
		}
		
		
		
	}
	
	/*
	public int comprobarObjetoOtroJugador(Jugador jugador) {
        for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
            if(GestorPartida.getObjetoJugador()[i].getSala().equals(/*Sala del Jugador)) {
                if()
            }
        }
    } */
    
    public void cogerObjeto(Jugador jugador, ObjetoSala objeto){
        GestorPartida.getObjetoJugador()[GestorPartida.getContObjetosJugador()] = new ObjetoJugador(objeto.getNombreObjeto(), jugador);
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
    public void dejarObjeto(Sala sala, ObjetoJugador objeto) {
        GestorPartida.getObjetoSala()[GestorPartida.getContObjetosSala()] = new ObjetoSala(objeto.getNombreObjeto(), sala);
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
    
    
	public void cambiarSala(Jugador jugador) {
		String[] salasVecinas;
		Scanner entrada = new Scanner(System.in);
		
		
		salasVecinas= GestorPartida.verSalasVecinas(jugador.getId());
		int contVecinas;
		contVecinas=salasVecinas.length;
		System.out.printf("Sus salas vecinas son:\n");
		for(int i=0;i<contVecinas;i++) {
			System.out.println(i+1+" " + salasVecinas[i]);
		}
		int menu1 =0;
		System.out.println("Escriba el numero de la sala destino ");
		menu1 = entrada.nextInt()-1;
		
		entrada.close();
		GestorPartida.getJugadores()[jugador.getId()].setSala(salasVecinas[menu1]);
		System.out.println("Ahora " +GestorPartida.getJugadores()[jugador.getId()].getNombre()+" está en "+ GestorPartida.getJugadores()[jugador.getId()].getSala());
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
		int sala;
		System.out.println("Los objetos disponibles de la sala son: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getNombre().equalsIgnoreCase(this.sala)) {
				System.out.println(GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getSala().equalsIgnoreCase(this.sala)) {
				System.out.println("el jugador "+ GestorPartida.getObjetoJugador()[i].getNombre() + " tiene el objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() );
			}
		}
		
	}

	public boolean comprobarObjetoenSala(String objeto) {
		boolean objetoenSala = false;
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getNombre().equalsIgnoreCase(this.sala)) {
				System.out.println("El objeto " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " esta en tu sala");
				objetoenSala = true;
			}
		}
		return objetoenSala;
	}
	public boolean comprobarObjetoenJugador(String objeto) {
		boolean objetoenJugador = false;
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getSala().equalsIgnoreCase(this.sala)) {
				System.out.println("el jugador "+ GestorPartida.getObjetoJugador()[i].getNombre() + " tiene el objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() );
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
	private String objetoObjetivo() {
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
}
