package pl.mr.kebab.model;

import java.util.List;

public class Menu {
    private int id;
    private int idRest;
    private String nazwaProduktu;
    private float cena;

    private Restauracja restauracja;
    private List<Porcja> porcjaList;
    private List<DodatkiMenu> dodatkiMenuList;

    public Menu() {
    }

    public Menu(int id) {
        this.id = id;
    }

    public Menu(int idRest, String nazwaProduktu, float cena) {
        this.idRest = idRest;
        this.nazwaProduktu = nazwaProduktu;
        this.cena = cena;
    }

    public Menu(int id, int idRest, String nazwaProduktu, float cena) {
        this.id = id;
        this.idRest = idRest;
        this.nazwaProduktu = nazwaProduktu;
        this.cena = cena;
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

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Restauracja getRestauracja() {
        return restauracja;
    }

    public void setRestauracja(Restauracja restauracja) {
        this.restauracja = restauracja;
    }

    public List<Porcja> getPorcjaList() {
        return porcjaList;
    }

    public void setPorcjaList(List<Porcja> porcjaList) {
        this.porcjaList = porcjaList;
    }

    public List<DodatkiMenu> getDodatkiMenuList() {
        return dodatkiMenuList;
    }

    public void setDodatkiMenuList(List<DodatkiMenu> dodatkiMenuList) {
        this.dodatkiMenuList = dodatkiMenuList;
    }
}
