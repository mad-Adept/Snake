import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameField extends JPanel implements ActionListener {

    int widthFrame;
    int heightFrame;
    int score;
    Snake snake;
    Frog frog;
    Timer mainTimer = new Timer(10, this);
    Image img = new ImageIcon("src\\resources\\bg.gif").getImage();


    GameField(int width, int height){
        widthFrame = width;
        heightFrame = height;
        snake = new Snake(widthFrame, heightFrame);
        frog.infoSnake(snake.snake_coord_X, snake.snake_coord_Y, snake.dir);
        frog = new Frog(widthFrame, heightFrame, "Green");
        frog.setRandomPosition();
        snake.snakeSuspend();
        frog.frogSuspend();
        mainTimer.start();
        addMouseListener(new myMouseAdapter());
        setFocusable(true);
        init();
        score = 0;
    }



    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.drawImage(img, 0, 0, null);
        BasicStroke pen = new BasicStroke(2);
        g.setStroke(pen);
        frog.infoSnake(snake.snake_coord_X, snake.snake_coord_Y, snake.dir);

        g.setColor(new Color(183, 153, 0));
        for (int coord_x = 0; coord_x <= widthFrame; coord_x += 25) {
            g.drawLine(coord_x, 0, coord_x, heightFrame);
        }
        for (int coord_y = 0; coord_y <= heightFrame; coord_y += 25) {
            g.drawLine(0, coord_y, widthFrame, coord_y);
        }
        g.setColor(new Color(255, 255, 255));
        g.drawString("SCORE: " + score, 0, heightFrame + 15);

        g.setColor(new Color(11, 255, 84));
        g.fillOval(frog.frog_coord_X, frog.frog_coord_Y, 25, 25);

        g.setColor(new Color(255, 100, 100));
        for (int part = 0; part <= snake.tail - 1; part++){
            g.fillOval(snake.snake_coord_X[part], snake.snake_coord_Y[part], 25, 25);
        }
    }




    public void init(){

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(800, 600));
        topPanel.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(this);
        topPanel.add(scroll, BorderLayout.CENTER);


        JPanel botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(1, 1, 30, 10));


        final JButton jbtStart = new JButton("Start");
        jbtStart.setEnabled(true);
        final JButton jbtPause = new JButton("Pause");
        jbtPause.setEnabled(false);
        final JButton jbtStop = new JButton("Stop");
        jbtStop.setEnabled(false);

        jbtStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                snake.snakeResume();
                frog.frogResume();
                System.out.println("Knopka Start");
                jbtStart.setEnabled(false);
                jbtPause.setEnabled(true);
                jbtStop.setEnabled(true);
            }
        });
        jbtPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ea) {
                snake.snakeSuspend();
                frog.frogSuspend();
                System.out.println("Knopka Pause");
                jbtPause.setEnabled(false);
                jbtStart.setEnabled(true);
            }
        });
        jbtStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ea) {
                snake.snakeStop();
                frog.frogStop();
                System.out.println("Knopka Stop");
                jbtStop.setEnabled(false);
                jbtStart.setEnabled(false);
                jbtPause.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Игра закончена, ваш счет: " + score, "Сообщение",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        botPanel.add(jbtStart);
        botPanel.add(jbtPause);
        botPanel.add(jbtStop);


        JFrame f = new JFrame("Snake");
        f.setSize(new Dimension(800, 680));
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(botPanel, BorderLayout.SOUTH);
        f.pack();
    }

    private class myMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent me){
            snake.MousePressed(me);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(snake.chekEattheFrog(frog)){
            frog.frogStop();
            if (frog.color.equals("Green")){
                frog = new Frog(widthFrame, heightFrame, "Green");
                snake.tail++;
                score++;
            }
            if (frog.color.equals("Blue")){
                frog = new Frog(widthFrame, heightFrame, "Blue");
                if (snake.tail > 3) snake.tail--;
                score =+2;
            }
            if (frog.color.equals("Red")){
                frog = new Frog(widthFrame, heightFrame, "Red");
                snake.snakeStop();
                frog.frogStop();
                JOptionPane.showMessageDialog(null, "Игра закончена, ваш счет: " + score, "Сообщение",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            frog.setRandomPosition();
        }
        repaint();
    }
}

