package dbproject.database;

import dbproject.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBProxy {

  public DBProxy() {

  }

  public static User isValidUser(String name) {
    try {
      ResultSet rs = DBConnector.makeQuery("select * from person;").getResultSet();
      while (rs.next()) {
        int id = rs.getInt("personNr");
        String navn = rs.getString("navn");
        if (name.equals(navn)) {
          return new User(id, navn);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void addKategori(String kategori, User user) {
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM kategori WHERE personNr=" + user.NR + " AND katNavn='" + kategori + "';").getResultSet();
      if (!rs.isBeforeFirst()) {
        DBConnector.makeStatement("insert into kategori(personNr,katNavn) values(" + user.NR + "," + "'" + kategori + "'" + ");");
      } else {
        throw new IllegalArgumentException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<List<String>> getKategorier() {
    List<List<String>> kategorier = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM kategori;").getResultSet();
      while (rs.next()) {
        int nr = rs.getInt("personNr");
        String name = rs.getString("katNavn");
        kategorier.add(Arrays.asList(Integer.toString(nr), name));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return kategorier;
  }

  public static void addOvelse(String ovelse) {

  }

}
