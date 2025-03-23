import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

public class AgeCalculator implements ActionListener {
    private static final int START_YEAR = 1901;
    private static final int END_YEAR = 2100;
    private static final int MAX_DAYS = 31;
    private static final int MAX_MONTHS = 12;

    private JComboBox<Integer>[] dobSelectors;
    private JTextField[] dataFields;
    private JTextArea dobLabel, ageLabel;
    private JTextArea[] dobLabels, ageLabels;
    private JButton calculateButton, closeButton, clearButton;

    private LocalDate dob;

    public AgeCalculator() {
        initializeComponents();
        setupFrame();
    }

    private void initializeComponents() {
        dobSelectors = new JComboBox[3];
        dobSelectors[0] = createComboBox(END_YEAR - START_YEAR + 1, START_YEAR);
        dobSelectors[1] = createComboBox(MAX_MONTHS, 1);
        dobSelectors[2] = createComboBox(MAX_DAYS, 1);
        dobSelectors[0].setSelectedIndex(124);

        dataFields = new JTextField[3];
        for (int i = 0; i <= 2; i++)
            dataFields[i] = createTextField(9);

        dobLabel = createTextArea("DOB:");
        ageLabel = createTextArea("AGE:");

        dobLabels = new JTextArea[3];
        ageLabels = new JTextArea[3];
        String[] areaFields = {"year", "month", "day"};

        for (int i = 0; i <= 2; i++) {
            dobLabels[i] = createTextArea(areaFields[i]);
            ageLabels[i] = createTextArea(areaFields[i] + "(s)");
        }

        calculateButton = createButton("    CALCULATE   ");
        clearButton = createButton("  CLEAR  ");
        closeButton = createButton("  CLOSE  ");
    }


    private JComboBox<Integer> createComboBox(int size, int start) {
        Integer[] items = new Integer[size];
        for (int i = 0; i < size; i++)
            items[i] = start + i;

        JComboBox<Integer> comboBox = new JComboBox<>(items);
        comboBox.setEditable(false);
        return comboBox;
    }

    private JTextField createTextField(int size) {
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText(" ".repeat(size));
        return textField;
    }

    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        return textArea;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    private void setupFrame() {
        JFrame frame = new JFrame("AGE CALCULATOR");
        frame.setSize(340, 130);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new FlowLayout());

        frame.add(dobLabel);
        for (int i = 2; i >= 0; i--) {
            frame.add(dobSelectors[i]);
            frame.add(dobLabels[i]);
        }

        frame.add(clearButton);
        frame.add(calculateButton);
        frame.add(closeButton);

        frame.add(ageLabel);
        for (int i = 0; i <= 2; i++) {
            frame.add(dataFields[i]);
            frame.add(ageLabels[i]);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        Map<Object, Runnable> actionMap = new HashMap<>();
        actionMap.put(calculateButton, this::calculateAge);
        actionMap.put(clearButton, this::clearFields);
        actionMap.put(closeButton, () -> System.exit(0));

        Runnable action = actionMap.get(source);
        if (action != null) action.run();
    }

    private void calculateAge() {
        int year = (int) dobSelectors[0].getSelectedItem();
        int month = (int) dobSelectors[1].getSelectedItem();
        int day = (int) dobSelectors[2].getSelectedItem();

        if (isValidDate(month, day)) {
            dob = LocalDate.of(year, month, day);
            LocalDate today = LocalDate .now();
            Period age = Period.between(dob, today);

            dataFields[0].setText(String.valueOf(age.getYears()));
            dataFields[1].setText(String.valueOf(age.getMonths()));
            dataFields[2].setText(String.valueOf(age.getDays()));
        } else {
            JOptionPane.showMessageDialog(
                null,
                "PLEASE, CHOOSE A VALID DATE", "INVALID DATE",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean isValidDate(int month, int day) {
        switch (month) {
            case 2:
                if (day > 29) return false;
            case 4: case 6: case 9: case 11:
                if (day == 31) return false;
            default:
                return true;
        }
    }

    private void clearFields() {
        for (JTextField dataField : dataFields)
            dataField.setText(" ".repeat(9));

        dobSelectors[0].setSelectedIndex(124);
        dobSelectors[1].setSelectedIndex(0);
        dobSelectors[2].setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AgeCalculator();
    }
}
