package pl.mr.kebab.dao;

import pl.mr.kebab.model.Adres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAO extends AbstractDAO{

    public AdresDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public AdresDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(Adres adres) throws SQLException {
        String sql = "INSERT INTO OWNER.ADRES (MIEJSCOWOSC, ULICA, NR_LOKALU, KOD) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, adres.getMiejscowosc());
        statement.setString(2, adres.getUlica());
        statement.setString(3, adres.getNrLokalu());
        statement.setString(4, adres.getKod());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowInserted;
    }

    public List<Adres> listAll() throws SQLException {
        List<Adres> listAdres = new ArrayList<>();

        String sql = "SELECT * FROM OWNER.ADRES";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String miejscowosc = resultSet.getString("MIEJSCOWOSC");
            String ulica = resultSet.getString("ULICA");
            String nrLokalu = resultSet.getString("NR_LOKALU");
            String kod = resultSet.getString("KOD");

            Adres adres = new Adres(id, miejscowosc, ulica, nrLokalu, kod);
            listAdres.add(adres);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listAdres;
    }

    public boolean delete(Adres adres) throws SQLException {
        String sql = "DELETE FROM OWNER.ADRES where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, adres.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowDeleted;
    }

    public boolean update(Adres adres) throws SQLException {
        String sql = "UPDATE OWNER.ADRES SET MIEJSCOWOSC = ?, ULICA = ?, NR_LOKALU = ?, KOD = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, adres.getMiejscowosc());
        statement.setString(2, adres.getUlica());
        statement.setString(3, adres.getNrLokalu());
        statement.setString(4, adres.getKod());
        statement.setInt(5, adres.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowUpdated;
    }

    public Adres get(int id) throws SQLException {
        Adres adres = null;
        String sql = "SELECT * FROM OWNER.ADRES WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String miejscowosc = resultSet.getString("MIEJSCOWOSC");
            String ulica = resultSet.getString("ULICA");
            String nrLokalu = resultSet.getString("NR_LOKALU");
            String kod = resultSet.getString("KOD");

            adres = new Adres(id, miejscowosc, ulica, nrLokalu, kod);
        }

        resultSet.close();
        statement.close();

        return adres;
    }
}
