
public class GestorPartida {
	static Localizaciones[] zonas = new Localizaciones[10];
	static Personajes[] jugadores = new Personajes[10];
	static Objetos[] objects = new Objetos[10];
	//static Objetivos[] objetivo= new Objetivos[10];
	
	private static int contZonas = 0;
	private static int contJugadores = 0;
	
	public static void rellenadorZonas(Localizaciones zona) {
		zonas[contZonas] = zona;
		contZonas++;
	}
	public static void rellenadorJugadores(Personajes jugadoress) {
		jugadores[contJugadores] = jugadoress;
		contJugadores++;
	}
	
	public static void mostarLocalizaciones() {
		for(int i =0; zonas[i] != null; i++) {
			System.out.println(zonas[i].getNombre());
			if(zonas[i].getObject()!= null) {
				System.out.println(zonas[i].getObject().getNombre());
			}
			System.out.println();
		}
		
	}
	public static void mostarVecinos() {
		String[] aux = null;
		for(int i =0; zonas[i] != null; i++) {
			if(zonas[i].getSalasVecinas() !=  null) {
				aux = zonas[i].getSalasVecinas();
				for(int j = 0; aux[j] != null; j++) {
					System.out.println(aux[j]);
				}
			}
		}
		
	}
	public static void mostarJugadores() {
		for(int i =0; jugadores[i] != null; i++) {
			System.out.println(jugadores[i].getNombre() + "   " + jugadores[i].getLocalizacion());
			if(jugadores[i].getObjeto()!= null) {
				System.out.println(jugadores[i].getObjeto().getNombre());
				
			}
			System.out.println("Objetivos: " + jugadores[i].getObjetivo().getLocalizacion() + "  " + jugadores[i].getObjetivo().getObjeto());
			System.out.println();
		}
	}
	
	
	
}
