package dbproject.command;

import dbproject.Main;
import dbproject.command.core.CommandBase;
import dbproject.database.DBProxy;

import java.util.List;

public class CommandKategori extends CommandBase {

  @Override
  public String getName() {
    return "kategori";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(List<String> args) {

    for (List<String> kat : DBProxy.getKategorier()) {
      System.out.println(kat.get(1));
    }

    if (args.size() > 0) {
      String kategori = args.get(0).toLowerCase();
      try {
        DBProxy.addKategori(kategori, Main.user);
        System.out.println(kategori + " lagt til i kategorier");
      } catch (IllegalArgumentException e) {
        System.out.println(kategori + " eksisterer allerede");
      }
    } else {
      System.out.println(getUsage());
    }
  }

}
