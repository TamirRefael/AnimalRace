package Graphics;

import competition.Competition;
import competition.CompetitionManager;
import competitions.CourierTournament;
import competitions.RegularTournament;
import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompetitionDialog extends JDialog {
    private CompetitionManager competitionManager;

    public AddCompetitionDialog(Frame owner, CompetitionManager competitionManager) {
        super(owner, "Add Competition", true);
        this.competitionManager = competitionManager;
        this.setSize(400, 250);
        this.setLocationRelativeTo(owner);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Competition Type:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(new String[]{"Air", "Water", "Land"});
        mainPanel.add(competitionTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Competition Mode:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> competitionModeComboBox = new JComboBox<>(new String[]{"Regular", "Relay"});
        mainPanel.add(competitionModeComboBox, gbc);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) competitionTypeComboBox.getSelectedItem();
                String selectedMode = (String) competitionModeComboBox.getSelectedItem();

                int numHeats;
                switch (selectedType) {
                    case "Air":
                        numHeats = 5;
                        break;
                    case "Water":
                        numHeats = 4;
                        break;
                    case "Land":
                        numHeats = 1;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid competition type.");
                }

                try {
                    Animal[][] animals = new Animal[0][0]; // זהו מערך ריק לצורך הדוגמה
                    Object additionalInfo = new Object();  // כל מידע נוסף נדרש

                    Competition competition;

                    if ("Relay".equals(selectedMode)) {
                        competition = new CourierTournament(selectedType, numHeats, animals, additionalInfo);
                    } else {
                        competition = new RegularTournament(selectedType, numHeats, animals, additionalInfo);
                    }

                    competitionManager.addCompetition(competition);
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition added with ID: " + competition.getCompetitionId());
                    ((CompetitionFrame) owner).updateButtonStates();
                    dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
