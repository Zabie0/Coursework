package com.staryy.coursework.Database;

import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseExecuter {
    private static final String connectionUrl =
            "jdbc:sqlserver://DESKTOP-1RDNU8A:1433;"
                    + "database=CreditsBase;"
                    + "user=Vlad;"
                    + "password=1337;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public static boolean dbExecute(String sqlRequest) {
        boolean successful = true;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            prepsInsertProduct.execute();
        } catch (Exception e) {
            successful = false;
            e.printStackTrace();
        }
        return successful;
    }
    public static biler dbExecuteSelect(int num,String tot,String sqlRequest) throws SQLException {
        ResultSet resultSet;
        biler biler = new biler();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                biler.setId(resultSet.getInt(num));
                if(tot != "null") {
                    biler.setName(resultSet.getString(tot));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return biler;
    }
    public static List<String> dbExecuteSelectNames(String name, String sqlRequest) throws SQLException {
        ResultSet resultSet;
        List<String> names = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                names.add(resultSet.getString(name));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
    public static biler dbExecuteSelect4(String name1, String name2, String name3, String name4, String sqlRequest) throws SQLException {
        ResultSet resultSet;
        biler biler = new biler();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                biler.setId(resultSet.getInt(name1));
                biler.setId1(resultSet.getInt(name2));
                biler.setId2(resultSet.getInt(name3));
                biler.setId3(resultSet.getInt(name4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return biler;
    }
    public static biler dbExecuteSelect8(String name1, String name2, String name3, String name4, String name5, String name6, String name7, String name8, String sqlRequest){
        ResultSet resultSet;
        biler biler = new biler();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                biler.setId(resultSet.getInt(name1));
                biler.setId1(resultSet.getInt(name2));
                biler.setId2(resultSet.getInt(name3));
                biler.setId3(resultSet.getInt(name4));
                biler.setId4(resultSet.getInt(name5));
                biler.setId5(resultSet.getInt(name6));
                biler.setId6(resultSet.getInt(name7));
                biler.setName(resultSet.getString(name8));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return biler;
    }
    public static void dbExecuteShowAllBanks(TextArea allBanksField, String sqlRequest) {
        ResultSet resultSet;
        String res = "";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                res = (res + "Bank id: "+resultSet.getInt("bankId")+" | Name: "+resultSet.getString("name")+" | Address: "+resultSet.getString("address")+" | Email: "+resultSet.getString("email")+"\n");
            }
            allBanksField.setText(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dbExecuteShowAllCredits(TextArea allCreditsField, String sqlRequest) {
        ResultSet resultSet;
        String res = "";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(sqlRequest);
            while(resultSet.next()){
                res = (res + "Credit id: "+resultSet.getInt("creditId")+" | Bank: "+resultSet.getString("name")+" | Money given : "+resultSet.getInt("moneyGiven")+"$ | Percentage: "+resultSet.getInt("percentage")+"% | Time: "+resultSet.getInt("months")+" months | To repay: "+resultSet.getString("moneyToGive")+"$ | Extendable: "+resultSet.getBoolean("creditLineExtendable")+"\n");
            }
            allCreditsField.setText(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
