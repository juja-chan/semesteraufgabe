package data;

/**
 * Ausnahme um Anzuzeigen, dass ein Benutzer ung�ltige Attributwerte eingegeben
 * hat, welche Datenstrukturinvarianten widersprechen
 * 
 * @author derBe
 *
 */
public class IllegalInputException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Konstruiert eine Ausnahme mit spezifischer Fehlernachricht
     * 
     * @param message
     *            die Fehlernachricht
     * @param input
     *            der vom Benutzer �bergebene ung�ltige Wert in String-Form
     */
    public IllegalInputException(String message, String input) {
	super(message + ": " + input);
    }
}
