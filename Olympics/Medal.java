package Olympics;

import java.util.Objects;

/**
 * Represents a medal awarded in a tournament.
 */
public class Medal {

    /**
     * The type of medal: BRONZE, SILVER, or GOLD.
     */
    public enum Type {BRONZE, SILVER, GOLD}

    private Type type;
    private String tournament;
    private int year;

    /**
     * Constructs a new Medal with the specified type, tournament, and year.
     *
     * @param type       The type of the medal.
     * @param tournament The name of the tournament.
     * @param year       The year the medal was awarded.
     */
    public Medal(Type type, String tournament, int year) {
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }

    /**
     * Gets the type of the medal.
     *
     * @return The type of the medal.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the medal.
     *
     * @param type The type to set.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the name of the tournament where the medal was awarded.
     *
     * @return The name of the tournament.
     */
    public String getTournament() {
        return tournament;
    }

    /**
     * Sets the name of the tournament where the medal was awarded.
     *
     * @param tournament The name of the tournament to set.
     */
    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    /**
     * Gets the year the medal was awarded.
     *
     * @return The year the medal was awarded.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year the medal was awarded.
     *
     * @param year The year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Compares this medal to the specified object. The result is true if and only if the argument is not null
     * and is a Medal object that has the same type, tournament, and year as this medal.
     *
     * @param o The object to compare this Medal against.
     * @return true if the given object represents a Medal equivalent to this medal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medal)) return false;
        Medal medal = (Medal) o;
        return year == medal.year &&
                type == medal.type &&
                Objects.equals(tournament, medal.tournament);
    }

    /**
     * Returns a string representation of this Medal.
     *
     * @return A string representation of this Medal.
     */
    @Override
    public String toString() {
        return type +
                ", tournament: " + tournament +
                ", year: " + year;
    }
}
