package correr;

public class RangoEdad
{
	private int edadInicial;
	private int edadFinal;
	private int ganadores[];
	private int cantGanadores;
	
	public RangoEdad (int edadInicial, int edadFinal)
	{
		this.edadFinal = edadFinal;
		this.edadInicial = edadInicial;
		ganadores = new int[3];
	}

	public int getEdadInicial()
	{
		return edadInicial;
	}

	public int getEdadFinal()
	{
		return edadFinal;
	}

	public boolean agregarGanador (int numCorredor)
	{
		if (cantGanadores < 3)
		{
			ganadores[cantGanadores] = numCorredor;
			cantGanadores++;
			return true;
		}
		
		return false;
	}

	public int[] getGanadores()
	{
		return ganadores;
	}

	@Override
	public String toString()
	{
		return "RangoEdad [edadInicial=" + edadInicial + ", edadFinal=" + edadFinal + "]";
	}
}
