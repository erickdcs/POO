import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\jorge\\pruebaPOO.txt");
		GestorPartida.instanciarCreencias();
		//GestorPartida.getJugadores()[0].setSala("Jardin");
		//GestorPartida.getJugadores()[1].setSala("Jardin");
		GestorPartida.actualizarCreencias(0);
		GUI interfaz = new GUI(0);
		
		System.out.println(GestorPartida.getSalas()[1].getSalasVecinas().length);
	
	}
}
