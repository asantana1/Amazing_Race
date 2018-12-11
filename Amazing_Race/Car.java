import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics2D;

/** 
 *  This class represents a Car with an xy coordinate system, an image, 
 *  a name, an Engine and 4 Tires.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class Car 
{
  private int x, y, speed, performance;
  private String name;
  private ImageIcon image;
  private Engine engine;
  private ArrayList<Tire> tires = new ArrayList<Tire>();

  /** 
   *  Constructor to create a Car.
   *  @param x The x coordinate
   *  @param y The y coordinate
   *  @param name The car's name
   *  @param image The image for the car
   *  @param tires A collection of tires
   */ 
  public Car(int x, int y, String name, ImageIcon image, Engine engine, ArrayList<Tire> tires) 
  {
    this.x = x;
    this.y = y;
    this.name = name;
    this.image = image;
    this.engine = engine;
    this.tires = tires;
    setEnginePerformance();
    setTirePerformance();
    setSpeed();
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
   *  This method retrieves the name of the car.
   *  @return The car's name
   */ 
  public String getName()
  {
    return name;
  }
  
  /** 
   *  This method retrieves the engine power.
   *  @return The engine
   */
  public int getEngine()
  {
    return engine.getHorsePower();
  }
  
  /** 
   *  This method retrieves the tire grip.
   *  @return The tire
   */
  public int getTire()
  {
    return tires.get(0).getGrip();
  }
  
  /** 
   *  This method changes the speed of the car according to it's performance.
   */
  public void setSpeed()
  {
    if(performance == 13)
      speed = 25;
    else if(performance == 12)
      speed = 24;
    else if(performance == 11)
      speed = 20;
    else if(performance == 10)
      speed = 15;
    else if(performance == 9)
      speed = 12;
    else if(performance == 8)
      speed = 10;
    else if(performance == 7)
      speed = 8;
    else
      speed = performance;
  }
  
  /** 
   *  This method returns the current speed of the car.
   *  @return The current speed
   */
  public int getSpeed()
  {
    return speed;
  }
  
  /** 
   *  This method starts the car of the car.
   */
  public void startCar() 
  {
    engine.startEngine();
  }

  /** 
   *  This method stops the car and lowers its speed to 0.
   */
  public void stopCar() 
  {
    engine.stopEngine();
    speed = 0;
  }
  
  /** 
   *  This method returns whether the car is on or not.
   *  @return true If car is on, otherwise false
   */
  public boolean isCarOn() 
  {
    return engine.isEngineRunning();
  }
  
  /** 
   *  This method set's the car performance according to it's engine's horse power.
   */
  public void setEnginePerformance()
  {
    if(this.engine.getHorsePower() < 60)
      performance+= 1;
    else if(this.engine.getHorsePower() >= 60 && this.engine.getHorsePower() < 70)
      performance+= 2;
    else if(this.engine.getHorsePower() >= 70 && this.engine.getHorsePower() < 80)
      performance+= 3;
    else if(this.engine.getHorsePower() >= 80 && this.engine.getHorsePower() < 90)
      performance+= 4;
    else if(this.engine.getHorsePower() >= 90 && this.engine.getHorsePower() < 100)
      performance+= 5;
    else if(this.engine.getHorsePower() >= 100 && this.engine.getHorsePower() < 110)
      performance+= 6;
    else if(this.engine.getHorsePower() >= 110 && this.engine.getHorsePower() < 120)
      performance+= 7;
    else if(this.engine.getHorsePower() >= 120 && this.engine.getHorsePower() < 130)
      performance+= 8;
    else if(this.engine.getHorsePower() >= 130 && this.engine.getHorsePower() < 140)
      performance+= 9;
    else
      performance+= 10;
  }
  
  /** 
   *  This method set's the car performance according to it's tires's grip.
   */
  public void setTirePerformance()
  {
    if(this.tires.get(0).getGrip() < 5)
      performance+= 0;
    else if(this.tires.get(0).getGrip() >= 5 && this.tires.get(0).getGrip() < 10)
      performance+= 1;
    else if(this.tires.get(0).getGrip() >= 10 && this.tires.get(0).getGrip() < 15)
      performance+= 2;
    else
      performance+= 3;
  }
   
  /** 
   *  This method drives the car.
   *  @param g The graphics context
   *  @param panel The panel where the car is to be drawn
   */
  public void drive(Graphics2D g, JPanel panel)
  {
    if(this.getX() < 610 && this.getY() == 10) // From checkpoint A to B
      for(int i = 0; i < this.getSpeed(); i++)
        x++;
    else if(this.getX() == 610 && this.getY() < 610) // From checkpoint B to C
      for(int i = 0; i < this.getSpeed(); i++)
        y++;
    else if(this.getX() > 10 && this.getY() == 610) // From checkpoint C to D
      for(int i = 0; i < this.getSpeed(); i++)
        x--;
    else if(this.getX() == 10 && this.getY() > 10) // From checkpoint D to A
      for(int i = 0; i < this.getSpeed(); i++)
        y--;
  }
  
  /** 
   *  All cars are drawn on an (x,y) coordinate with a specific rotation
   *  according to it's location.
   *  @param g The graphics context
   *  @param panel The panel where the car is to be drawn
   */
  public void drawCar(Graphics2D g, JPanel panel) 
  {
    RotatedIcon rotate[] = new RotatedIcon[4];
    // Draws all cars on all locations between checkpoint A and checkpoint B not including B
    if((this.getX() >= 10 && this.getX() < 610) && this.getY() == 10) 
    {
      rotate[0] = new RotatedIcon(image, RotatedIcon.Rotate.DOWN);
      rotate[0].paintIcon(panel, g, x, y);
    }
    // Draws all cars on all locations between checkpoint B and checkpoint C not including C
    else if(this.getX() == 610 && (this.getY() >= 10 && this.getY() < 610))
    {
      rotate[1] = new RotatedIcon(image, RotatedIcon.Rotate.UPSIDE_DOWN);
      rotate[1].paintIcon(panel, g, x, y);
    }
    // Draws all cars on all locations between checkpoint C and checkpoint D not including D
    else if((this.getX() > 10 && this.getX() <= 610) && this.getY() == 610)
    {
      rotate[2] = new RotatedIcon(image, RotatedIcon.Rotate.UP);
      rotate[2].paintIcon(panel, g, x, y);
    }
    // Draws all cars on all locations between checkpoint D and checkpoint A not including A
    else if(this.getX() == 10 && (this.getY() > 10 && this.getY() <= 610))
    {
      rotate[3] = new RotatedIcon(image, RotatedIcon.Rotate.ABOUT_CENTER);
      rotate[3].paintIcon(panel, g, x, y);
    }
  }
  
  /** 
   *  A description of the car.
   *  @return The car's description
   */
  public String toString() 
  {
    String info = "\nName: " + name; 
    info += "\nEngine: " + engine.toString();
    info += "\nTires: " + tires.get(0).toString();
    info += "\nSpeed: " + speed + " Pixels Per Delay";
    return info;
  }
}