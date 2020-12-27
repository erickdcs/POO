import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		GestorPartida.getJugadores()[1].setSala("comedor");
		GestorPartida.getJugadores()[1].hacerPeticion();
		GestorPartida.hud(GestorPartida.getJugadores()[0]);
		
		
	}
}
