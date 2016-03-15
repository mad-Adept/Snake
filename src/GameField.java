import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameField extends JPanel implements ActionListener {

    Main main = new Main();
    Snake snake = new Snake(0, 125);
    Timer mainTimer = new Timer(200, this);
    Image img = new ImageIcon("src\\resources\\bg.gif").getImage();

    GameField(){
        mainTimer.start();
        addMouseListener(new myMouseAdapter());
        setFocusable(true);
    }

    private class myMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            snake.MousePressed(e);
        }
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.drawImage(img, 0, 0, null);
        BasicStroke pen = new BasicStroke(2);
        g.setStroke(pen);
        g.setColor(new Color(183, 153, 0));

        for (int coord_x = 0; coord_x <= main.width; coord_x += 25) {
            g.drawLine(coord_x, 0, coord_x, main.height);
        }
        for (int coord_y = 0; coord_y <= main.height; coord_y += 25) {
            g.drawLine(0, coord_y, main.width, coord_y);
        }
        g.setColor(new Color(255, 100, 100));

        for (int part = 0; part <= snake.tail - 1; part++){
            g.fillOval(snake.snake_X[part], snake.snake_Y[part], 25, 25);
        }
    }


    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();
    }
}
