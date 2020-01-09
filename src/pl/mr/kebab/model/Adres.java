package pl.mr.kebab.model;

import java.util.List;

public class Adres {
    private int id;
    private String miejscowosc;
    private String ulica;
    private String nrLokalu;
    private String kod;
    private List<Restauracja> restauracjaList;

    public Adres() {
    }

    public Adres(int id) {
        this.id = id;
    }

    public Adres(String miejscowosc, String ulica, String nrLokalu, String kod) {
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrLokalu = nrLokalu;
        this.kod = kod;
    }

    public Adres(int id, String miejscowosc, String ulica, String nrLokalu, String kod) {
        this.id = id;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrLokalu = nrLokalu;
        this.kod = kod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public List<Restauracja> getRestauracjaList() {
        return restauracjaList;
    }

    public void setRestauracjaList(List<Restauracja> restauracjaList) {
        this.restauracjaList = restauracjaList;
    }
}
