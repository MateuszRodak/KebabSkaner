package pl.mr.kebab.dao;

import pl.mr.kebab.model.Adres;
import pl.mr.kebab.model.Platnosc;
import pl.mr.kebab.model.Restauracja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RestauracjaDAO extends AbstractDAO {

    public RestauracjaDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public RestauracjaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(Restauracja restauracja) throws SQLException {
        String sql = "INSERT INTO OWNER.RESTAURACJA (NAZWA, ID_ADRES, H_OTW, H_ZAM, DOWOZ) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, restauracja.getNazwa());
        statement.setInt(2, restauracja.getIdAdres());
        statement.setTime(3, restauracja.getGodzOtw());
        statement.setTime(4, restauracja.getGodzZam());
        statement.setBoolean(5, restauracja.isDowoz());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowInserted;
    }

    public List<Restauracja> listAll() throws SQLException {
        List<Restauracja> listRestauracja = new ArrayList<>();

        String sql = "SELECT * FROM OWNER.RESTAURACJA";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nazwa = resultSet.getString("NAZWA");
            int idAdres = Integer.parseInt(resultSet.getString("ID_ADRES"));
            Time hOtw = Time.valueOf(resultSet.getString("H_OTW"));
            Time hZam = Time.valueOf(resultSet.getString("H_ZAM"));
            boolean dowoz = Boolean.parseBoolean(resultSet.getString("DOWOZ"));

            Restauracja restauracja = new Restauracja(id, nazwa, idAdres, hOtw, hZam, dowoz);

            // dociągniecie danych adresów
            AdresDAO adresDAO = new AdresDAO(jdbcConnection);
            Adres adres = adresDAO.get(idAdres);
            restauracja.setAdres(adres);

            listRestauracja.add(restauracja);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listRestauracja;
    }

    public boolean delete(Restauracja restauracja) throws SQLException {
        String sql = "DELETE FROM OWNER.RESTAURACJA where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, restauracja.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowDeleted;
    }

    public boolean update(Restauracja restauracja) throws SQLException {
        String sql = "UPDATE OWNER.RESTAURACJA SET NAZWA = ?, ID_ADRES = ?, H_OTW = ?, H_ZAM = ?, DOWOZ = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, restauracja.getNazwa());
        statement.setInt(2, restauracja.getIdAdres());
        statement.setTime(3, restauracja.getGodzOtw());
        statement.setTime(4, restauracja.getGodzZam());
        statement.setBoolean(5, restauracja.isDowoz());
        statement.setInt(6, restauracja.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowUpdated;
    }

    public Restauracja get(int id) throws SQLException {
        Restauracja restauracja = null;
        String sql = "SELECT * FROM OWNER.RESTAURACJA WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nazwa = resultSet.getString("NAZWA");
            int idAdres = Integer.parseInt(resultSet.getString("ID_ADRES"));
            Time hOtw = Time.valueOf(resultSet.getString("H_OTW"));
            Time hZam = Time.valueOf(resultSet.getString("H_ZAM"));
            boolean dowoz = Boolean.parseBoolean(resultSet.getString("DOWOZ"));

            restauracja = new Restauracja(id, nazwa, idAdres, hOtw, hZam, dowoz);

            // dociągniecie danych adresu
            AdresDAO adresDAO = new AdresDAO(jdbcConnection);
            Adres adres = adresDAO.get(idAdres);
            restauracja.setAdres(adres);

            //dociagniecie platnosci
            PlatnoscDAO platnoscDAO = new PlatnoscDAO(jdbcConnection);
            List<Platnosc> listPlatnosc = platnoscDAO.simpleListForRestauracja(id);
            restauracja.setPlatnoscList(listPlatnosc);
        }

        resultSet.close();
        statement.close();

        return restauracja;
    }
}
