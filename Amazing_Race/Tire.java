/** 
 *  This class represents a Tire with a given grip.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class Tire
{
  private int grip;
  
  /** 
   *  Constructor to create a Wheel.
   *  @param grip The wheel grip
   */ 
  public Tire(int grip)
  {
    this.grip = grip;
  }
  
  /** 
   *  This method returns the wheel grip.
   *  @return The wheel's grip
   */ 
  public int getGrip()
  {
    return grip;
  }
    
  /** 
   *  A description of the wheel.
   *  @return The wheel's description
   */
  public String toString()
  {
    return grip + " Grip";
  }
}