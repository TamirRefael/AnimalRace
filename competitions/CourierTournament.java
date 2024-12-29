package competitions;

import animals.Animal;
import competition.AnimalThread;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {

    public CourierTournament(String type, int numHeats, Animal[][] animals, Object additionalInfo) {
        super(type, numHeats);  // קריאה לבנאי של Tournament/Competition עם סוג התחרות ומספר המקצים
        setup(animals, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animals, Object additionalInfo) {
        AtomicBoolean startFlag = new AtomicBoolean(false);
        Scores scores = new Scores();

        for (Animal[] team : animals) {
            int teamSize = team.length;
            AtomicBoolean[] finishFlags = new AtomicBoolean[teamSize];
            for (int i = 0; i < teamSize; i++) {
                finishFlags[i] = new AtomicBoolean(false);
            }

            double neededDistance = 100.0 / teamSize;

            for (int i = 0; i < teamSize; i++) {
                AtomicBoolean currentStartFlag = (i == 0) ? startFlag : finishFlags[i - 1];
                AtomicBoolean currentFinishFlag = finishFlags[i];

                AnimalThread animalThread = new AnimalThread(team[i], neededDistance, currentStartFlag, currentFinishFlag);
                new Thread(animalThread).start();
            }

            Referee referee = new Referee(team[teamSize - 1].getName(), scores, team[teamSize - 1], neededDistance);
            new Thread(referee).start();
        }

        int groups = animals.length;
        this.tournamentThread = new TournamentThread(scores, startFlag, groups);
        new Thread(this.tournamentThread).start();
    }
}
