//Båtprogram.java
//Kjøreprogram

import javax.swing.*;
import java.awt.event.*;

public class Båtprogram
{
	public static void main( String[] args )
	{
		final Båtarkiv vindu = new Båtarkiv();
		vindu.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				vindu.skrivTilFil();	//Lagrer data når vindu lukkes
				System.exit( 0 );		//Exit program ved lukking av vindu
			}
		} );
	}
}
