package correr;

import java.io.File;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		File dir = new File(".//IN//");
		
		for (File archivo : dir.listFiles())
		{
			CarreraMaster carrera = new CarreraMaster(archivo);
			carrera.otorgarMedallas();
			carrera.mostrarPodios(archivo);
		}
	}
}
