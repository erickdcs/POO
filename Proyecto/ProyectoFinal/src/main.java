import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		System.out.println(GestorPartida.getContJugadores());
		/*
		for(int i =0; i< GestorPartida.getContJugadores(); i++) {
			System.out.println(GestorPartida.getJugadores()[i].getNombre());
			System.out.println(GestorPartida.getJugadores()[i].getId());
			System.out.println(GestorPartida.getJugadores()[i].getSala());
			System.out.println(GestorPartida.getJugadores()[i].getObjetivoObjeto());
			System.out.println(GestorPartida.getJugadores()[i].getObjetivoSala());
			System.out.printf("\n\n\n");
		}
		*/
		System.out.println(GestorPartida.getContObjetosJugador());
		
		for(int i =0; i < GestorPartida.getContObjetosJugador(); i++) {
			System.out.println(GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			System.out.println(GestorPartida.getObjetoJugador()[i].getNombre());
		}
		for(int i =0; i < GestorPartida.getContObjetosSala(); i++) {
			System.out.println(GestorPartida.getObjetoSala()[i].getNombreObjeto());
			System.out.println(GestorPartida.getObjetoSala()[i].getNombre());
		}
	}
}
