package Graphics;

import competition.*;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.List;

/**
 * A dialog for selecting a competition from a list of available competitions.
 */
public class SelectCompetitionDialog extends JDialog {
    private JComboBox<Competition> competitionComboBox;
    private CompetitionManager competitionManager;
    private ZooPanel zooPanel;

    /**
     * Constructs a new SelectCompetitionDialog.
     *
     * @param owner               The frame that owns this dialog.
     * @param competitionManager  The competition manager that provides the list of competitions.
     * @param zooPanel            The zoo panel where the race will be displayed.
     */
    public SelectCompetitionDialog(Frame owner, CompetitionManager competitionManager, ZooPanel zooPanel) {
        super(owner, "Select Competition", true);
        this.competitionManager = competitionManager;
        this.zooPanel = zooPanel;

        this.setSize(400, 200);
        this.setLocationRelativeTo(owner);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Select Competition:"), gbc);

        gbc.gridx = 1;
        competitionComboBox = new JComboBox<>(new Vector<>((Collection) getValidCompetitions()));
        mainPanel.add(competitionComboBox, gbc);

        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("Cancel");

        startButton.addActionListener(e -> startRace());
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Gets the list of valid competitions that have at least two animals.
     *
     * @return A list of valid competitions.
     */
    private List<Competition> getValidCompetitions() {
        return competitionManager.getCompetitions().stream()
                .filter(comp -> comp.getAnimals().size() >= 2)
                .collect(Collectors.toList());
    }

    /**
     * Starts the race for the selected competition.
     */
    private void startRace() {
        Competition selectedCompetition = (Competition) competitionComboBox.getSelectedItem();
        System.out.println("Selected competition: " + selectedCompetition);
        if (selectedCompetition != null) {
            new RaceManager(selectedCompetition, zooPanel).startRace();
        }
        dispose();
    }

}
