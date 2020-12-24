import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		System.out.println(GestorPartida.getContJugadores());
		GestorPartida.getJugadores()[1].setSala("Comedor");
		
		for(int i =0; GestorPartida.getJugadores()[i]!=null; i++) {
			System.out.println(GestorPartida.getJugadores()[i].getNombre());
			System.out.println(GestorPartida.getJugadores()[i].getId());
			System.out.println(GestorPartida.getJugadores()[i].getSala());
			System.out.println(GestorPartida.getJugadores()[i].getObjetivoObjeto());
			System.out.println(GestorPartida.getJugadores()[i].getObjetivoSala());
			System.out.printf("\n\n\n");
		}
		GestorPartida.getJugadores()[1].verObjetosEnSala();
		//System.out.println(GestorPartida.getContObjetosJugador());
		//GestorPartida.getJugadores()[1].hacerPeticion();
		//GestorPartida.getJugadores()[0].cambiarObjeto();
		for(int i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			System.out.println(GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			System.out.println(GestorPartida.getObjetoJugador()[i].getNombre());
			System.out.println(GestorPartida.getObjetoJugador()[i].getSala());
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i =0; i < GestorPartida.getContObjetosSala(); i++) {
			System.out.println(GestorPartida.getObjetoSala()[i].getNombreObjeto());
			System.out.println(GestorPartida.getObjetoSala()[i].getNombre());
		}
		
		
	}
}
