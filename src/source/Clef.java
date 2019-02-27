package source;

public class Clef {
    private final int position; //posizione sul pentagramma
    private final int mainNote; //il nome della nota sulla riga centrale del pentagramma
    private final String unicodeSign; //stringa unicode che corrisponde al disegno della chiave, font: Bravura

    public Clef(int position, int mainNote, String unicodeSign) {
        this.position = position;
        this.mainNote = mainNote;
        this.unicodeSign = unicodeSign;
    }

    public int getPosition() {
        return position;
    }

    public int getMainNote() {
        return mainNote;
    }

    public String getUnicodeSign() {
        return unicodeSign;
    }
    
}
