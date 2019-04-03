package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class PredictionUI extends JFrame {
    private String[] confidence = new String[10];
    private JLabel[] guessLabels = new JLabel[10];
    private JProgressBar[] progressBars = new JProgressBar[10];

    /*
     * Creates the window which displays the confidence of the networks guess
     */
    public PredictionUI() {
        setTitle("Neural Network - Digit Classifier - Predictions");
        setSize(500, 600);
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(1410, 220);
        setResizable(false);
        createUI();
        setVisible(true);
    }

    /*
     * Adds a label and progress bar into the window
     */
    private void createUI() {
        int yPos = 10;
        for (int i = 0; i < 10; i++) {
            guessLabels[i] = createLabel(yPos, i);
            progressBars[i] = createProgressBar(yPos);
            yPos += 50;
            getContentPane().add(guessLabels[i]);
            getContentPane().add(progressBars[i]);
        }
        getContentPane().add(createPanel());
    }

    /*
     * creates a progress Bar
     */
    private JProgressBar createProgressBar(int yPos) {
        JProgressBar temp = new JProgressBar();
        temp.setSize(200, 20);
        temp.setLocation(140, yPos);
        temp.setMaximum(0);
        temp.setMaximum(100);
        temp.setStringPainted(true);
        return temp;
    }

    /*
     * creates a label
     */
    private JLabel createLabel(int yPos, int i) {
        JLabel temp = new JLabel(i + ": 0.00%");
        temp.setSize(100, 20);
        temp.setFont(new Font(getName(), Font.PLAIN, 20));
        temp.setLocation(10, yPos);
        return temp;
    }

    /*
     * creates a JPanel
     */
    private JPanel createPanel() {
        JPanel temp = new JPanel();
        temp.setLocation(500, 600);
        return temp;
    }

    /*
     * Sets the progress bar and guess label to the appropriate values (values that the network generated)
     */
    public void setConfidence(double[] outputs) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < 10; i++) {
            confidence[i] = df.format(outputs[i] * 100);
        }
        String[] guess = {"0: " + confidence[0] + "%", "1: " + confidence[1] + "%", "2: " + confidence[2] + "%", "3: " + confidence[3] + "%", "4: " + confidence[4] + "%", "5: " + confidence[5] + "%", "6: " + confidence[6] + "%", "7: " + confidence[7] + "%", "8: " + confidence[8] + "%", "9: " + confidence[9] + "%"};
        for (int i = 0; i < 10; i++) {
            int value = (int) (Double.parseDouble(confidence[i]));
            progressBars[i].setForeground(getPbColour(value));
            progressBars[i].setValue(value);
            guessLabels[i].setText(guess[i]);
        }
    }

    /*
     * Returns the colour of the progress bar based on what the confidence is
     */
    private Color getPbColour(int value) {
        if (value >= 0 && value < 20) {
            return Color.red;
        } else if (value >= 20 && value < 40) {
            return Color.pink;
        } else if (value >= 40 && value < 80) {
            return Color.yellow;
        } else if (value >= 80 && value < 100) {
            return Color.green;
        }
        return null;
    }
}