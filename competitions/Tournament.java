package competitions;

import animals.Animal;
import competition.Competition;

public abstract class Tournament extends Competition {
    protected TournamentThread tournamentThread;

    /**
     * הבנאי של המחלקה, מקבל את סוג התחרות ומספר המקצים הנדרש להקמת התחרות.
     * @param type סוג התחרות (למשל "Land", "Water", "Air").
     * @param numHeats מספר המקצים בתחרות.
     */
    public Tournament(String type, int numHeats) {
        super(type, numHeats);  // קריאה לבנאי של Competition עם סוג התחרות ומספר המקצים
    }

    /**
     * פונקציה אבסטרקטית להקמת התחרות, תוגדר על ידי המחלקות היורשות.
     * @param animals מערך דו ממדי של חיות.
     * @param additionalInfo מידע נוסף הנדרש להקמת התחרות.
     */
    protected abstract void setup(Animal[][] animals, Object additionalInfo);

    /**
     * פונקציה המחזירה את השדה TournamentThread.
     * @return השדה TournamentThread.
     */
    public TournamentThread getTournamentThread() {
        return tournamentThread;
    }
}
