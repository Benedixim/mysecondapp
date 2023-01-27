package com.example.mysecondapp;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signupUser(User user)
    {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," +
                Const.USER_USERNAME + "," + Const.USER_PASSWORD + "," +
                Const.USER_LOCATION + "," + Const.USER_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user, Long idSelected)
    {

            String up = "UPDATE " + Const.USER_TABLE +
                    " SET " + Const.USER_FIRSTNAME + "=?," + Const.USER_LASTNAME + "=?," +
                    Const.USER_USERNAME + "=?," + Const.USER_PASSWORD + "=?," +
                    Const.USER_LOCATION + "=?," + Const.USER_GENDER + "=?" +
                    " WHERE " + Const.USER_ID + " = " + idSelected ;

            PreparedStatement prSt = null;
            try {
                prSt = getDbConnection().prepareStatement(up);
                prSt.setString(1, user.getFirstName());
                prSt.setString(2, user.getLastName());
                prSt.setString(3, user.getUserName());
                prSt.setString(4, user.getPassword());
                prSt.setString(5, user.getLocation());
                prSt.setString(6, user.getGender());

                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }

    public ResultSet getUser (User user)
    {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_USERNAME + "=? AND " + Const.USER_PASSWORD + "=?" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }


    public void deleteUser (User user)
    {
        String query = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=" + user.getId();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
             prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public ResultSet getAllUsers()
    {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);


            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public int maleCount() {
        int count = 0;

        ResultSet resSet = null;

        String select = "SELECT count(*) FROM " + Const.USER_TABLE + " WHERE gender='Мужской'" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
            //preparedStatement = connection.prepareStatement("SELECT count(*) FROM taxtype WHERE taxName='MoneyTax'");
            //ResultSet rs = preparedStatement.executeQuery();
            while (resSet.next()) {
                count = resSet.getInt("count(*)");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int femaleCount() {
        int count = 0;

        ResultSet resSet = null;

        String select = "SELECT count(*) FROM " + Const.USER_TABLE + " WHERE gender='Женский'" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
            //preparedStatement = connection.prepareStatement("SELECT count(*) FROM taxtype WHERE taxName='MoneyTax'");
            //ResultSet rs = preparedStatement.executeQuery();
            while (resSet.next()) {
                count = resSet.getInt("count(*)");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
