package pl.mr.kebab.dao;

import pl.mr.kebab.model.ListaDodatkow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListaDodatkowDAO extends AbstractDAO {

    public ListaDodatkowDAO(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public ListaDodatkowDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public boolean insert(ListaDodatkow listaDodatkow) throws SQLException {
        String sql = "INSERT INTO OWNER.LISTA_DODATKOW (NAZWA) VALUES (?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, listaDodatkow.getNazwa());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowInserted;
    }

    public List<ListaDodatkow> listAll() throws SQLException {
        List<ListaDodatkow> listListaDodatkow = new ArrayList<>();

        String sql = "SELECT * FROM OWNER.LISTA_DODATKOW";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nazwa = resultSet.getString("NAZWA");

            ListaDodatkow listaDodatkow = new ListaDodatkow(id, nazwa);
            listListaDodatkow.add(listaDodatkow);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listListaDodatkow;
    }

    public boolean delete(ListaDodatkow listaDodatkow) throws SQLException {
        String sql = "DELETE FROM OWNER.LISTA_DODATKOW where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, listaDodatkow.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowDeleted;
    }

    public boolean update(ListaDodatkow listaDodatkow) throws SQLException {
        String sql = "UPDATE OWNER.LISTA_DODATKOW SET NAZWA = ? WHERE ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, listaDodatkow.getNazwa());
        statement.setInt(2, listaDodatkow.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        commit();
        disconnect();
        return rowUpdated;
    }

    public ListaDodatkow get(int id) throws SQLException {
        ListaDodatkow listaDodatkow = null;
        String sql = "SELECT * FROM OWNER.LISTA_DODATKOW WHERE ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nazwa = resultSet.getString("NAZWA");

            listaDodatkow = new ListaDodatkow(id, nazwa);
        }

        resultSet.close();
        statement.close();

        return listaDodatkow;
    }
}
