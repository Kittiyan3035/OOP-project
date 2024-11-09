import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class DrawArea extends JPanel {

    Image scene1;
    BufferedImage imgMask;
    BufferedImage[] imgColor;
    ArrayList<Cat> cats;
    int numCats;
    private JFrame parentFrame;

    public DrawArea(Image scene1,BufferedImage imgMask,BufferedImage[] imgColor, JFrame parentFrame) {
        this.scene1 = scene1;
        this.imgMask = resizeImage(imgMask, 1440, 900);
        this.imgColor = imgColor;
        this.cats = new ArrayList<>();
        numCats =15;
        this.parentFrame = parentFrame;

        cats = new ArrayList<>();
        for (int i = 0; i < imgColor.length; i++) {
            cats.add(new Cat(imgColor[i]));
        }

        setupMouseListener();
    }

    private void setupMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCatClick(e.getX(), e.getY());
            }
        });
    }


    private void handleCatClick(int x, int y) {
        for (Cat cat : cats) {

            if (cat.isCheck()) continue;
            BufferedImage img = cat.getImgCat();

            double scaleX = (double) img.getWidth() / getWidth();
            double scaleY = (double) img.getHeight() / getHeight();
    
            int imgX = (int) (x * scaleX);
            int imgY = (int) (y * scaleY);

            if (imgX >= 0 && imgX < img.getWidth() && imgY >= 0 && imgY < img.getHeight()) {
                int pixelColor = img.getRGB(imgX, imgY);
                Color color = new Color(pixelColor, true);

                //System.out.println("Clicked color: " + color);
                if (color.equals(new Color(170, 212, 203))) {
                    cat.setCheck(true);
                    // numCats--;
                    // catCounter.setText("Cats remaining: " + numCats);
                    repaint();   

                    break;
                }

                // if (numCats == 0) {
                //     showEndScreen();
                // }
            }
            
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(scene1, 0, 0, getWidth(), getHeight(),this);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        int count = 0;
        for (Cat c : cats){
            if (!c.isCheck()){
                count++;
            }
        }
        g.drawString("Cat count : " + count, 20, 20);
        
       
        // Draw Transparency image
        for (Cat c : cats){

            BufferedImage temp = c.getImgCat();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            
            if (!c.isCheck()){
                count++;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
                g2d.drawImage(temp, 0, 0, getWidth(), getHeight(), null);

            }
            else {
                g2d.drawImage(temp, 0, 0, getWidth(), getHeight(), null);
            }
        }
        if (count == 0){
            showEndScreen();
        }
        
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    class Cat {
        BufferedImage imgCat;
        boolean isCheck;

        Cat(BufferedImage image) {

            this.imgCat = resizeImage(image, 1440, 900);
            this.isCheck = false;

        }

        public BufferedImage getImgCat() {
            return imgCat;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean isCheck) {
            this.isCheck = isCheck;
        }
    }

    private void showEndScreen() {
        EndScreen endScreen = new EndScreen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // ออกโปรแกรมเมื่อกดปุ่ม Exit
            }
        });
        parentFrame.setContentPane(endScreen);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}