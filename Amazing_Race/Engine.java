/** 
 *  This class represents an Engine with a given horse power.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class Engine
{
  private boolean isRunning;
  private int horsePower;

  /** 
   *  Constructor to create an Engine.
   *  @param horsePower The engine horse power
   */ 
  public Engine(int horsePower)
  {
    this.horsePower = horsePower;
  }
  
  /** 
   *  This method returns the engine's horse power.
   *  @return The engine horse power
   */ 
  public int getHorsePower()
  {
    return horsePower;
  }
  
  /** 
   *  This method starts the engine.
   */ 
  public void startEngine()
  {
    isRunning = true;
  }
  
  /** 
   *  This method stops the engine.
   */ 
  public void stopEngine()
  {
    isRunning = false;
  }
  
  /** 
   *  This method returns whether the engine is on or not.
   *  @return true If engine is running, otherwise false
   */
  public boolean isEngineRunning()
  {
    return isRunning;
  }
  
  /** 
   *  A description of the engine.
   *  @return The engine's description
   */
  public String toString()
  {
    return horsePower + " horsepower";
  }
}