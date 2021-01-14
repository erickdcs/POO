import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String argv[]){
		try {
			GestorPartida.crearPartida();
		}
		catch(FileNotFoundException f) {
			f.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ExcepcionesFich ex) {
			ex.printStackTrace();
		}
	}
}
