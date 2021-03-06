import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/** 
 *  This class represents a Racing Venue with an xy coordinate system and checkpoints.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class RacingVenue 
{
  private int x;
  private int y;
  private ArrayList<Checkpoint> checkpoints;
    
  /** 
   *  Constructor to create a Racing Venue.
   *  @param x The x coordinate
   *  @param y The y coordinate
   *  @param checkpoints A collection of checkpoints
   */ 
  public RacingVenue(int x, int y, ArrayList<Checkpoint> checkpoints) 
  {
    this.x = x;
    this.y = y;
    this.checkpoints = checkpoints;
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
   *  This method retrieves the checkpoint x coordinate location 
   *  of the venue indicated by it's parameter.
   *  @param coordinate The x coordinate of this checkpoint
   *  @return The x coordinate
   */
  public int getXCheckpoint(int coordinate) 
  {
    return checkpoints.get(coordinate).getX();
  }
  
  /** 
   *  This method retrieves the checkpoint y coordinate location 
   *  of the venue indicated by it's parameter.
   *  @param coordinate The y coordinate of this checkpoint
   *  @return The y coordinate
   */
  public int getYCheckpoint(int coordinate) 
  {
    return checkpoints.get(coordinate).getY();
  }
  
  /** 
   *  This method retrieves the checkpoint path of the venue 
   *  indicated by it's parameter.
   *  @param path The path of this checkpoint
   *  @return The path of the checkpoint
   */
  public String getPath(int path)
  {
    return checkpoints.get(path).getPath();
  }

  /** 
   *  A square venue is drawn on an (x,y) coordinate.
   *  @param g The graphics context
   *  @param panel The panel where the car is to be drawn
   */
  public void drawSquareVenue(Graphics2D g, JPanel panel) 
  {
   g.drawRect(x, y, 695, 695);
   // Draws a middle square inside the venue.
   g.setColor(Color.darkGray); 
   g.fillRect(100, 100, 495, 495);
  }
}