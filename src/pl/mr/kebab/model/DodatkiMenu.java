package pl.mr.kebab.model;

public class DodatkiMenu {

    private int id;
    private int idMenu;
    private int idListyDodatkow;

    private Menu menu;
    private ListaDodatkow listaDodatkow;

    public DodatkiMenu() {
    }

    public DodatkiMenu(int id) {
        this.id = id;
    }

    public DodatkiMenu(int idMenu, int idListyDodatkow) {
        this.idMenu = idMenu;
        this.idListyDodatkow = idListyDodatkow;
    }

    public DodatkiMenu(int id, int idMenu, int idListyDodatkow) {
        this.id = id;
        this.idMenu = idMenu;
        this.idListyDodatkow = idListyDodatkow;
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

    public int getIdListyDodatkow() {
        return idListyDodatkow;
    }

    public void setIdListyDodatkow(int idListyDodatkow) {
        this.idListyDodatkow = idListyDodatkow;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ListaDodatkow getListaDodatkow() {
        return listaDodatkow;
    }

    public void setListaDodatkow(ListaDodatkow listaDodatkow) {
        this.listaDodatkow = listaDodatkow;
    }
}
