package competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private final Map<String, Date> scores;

    public Scores() {
        // יצירת ה-Map בעזרת SynchronizedMap לשמירה על בטיחות ריבוי תהליכים
        this.scores = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * מוסיף מחרוזת עם הזמן הנוכחי ל-scores.
     * @param name שם התוצאה להוסיף.
     */
    public void add(String name) {
        synchronized (scores) {
            scores.put(name, new Date());
        }
    }

    /**
     * מחזיר את כל התוצאות השמורות.
     * @return Map עם כל התוצאות והשעות.
     */
    public Map<String, Date> getAll() {
        synchronized (scores) {
            return new HashMap<>(scores); // מחזיר עותק כדי לשמור על בטיחות ריבוי תהליכים
        }
    }
}
