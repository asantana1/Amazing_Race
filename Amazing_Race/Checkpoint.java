/** 
 *  This class represents a Checkpoint with an xy coordinate system, a name,
 *  and a path.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class Checkpoint 
{
  private int x;
  private int y;
  private String name;
  private String path;

  /** 
   *  Constructor to create a Checkpoint. 
   *  @param x The x coordinate
   *  @param y The y coordinate
   *  @param name The checkpoint's name
   *  @param path A path of checkpoints
   */ 
  public Checkpoint(int x, int y, String name, String path)
  {
    this.x = x;
    this.y = y;
    this.name = name;
    this.path = path;
  }

  /** 
   *  This method retrieves the current value for the x coordinate.
   *  @return The x coordinate
   */ 
  public int getX() 
  {
    return x;
  }

  /** 
   *  This method retrieves the current value for the y coordinate.
   *  @return The y coordinate
   */ 
  public int getY() 
  {
    return y;
  }
  
  /** 
   *  This method returns the name of the checkpoint.
   *  @return The checkpoint's name
   */ 
  public String getPath() 
  {
    return path;
  }

  /** 
   *  This method returns a path of checkpoints.
   *  @return A path of checkpoints
   */ 
  public String getName()
  {
    return name;
  }
}