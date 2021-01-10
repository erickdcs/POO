import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		//GestorPartida.getJugadores()[0].setSala("dormitorio");
		//GestorPartida.getJugadores()[0].getPeticiones()[0] = new Peticiones("pan", "Esther");
		
		GUI interfaz = new GUI(1);

	}
}
