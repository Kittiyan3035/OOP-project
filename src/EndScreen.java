import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndScreen extends JPanel {

    public EndScreen(ActionListener exitListener) {
        setLayout(new BorderLayout());

        JLabel thankYouLabel = new JLabel("ありがとう", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        thankYouLabel.setForeground(new Color(160, 120, 190));  // สีตัวอักษรที่เข้ากับตัวอย่าง
        add(thankYouLabel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 50));
        exitButton.setBackground(new Color(200, 200, 250)); // สีพื้นหลังปุ่มที่เข้ากับตัวอย่าง
        exitButton.setForeground(new Color(160, 120, 190)); // สีตัวอักษรปุ่มที่เข้ากับตัวอย่าง
        exitButton.addActionListener(exitListener);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
