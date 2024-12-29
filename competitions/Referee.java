package competitions;

import animals.Animal;

public class Referee implements Runnable {
    private final String name;
    private final Scores scores;
    private final Animal animal;
    private final double finishLine;

    public Referee(String name, Scores scores, Animal animal, double finishLine) {
        this.name = name;
        this.scores = scores;
        this.animal = animal;
        this.finishLine = finishLine;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (animal) {
                if (animal.getTotalDistance() >= finishLine) {
                    scores.add(name);
                    break;
                }
            }

            try {
                Thread.sleep(50); // זמן המתנה קצר כדי לאפשר לחיה להתקדם
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
