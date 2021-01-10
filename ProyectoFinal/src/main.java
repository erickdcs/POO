import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		GestorPartida.getJugadores()[2].setSala("dormitorio");
		GestorPartida.getJugadores()[0].setSala("dormitorio");
		GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(1);
		//GUI interfaz = new GUI(1);
		for(int i =0; GestorPartida.getJugadores()[1].getCreencias().getNombrePersona()[i] !=null; i++) {
			System.out.println(GestorPartida.getJugadores()[1].getCreencias().getNombrePersona()[i] + "      "  + GestorPartida.getJugadores()[1].getCreencias().getSalaPersona()[i] + "\n");
		}
		
	}
}
