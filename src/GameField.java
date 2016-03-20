import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameField extends JPanel implements ActionListener {

    Main m = new Main();
    Snake snake = new Snake(0, 125);
    Frog frog = new Frog(m.width, m.height);
    Timer mainTimer = new Timer(10, this);
    boolean flagStart;
    boolean flagPause;
    boolean flagStop;
    Image img = new ImageIcon("src\\resources\\bg.gif").getImage();


    GameField(){
        mainTimer.start();
        addMouseListener(new myMouseAdapter());
        setFocusable(true);
    }



    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.drawImage(img, 0, 0, null);
        BasicStroke pen = new BasicStroke(2);
        g.setStroke(pen);
        g.setColor(new Color(183, 153, 0));

        for (int coord_x = 0; coord_x <= m.width; coord_x += 25) {
            g.drawLine(coord_x, 0, coord_x, m.height);
        }
        for (int coord_y = 0; coord_y <= m.height; coord_y += 25) {
            g.drawLine(0, coord_y, m.width, coord_y);
        }

        g.setColor(new Color(11, 255, 84));
        g.fillOval(frog.coord_X, frog.coord_Y, 25, 25);

        g.setColor(new Color(255, 100, 100));
        frog.coordinatsSnake(snake.snake_X, snake.snake_Y, snake.dir);
        for (int part = 0; part <= snake.tail - 1; part++){
            g.fillOval(snake.snake_X[part], snake.snake_Y[part], 25, 25);
        }
    }

    private class myMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent me){
            snake.MousePressed(me);
        }
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Start")){
            System.out.println("Knopka Start");
           // setEnabled(flagStart);
        }
       /* if(e.getActionCommand().equals("Pause")){
            System.out.println("Knopka Pause");
            setEnabled(flagPause);
        }
        if (e.getActionCommand().equals("Stop")){
            setEnabled(flagStop);
            System.out.println("Knopka Stop");
        }*/
        if(e.getSource().equals(mainTimer)){
            repaint();
        }

    }
}
