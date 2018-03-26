//Båteier.java
import java.io.*;

public abstract class Båteier implements Serializable
{
	private String navn, adresse;
	private Båtliste båtliste;
	Båteier neste;

	/*public Båteier()
	{
	}*/

	public Båteier( String n, String a )
	{
		navn = n;
		adresse = a;
		båtliste = new Båtliste();
		neste = null;
	}

	public void nyBåt( Båt ny )
	{
		båtliste.settNyInn( ny );
	}

	public String slett( String nr )
	{
		if( båtliste.slett( nr ) )
    {
			return "Båt med registreringsnr " + nr + " har blitt fjernet.";
    }
		return "Båt ble ikke fjernet";
	}

	public Båt finn( String reg )
	{
		if( båtliste.finn(reg) == null )
		{
      return null;
    }
		return båtliste.finn(reg);
	}

	public Båtliste getBåtliste()
	{
		return båtliste;
	}

	public int getMNr()
	{
		return -1;
	}

	public String toString()
	{
		String s = "Båteier: " + navn + "\nAdresse: " + adresse + "\nBåter: \n------";
		s += båtliste.toString() + "\n\n---------------------------------";
		return s;
	}
} //slutt på Båteier.java

class Person extends Båteier
{
	private int medlemsnr;
	private static int nestenr = 1;

	public Person( String n, String a ) // int medlemsnr
	{
		super( n, a );
		medlemsnr = nestenr;
		nestenr++;
	}

	public int getMNr()
	{
		return medlemsnr;
	}

	public String toString()
	{
		return "\nMedlemsnr: " + medlemsnr + "\n" + super.toString();
	}
}
