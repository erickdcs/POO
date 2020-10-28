import java.io.FileNotFoundException;
import java.io.IOException;

public class mainAuxiliar {
	public static void main(String argv[]) throws FileNotFoundException, IOException {
		LecturaFicheros.AllLecture("C:\\Users\\erick\\pruebaPOO.txt");
		int a;
		GestorPartida.mostarJugadores();
		GestorPartida.mostarLocalizaciones();
	}
	
}
/*
	jardin ------
	|		      \
	|				comedor ----- cocina
	|             /
	dormitorio --





*/
/*
Objetos pan[] = {new Objetos("Pan")};
int array[] = {1,2};

Localizaciones local1 = new Localizaciones("city", 1, pan);
Personajes Erick = new Personajes ("Erick", 1);
Personajes Jorge = new Personajes ("Jorge", 1, 2, array);
Jorge.objects[0] = pan[0];
Personajes allPlayers[] = {Erick, Jorge};

allPlayers[0].PedirObjecto(allPlayers);
allPlayers[1].darObjeto(allPlayers);
System.out.println("Hola");
System.out.println(allPlayers[0].objects[0].getNombre());
*/