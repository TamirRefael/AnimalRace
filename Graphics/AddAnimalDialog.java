package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import animals.*;
import competition.*;

/**
 * A dialog for adding a new animal to a competition.
 */
public class AddAnimalDialog extends JDialog {
    private JComboBox<String> animalTypeComboBox;
    private JTextField nameField, weightField, speedField, diveDepthField, breedField, lengthField, altitudeOfFlightField, wingspanField, familyField, foodTypeField;
    private JComboBox<String> genderComboBox, poisonousComboBox, waterTypeComboBox, imageComboBox;
    private JComboBox<Competition> competitionComboBox;
    private JComboBox<Integer> heatComboBox;
    private JPanel dynamicPanel;
    private CompetitionManager competitionManager;
    private RaceTrack raceTrack;
    private String competitionType;

    /**
     * Constructs a new AddAnimalDialog.
     *
     * @param owner The frame that owns this dialog.
     * @param zooPanel The zoo panel to which the animal will be added.
     * @param competitionManager The competition manager that manages the competitions.
     */
    public AddAnimalDialog(Frame owner, ZooPanel zooPanel, CompetitionManager competitionManager) {
        super(owner, "Add Animal", true);
        this.competitionManager = competitionManager;
        this.setSize(500, 500);
        this.setLocationRelativeTo(owner);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Animal Type:"), gbc);

        gbc.gridx = 1;
        animalTypeComboBox = new JComboBox<>(new String[]{"Cat", "Dog", "Eagle", "Dolphin", "Alligator", "Snake", "Pigeon", "Whale"});
        mainPanel.add(animalTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        genderComboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});
        mainPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Weight:"), gbc);

        gbc.gridx = 1;
        weightField = new JTextField(20);
        mainPanel.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Speed:"), gbc);

        gbc.gridx = 1;
        speedField = new JTextField(20);
        mainPanel.add(speedField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Image:"), gbc);

        gbc.gridx = 1;
        imageComboBox = new JComboBox<>();
        mainPanel.add(imageComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Competition:"), gbc);

        gbc.gridx = 1;
        competitionComboBox = new JComboBox<>();
        mainPanel.add(competitionComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(new JLabel("Heat:"), gbc);

        gbc.gridx = 1;
        heatComboBox = new JComboBox<>();
        mainPanel.add(heatComboBox, gbc);

        dynamicPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        mainPanel.add(dynamicPanel, gbc);

        updateImageComboBox();
        updateDynamicFields();
        updateHeatComboBox();

        animalTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImageComboBox();
                updateDynamicFields();
            }
        });

        competitionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHeatComboBox();
                Competition selectedCompetition = (Competition) competitionComboBox.getSelectedItem();
                if (selectedCompetition != null) {
                    competitionType = selectedCompetition.getType();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(e -> {
            try {
                Animal animal = createAnimal();
                if (animal != null) {
                    Competition selectedCompetition = (Competition) competitionComboBox.getSelectedItem();
                    if (selectedCompetition != null && selectedCompetition.isAnimalCompatible(animal)) {
                        selectedCompetition.addAnimal(animal);
                        zooPanel.addAnimal(animal);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Selected competition is not compatible with this animal.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for weight, speed, and other numeric fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> this.dispose());

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        updateDynamicFields();
        updateImageComboBox();
        updateHeatComboBox();
    }

    /**
     * Creates an animal instance based on the selected animal type and input fields.
     *
     * @return The created animal.
     * @throws NumberFormatException If numeric fields contain invalid data.
     * @throws IllegalArgumentException If any field contains invalid data.
     */
    private Animal createAnimal() throws NumberFormatException, IllegalArgumentException {
        String name = nameField.getText();
        Animal.Gender gender = Animal.Gender.valueOf((String) genderComboBox.getSelectedItem());
        double weight = Double.parseDouble(weightField.getText());
        double speed = Double.parseDouble(speedField.getText());
        String imageName = (String) imageComboBox.getSelectedItem();
        int heat = (int) heatComboBox.getSelectedItem();
        Competition selectedCompetition = (Competition) competitionComboBox.getSelectedItem();

        RaceTrack raceTrack = null;
        if (selectedCompetition != null) {
            raceTrack = new RaceTrack();
        }

        if (weight <= 0 || speed <= 0) {
            throw new IllegalArgumentException("Weight and speed must be positive numbers.");
        }

        String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();
        Animal animal;
        switch (selectedAnimal) {
            case "Cat":
                boolean castrated = false;
                animal = new Cat(name, gender, weight, speed, null, 4, castrated, 1000, 10, imageName, heat, raceTrack);
                break;
            case "Dog":
                animal = new Dog(name, gender, weight, speed, null, 4, breedField.getText(), 1000, 10, imageName, heat, raceTrack);
                break;
            case "Eagle":
                double altitude = Double.parseDouble(altitudeOfFlightField.getText());
                if (altitude < 0) throw new IllegalArgumentException("Altitude must be a positive number.");
                animal = new Eagle(name, gender, weight, speed, null, 2.0, altitude, 1000, 10, imageName, heat, raceTrack);
                break;
            case "Dolphin":
                Dolphin.WaterType waterType = Dolphin.WaterType.valueOf((String) waterTypeComboBox.getSelectedItem());
                double diveDept = Double.parseDouble(diveDepthField.getText());
                if (diveDept < 0) throw new IllegalArgumentException("Dive depth must be a positive number.");
                animal = new Dolphin(name, gender, weight, speed, null, diveDept, waterType, 1000, 10, imageName, heat, raceTrack);
                break;
            case "Alligator":
                diveDept = Double.parseDouble(diveDepthField.getText());
                if (diveDept < 0) throw new IllegalArgumentException("Dive depth must be a positive number.");
                animal = new Alligator(name, gender, weight, speed, null, diveDept, "River", 1000, 10, imageName, heat, competitionType, raceTrack);
                break;
            case "Snake":
                double length = Double.parseDouble(lengthField.getText());
                if (length < 0) throw new IllegalArgumentException("Length must be a positive number.");
                Snake.Poisonous poisonous = Snake.Poisonous.valueOf((String) poisonousComboBox.getSelectedItem());
                animal = new Snake(name, gender, weight, speed, null, 0, poisonous, length, 1000, 10, imageName, heat, raceTrack);
                break;
            case "Pigeon":
                animal = new Pigeon(name, gender, weight, speed, null, 0.5, familyField.getText(), 1000, 10, imageName, heat, raceTrack);
                break;
            case "Whale":
                diveDept = Double.parseDouble(diveDepthField.getText());
                if (diveDept < 0) throw new IllegalArgumentException("Dive depth must be a positive number.");
                animal = new Whale(name, gender, weight, speed, null, diveDept, foodTypeField.getText(), 1000, 10, imageName, heat, raceTrack);
                break;
            default:
                throw new IllegalArgumentException("Invalid animal type.");
        }

        return animal;
    }

    /**
     * Updates the dynamic fields in the dialog based on the selected animal type.
     */
    private void updateDynamicFields() {
        dynamicPanel.removeAll();
        String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        switch (selectedAnimal) {
            case "Cat":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Castrated:"), gbc);
                gbc.gridx = 1;
                JCheckBox castratedCheckBox = new JCheckBox();
                dynamicPanel.add(castratedCheckBox, gbc);
                break;
            case "Dog":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Breed:"), gbc);
                gbc.gridx = 1;
                breedField = new JTextField(20);
                dynamicPanel.add(breedField, gbc);
                break;
            case "Eagle":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Altitude of Flight:"), gbc);
                gbc.gridx = 1;
                altitudeOfFlightField = new JTextField(20);
                dynamicPanel.add(altitudeOfFlightField, gbc);
                break;
            case "Dolphin":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Water Type:"), gbc);
                gbc.gridx = 1;
                waterTypeComboBox = new JComboBox<>(new String[]{"Sea", "Sweet"});
                dynamicPanel.add(waterTypeComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Dive Depth:"), gbc);
                gbc.gridx = 1;
                diveDepthField = new JTextField(20);
                dynamicPanel.add(diveDepthField, gbc);
                break;
            case "Alligator":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Dive Depth:"), gbc);
                gbc.gridx = 1;
                diveDepthField = new JTextField(20);
                dynamicPanel.add(diveDepthField, gbc);
                break;
            case "Snake":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Poisonous:"), gbc);
                gbc.gridx = 1;
                poisonousComboBox = new JComboBox<>(new String[]{"Low", "Mid", "High"});
                dynamicPanel.add(poisonousComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Length:"), gbc);
                gbc.gridx = 1;
                lengthField = new JTextField(20);
                dynamicPanel.add(lengthField, gbc);
                break;
            case "Pigeon":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Family:"), gbc);
                gbc.gridx = 1;
                familyField = new JTextField(20);
                dynamicPanel.add(familyField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Wingspan:"), gbc);
                gbc.gridx = 1;
                wingspanField = new JTextField(20);
                dynamicPanel.add(wingspanField, gbc);
                break;
            case "Whale":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Food Type:"), gbc);
                gbc.gridx = 1;
                foodTypeField = new JTextField(20);
                dynamicPanel.add(foodTypeField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Dive Depth:"), gbc);
                gbc.gridx = 1;
                diveDepthField = new JTextField(20);
                dynamicPanel.add(diveDepthField, gbc);
                break;
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    /**
     * Updates the image combo box based on the selected animal type.
     */
    private void updateImageComboBox() {
        imageComboBox.removeAllItems();
        competitionComboBox.removeAllItems();

        String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();

        switch (selectedAnimal) {
            case "Cat":
                imageComboBox.addItem("cat1");
                imageComboBox.addItem("cat2");
                addCompetitionsForAnimal("Land");
                break;
            case "Dog":
                imageComboBox.addItem("dog1");
                imageComboBox.addItem("dog2");
                imageComboBox.addItem("dog3");
                addCompetitionsForAnimal("Land");
                break;
            case "Eagle":
                imageComboBox.addItem("eagle1");
                imageComboBox.addItem("eagle2");
                imageComboBox.addItem("eagle3");
                addCompetitionsForAnimal("Air");
                break;
            case "Dolphin":
                imageComboBox.addItem("dolphin1");
                imageComboBox.addItem("dolphin2");
                imageComboBox.addItem("dolphin3");
                addCompetitionsForAnimal("Water");
                break;
            case "Alligator":
                imageComboBox.addItem("alligator1");
                imageComboBox.addItem("alligator2");
                imageComboBox.addItem("alligator3");
                addCompetitionsForAnimal("Land");
                addCompetitionsForAnimal("Water");
                break;
            case "Snake":
                imageComboBox.addItem("snake1");
                imageComboBox.addItem("snake2");
                imageComboBox.addItem("snake3");
                addCompetitionsForAnimal("Land");
                break;
            case "Pigeon":
                imageComboBox.addItem("pigeon1");
                addCompetitionsForAnimal("Air");
                break;
            case "Whale":
                imageComboBox.addItem("whale1");
                imageComboBox.addItem("whale2");
                addCompetitionsForAnimal("Water");
                break;
        }

        imageComboBox.revalidate();
        imageComboBox.repaint();
    }

    /**
     * Updates the heat combo box based on the selected competition.
     */
    private void updateHeatComboBox() {
        heatComboBox.removeAllItems();
        Competition selectedCompetition = (Competition) competitionComboBox.getSelectedItem();
        if (selectedCompetition != null) {
            int numHeats = selectedCompetition.getNumHeats();
            for (int i = 1; i <= numHeats; i++) {
                heatComboBox.addItem(i);
            }
        }
        heatComboBox.revalidate();
        heatComboBox.repaint();
    }

    /**
     * Adds competitions of the specified type to the competition combo box.
     *
     * @param competitionType The type of competitions to add.
     */
    private void addCompetitionsForAnimal(String competitionType) {
        java.util.List<Competition> competitions = competitionManager.getCompetitionsByType(competitionType); // שימוש ב-java.util.List במקום java.awt.List
        for (Competition competition : competitions) {
            competitionComboBox.addItem(competition);
        }
    }
}
