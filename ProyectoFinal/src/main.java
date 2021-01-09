import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
<<<<<<< HEAD
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		GestorPartida.getJugadores()[0].setSala("dormitorio");
		GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(1);
		GUI interfaz = new GUI(1);
=======
		LecturaFicheros.AllLecture("C:\\Users\\jorge\\pruebaPOO.txt");
		
		GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(0);
>>>>>>> 167e33fbc0a2a0a95ec9d1a74716c81c90652415
		
		GUI interfaz = new GUI(0);
	
	}
}
