package dbproject.core.exercise;

import dbproject.core.Util;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Strength extends Exercise {

  private String type;
  private int weight;
  private int repetitions;
  private int sets;

  public Strength(String NAME) {
    super(NAME);
  }

  public Strength(String NAME, String type, int weight, int repetitions, int sets) {
    super(NAME);
    this.type = type;
    this.weight = weight;
    this.repetitions = repetitions;
    this.sets = sets;
  }

  @Override
  public void make() {
    setType();
    setWeight();
    setRepetitions();
    setSets();
  }

  public String getType() {
    return type;
  }

  public void setType() {
    type = Util.getStringInput("Kondisjon eller Styrke (k/s)", "Ugyldig input");
    if (type.equals("s")) {
      type = "styrke";
      return;
    }
    type = "kondisjon";
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight() {
    weight = Util.getIntInput("Belastning", "Ugyldig tall");
  }

  public int getRepetitions() {
    return repetitions;
  }

  public void setRepetitions() {
    repetitions = Util.getIntInput("Repetisjoner", "Ugyldig tall");
  }

  public int getSets() {
    return sets;
  }

  public void setSets() {
    sets = Util.getIntInput("Antall sett", "Ugyldig tall");
  }
}
