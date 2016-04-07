/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegenerator.action;

import com.javacodegenerator.db.Dbcon;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author kites
 */
public class CreateDatabaseProcess {

    public static void createDatabaseProcess(ArrayList<String> tables, String file, String db) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String text = "package com." + db + "." + new File(new File(file).getParent()).getName() + ";\n\n";
            for (String string : tables) {
                text += "import com." + db + ".bean." + string.substring(0, 1).toUpperCase() + string.substring(1, string.length()) + ";\n";
            }
            text += "\nimport java.sql.ResultSet;\n"
                    + "import java.util.ArrayList;\n"
                    + "public class DatabaseProcess extends DatabaseConnection{\n";
            fileOutputStream.write(text.getBytes());
            for (String table : tables) {
                ArrayList<String> tupples = Dbcon.getTupples(table);
                text = "\n    public ArrayList<" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "> getAll" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "s() {\n"
                        + "        init();\n"
                        + "        ArrayList<" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "> " + table + "s = new ArrayList<" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + ">();\n"
                        + "        String sql = \"select * from `" + table + "`\";\n"
                        + "        ResultSet resultSet = getData(sql);\n"
                        + "        try {\n"
                        + "            while (resultSet.next()) {\n"
                        + "                " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + " " + table + " = new " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "();\n";
                for (int i = 0; i < tupples.size(); i++) {
                    String tupple = tupples.get(i);
                    text += "                " + table + ".set" + tupple.substring(0, 1).toUpperCase() + tupple.substring(1, tupple.length()) + "(resultSet.getString(" + (i + 1) + "));\n";
                }
                text += "                " + table + "s.add(" + table + ");\n"
                        + "            }\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return " + table + "s;\n"
                        + "    }\n"
                        + "\n"
                        + "    public " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + " get" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "(String id) {\n"
                        + "        init();\n"
                        + "        String sql = \"select * from `" + table + "` where `" + tupples.get(0) + "`='\" + id + \"'\";\n"
                        + "        ResultSet resultSet = getData(sql);\n"
                        + "        " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + " " + table + " = null;\n"
                        + "        try {\n"
                        + "            if (resultSet.next()) {\n"
                        + "                " + table + " =new " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "();\n";
                for (int i = 0; i < tupples.size(); i++) {
                    String tupple = tupples.get(i);
                    text += "                " + table + ".set" + tupple.substring(0, 1).toUpperCase() + tupple.substring(1, tupple.length()) + "(resultSet.getString(" + (i + 1) + "));\n";
                }
                text += "            }\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return " + table + ";\n"
                        + "    }\n"
                        + "\n"
                        + "    public int getMax_" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "() {\n"
                        + "        init();\n"
                        + "        String sql = \"select MAX(" + tupples.get(0) + ") from `" + table + "`\";\n"
                        + "        ResultSet resultSet = getData(sql);\n"
                        + "        int i = 0;\n"
                        + "        try {\n"
                        + "            if (resultSet.next()) {\n"
                        + "                i=resultSet.getInt(1);\n"
                        + "            }\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return i;\n"
                        + "    }\n"
                        + "\n"
                        + "    public int save" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "(" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + " " + table + ") {\n"
                        + "        init();\n"
                        + "        String query = \"insert into `" + table + "` values('\" + " + table + ".get" + tupples.get(0).substring(0, 1).toUpperCase() + tupples.get(0).substring(1, tupples.get(0).length()) + "() + \"'";
                for (int i = 1; i < tupples.size(); i++) {
                    text += ",'\" + " + table + ".get" + tupples.get(i).substring(0, 1).toUpperCase() + tupples.get(i).substring(1, tupples.get(i).length()) + "() + \"'";
                }
                text += ")\";\n"
                        + "        int response = 0;\n"
                        + "        try {\n"
                        + "            response = putData(query);\n"
                        + "            query = \"select max(`" + tupples.get(0) + "`) from `" + table + "`\";\n"
                        + "            ResultSet resultSet = getData(query);\n"
                        + "            if(resultSet.next()){\n"
                        + "                response = resultSet.getInt(1);\n"
                        + "            }\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return response;\n"
                        + "    }\n"
                        + "\n"
                        + "    public int update" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "(" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + " " + table + ") {\n"
                        + "        init();\n"
                        + "        String query = \"update `" + table + "` set `" + tupples.get(0) + "`='\" + " + table + ".get" + tupples.get(0).substring(0, 1).toUpperCase() + tupples.get(0).substring(1, tupples.get(0).length()) + "() + \"'";
                for (int i = 1; i < tupples.size(); i++) {
                    text += ",`" + tupples.get(i) + "`='\" + " + table + ".get" + tupples.get(i).substring(0, 1).toUpperCase() + tupples.get(i).substring(1, tupples.get(i).length()) + "() + \"'";
                }
                text += " where `" + tupples.get(0) + "`='\" + " + table + ".get" + tupples.get(0).substring(0, 1).toUpperCase() + tupples.get(0).substring(1, tupples.get(0).length()) + "() + \"' \";\n"
                        + "        int response = 0;\n"
                        + "        try {\n"
                        + "            response = putData(query);\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return response;\n"
                        + "    }\n"
                        + "\n"
                        + "    public int delete" + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "(String id) {\n"
                        + "        init();\n"
                        + "        String query = \"delete from `" + table + "` where `" + tupples.get(0) + "`='\" + id + \"' \";\n"
                        + "        int response = 0;\n"
                        + "        try {\n"
                        + "            response = putData(query);\n"
                        + "            close();\n"
                        + "        } catch (Exception e) {\n"
                        + "            e.printStackTrace();\n"
                        + "        }\n"
                        + "        return response;\n"
                        + "    }";
                fileOutputStream.write(text.getBytes());
            }
            text = "\n}";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
