package pl.mr.kebab.dao;

import pl.mr.kebab.model.Menu;
import pl.mr.kebab.model.Platnosc;
import pl.mr.kebab.model.Restauracja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlatnoscDAO extends AbstractDAO {

    public PlatnoscDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public PlatnoscDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(Platnosc platnosc) throws SQLException {
        String sql = "INSERT INTO OWNER.PLATNOSC (ID_REST, RODZAJ_PLATNOSC) VALUES (?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, platnosc.getIdRest());
        statement.setString(2, platnosc.getRodzajPlatnosci());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Platnosc> listAll() throws SQLException {
        String sql = "SELECT * FROM OWNER.PLATNOSC";

        connect();
        Statement statement = jdbcConnection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        List<Platnosc> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    public List<Platnosc> listForRestauracja(int idRest) throws SQLException {
        String sql = "SELECT * FROM OWNER.PLATNOSC WHERE ID_REST = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, idRest);

        ResultSet resultSet = statement.executeQuery();
        List<Platnosc> list = list(resultSet);

        statement.close();
        disconnect();

        return list;
    }

    private List<Platnosc> list(ResultSet resultSet) throws SQLException {
        List<Platnosc> listPlatnosc = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            int idRest = resultSet.getInt("ID_REST");
            String rodzajPlatnosci = resultSet.getString("RODZAJ_PLATNOSCI");

            Platnosc platnosc = new Platnosc(id, idRest, rodzajPlatnosci);

            //  dociągniecie danych restauracji
            RestauracjaDAO restauracjaDAO = new RestauracjaDAO(jdbcConnection);
            Restauracja restauracja = restauracjaDAO.get(idRest);
            platnosc.setRestauracja(restauracja);

            listPlatnosc.add(platnosc);
        }

        resultSet.close();

        return listPlatnosc;
    }

    public boolean delete(Platnosc platnosc) throws SQLException {
        String sql = "DELETE FROM OWNER.PLATNOSC where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, platnosc.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }


    public Platnosc get(int id) throws SQLException {
        Platnosc platnosc = null;
        String sql = "SELECT * FROM OWNER.PLATNOSC WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int idRest = resultSet.getInt("ID_REST");
            String rodzajPlatnosci = resultSet.getString("RODZAJ_PLATNOSCI");

            platnosc = new Platnosc(id, idRest, rodzajPlatnosci);

            //  dociągniecie danych restauracji
            RestauracjaDAO restauracjaDAO = new RestauracjaDAO(jdbcConnection);
            Restauracja restauracja = restauracjaDAO.get(idRest);
            platnosc.setRestauracja(restauracja);
        }

        resultSet.close();
        statement.close();

        return platnosc;
    }
}