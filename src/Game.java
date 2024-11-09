import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Game extends JFrame{

    BufferedImage scene1;
    BufferedImage imgMask;
    BufferedImage[] imgColor;
    DrawArea draw;

    Game(){
        
        loadImages();
        showStartScreen();
    }

    private void loadImages() {
        try {
            scene1 = ImageIO.read(getClass().getResource("scene1.png"));
            imgMask = ImageIO.read(getClass().getResource("lv1_onlyBlack.png"));
            imgColor = new BufferedImage[15];
            for (int i = 0; i < imgColor.length; i++) {
                imgColor[i] = ImageLoader.loadImage("lv1-" + (i + 1) + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showStartScreen() {
        StartScreen startScreen = new StartScreen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        setContentPane(startScreen);
        revalidate();
    }

    private void startGame() {
        draw = new DrawArea(scene1, imgMask, imgColor, this);
        setContentPane(draw);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new Game();
        frame.setTitle("Kakureru");
        frame.setSize(1440,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
