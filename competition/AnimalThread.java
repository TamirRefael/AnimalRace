package competition;

import animals.Animal;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnimalThread implements Runnable {
    private final Animal participant;
    private final double neededDistance;
    private final AtomicBoolean startFlag;
    private final AtomicBoolean finishFlag;

    public AnimalThread(Animal participant, double neededDistance, AtomicBoolean startFlag, AtomicBoolean finishFlag) {
        this.participant = participant;
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    @Override
    public void run() {
        synchronized (startFlag) {
            while (!startFlag.get()) { // משתמשים ב-get() כדי לקרוא את הערך של AtomicBoolean
                try {
                    startFlag.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        while (!Thread.currentThread().isInterrupted()) {
            synchronized (participant) {
                participant.move(); // השתמש בפונקציה move הקיימת
                if (participant.getTotalDistance() >= neededDistance) {
                    synchronized (finishFlag) {
                        finishFlag.set(true); // משתמשים ב-set() כדי לשנות את הערך של AtomicBoolean
                        finishFlag.notifyAll();
                    }
                    break;
                }
            }

            try {
                Thread.sleep(100); // שינה למשך זמן מסוים
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
