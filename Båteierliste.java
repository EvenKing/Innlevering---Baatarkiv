//Båteierliste.java
import java.io.*;
import javax.swing.*;

public class Båteierliste implements Serializable
{
	private Båteier første;

	public Båteierliste()
	{
		første = null;
	}

	public String settInnBåteier( Båteier ny, int nr )
	{
		if( finnBåteier(nr) != null )
		{
      return "Eier " + nr + " er allerede registrert.";
    }
		ny.neste = første;
		første = ny;
		return "Eier " + nr + " er registrert.";
	}

	public Båteier finnBåteier( int nr )
	{
		if( første == null )
    {
			return null;
    }
		Båteier løper = første;
		while( løper != null )
		{
			if( nr == løper.getMNr() )
			{
        return løper;
      }
			løper = løper.neste;
		}
		return null;
	}

	public Båteier finnBåteier( String r )
	{
		if( første == null )
		{
      return null;
    }
		Båteier løper = første;
		while( løper != null )
		{
			if( løper.finn(r) != null )
			{
        return løper;
      }
			løper = løper.neste;
		}
		return null;
	}

	public String slettEier( int nr )
	{
		if( første == null )
		{
      return "Listen er tom.";
    }
		Båteier eier = finnBåteier(nr);
		if( eier == null )
		{
      return "Finner ikke båteier " + nr;
    }
		if( !eier.getBåtliste().tomListe() )
		{
      return "Båteier " + nr + " har ennå båt registrert og kan ikke fjernes.";
    }
		if( første.getMNr() == nr )
		{
			første = første.neste;
			return "Båteier " + nr + " har blitt fjernet.";
		}

		Båteier løper = første;
		while(løper.neste != null )
		{
			if( løper.neste.getMNr() == nr )
			{
				løper.neste = løper.neste.neste;
				return "Båteier " + nr + " har blitt fjernet.";
			}
		}
		return "Båteier " + nr + " har ikke blitt fjernet.";
	}

	public void skrivListe( JTextArea eier )		//public String skrivListe
	{
		if( første == null )
		{ 
      eier.append( "Ingen båteier registrert." );
		}
    else
		{
			eier.setText( "" );
			Båteier løper = første;
			while( løper != null )
			{
				eier.append( løper.toString() + "\n" );
				løper = løper.neste;
			}
		}
	}
	public String eierSkifte( String reg, int nrGam, int nrNy )
	{
		Båteier gammel = finnBåteier( nrGam );
		Båteier ny = finnBåteier( nrNy );
		if( gammel == null || ny == null )
		{
      return "Finner ikke angitt eier.";
    }
		Båt båt = gammel.finn(reg);
		if( båt == null )
		{
      return "Finner ikke angitt båt.";
    }
		gammel.slett(reg);
		ny.nyBåt(båt);
		return "Eierskiftet er registrert.";
	}

	public String toString()
	{
		Båteier løper = første;
		String t = "";
		while( løper != null )
		{
			t += løper.toString();
			løper = løper.neste;
		}
		return t;
	}
}
