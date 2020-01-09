package pl.mr.kebab.model;

import java.util.List;

public class ListaDodatkow {

    private int id;
    private String nazwa;

    private List<DodatkiMenu> dodatkiMenuList;

    public ListaDodatkow() {
    }

    public ListaDodatkow(int id) {
        this.id = id;
    }

    public ListaDodatkow(String nazwa) {
        this.nazwa = nazwa;
    }

    public ListaDodatkow(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<DodatkiMenu> getDodatkiMenuList() {
        return dodatkiMenuList;
    }

    public void setDodatkiMenuList(List<DodatkiMenu> dodatkiMenuList) {
        this.dodatkiMenuList = dodatkiMenuList;
    }
}
