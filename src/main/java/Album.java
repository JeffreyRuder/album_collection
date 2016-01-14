import java.time.LocalDateTime;
import java.util.ArrayList;

public class Album {
  private static ArrayList<Album> instances = new ArrayList<Album>();

  private final String mName;
  private final Artist mArtist;
  private int mId;
  private LocalDateTime mCreatedAt;

  //CONSTRUCTOR

  public Album(String name, Artist artist) {
    mName = name;
    mArtist = artist;
    mCreatedAt = LocalDateTime.now();
    instances.add(this);
    mId = instances.size();
  }

  //STATIC METHODS

  public static ArrayList<Album> all() {
    return instances;
  }

  public static Album find(int id) {
    try {
      return instances.get(id -1);
    } catch (IndexOutOfBoundsException e) {
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

  public Artist getArtist() {
    return mArtist;
  }

  public LocalDateTime getCreatedAt() {
    return mCreatedAt;
  }
}
