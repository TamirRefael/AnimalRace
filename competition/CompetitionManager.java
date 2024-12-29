package competition;

import animals.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of competitions.
 */
public class CompetitionManager {
    private List<Competition> competitions;

    /**
     * Constructs a new CompetitionManager with an empty list of competitions.
     */
    public CompetitionManager() {
        this.competitions = new ArrayList<>();
    }

    /**
     * Adds a new competition to the manager.
     *
     * @param competition The competition to add.
     * @throws IllegalArgumentException If a competition of the same type already exists.
     */
    public void addCompetition(Competition competition) {
        if (getCompetitionByType(competition.getType()) == null) {
            competitions.add(competition);
        } else {
            throw new IllegalArgumentException("Competition of this type already exists.");
        }
    }

    /**
     * Gets a competition by its type.
     *
     * @param type The type of the competition.
     * @return The competition of the specified type, or null if no such competition exists.
     */
    public Competition getCompetitionByType(String type) {
        for (Competition competition : competitions) {
            if (competition.getType().equalsIgnoreCase(type)) {
                return competition;
            }
        }
        return null;
    }

    /**
     * Gets all competitions.
     *
     * @return A list of all competitions.
     */
    public List<Competition> getCompetitions() {
        return competitions;
    }

    /**
     * Gets competitions by their type.
     *
     * @param type The type of competitions to get.
     * @return A list of competitions of the specified type.
     */
    public List<Competition> getCompetitionsByType(String type) {
        List<Competition> filteredCompetitions = new ArrayList<>();
        for (Competition competition : competitions) {
            if (competition.getType().equalsIgnoreCase(type)) {
                filteredCompetitions.add(competition);
            }
        }
        return filteredCompetitions;
    }
}
