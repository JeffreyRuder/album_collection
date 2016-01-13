import java.util.ArrayList;

public class Artist {
  private static ArrayList<Artist> instances = new ArrayList<Artist>();

  private final String mName;
  private int mId;

  //CONSTRUCTOR

  public Artist(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
  }

  //STATIC METHODS

  public static ArrayList<Artist> all() {
    return instances;
  }

  public static Artist find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException iobe) {
      return null;
    }
  }

  public static void clear() {
    instances.clear();
  }

  //INSTANCE METHODS

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }
}
