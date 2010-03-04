package JExplorer;


public class DiskObject {
  public int type;
  public String name;
  public String path;
  public DiskObject(int type, String name, String path) {
    this.type = type;
    this.name = name;
    this.path = path;
  }
  public String toString() {
    return name;
  }
}