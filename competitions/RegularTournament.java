package competitions;

import animals.Animal;
import competition.AnimalThread;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament {

    public RegularTournament(String type, int numHeats, Animal[][] animals, Object additionalInfo) {
        super(type, numHeats);  // קריאה לבנאי של Tournament/Competition עם סוג התחרות ומספר המקצים
        setup(animals, additionalInfo);
    }

    @Override
    protected void setup(Animal[][] animals, Object additionalInfo) {
        AtomicBoolean startFlag = new AtomicBoolean(false);
        Scores scores = new Scores();

        for (Animal[] team : animals) {
            AtomicBoolean finishFlag = new AtomicBoolean(false);

            AnimalThread animalThread = new AnimalThread(team[0], 100.0, startFlag, finishFlag);
            new Thread(animalThread).start();

            Referee referee = new Referee(team[0].getName(), scores, team[0], 100.0);
            new Thread(referee).start();
        }

        int groups = animals.length;
        this.tournamentThread = new TournamentThread(scores, startFlag, groups);
        new Thread(this.tournamentThread).start();
    }
}
