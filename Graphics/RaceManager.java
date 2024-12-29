package Graphics;

import animals.Animal;
import competition.Competition;
import mobility.Point;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manages the race for a given competition and updates the zoo panel.
 */
public class RaceManager {
    private Competition competition;
    private ZooPanel zooPanel;
    private Timer raceTimer;

    /**
     * Constructs a new RaceManager.
     *
     * @param competition The competition to be managed.
     * @param zooPanel    The zoo panel where the race will be displayed.
     */
    public RaceManager(Competition competition, ZooPanel zooPanel) {
        this.competition = competition;
        this.zooPanel = zooPanel;
    }

    /**
     * Starts the race for the competition.
     */
    public void startRace() {
        ActionListener raceTask = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean allAnimalsStopped = true;
                for (Animal animal : competition.getAnimals()) {
                    if (animal.getEnergyAmount() > 0 && !reachedFinishLine(animal)) {
                        animal.move();
                        allAnimalsStopped = false;
                    }
                }
                // If all animals have stopped, stop the timer
                if (allAnimalsStopped) {
                    raceTimer.stop();
                }
                zooPanel.repaint();
            }
        };
        raceTimer = new Timer(85, raceTask);
        raceTimer.start();
    }

    /**
     * Checks if the given animal has reached the finish line.
     *
     * @param animal The animal to check.
     * @return true if the animal has reached the finish line, false otherwise.
     */
    private boolean reachedFinishLine(Animal animal) {
        Point startPosition = animal.getRaceTrack().getStartPosition(animal.getcompetitionspecificType(), animal.getheat());
        Point currentLocation = animal.getLocation();
        String orientation = animal.getOrientation(); // קבלת הכיוון הנוכחי של החיה באמצעות getOrientation()

        if (animal.getcompetitionspecificType().equals("Land")) {

            // בדיקת סיום תנועה אנכית סופית (צפונה - NORTH)
            if (orientation.equals("NORTH") && currentLocation.getX() == startPosition.getX() &&
                    currentLocation.getY() == startPosition.getY()) {
                return true; // סיימה את המסלול
            }

            return false;

        } else {
            // לוגיקה קיימת לחיות שאינן יבשתיות
            Point finishLine = animal.getRaceTrack().getFinishLine(animal.getcompetitionspecificType(), animal.getheat());
            return currentLocation.getX() >= finishLine.getX();
        }
    }
}
