package pl.mr.kebab.model;

public class Platnosc {

    private int id;
    private int idRest;
    private String rodzajPlatnosci;

    private Restauracja restauracja;

    public Platnosc() {
    }

    public Platnosc(int id) {
        this.id = id;
    }

    public Platnosc(int idRest, String rodzajPlatnosci, Restauracja restauracja) {
        this.idRest = idRest;
        this.rodzajPlatnosci = rodzajPlatnosci;
        this.restauracja = restauracja;
    }

    public Platnosc(int id, int idRest, String rodzajPlatnosci, Restauracja restauracja) {
        this.id = id;
        this.idRest = idRest;
        this.rodzajPlatnosci = rodzajPlatnosci;
        this.restauracja = restauracja;
    }

    public Platnosc(int id, int idRest, String rodzajPlatnosci) {
        this.id = id;
        this.idRest = idRest;
        this.rodzajPlatnosci = rodzajPlatnosci;
    }

    public Platnosc(int idRest, String rodzajPlatnosci) {
        this.idRest = idRest;
        this.rodzajPlatnosci = rodzajPlatnosci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRest() {
        return idRest;
    }

    public void setIdRest(int idRest) {
        this.idRest = idRest;
    }

    public String getRodzajPlatnosci() {
        return rodzajPlatnosci;
    }

    public void setRodzajPlatnosci(String rodzajPlatnosci) {
        this.rodzajPlatnosci = rodzajPlatnosci;
    }

    public Restauracja getRestauracja() {
        return restauracja;
    }

    public void setRestauracja(Restauracja restauracja) {
        this.restauracja = restauracja;
    }
}
