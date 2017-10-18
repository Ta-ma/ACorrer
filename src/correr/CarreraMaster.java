package correr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CarreraMaster
{
	public Corredor[] corredores;
	public RangoEdad[] rangosEdadF;
	public RangoEdad[] rangosEdadM;
	public String[] premiosF;
	public String[] premiosM;
	public int[] llegadas;
	
	public CarreraMaster (File archivo) throws IOException
	{
		Scanner sc = new Scanner(archivo);
		
		int cantCorredores = sc.nextInt();
		int cantREF = sc.nextInt();
		int cantREM = sc.nextInt();
		int cantLlegadas = sc.nextInt();
		
		corredores = new Corredor[cantCorredores];
		rangosEdadF = new RangoEdad[cantREF];
		rangosEdadM = new RangoEdad[cantREM];
		llegadas = new int[cantLlegadas];
		
		for (int i = 0; i < cantREF; i++)
		{
			rangosEdadF[i] = new RangoEdad(sc.nextInt(), sc.nextInt());
			//System.out.println(rangosEdadF[i]);
		}
		
		for (int i = 0; i < cantREM; i++)
		{
			rangosEdadM[i] = new RangoEdad(sc.nextInt(), sc.nextInt());
			//System.out.println(rangosEdadM[i]);
		}
		
		//sc.nextLine();
		for (int i = 0; i < cantCorredores; i++)
		{
			int edad = sc.nextInt();
			String linea = sc.nextLine();
			corredores[i] = new Corredor(edad, linea.charAt(1));
			//System.out.println(corredores[i]);
		}
		
		for (int i = 0; i < cantLlegadas; i++)
		{
			llegadas[i] = sc.nextInt();
			//System.out.println(llegadas[i]);
		}
		
		sc.close();
	}
	
	public void otorgarMedallas () throws Exception
	{
		if (corredores.length == 0 || rangosEdadF.length == 0 || rangosEdadM.length == 0)
			throw new Exception("Error en el input");
		
		if (corredores.length < llegadas.length)
			throw new Exception("Hay más llegadas que corredores!");
		
		for (int i = 0; i < llegadas.length; i++)
		{
			// busco en que rango de edad está
			char sexo = corredores[llegadas[i] - 1].getSexo();
			int edad = corredores[llegadas[i] - 1].getEdad();
			int numRangoEdad = 0;
			
			if (sexo == 'F')
			{
				while (numRangoEdad < rangosEdadF.length && 
						!(edad >= rangosEdadF[numRangoEdad].getEdadInicial() && 
						edad <= rangosEdadF[numRangoEdad].getEdadFinal()))
				{
					numRangoEdad++;
				}
				
				if (numRangoEdad == rangosEdadF.length - 1 && 
						!(edad >= rangosEdadF[numRangoEdad].getEdadInicial() && 
						edad <= rangosEdadF[numRangoEdad].getEdadFinal()))
					throw new Exception ("No hay rango de edad para este jugador");
				
				rangosEdadF[numRangoEdad].agregarGanador(llegadas[i]);
			}
			else
			{
				while (numRangoEdad < rangosEdadM.length && 
						!(edad >= rangosEdadM[numRangoEdad].getEdadInicial() && 
						edad <= rangosEdadM[numRangoEdad].getEdadFinal()))
				{
					numRangoEdad++;
				}
				
				if (numRangoEdad == rangosEdadM.length - 1 && 
						!(edad >= rangosEdadM[numRangoEdad].getEdadInicial() && 
						edad <= rangosEdadM[numRangoEdad].getEdadFinal()))
					throw new Exception ("No hay rango de edad para este jugador");
				
				rangosEdadM[numRangoEdad].agregarGanador(llegadas[i]);
			}
		}
	}
	
	public void mostrarPodios (File archivo) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(new File(".//OUT//" + archivo.getName().replaceAll(".in", ".out"))));
		
		for (int i = 0; i < rangosEdadF.length; i++)
		{
			int[] ganadores = rangosEdadF[i].getGanadores();
			out.println((i + 1)  + " " + ganadores[0] + " "+ ganadores[1] + " "+ ganadores[2]);
		}
		
		for (int i = 0; i < rangosEdadM.length; i++)
		{
			int[] ganadores = rangosEdadM[i].getGanadores();
			out.println((i + 1) + " " + ganadores[0] + " "+ ganadores[1] + " "+ ganadores[2]);
		}
		
		out.close();
	}

}
