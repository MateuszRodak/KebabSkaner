package pl.mr.kebab.model;

import pl.mr.kebab.model.enums.JednostkaPorcja;
import pl.mr.kebab.model.enums.OpisPorcja;

public class Porcja {

    private int id;
    private int idMenu;
    private int wielkosc;
    private JednostkaPorcja jednostka;
    private OpisPorcja opis;

    private Menu menu;

    public Porcja() {
    }

    public Porcja(int id) {
        this.id = id;
    }

    public Porcja(int idMenu, int wielkosc, JednostkaPorcja jednostka, OpisPorcja opis) {
        this.idMenu = idMenu;
        this.wielkosc = wielkosc;
        this.jednostka = jednostka;
        this.opis = opis;
    }

    public Porcja(int id, int idMenu, int wielkosc, JednostkaPorcja jednostka, OpisPorcja opis) {
        this.id = id;
        this.idMenu = idMenu;
        this.wielkosc = wielkosc;
        this.jednostka = jednostka;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(int wielkosc) {
        this.wielkosc = wielkosc;
    }

    public JednostkaPorcja getJednostka() {
        return jednostka;
    }

    public void setJednostka(JednostkaPorcja jednostka) {
        this.jednostka = jednostka;
    }

    public OpisPorcja getOpis() {
        return opis;
    }

    public void setOpis(OpisPorcja opis) {
        this.opis = opis;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
