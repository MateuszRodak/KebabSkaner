package pl.mr.kebab.model;

import java.sql.Time;
import java.util.List;

public class Restauracja {

    private int id;
    private String nazwa;
    private int idAdres;
    private Time godzOtw;
    private Time godzZam;
    private boolean dowoz;

    private Adres adres;
    private List<Menu> menuList;

    public Restauracja() {
    }

    public Restauracja(int id) {
        this.id = id;
    }

    public Restauracja(String nazwa, int idAdres, Time godzOtw, Time godzZam, boolean dowoz) {
        this.nazwa = nazwa;
        this.idAdres = idAdres;
        this.godzOtw = godzOtw;
        this.godzZam = godzZam;
        this.dowoz = dowoz;
    }

    public Restauracja(int id, String nazwa, int idAdres, Time godzOtw, Time godzZam, boolean dowoz) {
        this.id = id;
        this.nazwa = nazwa;
        this.idAdres = idAdres;
        this.godzOtw = godzOtw;
        this.godzZam = godzZam;
        this.dowoz = dowoz;
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

    public int getIdAdres() {
        return idAdres;
    }

    public void setIdAdres(int idAdres) {
        this.idAdres = idAdres;
    }

    public Time getGodzOtw() {
        return godzOtw;
    }

    public void setGodzOtw(Time godzOtw) {
        this.godzOtw = godzOtw;
    }

    public Time getGodzZam() {
        return godzZam;
    }

    public void setGodzZam(Time godzZam) {
        this.godzZam = godzZam;
    }

    public boolean isDowoz() {
        return dowoz;
    }

    public void setDowoz(boolean dowoz) {
        this.dowoz = dowoz;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}