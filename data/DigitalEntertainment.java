package data;

import java.util.zip.DataFormatException;

public abstract class DigitalEntertainment {
	private String name, regisseur;
	private int jahr;
	private boolean gesehen;
	private int bewertung;
	private int id;

	public DigitalEntertainment(String name, String regisseur, int jahr, boolean gesehen, int bewertung)
			throws DataFormatException {

		setName(name);
		setRegisseur(regisseur);
		setJahr(jahr);
		setGesehen(gesehen);
		setBewertung(bewertung);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (!checkName(name))
			throw new DataFormatException("zu wenig zeichen");
		this.name = name;

	}

	public boolean checkName(String name) {
		if (name == null || name.equals(""))
			return false;
		return true;
	}

	public String getRegisseur() {
		return regisseur;
	}

	public void setRegisseur(String regisseur) throws DataFormatException {
		if (!checkRegisseur(regisseur))
			throw new DataFormatException("zu wenig zeichen");
		this.regisseur = regisseur;
	}

	public boolean checkRegisseur(String regisseur) {
		if (regisseur == null || regisseur.equals(""))
			return false;
		return true;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) throws DataFormatException {
		if (!checkJahr(jahr))
			throw new DataFormatException("Film wurden damals noch nicht erfunden - späteres Jahr angeben");
		this.jahr = jahr;

	}

	public boolean checkJahr(int jahr) {
		if (jahr < 1895)
			return false;
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
		if (!checkBewertung(bewertung))
			throw new DataFormatException("falsche bewertung");
		this.bewertung = bewertung;

	}

	public boolean checkBewertung(int bewertung) {
		if (bewertung < 0 || bewertung > 10)
			return false;
		return true;
	}

}
