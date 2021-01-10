import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String argv[]) throws FileNotFoundException, IOException{
		LecturaFicheros.AllLecture("C:\\Users\\jorge\\pruebaPOO.txt");

		int jugadorPersona = 0;
				//(int) (Math.random()*GestorPartida.getContJugadores());
		
		GestorPartida.getJugadores()[jugadorPersona].setIA(false);		
		GUI interfaz = new GUI(jugadorPersona);		
	}
}
