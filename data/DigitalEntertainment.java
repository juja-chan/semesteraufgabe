package data;

import java.util.zip.DataFormatException;

public abstract class DigitalEntertainment {
	private String name, regieseur;
	private int jahr;
	private boolean gesehen;
	private int bewertung;

	public DigitalEntertainment(String Name, String regisseur, int jahr, boolean gesehen, int bewertung) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException{
		if (checkName(name))
			this.name = name;
		throw new DataFormatException("zu wenig zeichen");

	}

	public boolean checkName(String name) {
		if (name == null || name.equals("")) {
			return false;
		}
		return true;
	}

	public String getRegieseur() {
		return regieseur;
	}

	public void setRegieseur(String regieseur) throws DataFormatException{
		if(checkRegieseur(regieseur))
		this.regieseur = regieseur;
		throw new DataFormatException("zu wenig zeichen");
	}

	public boolean checkRegieseur(String regieseur) {
		if (regieseur == null || regieseur.equals("")) {
			return false;
		}
		return true;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) throws DataFormatException{
		if(checkJahr(jahr))
		this.jahr = jahr;
		throw new DataFormatException("Film wurden damals noch nicht erfunden - spwateres Jahr angeben");
	}

	public boolean checkJahr(int jahr) {
		if (jahr < 1895) {
			return false;
		}
		return true;
	}

	public boolean isGesehen() {
		return gesehen;
	}

	public void setGesehen(boolean gesehen) {
		this.gesehen = gesehen;
	}

	public int getBewertung() {
		return bewertung;
	}

	public void setBewertung(int bewertung) throws DataFormatException {
		if(checkBewertung(bewertung))
		this.bewertung = bewertung;
		throw new DataFormatException("falsche bewertung");
	}

	public boolean checkBewertung(int bewertung) {
		if (bewertung < 1 || bewertung > 10) {
			return false;
		}
		return true;
	}

}

