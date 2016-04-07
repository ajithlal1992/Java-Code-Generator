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
public class CreateBean {

    public static void createBean(String path, String table, String db) {
        try {
            File Fpath = new File(path);
            File file = new File(path + File.separator + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + ".java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ArrayList<String> tupples = Dbcon.getTupples(table);
            String text = "package com." + db + "." + Fpath.getName() + ";\n\n";
            text += "public class " + table.substring(0, 1).toUpperCase() + table.substring(1, table.length()) + "{\n\n";
            fileOutputStream.write(text.getBytes());

            for (String tupple : tupples) {
                text = "    private String " + tupple + " = \"\";\n";
                fileOutputStream.write(text.getBytes());
            }
            for (String tupple : tupples) {
                text = "\n    public String get" + tupple.substring(0, 1).toUpperCase() + tupple.substring(1, tupple.length()) + "() {\n"
                        + "        return " + tupple + ";\n"
                        + "    }\n";
                fileOutputStream.write(text.getBytes());
            }
            for (String tupple : tupples) {
                text = "    public void set" + tupple.substring(0, 1).toUpperCase() + tupple.substring(1, tupple.length()) + "(String " + tupple + ") {\n"
                        + "        this." + tupple + " = " + tupple + ";\n"
                        + "    }\n\n";
                fileOutputStream.write(text.getBytes());
            }
            text = "    @Override\n"
                    + "    public String toString() {\n"
                    + "        return " + tupples.get(0);
            fileOutputStream.write(text.getBytes());
            text = "";
            for (int i = 1; i < tupples.size(); i++) {
                text += "+\",,\"+" + tupples.get(i);
            }
            text += ";\n    }\n}";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
