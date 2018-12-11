import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.sound.midi.*;
import java.io.*;
import java.net.*;


/** 
 *  This class represents the Panel that holds all GUI Components.
 *  @author Anderson Santana
 *  @author Shaunt Keshishian
 *  @version 1.1
 */ 
public class RacePanel extends JPanel 
{
  private final int WIDTH = 1500, HEIGHT = 700;
  private final int DELAY = 30;
  private JButton start;
  private Race race;
  private Timer timer;
  private ArrayList<JLabel> labels = new ArrayList <JLabel>();
  Sequence intro;
  Sequence first;
  Sequencer introSequencer;
  Sequencer firstSequencer;
  
  /** 
   *  Constructor for the RacePanel.
   */
  public RacePanel() 
  {
    try
    {
     intro = MidiSystem.getSequence(new File("intro.mid"));
     introSequencer = MidiSystem.getSequencer();
     introSequencer.open();
     introSequencer.setSequence(intro);
     introSequencer.start();
     
     first = MidiSystem.getSequence(new File("first.mid"));
     firstSequencer = MidiSystem.getSequencer();
     firstSequencer.open();
     firstSequencer.setSequence(first);
  }
  catch (MalformedURLException e) {}
  catch (IOException e) {}  
  catch (MidiUnavailableException e) {}
  catch (InvalidMidiDataException e) {}


    
    setLayout(null);
    Random generator = new Random();
    RaceListener listener = new RaceListener();
    start = new JButton("START");
    timer = new Timer(DELAY, listener);
    start.addActionListener(listener);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.lightGray);
    
    
    // Creates and initializes all checkpoints
    ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
    checkpoints.add(new Checkpoint(10, 10, "A", "B, C, D, A"));
    checkpoints.add(new Checkpoint(610, 10, "B", "C, D, A, B"));
    checkpoints.add(new Checkpoint(610, 610, "C", "D, A, B, C"));
    checkpoints.add(new Checkpoint(10, 610, "D", "A, B, C, D"));
   
    // Creates all set of tires
    ArrayList<Tire> tires1 = new ArrayList<Tire>();
    ArrayList<Tire> tires2 = new ArrayList<Tire>();
    ArrayList<Tire> tires3 = new ArrayList<Tire>();
    ArrayList<Tire> tires4 = new ArrayList<Tire>();
   
    // Initializes all set of tires with random grips
    for(int i = 0; i < 4; i++)
    {
      int tireGrip = generator.nextInt(20);
      for(int j = 0; j < 4; j++)
      {
        if(i == 0)
          tires1.add(new Tire(tireGrip));
        else if(i == 1)
          tires2.add(new Tire(tireGrip));
        else if(i == 2)
          tires3.add(new Tire(tireGrip));
        else
          tires4.add(new Tire(tireGrip));
      }
    }
    
    // Creates and initializes the racing venue
    RacingVenue venue = new RacingVenue(0, 0, checkpoints);

    // Creates and initializes all cars with random Engines
    ArrayList<Car> cars = new ArrayList<Car>();
    cars.add(new Car(checkpoints.get(0).getX(), checkpoints.get(0).getY(), "Big Red Machine", new ImageIcon("red.png"), new Engine(generator.nextInt(100) + 50), tires1));
    cars.add(new Car(checkpoints.get(1).getX(), checkpoints.get(1).getY(), "Gray Matter", new ImageIcon("gray.png"), new Engine(generator.nextInt(100) + 50), tires2));
    cars.add(new Car(checkpoints.get(2).getX(), checkpoints.get(2).getY(), "Yellow Mellow", new ImageIcon("yellow.png"), new Engine(generator.nextInt(100) + 50), tires3));
    cars.add(new Car(checkpoints.get(3).getX(), checkpoints.get(3).getY(), "Cyber Cyan", new ImageIcon("cyan.png"), new Engine(generator.nextInt(100) + 50), tires4));
       
    // Creates individual labels for each car in the race.
    for(int i = 0; i < cars.size() ;i++) 
    {
      labels.add(new JLabel());
    }
    
    // Initializes all labels for each car in the race.
    for(int i = 0; i < cars.size(); i++)
    {
      labels.get(i).setText("Checkpoint: " + checkpoints.get(i).getName() + " | Name: " + cars.get(i).getName() + 
                               " | Engine: " + cars.get(i).getEngine() + " horse power " + " | Tire: " + cars.get(i).getTire() + " grip");
    }

    
    // Creates another JPanel to hold the above labels and the start race button   
    JPanel carPanel= new JPanel();
    carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
    carPanel.setSize(575, 300);
    carPanel.setLocation(700, 0);
    carPanel.setBackground(Color.white);
    carPanel.add(start);
    carPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    carPanel.add(labels.get(0));
    carPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    carPanel.add(labels.get(1));
    carPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    carPanel.add(labels.get(2));
    carPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    carPanel.add(labels.get(3));
         
    
  
    // Initializes the race
    race = new Race(cars, venue);
    add(carPanel);
    timer.start();
    
  }
 
  /** 
   *  This method paints all the components of the panel.
   *  @param g The graphics context
   */
  public void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    race.drawRace(g2, this);
    if(race.isRaceInProgress()) // Update component when race is in progress.
      race.update(g2, this);
    if(race.isRaceOver())
    {
      race.resetRace();
      JOptionPane.showMessageDialog(null, race.finalRaceResults());
      System.exit(0);
    }
  }

  /** 
   *  This class represents the listener that will respond to the start button..
   *  @author Anderson Santana
   *  @author Shaunt Keshishian
   *  @version 1.0
   */ 
  private class RaceListener implements ActionListener 
  {
    public void actionPerformed(ActionEvent event) 
    {
      if(!race.isRaceInProgress()) // Starts the race only when race is not in progress.
      {        
        if(event.getSource() == start)
        {
          introSequencer.stop();
          firstSequencer.start();
          race.startRace();
          start.setEnabled(false);
        }
      }
      repaint();
    }
  }
}