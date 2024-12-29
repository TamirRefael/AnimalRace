package competitions;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {
    private final Scores scores;
    private final AtomicBoolean startSignal;
    private final int groups;

    public TournamentThread(Scores scores, AtomicBoolean startSignal, int groups) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.groups = groups;
    }

    @Override
    public void run() {
        // הצבת הדגל להתחלת התחרות והודעה לכל התהליכים הממתינים
        startSignal.set(true);
        synchronized (startSignal) {
            startSignal.notifyAll();
        }

        // מעקב אחרי התקדמות הקבוצות
        while (true) {
            synchronized (scores) {
                Map<String, Date> allScores = scores.getAll();
                int finishedGroups = allScores.size();

                // הצגת תוצאות לקבוצות שסיימו
                updateDisplay(allScores);

                // אם כל הקבוצות סיימו, יציאה מהלולאה
                if (finishedGroups == groups) {
                    break;
                }
            }

            try {
                Thread.sleep(100); // המתנה לפני העדכון הבא של הנתונים
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updateDisplay(Map<String, Date> allScores) {
        // פונקציה זו תטפל בעדכון התצוגה בזמן אמת
        for (Map.Entry<String, Date> entry : allScores.entrySet()) {
            System.out.println("קבוצה " + entry.getKey() + " סיימה בזמן " + entry.getValue());
        }
    }
}
