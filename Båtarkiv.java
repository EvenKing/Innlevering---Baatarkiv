//Båtarkiv.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Båtarkiv extends JFrame
{
	private JTextField navnFelt, adrFelt, mNrFelt, regNrFelt, merkeFelt, typeFelt, årsmodellFelt,
				lengdeFelt, HKFelt, fargeFelt, eierFelt, skiftReg, gamEierFelt, nyEierFelt, finnFelt, fjernFelt, fjernEierFelt;
	private JButton regPerson, regBåteier, visReg, fjernBåt, fjernBåteier, finnBåteier, regEierskifte;
	private JTextArea utskriftsområde;
	private Båteierliste båteierliste;
	private String filnavn;

	public Båtarkiv()
	{
		super( "Båtarkiv" );

		//Definerer tekstfelt og knapper
    navnFelt = new JTextField( 12 );
		adrFelt = new JTextField( 15 );
		mNrFelt = new JTextField( 4 );
		regNrFelt = new JTextField( 6 );
		merkeFelt = new JTextField( 10 );
		typeFelt = new JTextField( 10 );
		årsmodellFelt = new JTextField( 4 );
		lengdeFelt = new JTextField( 3 );
		HKFelt = new JTextField( 4 );
		fargeFelt = new JTextField( 10 );
		eierFelt = new JTextField( 4 );
		skiftReg = new JTextField( 6 );
		gamEierFelt = new JTextField( 4 );
		nyEierFelt = new JTextField( 4 );
		finnFelt = new JTextField( 10 );
		fjernFelt = new JTextField( 10 );
		fjernEierFelt = new JTextField( 18 );
		regPerson = new JButton( "Registrer ny person" );
		regBåteier = new JButton( "Registrer ny båteier" );
		visReg = new JButton( "Vis båtregister" );
		fjernBåt = new JButton( "Fjern båt" );
		fjernBåteier = new JButton( "Fjern båteier" );
		finnBåteier = new JButton( "Finn båteier" );
		regEierskifte = new JButton( "Registrer eierskifte" );

		//Definerer utseendet på dashbordet
    Container c = getContentPane();
		c.setLayout( new FlowLayout() );

		c.add( new JLabel( "______________________________ Eierinformasjon ______________________________" ) ); // 45 -'ere hver side

		c.add( new JLabel( "Navn:" ) );
		c.add( navnFelt );
		c.add( new JLabel( "Adresse:" ) );
		c.add( adrFelt );
		c.add( new JLabel( "Medlemsnr:" ) );
		c.add( mNrFelt );
		c.add( regPerson );

		c.add( new JLabel( "______________________________ Båtinformasjon ______________________________" ) );

		c.add( new JLabel( "Reg.Nr:" ) );
		c.add( regNrFelt );
		c.add( new JLabel( "Merke:" ) );
		c.add( merkeFelt );
		c.add( new JLabel( "Type:" ) );
		c.add( typeFelt );
		c.add( new JLabel( "Årsmodell:" ) );
		c.add( årsmodellFelt );
		c.add( new JLabel( "Lengde(fot):" ) );
		c.add( lengdeFelt );
		c.add( new JLabel( "Hestekrefter:" ) );
		c.add( HKFelt );
		c.add( new JLabel( "Skrogfarge:" ) );
		c.add( fargeFelt );
		c.add( new JLabel( "Eiers medlemsnr:") );
		c.add( eierFelt );
		c.add( regBåteier );
		c.add( visReg );

		c.add( new JLabel( "________________________________ Finn/fjern ________________________________" ) );

		c.add( new JLabel( "Registreringsnr:" ) );
		c.add( finnFelt );
		c.add( finnBåteier );

		c.add( new JLabel( "Registreringsnr:" ) );
		c.add( fjernFelt );
		c.add( fjernBåt );

		c.add( new JLabel( "Medlemsnr:" ) );
		c.add( fjernEierFelt );
		c.add( fjernBåteier );

		c.add( new JLabel( "________________________________ Skift eier ________________________________" ) );

		c.add( new Label( "Registreringsnr:" ) );
		c.add( skiftReg );
		c.add( new JLabel( "Skift fra:" ) );
		c.add( gamEierFelt );
		c.add( new JLabel( "Skift til:" ) );
		c.add( nyEierFelt );
		//c.add( new JLabel( "            ") );
		c.add( regEierskifte );

    //Setter effekt til knappetrykk
		Knappelytter lytter = new Knappelytter();

		regPerson.addActionListener( lytter );
		regBåteier.addActionListener( lytter );
		visReg.addActionListener( lytter );
		fjernBåt.addActionListener( lytter );
		fjernBåteier.addActionListener( lytter );
		finnBåteier.addActionListener( lytter );
		regEierskifte.addActionListener( lytter );

		utskriftsområde = new JTextArea( 15, 35 );
		utskriftsområde.setEditable( false );
		c.add( new JScrollPane( utskriftsområde ) );

		filnavn = "Båt1.data";
		lesFil();
		visRegister();


		setSize( 450, 700 );
		setVisible( true );
	}

	private void visFeilmelding( String melding )
	{
		//Standard for feilmelding
		JOptionPane.showMessageDialog(this, melding, "Problem", JOptionPane.ERROR_MESSAGE);
	}
	private void visMelding( String melding )
	{
		JOptionPane.showMessageDialog( this, melding );
	}

  //Leser fra tekstfil og henter liste
	public void lesFil()
	{
		try( ObjectInputStream inn = new ObjectInputStream( new FileInputStream( filnavn ) ) )
		{
			båteierliste = ( Båteierliste ) inn.readObject();
		}
		catch( FileNotFoundException fnfe )
		{
			visFeilmelding( "Finner ikke fil. Oppretter ny båteierliste.\n");
			båteierliste = new Båteierliste();
		}
		catch( ClassNotFoundException cnfe )
		{
			visFeilmelding( "Finner ikke klasse. Oppretter tom båteierliste.\n" );
			båteierliste = new Båteierliste();
		}
		catch( IOException ioe )
		{
			visFeilmelding( "Får ikke lest datafil. Oppretter tom båteierliste.\n");
			båteierliste = new Båteierliste();
		}
	}

  //Skriver til tekstfil for å lagre båter
	public void skrivTilFil()
	{
		try( ObjectOutputStream ut = new ObjectOutputStream( new FileOutputStream( filnavn ) ) )
		{
			ut.writeObject( båteierliste );
		}
		catch( NotSerializableException nse )
		{
			visFeilmelding( "Objektet er ikke serialisert!" );
		}
		catch( IOException e )
		{
			visFeilmelding( "Problem med utskrift til fil." );
		}
	}

  //Registrerer ny båt
	public void nyBåteier()
	{
		if( regNrFelt.getText().equals("") || merkeFelt.getText().equals("") || typeFelt.getText().equals("") || årsmodellFelt.getText().equals("") ||
			lengdeFelt.getText().equals("") || HKFelt.getText().equals("") || fargeFelt.getText().equals("") || eierFelt.getText().equals("") )
		{
			visFeilmelding( "Fyll ut alle nødvendige felter. ");
			return;
		}

		String rnr = regNrFelt.getText();
		String bme = merkeFelt.getText();
		String bt = typeFelt.getText();
		int år = Integer.parseInt(årsmodellFelt.getText());
		int bl= Integer.parseInt(lengdeFelt.getText());
		int hk = Integer.parseInt(HKFelt.getText());
		String bf = fargeFelt.getText();

		int emnr = Integer.parseInt( eierFelt.getText());

		if( båteierliste.finnBåteier(rnr) != null )
		{
			utskriftsområde.setText("Båten finnes allerede i registeret");
			return;
		}

		Båt b = new Båt( rnr, bme, bt, år, bl, hk, bf );

		if( båteierliste.finnBåteier( emnr ) == null )
		{
			utskriftsområde.setText( "Finner ikke medlemsnr " + emnr );
			return;
		}

		Båteier eier = båteierliste.finnBåteier( emnr );

		eier.nyBåt( b );

		visMelding( "Båt med reg.nr " + rnr + " registrert på medlemsnr " + emnr );
		slettFelter();
	}

  //Funksjon for å søke opp en båt
	public void finnBåteier()
	{
		if( finnFelt.getText().equals("") )
		{
			visFeilmelding( "Du må angi medlemsnr." );
			return;
		}

		String be = finnFelt.getText();

		Båteier e = båteierliste.finnBåteier( be );

		if( e == null )
			utskriftsområde.setText( "Finner ikke båt med registreringsnr. " + be );
		else
			utskriftsområde.setText( e.toString() );

		finnFelt.setText( "" );
	}

  //Funksjon for å slette en båt fra arkivet
	public void slettBåt()
	{
		if( fjernFelt.getText().equals("") )
		{
			visFeilmelding( "Du må angi registreringsnr.");
			return;
		}
		String r = fjernFelt.getText();

		if( båteierliste.finnBåteier(r) == null )
			utskriftsområde.setText( r + " finnes ikke i registeret." );
		else
		{
			utskriftsområde.setText( båteierliste.finnBåteier(r).slett(r) );
			fjernFelt.setText( "" );
		}
	}

  //Funksjon for å slette båteier fra arkivet
	public void slettBåteier()
	{
		if( fjernEierFelt.getText().equals("") )
		{
			visFeilmelding( "Du må angi medlemsnr." );
			return;
		}
		int nr = Integer.parseInt( fjernEierFelt.getText() );

		utskriftsområde.setText( båteierliste.slettEier(nr) );
	}

  //Skriver ut liste med registrerte båteiere
	public void skrivListe()
	{
		String s = båteierliste.toString();

		if( s == "" )
			utskriftsområde.setText( "Ingen båteier registrert" );
		else
			utskriftsområde.setText( s );
	}

  //Registrerer ny person i arkivet
	public void nyPerson()
	{
		if( navnFelt.getText().equals("") || adrFelt.getText().equals("") )
		{
			visFeilmelding( "Du må fylle ut navn og adresse." );
			return;
		}

		String navn = navnFelt.getText();
		String adresse = adrFelt.getText();
		//int medlemnr = Integer.parseInt( mNrFelt.getText() );

		Person p = new Person( navn, adresse ); //medlemnr

		int medlemnr = p.getMNr();

		utskriftsområde.setText( båteierliste.settInnBåteier( p, medlemnr ) );
		navnFelt.setText( "" );
		adrFelt.setText( "" );
	}

  //Funksjon for å overgi eierskap av båt til en annen
	public void skiftEier()
	{
		if( skiftReg.getText().equals("") || gamEierFelt.getText().equals("") || nyEierFelt.getText().equals("") )
		{
			visFeilmelding( "Du må fylle ut registreringsnummer og medlemsnummer." );
			return;
		}

		String rNr = skiftReg.getText();
		int gamNr = Integer.parseInt( gamEierFelt.getText() );
		int nyNr = Integer.parseInt( nyEierFelt.getText() );

		utskriftsområde.setText( båteierliste.eierSkifte( rNr, gamNr, nyNr ) );

		skiftReg.setText("");
		gamEierFelt.setText("");
		nyEierFelt.setText("");
	}

  //Skriver ut registeret på skjermen
	public void visRegister()
	{
		String register = båteierliste.toString();

		if( register == "" )
			utskriftsområde.setText("Ingen båteier i registeret.");
		else
			utskriftsområde.setText( register );
		//båteierliste.skrivListe( utskriftsområde );
	}

  //Funksjon for å tømme alle tekstfelt
	public void slettFelter()
	{
		navnFelt.setText("");
		adrFelt.setText("");
		mNrFelt.setText("");
		regNrFelt.setText("");
		merkeFelt.setText("");
		typeFelt.setText("");
		årsmodellFelt.setText("");
		lengdeFelt.setText("");
		HKFelt.setText("");
		fargeFelt.setText("");
		eierFelt.setText("");
		skiftReg.setText("");
		gamEierFelt.setText("");
		nyEierFelt.setText("");
		finnFelt.setText("");
		fjernFelt.setText("");
		fjernEierFelt.setText("");

	}

  //Setter funksjon opp mot knappetrykk
	private class Knappelytter implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			try
			{
				if( e.getSource() == regPerson )
					nyPerson();
				else if( e.getSource() == regBåteier )
					nyBåteier();
				else if( e.getSource() == visReg )
					visRegister();
				else if( e.getSource() == fjernBåt || e.getSource() == fjernFelt )
					slettBåt();
				else if( e.getSource() == fjernBåteier )
					slettBåteier();
				else if( e.getSource() == finnBåteier || e.getSource() == finnFelt )
					finnBåteier();
				else if( e.getSource() == regEierskifte )
					skiftEier();
			}
			catch( NumberFormatException nfe )
			{
				visFeilmelding( "Årstall og medlemsnr skal være et heltall" );
			}
		}
	}
}

