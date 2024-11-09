import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.io.File;
// import java.io.IOException;

public class StartScreen extends JPanel {
    public StartScreen(ActionListener startGameListener, ActionListener exitGameListener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 200, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);

        JLabel titleLabel = new JLabel("隠れる");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 64));
        titleLabel.setForeground(new Color(150, 120, 200));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("kakureru");
        subtitleLabel.setFont(new Font("Serif", Font.ITALIC, 24));
        subtitleLabel.setForeground(new Color(150, 120, 200));
        gbc.gridy = 1;
        add(subtitleLabel, gbc);

        JButton startButton = createStyledButton("Start");
        startButton.addActionListener(startGameListener);
        gbc.gridy = 2;
        add(startButton, gbc);

        JButton exitButton = createStyledButton("Exit");
        exitButton.addActionListener(exitGameListener);
        gbc.gridy = 3;
        add(exitButton, gbc);
    }

    // private Font loadCustomFont(String fontPath, float size) throws FontFormatException, IOException {
    //     Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("OOP_game/src/TryingToBeCool-Regular.ttf"));
    //     return customFont.deriveFont(size);
    // }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Serif", Font.PLAIN, 24));
        button.setForeground(new Color(100, 80, 160));
        button.setBackground(new Color(190, 180, 250));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 80, 160), 2, true));
        return button;
    }
}