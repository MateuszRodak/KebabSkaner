package pl.mr.kebab.dao;

import pl.mr.kebab.model.DodatkiMenu;
import pl.mr.kebab.model.ListaDodatkow;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Restauracja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DodatkiMenuDAO extends AbstractDAO {

    public DodatkiMenuDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public DodatkiMenuDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(DodatkiMenu dodatkiMenu) throws SQLException {
        String sql = "INSERT INTO OWNER.DODATKI_MENU (ID_MENU, ID_LISTY_DODATKOW) VALUES (?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, dodatkiMenu.getIdMenu());
        statement.setInt(2, dodatkiMenu.getIdListyDodatkow());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    private List<DodatkiMenu> list(ResultSet resultSet, boolean withDependencies) throws SQLException {
        List<DodatkiMenu> listDodatkiMenu = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int idMenu = resultSet.getInt("ID_MENU");
            int idListyDodatkow = resultSet.getInt("ID_LISTY_DODATKOW");

            DodatkiMenu dodatkiMenu = new DodatkiMenu(id, idMenu, idListyDodatkow);

            if (withDependencies) {
                //  dociągniecie danych menu
                MenuDAO menuDAO = new MenuDAO(jdbcConnection);
                Menu menu = menuDAO.get(idMenu);
                dodatkiMenu.setMenu(menu);

            }

            ListaDodatkowDAO listaDodatkowDAO = new ListaDodatkowDAO(jdbcConnection);
            ListaDodatkow listaDodatkow = listaDodatkowDAO.get(idListyDodatkow);
            dodatkiMenu.setListaDodatkow(listaDodatkow);

            listDodatkiMenu.add(dodatkiMenu);
        }

        resultSet.close();

        return listDodatkiMenu;
    }

    public List<DodatkiMenu> listAll() throws SQLException {
        List<DodatkiMenu> listDodatkiMenu = new ArrayList<>();

        String sql = "SELECT * FROM OWNER.DODATKI_MENU";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int idMenu = resultSet.getInt("ID_MENU");
            int idListyDodatkow = resultSet.getInt("ID_LISTY_DODATKOW");

            DodatkiMenu dodatkiMenu = new DodatkiMenu(id, idMenu, idListyDodatkow);

            //  dociągniecie danych menu
            MenuDAO menuDAO = new MenuDAO(jdbcConnection);
            Menu menu = menuDAO.get(idMenu);
            dodatkiMenu.setMenu(menu);

            //  dociągniecie danych listy dodatkow
            ListaDodatkowDAO listaDodatkowDAO = new ListaDodatkowDAO(jdbcConnection);
            ListaDodatkow listaDodatkow = listaDodatkowDAO.get(idListyDodatkow);
            dodatkiMenu.setListaDodatkow(listaDodatkow);

            listDodatkiMenu.add(dodatkiMenu);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listDodatkiMenu;
    }

    public List<DodatkiMenu> listForMenu(int idMenu) throws SQLException {
        String sql = "SELECT * FROM OWNER.DODATKI_MENU WHERE ID_MENU = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, idMenu);

        ResultSet resultSet = statement.executeQuery();
        List<DodatkiMenu> list = list(resultSet, true);

        statement.close();
        disconnect();

        return list;
    }

    public List<DodatkiMenu> simpleListForMenu(int idMenu) throws SQLException {
        String sql = "SELECT * FROM OWNER.DODATKI_MENU WHERE ID_MENU = ?";

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, idMenu);

        ResultSet resultSet = statement.executeQuery();
        List<DodatkiMenu> list = list(resultSet, false);

        statement.close();

        return list;
    }

    public boolean delete(DodatkiMenu dodatkiMenu) throws SQLException {
        String sql = "DELETE FROM OWNER.DODATKI_MENU where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, dodatkiMenu.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean update(DodatkiMenu dodatkiMenu) throws SQLException {
        String sql = "UPDATE OWNER.DODATKI_MENU SET ID_MENU = ?, ID_LISTY_DODATKOW = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, dodatkiMenu.getIdMenu());
        statement.setInt(2, dodatkiMenu.getIdListyDodatkow());
        statement.setInt(3, dodatkiMenu.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public DodatkiMenu get(int id) throws SQLException {
        DodatkiMenu dodatkiMenu = null;
        String sql = "SELECT * FROM OWNER.DODATKI_MENU WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int idMenu = resultSet.getInt("ID_MENU");
            int idListyDodatkow = resultSet.getInt("ID_LISTY_DODATKOW");

            dodatkiMenu = new DodatkiMenu(id, idMenu, idListyDodatkow);

            //  dociągniecie danych menu
            MenuDAO menuDAO = new MenuDAO(jdbcConnection);
            Menu menu = menuDAO.get(idMenu);
            dodatkiMenu.setMenu(menu);

            //  dociągniecie danych listy dodatkow
            ListaDodatkowDAO listaDodatkowDAO = new ListaDodatkowDAO(jdbcConnection);
            ListaDodatkow listaDodatkow = listaDodatkowDAO.get(idListyDodatkow);
            dodatkiMenu.setListaDodatkow(listaDodatkow);
        }

        resultSet.close();
        statement.close();

        return dodatkiMenu;
    }
}
