package Graphics;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

import animals.AirAnimal;
import animals.Animal;
import animals.WaterAnimal;

/**
 * The ZooPanel class represents a panel in a zoo application where animals are displayed
 * and can be interacted with.
 */
public class ZooPanel extends JPanel {
    private ArrayList<Animal> animals;
    private Image backgroundImage;

    /**
     * Constructs a new ZooPanel with an empty list of animals and a background image.
     */
    public ZooPanel() {
        this.animals = new ArrayList<>();
        this.setLayout(new BorderLayout());
        loadBackgroundImage();
    }

    /**
     * Loads the background image for the panel.
     */
    private void loadBackgroundImage() {
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Graphics/competitionBackground.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the component, including the background image and all animals.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (Animal animal : animals) {
            animal.drawObject(g);
        }
    }

    /**
     * Adds an animal to the panel and repaints it.
     *
     * @param animal The animal to add.
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
        repaint();
    }

    /**
     * Clears all animals from the panel and repaints it.
     */
    public void clearAnimals() {
        animals.clear();
        repaint();
    }

    /**
     * Feeds all animals in the panel with the specified amount of energy.
     *
     * @param energy The amount of energy to feed each animal.
     */
    public void feedAnimals(int energy) {
        for (Animal animal : animals) {
            animal.feed(energy);
        }
        repaint();
    }

    /**
     * Displays information about all animals in a table format.
     */
    public void showInfo() {
        String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
        Object[][] data = new Object[animals.size()][7];

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            data[i][0] = animal.getName(); // Assuming getName() exists in Animal
            data[i][1] = getCategory(animal); // Assuming getCategory() returns Air, Water, or Terrest
            data[i][2] = animal.getClass().getSimpleName(); // Assuming the type is the class name
            data[i][3] = animal.getSpeed(); // Assuming getSpeed() exists in Animal
            data[i][4] = animal.getEnergyAmount(); // Assuming getEnergyAmount() exists in Animal
            data[i][5] = animal.getTotalDistance(); // Using getTotalDistance() from Mobile
            data[i][6] = animal.getEnergyConsumption(); // Assuming getEnergyConsumption() exists in Animal
        }

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);

        // Set column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); // Animal
        columnModel.getColumn(1).setPreferredWidth(100); // Category
        columnModel.getColumn(2).setPreferredWidth(100); // Type
        columnModel.getColumn(3).setPreferredWidth(100); // Speed
        columnModel.getColumn(4).setPreferredWidth(150); // Energy Amount
        columnModel.getColumn(5).setPreferredWidth(150); // Distance
        columnModel.getColumn(6).setPreferredWidth(200); // Energy Consumption

        // Improve table appearance
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.GRAY);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Animal Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gets the category of the animal, which can be Air, Water, or Terrest.
     *
     * @param animal The animal to categorize.
     * @return The category of the animal.
     */
    private String getCategory(Animal animal) {
        if (animal instanceof AirAnimal) {
            return "Air";
        } else if (animal instanceof WaterAnimal) {
            return "Water";
        } else {
            return "Terrest";
        }
    }

}
