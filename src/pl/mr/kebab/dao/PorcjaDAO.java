package pl.mr.kebab.dao;

import pl.mr.kebab.model.Porcja;
import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.enums.JednostkaPorcja;
import pl.mr.kebab.model.enums.OpisPorcja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PorcjaDAO extends AbstractDAO {

    public PorcjaDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public PorcjaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(Porcja porcja) throws SQLException {
        String sql = "INSERT INTO OWNER.PORCJA (ID_MENU, WIELKOSC, JEDNOSTKA, OPIS) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, porcja.getIdMenu());
        statement.setInt(2, porcja.getWielkosc());
        statement.setString(3, porcja.getJednostka().getValue());
        statement.setString(4, porcja.getOpis().getValue());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Porcja> listAll() throws SQLException {
        String sql = "SELECT * FROM OWNER.PORCJA";

        connect();
        Statement statement = jdbcConnection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<Porcja> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    public List<Porcja> listForMenu(int idMenu) throws SQLException {
        String sql = "SELECT * FROM OWNER.PORCJA WHERE ID_MENU = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, idMenu);

        ResultSet resultSet = statement.executeQuery();
        List<Porcja> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    private List<Porcja> list(ResultSet resultSet) throws SQLException {
        List<Porcja> listPorcja = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int idMenu = resultSet.getInt("ID_MENU");
            int wielkosc = resultSet.getInt("WIELKOSC");
            JednostkaPorcja jednostka = JednostkaPorcja.getEnum(resultSet.getString("JEDNOSTKA"));
            OpisPorcja opis = OpisPorcja.getEnum(resultSet.getString("OPIS"));

            Porcja porcja = new Porcja(id, idMenu, wielkosc, jednostka, opis);

            //  dociągniecie danych menu
            MenuDAO menuDAO = new MenuDAO(jdbcConnection);
            Menu menu = menuDAO.get(idMenu);
            porcja.setMenu(menu);

            listPorcja.add(porcja);
        }

        resultSet.close();

        return listPorcja;
    }

    public boolean delete(Porcja porcja) throws SQLException {
        String sql = "DELETE FROM OWNER.PORCJA where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, porcja.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean update(Porcja porcja) throws SQLException {
        String sql = "UPDATE OWNER.PORCJA SET ID_MENU = ?, WIELKOSC = ?, JEDNOSTKA = ?,  OPIS = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, porcja.getIdMenu());
        statement.setInt(2, porcja.getWielkosc());
        statement.setString(3, porcja.getJednostka().getValue());
        statement.setString(4, porcja.getOpis().getValue());
        statement.setInt(5, porcja.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Porcja get(int id) throws SQLException {
        Porcja porcja = null;
        String sql = "SELECT * FROM OWNER.PORCJA WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int idMenu = resultSet.getInt("ID_MENU");
            int wielkosc = resultSet.getInt("WIELKOSC");
            JednostkaPorcja jednostka = JednostkaPorcja.getEnum(resultSet.getString("JEDNOSTKA"));
            OpisPorcja opis = OpisPorcja.getEnum(resultSet.getString("OPIS"));

            porcja = new Porcja(id, idMenu, wielkosc, jednostka, opis);

            //  dociągniecie danych restauracji
            MenuDAO menuDAO = new MenuDAO(jdbcConnection);
            Menu menu = menuDAO.get(idMenu);
            porcja.setMenu(menu);
        }

        resultSet.close();
        statement.close();

        return porcja;
    }
}
