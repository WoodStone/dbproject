package dbproject.database;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBInitializer {

    private static String createTablesFile = "src/koie/database/database_create_tables.sql";
    private static String insertValuesFile = "src/koie/database/database_insert_values.sql";

    public static void createTables() {
        executeSqlFile(createTablesFile);
    }

    public static void insertValues() {
        executeSqlFile(insertValuesFile);
    }

    private static List<String> readSqlFile(String fileName) {
        ArrayList<String> statementList = new ArrayList<String>();
        String statement = "";
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader((new FileReader(fileName))));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                statement = statement.concat(line);

                if (line.contains(";")) {
                    statementList.add(statement);
                    statement = "";
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
        return statementList;
    }

    private static void executeSqlFile(String fileName) {
        List<String> statementList = readSqlFile(fileName);
        for (String s : statementList) {
            DBConnector.makeStatement(s);
        }
    }

}
