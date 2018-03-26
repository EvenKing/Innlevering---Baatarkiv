//Båtliste.java
import java.io.*;

public class Båtliste implements Serializable
{
	//private static final long serialVersionUID = 678L;
	private Båt første;

	public Båtliste()
	{
		første = null;
	}

	public void settNyInn( Båt ny )
	{
		ny.neste = første;
		første = ny;
	}

	public Båt finn( String b )
	{
		if( første == null )
    {
			return null;
    }
		Båt løper = første;

		while( løper != null )
		{
			if( løper.getRegNr().equals(b) )		//if( løper.getRegNr().equals(b) )
			{
        return løper;
      }
			if( løper == null )
			{
        return null;
      }
			løper = løper.neste;
		}
		return null;
	}

	public boolean slett( String nr )
	{
		if( tomListe() )
		{
			return false;
		}

		if( første.getRegNr().equals(nr) )	//if( første.getRegNr().equals(nr) )
		{
			første = første.neste;
			return true;
		}

		Båt løper = første;

		while( løper.neste != null )
		{
			if( løper.neste.getRegNr().equals(nr) )	//if( løper.neste.getRegNr().equals(nr)
			{
				løper.neste = løper.neste.neste;
				return true;
			}
			løper = løper.neste;
		}
		return false;
	}

	public boolean tomListe()
	{
		if( første == null )
    {
			return true;
    }
		else
    {
			return false;
    }
	}

	public String toString()
	{
		String s = "";
		if( tomListe() )
    {
			return "\nIngen båter registrert.";
    }
		else
		{
			Båt løper = første;
			while( løper != null )
			{
				s += løper.toString();
				løper = løper.neste;
			}
			return s;
		}
	}
}
