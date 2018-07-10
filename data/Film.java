package data;

import java.util.zip.DataFormatException;

public class Film extends DigitalEntertainment {

	public Film(String Name, String regisseur, int jahr, boolean gesehen, int bewertung) throws DataFormatException {
		super(Name, regisseur, jahr, gesehen, bewertung);

	}

	@Override
	public String toString() {
		return getName() + " (Regisseur: " + getRegisseur() + " - Jahr: " + getJahr() + " - Bewertung: " + getBewertung()
				+ " - Gesehen: " + isGesehen() + ")";
	}

	@Override
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
