package dbproject.core.exercise;

/**
 * Created by Vestein on 15.03.2016.
 */
public abstract class Exercise {

  public final String NAME;

  public Exercise(String NAME) {
    this.NAME = NAME;
  }

  public abstract void make();

}
