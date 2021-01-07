import java.util.Scanner;
public class GestorPartida {
	private static Sala salas[] = new Sala [10];
	private static int contSalas;
	private static Jugador jugadores[] = new Jugador[10];
	private static int contJugadores;
	
	
	private static ObjetoSala objetoSala[] = new ObjetoSala[10];
	private static int contObjetosSala;
	private static ObjetoJugador objetoJugador[] = new ObjetoJugador[10];
	private static int contObjetosJugador;
	
	
	private static int contPasarTurnos = 0;
	
	private static int rondas;
	public static void instanciarSala(Sala sala) {
		salas[contSalas] = sala;
		contSalas++;
	}
	
	public static void instanciarJugador(Jugador jugador) {
		jugadores[contSalas] = jugador;
		contJugadores++;
	}

	public static void gestorRondas() {
		//int turnos =0;
		Scanner eleccion = new Scanner(System.in);
		int opcion =0;
		int seguridad = 0;
		boolean pasarTurno = false;
		boolean victoria = false;
		while(victoria = false && contPasarTurnos != GestorPartida.contJugadores) {
			contPasarTurnos =0;
			for(int i =0; i < GestorPartida.getContJugadores(); i++) {
				pasarTurno = false;
				while (pasarTurno = false)
					eleccion = new Scanner(System.in);
					System.out.println("Turno del jugador: " + GestorPartida.getJugadores()[i].getNombre());
					
					System.out.println("Elige una accion: ");
					//Moverse
					System.out.println("1- Cambiar de Sala");
					
					//Coger objeto
					if(objetosEnSala(GestorPartida.getJugadores()[i]) && !objetosEnJugador(GestorPartida.getJugadores()[i])) {
						System.out.println("2- Coger un objeto");
						
					}
					//Dejar objeto
					if(objetosEnJugador(GestorPartida.getJugadores()[i])) {
						System.out.println("3- Dejar un objeto");
					}
					//Pedir objeto
					if(jugadorEnSala(GestorPartida.getJugadores()[i])) {
						System.out.println("4- Pedir un objeto");
					}
					
					
					//Dar objeto
					if(dar(GestorPartida.getJugadores()[i])) {
						imprimirPeticiones(GestorPartida.getJugadores()[i]);
						imprimirJugadores(GestorPartida.getJugadores()[i]);
						System.out.println("5- Dar un objeto");
					}
					//Nada
					System.out.println("6- Mostrar toda la informacion actual");
					System.out.println("7- Pasar turno");
					//if(eleccion.hasNextInt() )
					//	opcion = eleccion.nextInt(); // if there is another number
					opcion = eleccion.nextInt();
					switch(opcion) {
						//Moverse
						case 1: 
								imprimirSalasVecinas(GestorPartida.getJugadores()[i]);
								System.out.printf("Seguro que quieres moverte de sala?\n1- Moverse\n2-Menu de acciones\n");
								
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Moverse\n2-Menu de acciones\n");
								}
								if(seguridad == 1) {
									GestorPartida.getJugadores()[i].cambiarSala(GestorPartida.getJugadores()[i]);
									pasarTurno = true;
								}
								
								break;
						//Coger objeto
						case 2: imprimirObjetosEnSala(GestorPartida.getJugadores()[i]);
								System.out.printf("Seguro que quieres coger un objeto?\n1- Coger objeto\n2-Menu de acciones\n");
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Coger objeto\n2-Menu de acciones\n");
								}
								break;
						//Dejar objeto
						case 3: imprimirObjetos(GestorPartida.getJugadores()[i]);
								System.out.printf("Seguro que quieres dejar un objeto?\n1- Dejar objeto\n2-Menu de acciones\n");
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Dejar objeto\n2-Menu de acciones\n");
								}
								break;
						//Pedir objeto
						case 4: imprimirJugadores(GestorPartida.getJugadores()[i]);
								System.out.printf("Seguro que quieres pedir un objeto?\n1- Pedir objeto \n2-Menu de acciones\n");
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Pedir objeto\n2-Menu de acciones\n");
								}
								break;
						//Dar objeto
						case 5: imprimirPeticiones(GestorPartida.getJugadores()[i]);
								imprimirJugadores(GestorPartida.getJugadores()[i]);
								System.out.printf("Seguro que quieres dar un objeto?\n1- Dar objeto\n2-Menu de acciones\n");
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Dar objeto\n2-Menu de acciones\n");
								}
								break;
						case 6: hud(GestorPartida.getJugadores()[i]);
								break;
						//Pasar turno
						case 7: System.out.printf("Seguro que quieres pasar, perderas el turno \n1- Pasarn2-Menu de acciones\n");
								seguridad = eleccion.nextInt();
								while(seguridad !=1 && seguridad !=2) {
									System.out.printf("Numero no valido, introduzca uno nuevo\n1- Pasar turno\n2-Menu de acciones\n");
								}
								contPasarTurnos++;
								break;
					}
					eleccion.close();
			}
			
		}
	}
	
	public static void actualizarCreencias(int id) {
		for(int i =0; i < GestorPartida.getContJugadores();i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala()) &&  GestorPartida.getJugadores()[i].getId() != id) {
				
			}
		}
	}
	
	
	public static void hud(Jugador jugador) {
		imprimirObjetos(jugador);
		imprimirSala(jugador);
		imprimirSalasVecinas(jugador);
		imprimirPeticiones(jugador);
		imprimirJugadores(jugador);
	}
	public static void imprimirPeticiones(Jugador jugador) {
		for(int i =0; jugador.getPeticiones()[i]!=null; i++) {
			System.out.printf("Peticion de %s de %s\n", jugador.getPeticiones()[i].getJugadorPide(), jugador.getPeticiones()[i].getObjeto());
		}
		System.out.printf("\n");
	}
	
	
	
	
	public static boolean objetosEnSala(Jugador jugador) {
		boolean hayObjetos =false;
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
	}
	public static boolean objetosEnJugador(Jugador jugador) {
		boolean hayObjetos =false;
		for(int i =0; i < GestorPartida.getContObjetosJugador();i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				hayObjetos =true;
			}
		}
		return hayObjetos;		
	}
	public static boolean jugadorEnSala(Jugador jugador) {
		boolean hayJugador = false;
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getId() != jugador.getId() && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				hayJugador = true;
			}
			
		}
		return hayJugador;
	}
	public static void imprimirObjetosEnSala(Jugador jugador) {
		System.out.printf("Objetos en la sala: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getSala())) {
				System.out.printf("%s  ", GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
	}
	public static boolean dar(Jugador jugador) {
		boolean puedeDar = false;
		boolean hayJugador = jugadorEnSala(jugador);
		
		
		if(hayJugador) {
			String []PersonasEnSala = jugadoresEnSala(jugador);
			for(int i =0; jugador.getPeticiones()[i]!=null;i++) {
				for(int j =0; j < PersonasEnSala.length; j++) {
					if(jugador.getPeticiones()[i].getJugadorPide().equalsIgnoreCase(PersonasEnSala[j])) {
						if(objetoDelJugador(jugador).equalsIgnoreCase(jugador.getPeticiones()[i].getObjeto())) {
							puedeDar = true;
						}
					}
				
				}
			}
		}
		
		return puedeDar;
	}
	public static String [] jugadoresEnSala(Jugador jugador) {
		String []personas = new String[GestorPartida.getContJugadores()];
		int contPersonas = 0;
		for(int i =0; i < GestorPartida.contJugadores; i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				personas[contPersonas] = GestorPartida.getJugadores()[i].getNombre();
				contPersonas++;
			}
		}
		return personas;
	}
	public static String objetoDelJugador(Jugador jugador) {
		
		for(int i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				return GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		return "";
	}
	
	
	public static void imprimirSala(Jugador jugador) {
		System.out.println("Sala: " + jugador.getSala());
		System.out.printf("Objetos en la sala: ");
		for(int i =0; i < GestorPartida.getContObjetosSala();i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(jugador.getSala())) {
				System.out.printf("%s  ", GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
		System.out.printf("\n");
	}
	public static void imprimirSalasVecinas(Jugador jugador) {
		String salas[] =  verSalasVecinas(jugador.getId());
		System.out.printf("Salas vecinas: ");
		for(int i =0; i< salas.length; i++) {
			System.out.printf("%s  ", salas[i]);
		}
		System.out.printf("\n");
	}
	public static void imprimirObjetos(Jugador jugador) {
		System.out.printf("Objeto: ");
		for(int i =0; i < GestorPartida.contObjetosJugador; i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
				System.out.printf("%s\t",GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			}
		}
		System.out.printf("\n");
	}
	public static void imprimirJugadores(Jugador jugador) {
		System.out.printf("Jugadores en la misma sala:\n");
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getId() != jugador.getId() && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(jugador.getSala())) {
				System.out.printf("%s\t\tObjetos: ",GestorPartida.getJugadores()[i].getNombre());
				for(int j =0; j< GestorPartida.getContObjetosJugador();j++) {
					if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						System.out.printf("%s", GestorPartida.getObjetoJugador()[j].getNombreObjeto());
					}
				}
				System.out.printf("\n");
			}
			
		}
	}
	public static String[] verSalasVecinas(int a) {
		int sala =0;
		int b =a;
		String[] salasVecinas;
		//System.out.println(GestorPartida.getJugadores()[b].getNombre());
		//System.out.printf("Esta en:\n");
		//System.out.println(GestorPartida.getJugadores()[b].getSala());
		for( sala = 0;sala< GestorPartida.getContSalas(); sala++) {
			if(GestorPartida.getJugadores()[b].getSala().equalsIgnoreCase(GestorPartida.getSalas()[sala].getNombre() )) {
				break;
			}
		}
		salasVecinas=GestorPartida.getSalas()[sala].getSalasVecinas();
		return salasVecinas;
	}
	
	public static Sala[] getSalas() {
		return salas;
	}

	public static void setSalas(Sala[] salas) {
		GestorPartida.salas = salas;
	}

	public static int getContSalas() {
		return contSalas;
	}

	public static void setContSalas(int contSalas) {
		GestorPartida.contSalas = contSalas;
	}

	public static Jugador[] getJugadores() {
		return jugadores;
	}

	public static void setJugadores(Jugador[] jugadores) {
		GestorPartida.jugadores = jugadores;
	}

	public static int getContJugadores() {
		return contJugadores;
	}

	public static void setContJugadores(int contJugadores) {
		GestorPartida.contJugadores = contJugadores;
	}

	public static ObjetoSala[] getObjetoSala() {
		return objetoSala;
	}

	public static void setObjetoSala(ObjetoSala objetoSala[]) {
		GestorPartida.objetoSala = objetoSala;
	}

	public static ObjetoJugador[] getObjetoJugador() {
		return objetoJugador;
	}

	public static void setObjetoJugador(ObjetoJugador objetoJugador[]) {
		GestorPartida.objetoJugador = objetoJugador;
	}

	public static int getContObjetosSala() {
		return contObjetosSala;
	}

	public static void setContObjetosSala(int contObjetosSala) {
		GestorPartida.contObjetosSala = contObjetosSala;
	}

	public static int getContObjetosJugador() {
		return contObjetosJugador;
	}

	public static void setContObjetosJugador(int contObjetosJugador) {
		GestorPartida.contObjetosJugador = contObjetosJugador;
	}
	
	

}
