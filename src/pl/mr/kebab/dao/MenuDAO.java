package pl.mr.kebab.dao;

import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Restauracja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends AbstractDAO {

    public MenuDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public MenuDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(Menu menu) throws SQLException {
        String sql = "INSERT INTO OWNER.MENU (ID_REST, NAZWA_PRODUKTU, CENA) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, menu.getIdRest());
        statement.setString(2, menu.getNazwaProduktu());
        statement.setFloat(3, menu.getCena());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Menu> listAll() throws SQLException {
        String sql = "SELECT * FROM OWNER.MENU";

        connect();
        Statement statement = jdbcConnection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<Menu> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    public List<Menu> listForRestauracja(int idRest) throws SQLException {
        String sql = "SELECT * FROM OWNER.MENU WHERE ID_REST = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, idRest);

        ResultSet resultSet = statement.executeQuery();
        List<Menu> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    public List<Menu> search(Menu menu) throws SQLException {
        String sql = "SELECT * FROM OWNER.MENU WHERE 1=1";
        connect();
        PreparedStatement statement = null;

        if (menu.getNazwaProduktu() != null && menu.getCena() == 0.0f) {

            sql += " and lower(MENU.NAZWA_PRODUKTU) like ?";

            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, "%" + menu.getNazwaProduktu().toLowerCase() + "%");

        } else if (menu.getNazwaProduktu() == null && menu.getCena() > 0.0f) {

            sql += " and MENU.CENA <= ?";

            statement = jdbcConnection.prepareStatement(sql);
            statement.setFloat(1, menu.getCena());

        } else if (menu.getNazwaProduktu() != null && menu.getCena() > 0.0f) {

            sql += " and lower(MENU.NAZWA_PRODUKTU) like ?";
            sql += " and MENU.CENA <= ?";

            statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, "%" + menu.getNazwaProduktu().toLowerCase() + "%");
            statement.setFloat(2, menu.getCena());

        } else {
            sql += " and 1=2";
            statement = jdbcConnection.prepareStatement(sql);
        }

        ResultSet resultSet = statement.executeQuery();
        List<Menu> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    private List<Menu> list(ResultSet resultSet) throws SQLException {
        List<Menu> listMenu = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int idRest = resultSet.getInt("ID_REST");
            String nazwaProduktu = resultSet.getString("NAZWA_PRODUKTU");
            float cena = Float.parseFloat(resultSet.getString("CENA"));

            Menu menu = new Menu(id, idRest, nazwaProduktu, cena);

            //  dociągniecie danych restauracji
            RestauracjaDAO restauracjaDAO = new RestauracjaDAO(jdbcConnection);
            Restauracja restauracja = restauracjaDAO.get(idRest);
            menu.setRestauracja(restauracja);

            listMenu.add(menu);
        }

        resultSet.close();

        return listMenu;
    }

    public boolean delete(Menu menu) throws SQLException {
        String sql = "DELETE FROM OWNER.MENU where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, menu.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean update(Menu menu) throws SQLException {
        String sql = "UPDATE OWNER.MENU SET ID_REST = ?, NAZWA_PRODUKTU = ?, CENA = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, menu.getIdRest());
        statement.setString(2, menu.getNazwaProduktu());
        statement.setFloat(3, menu.getCena());
        statement.setInt(4, menu.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Menu get(int id) throws SQLException {
        Menu menu = null;
        String sql = "SELECT * FROM OWNER.MENU WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int idRest = resultSet.getInt("ID_REST");
            String nazwaProduktu = resultSet.getString("NAZWA_PRODUKTU");
            float cena = Float.parseFloat(resultSet.getString("CENA"));

            menu = new Menu(id, idRest, nazwaProduktu, cena);

            //  dociągniecie danych restauracji
            RestauracjaDAO restauracjaDAO = new RestauracjaDAO(jdbcConnection);
            Restauracja restauracja = restauracjaDAO.get(idRest);
            menu.setRestauracja(restauracja);
        }

        resultSet.close();
        statement.close();

        return menu;
    }
}
