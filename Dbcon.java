/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegenerator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kites
 */
public class Dbcon {

    static String driver = "com.mysql.jdbc.Driver";
    public static String database = "";
    static String url = "jdbc:mysql://localhost/";
    static String username = "root";
    static String password = "root";

    public static Connection connection;
    public static Statement statement;

    public static int init() {
        int i = 0;
        try {
            connection = DriverManager.getConnection(url + database, username, password);
            statement = connection.createStatement();
            i++;
        } catch (Exception e) {
        }
        return i;
    }

    public static ArrayList<String> getTables(){
        ArrayList<String> tables = new ArrayList<String>();
        try {
            String sql = "show tables";
            System.out.println("sql = " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tables.add(resultSet.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tables;
    }
    
    public static ArrayList<String> getTupples(String table){
        ArrayList<String> tupples = new ArrayList<String>();
        try {
            String sql = "desc `"+table+"`";
            System.out.println("sql = " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tupples.add(resultSet.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tupples;
    }

    public static ArrayList<String> getDatabases(){
        ArrayList<String> databases = new ArrayList<String>();
        try {
            String sql = "show databases";
            System.out.println("sql = " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                databases.add(resultSet.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return databases;
    }
    
    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }
}
