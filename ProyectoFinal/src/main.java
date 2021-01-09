import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\jorge\\pruebaPOO.txt");
		GestorPartida.getJugadores()[0].setSala("dormitorio");
		GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(1);
		GUI interfaz = new GUI(1);

	}
}
