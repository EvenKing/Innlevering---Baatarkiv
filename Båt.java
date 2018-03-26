//Båt.java
import java.io.*;

public class Båt implements Serializable		//var abstract
{
	private int lengde, årsmodell, hestekrefter;
	private String regNr, merke, type, skrogfarge;
	Båt neste;

	/*public Båt()
	{
	}*/

	public Båt( String nr, String m, String t, int år, int l, int hk, String sf )
	{
		regNr = nr;
		merke = m;
		type = t;
		årsmodell = år;
		lengde = l;
		hestekrefter = hk;
		skrogfarge = sf;
		neste = null;
	}

	public String getRegNr()
	{
		return regNr;
	}

	public String toString()
	{
		return "\nRegnr: " + regNr +
				"\nMerke: " + merke +
				"\nType: " + type +
				"\nÅrsmodell: " + årsmodell +
				"\nLengde i fot: " + lengde +
				"\nHestekrefter: " + hestekrefter +
				"\nSkrogfarge: " + skrogfarge + "\n------------";
	}
}
