import java.io.FileNotFoundException;
import java.io.IOException;

public class mainAuxiliar {
	public static void main(String argv[]) throws FileNotFoundException, IOException {
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		int a;
		GestorPartida.mostarJugadores();
		GestorPartida.mostarLocalizaciones();
		GestorPartida.jugadores[1].setLocalizacion("Comedor");
		//GestorPartida.jugadores[0].PedirObjecto();
		for(int i = 0; GestorPartida.objects[i]!= null; i++) {
			System.out.println(GestorPartida.objects[i].getNombre());
		}
	}
	
}
