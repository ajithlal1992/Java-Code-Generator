/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegenerator.action;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author kites
 */
public class CreateDatabaseConnection {

    public static void createDatabaseConnection(String database, String file) {
        try {
            String text = "package com."+database+"."+new File(new File(file).getParent()).getName()+";\n\n"
                    + "import java.sql.Connection;\n"
                    + "import java.sql.DriverManager;\n"
                    + "import java.sql.ResultSet;\n"
                    + "import java.sql.Statement;\n"
                    + "\n"
                    + "public class DatabaseConnection {\n"
                    + "\n"
                    + "    String driver = \"com.mysql.jdbc.Driver\";\n"
                    + "    String database = \"" + database + "\";\n"
                    + "    String url = \"jdbc:mysql://localhost/\";\n"
                    + "    String username = \"root\";\n"
                    + "    String password = \"root\";\n"
                    + "\n"
                    + "    Connection connection;\n"
                    + "    Statement statement;\n"
                    + "\n"
                    + "    public void init() {\n"
                    + "        try {\n"
                    + "            Class.forName(driver);\n"
                    + "            connection = DriverManager.getConnection(url+database, username, password);\n"
                    + "            statement = connection.createStatement();\n"
                    + "\n"
                    + "        } catch (Exception e) {\n"
                    + "            e.printStackTrace();\n"
                    + "        }\n"
                    + "    }\n"
                    + "\n"
                    + "    public int putData(String sql) {\n"
                    + "        System.out.println(\"sql = \" + sql);\n"
                    + "        int i = 0;\n"
                    + "        try {\n"
                    + "            i = statement.executeUpdate(sql);\n"
                    + "        } catch (Exception e) {\n"
                    + "            e.printStackTrace();\n"
                    + "        }\n"
                    + "        return i;\n"
                    + "    }\n"
                    + "\n"
                    + "    public ResultSet getData(String sql) {\n"
                    + "        System.out.println(\"sql = \" + sql);\n"
                    + "        ResultSet resultSet = null;\n"
                    + "        try {\n"
                    + "            resultSet = statement.executeQuery(sql);\n"
                    + "        } catch (Exception e) {\n"
                    + "            e.printStackTrace();\n"
                    + "        }\n"
                    + "        return resultSet;\n"
                    + "    }\n"
                    + "\n"
                    + "    public void close() {\n"
                    + "        try {\n"
                    + "            connection.close();\n"
                    + "        } catch (Exception e) {\n"
                    + "        }\n"
                    + "    }\n"
                    + "\n"
                    + "}\n"
                    + "";
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
