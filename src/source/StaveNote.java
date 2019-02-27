package source;

public class StaveNote {
    private int name; //nome della nota (0 = DO, 6 = SI)
    private boolean sharp; // se è diesis
    private boolean flat; //se è bemolle
    private int position; // posizione sul pentagramma (0 è la riga centrale)
    
    /*posizione:
                   ...
                   -6
                   -5
    -------------- -4 ---------------
    |              -3               
    -------------- -2 ---------------
    |              -1               
    --------------  0 ---------------
    |               1               
    --------------  2 ---------------
    |               3               
    --------------  4 ---------------
                    5
                    6
                   ...
    */
    
    
    //costruttore vuoto
    public StaveNote() {
        
    }
    
    //costruttore usato quando la nota viene generata random conoscendo la chiave
    //il nome viene scelto in base alla posizione sul pentagramma e alla chiave
    public StaveNote(int clef, boolean sharp, boolean flat, int position) {
        setSharp(sharp);
        setFlat(flat);
        this.position = position;
        setName(clef); //la nota non ha un nome se non si conosce la chiave
    }
    
    // costruttore usato quando la nota è suonata dall'utente
    // il nome della nota è conosciuto(perché scelto dall'utente)
    // la posizione non ha importanza
    public StaveNote(int name, boolean isSharp) {
        this.sharp = isSharp;
        this.name = name;
    }
    
    public void setName(int clef){
        int tempName = clef - position;
        if(tempName <= 6 && tempName >= 0) 
            this.name = tempName;
        //se il nome della nota è maggiore di 6, la riporto nel range 0...6
        else if(tempName >= 7)
            this.name =  tempName - 7;
        //se il nome della nota è minore di 0, la riporto nel range 0...6
        else
            this.name =  tempName + 7;
        check_E_B_sharp();
        check_F_C_flat();
    }
        
    //se dopo il calcolo del nome la nota è MI# o SI# la cambio in MI/SI
    private void check_E_B_sharp() {
        if((this.name == 2 || this.name == 6) && isSharp())
            this.sharp = false;
    }
    
    //se dopo il calcolo del nome la nota è FAb o DOb la cambio in FA/DO
    private void check_F_C_flat() {
        if((this.name == 0 || this.name == 3) && isFlat())
            this.flat = false;
    }

    public int getName() {
        return name;
    }
    
    public boolean isSharp() {
        return sharp;
    }

    public void setSharp(boolean sharp) {
        this.sharp = sharp;
        //se è sia diesis che bemolle tolgo il bemolle
        if(this.flat && this.sharp)
            this.flat = false;
    }

    public boolean isFlat() {
        return flat;
    }

    public void setFlat(boolean flat) {
        this.flat = flat;
        //se è sia diesis che bemolle tolgo il diesis
        if(this.flat && this.sharp)
            this.sharp = false;
    }
    
    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaveNote other = (StaveNote) obj;
        //controllo casi del tipo F# == Gb
        if(this.sharp && other.flat && this.name == other.name - 1)
            return true;
        if(this.flat && other.sharp && this.name == other.name + 1)
            return true;
        if (this.name != other.name) {
            return false;
        }
        if (this.sharp != other.sharp) {
            return false;
        }
        if (this.flat != other.flat) {
            return false;
        }          
        return true;
    }
}
