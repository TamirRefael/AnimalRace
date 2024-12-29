package Graphics;

import competition.CompetitionManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionFrame extends JFrame {
    private ZooPanel zooPanel;
    private CompetitionManager competitionManager;
    private JButton addAnimalButton;
    private JButton startRaceButton;

    public CompetitionFrame() {
        super("Competition");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 700);
        this.setLocationRelativeTo(null);

        zooPanel = new ZooPanel();
        competitionManager = new CompetitionManager();
        this.add(zooPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem helpItem = new JMenuItem("Help");

        fileMenu.add(exitItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));

        JButton addCompetitionButton = new JButton("Add Competition");
        addAnimalButton = new JButton("Add Animal");
        startRaceButton = new JButton("Start Race");
        JButton clearButton = new JButton("Clear");
        JButton eatButton = new JButton("Eat");
        JButton infoButton = new JButton("Info");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(addCompetitionButton);
        buttonPanel.add(addAnimalButton);
        buttonPanel.add(startRaceButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(eatButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(exitButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for menu items
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CompetitionFrame.this, "Home Work 2\nGUI", "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompetitionDialog(CompetitionFrame.this, competitionManager);
                updateButtonStates(); // Update button states after adding a competition
            }
        });

        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAnimalDialog(CompetitionFrame.this, zooPanel, competitionManager);
                updateButtonStates(); // Update button states after adding an animal
            }
        });

        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectCompetitionDialog(CompetitionFrame.this, competitionManager, zooPanel);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zooPanel.clearAnimals();
            }
        });

        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter energy amount:");
                if (input != null) {
                    try {
                        int energy = Integer.parseInt(input);
                        zooPanel.feedAnimals(energy);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(CompetitionFrame.this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zooPanel.showInfo();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setVisible(true);
        updateButtonStates(); // Update button states when the window is opened
    }

    public void updateButtonStates() {
        boolean hasValidCompetitions = competitionManager.getCompetitions().stream()
                .anyMatch(comp -> comp.getAnimals().size() >= 2);
        addAnimalButton.setEnabled(!competitionManager.getCompetitions().isEmpty());
        startRaceButton.setEnabled(hasValidCompetitions);
    }

    public static void main(String[] args) {
        new CompetitionFrame();
    }
}
