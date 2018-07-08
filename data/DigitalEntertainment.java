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
			throw new DataFormatException("Zu wenig zeichen");
		this.name = name;

	}

	public boolean checkName(String name) {
		return (name == null || name.equals("")) ? false : true;
	}

	public String getRegisseur() {
		return regisseur;
	}

	public void setRegisseur(String regisseur) throws DataFormatException {
		if (!checkRegisseur(regisseur))
			throw new DataFormatException("Zu wenig zeichen");
		this.regisseur = regisseur;
	}

	public boolean checkRegisseur(String regisseur) {
		return (regisseur == null || regisseur.equals("")) ? false : true;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) throws DataFormatException {
		if (!checkJahr(jahr))
			throw new DataFormatException("Filme wurden damals noch nicht erfunden - späteres Jahr angeben");
		this.jahr = jahr;

	}

	public boolean checkJahr(int jahr) {
		return (jahr < 1895) ? false : true;
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
			throw new DataFormatException("Bewertung zwischen 1 und 10 angeben");
		this.bewertung = bewertung;
	}

	public boolean checkBewertung(int bewertung) {
		return (bewertung < 0 || bewertung > 10) ? false : true;
	}

	public String toString() {
		return getName() + " (Regisseur: " + getRegisseur() + " - Jahr: " + getJahr() + "Bewertung: " + getBewertung()
				+ "Gesehen: " + isGesehen() + ")";
	}

	public boolean equals(Object o) {
		if (o != null) {
			if (o.getClass().equals(getClass())) {
				DigitalEntertainment d = (DigitalEntertainment) o;
				return (d.getName().equals(getName()) && d.getRegisseur().equals(getRegisseur())
						&& d.getBewertung() == getBewertung() && d.getJahr() == getJahr());
			}
		}
		return false;
	}

}
