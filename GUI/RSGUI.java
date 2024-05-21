package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class RSGUI extends JFrame {
    private ArrayList<String> queue = new ArrayList<>();
    private String currentPatient = "";
    private JTextArea queueTextArea, currentTextArea, timeTextArea;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public RSGUI() {
        setTitle("Hospital Queue System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel inputPanel = createInputPanel();
        JPanel queuePanel = createQueuePanel();
        JPanel currentPanel = createCurrentPanel();
        JPanel timePanel = createTimePanel();
        JPanel resetPanel = createResetPanel();

        cardPanel.add(inputPanel, "input");
        cardPanel.add(queuePanel, "queue");
        cardPanel.add(currentPanel, "current");
        cardPanel.add(timePanel, "time");
        cardPanel.add(resetPanel, "reset");

        add(cardPanel);

        loadQueueFromFile();
        updateQueueDisplay();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.PLAIN, 12);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        JTextField nameField = new JTextField();
        nameField.setFont(font);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(font);
        JTextField ageField = new JTextField();
        ageField.setFont(font);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(font);
        JTextField phoneField = new JTextField();
        phoneField.setFont(font);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String age = ageField.getText();
                    String phone = phoneField.getText();
                    String patientInfo = name + ", " + age + ", " + phone + ", " + sdf.format(new Date());
                    queue.add(patientInfo);
                    updateQueueDisplay();
                    nameField.setText("");
                    ageField.setText("");
                    phoneField.setText("");
                    saveQueueToFile();
                    cardLayout.show(cardPanel, "queue");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(nextButton);

        return panel;
    }

    private JPanel createQueuePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel queueLabel = new JLabel("Queue:");
        queueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        queueLabel.setForeground(Color.WHITE);

        queueTextArea = new JTextArea();
        queueTextArea.setEditable(false);
        queueTextArea.setBackground(Color.WHITE);
        JScrollPane queueScrollPane = new JScrollPane(queueTextArea);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "current");
            }
        });
        nextButton.setBackground(Color.WHITE);

        panel.add(queueLabel, BorderLayout.NORTH);
        panel.add(queueScrollPane, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCurrentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel currentLabel = new JLabel("Current Patient:");
        currentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        currentLabel.setForeground(Color.BLUE);

        currentTextArea = new JTextArea();
        currentTextArea.setEditable(false);
        currentTextArea.setBackground(Color.WHITE);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "time");
            }
        });
        nextButton.setBackground(Color.WHITE);

        panel.add(currentLabel, BorderLayout.NORTH);
        panel.add(currentTextArea, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTimePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel timeLabel = new JLabel("Time in Queue:");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timeLabel.setForeground(Color.RED);

        timeTextArea = new JTextArea();
        timeTextArea.setEditable(false);
        timeTextArea.setBackground(Color.WHITE);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "reset");
            }
        });
        nextButton.setBackground(Color.WHITE);

        panel.add(timeLabel, BorderLayout.NORTH);
        panel.add(timeTextArea, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createResetPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel resetLabel = new JLabel("Reset successful.");
        resetLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resetLabel.setForeground(Color.GREEN);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queue.clear();
                currentPatient = "";
                updateQueueDisplay();
                saveQueueToFile();
                cardLayout.show(cardPanel, "input");
            }
        });
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.WHITE);

        panel.add(resetLabel, BorderLayout.NORTH);
        panel.add(resetButton, BorderLayout.CENTER);

        return panel;
    }

    private void updateQueueDisplay() {
        StringBuilder queueBuilder = new StringBuilder();
        for (String patient : queue) {
            queueBuilder.append(patient).append("\n");
        }
        queueTextArea.setText(queueBuilder.toString());

        currentTextArea.setText(currentPatient);

        int time = queue.size() - queue.indexOf(currentPatient);
        timeTextArea.setText(Integer.toString(time));
    }

    private void saveQueueToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("queue.txt"))) {
            for (String patient : queue) {
                writer.println(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQueueFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("queue.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queue.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RSGUI().setVisible(true);
            }
        });
    }
}
