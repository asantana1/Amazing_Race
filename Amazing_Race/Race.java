import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/** 
 *  This class represents a Race with a cars and a racing venue.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class Race 
{
  private long[] start = new long[4];
  private long[] end = new long[4];
  private String[] raceResults = new String[4];
  private String finalRaceResults = "";
  private ArrayList<Car> cars;
  private RacingVenue venue;
  private boolean isRunning;
  private boolean isOver;
  private int position = 1;
  
  /** 
   *  Constructor to create a Race.
   *  @param cars A collection of cars
   *  @param venue The racing venue
   */
  public Race(ArrayList<Car> cars, RacingVenue venue) 
  {
    this.cars = cars;
    this.venue = venue;
    this.isRunning = false;
    this.isOver = false;
    raceResults[0] = "";
    raceResults[1] = "";
    raceResults[2] = "";
    raceResults[3] = "";
  }

  /** 
   * This method starts the race.
   */
  public void startRace()
  {
    isRunning = true;
    for (int i = 0; i < cars.size(); i++)
    {
      start[i] = System.currentTimeMillis();
      cars.get(i).startCar();
    }
  }

  /** 
   * This method stops the race.
   */
  public void stopRace()
  {
    isRunning = false;
    isOver = true;
    for (int i = 0; i < cars.size(); i++)
    {
      cars.get(i).stopCar();
    }
  }

  /** 
   *  This method draws the race.
   *  @param g The graphics context
   *  @param panel The panel where the race is to be drawn
   */
  public void drawRace(Graphics2D g, JPanel panel) 
  {
    venue.drawSquareVenue(g, panel);
    for (int i = 0; i < cars.size(); i++)
      cars.get(i).drawCar(g, panel);
  }
 
  /** 
   *  This method updates the race.
   *  @param g The graphics context
   *  @param panel The panel where the race is to be drawn
   */
  public void update(Graphics2D g, JPanel panel) 
  {
    if(position > cars.size()) // When all cars cross the finish line
      stopRace();
    
    for (int i = 0; i < cars.size(); i++)
    {
      cars.get(i).drive(g, panel);
      
      // When each car reaches the finish line.
      if(cars.get(i).getX() == venue.getXCheckpoint(i) && cars.get(i).getY() == venue.getYCheckpoint(i) && cars.get(i).isCarOn())
      {
        end[i] = System.currentTimeMillis();
        if(position == 1)
           raceResults[i]+= "Position: Winner " + cars.get(i).toString() + "\nPath: " + venue.getPath(i) + "\nTime: " + (end[i] - start[i]) / 1000.0 + " seconds!\n";
        else
          raceResults[i]+= "\nPosition: " + position + cars.get(i).toString() + "\nPath: " + venue.getPath(i) + "\nTime: " + (end[i] - start[i]) / 1000.0 + " seconds!\n";
        cars.get(i).stopCar();
        raceResults(raceResults[i]);
        position++;
      }
    }
  }
  
  /** 
   *  This method relays the result of the race to a String.
   */
  public void raceResults(String result)
  {
    finalRaceResults+= result;
  }
  
  /** 
   *  This method displays the final result of the race.
   */
  public String finalRaceResults()
  {
    return finalRaceResults;
  }
  
  /** 
   *  This method returns whether the race is in progress or not.
   *  @return true If race is in progress, otherwise false
   */
  public boolean isRaceInProgress() 
  {
    return isRunning;
  }
  
  /** 
   *  This method returns whether the race has ended.
   *  @return true If race has ended, otherwise false
   */
  public boolean isRaceOver() 
  {
    return isOver;
  }
  
  public void resetRace()
  {
    isRunning = false;
    isOver = false;
    JOptionPane.showMessageDialog(null, finalRaceResults());
    System.exit(0);
  } 
}