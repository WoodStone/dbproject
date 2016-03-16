package dbproject.core.exercise;

import dbproject.core.Util;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Stamina extends Exercise {

  private int length;
  private int time;

  public Stamina(String NAME) {
    super(NAME);
  }

  public Stamina(String NAME, int length, int time) {
    super(NAME);
    this.length = length;
    this.time = time;
  }

  @Override
  public void make() {
    setLength();
    setTime();
  }

  public int getTime() {
    return time;
  }

  public void setTime() {
    time = Util.getIntInput("Tid", "Ugyldig tid");
  }

  public int getLength() {
    return length;
  }

  public void setLength() {
    length = Util.getIntInput("Lengde i km", "Ugyldig lengde");
  }

}
