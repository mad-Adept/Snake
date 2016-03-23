import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;


public class GameField extends JPanel implements ActionListener {

    static final String LOCATION = GameField.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    static final int STEP = 25; // Шаг существ и размер клетки

    int widthFrame; // длина фрейма
    int heightFrame; //выоста фрейма
    int score; // счет игры
    Snake snake;
    ArrayList<Frog> listFrog;
    Timer mainTimer = new Timer(10, this);

    Image background = new ImageIcon(LOCATION + "\\resources\\bg.gif").getImage();
    Image green_frog_t = new ImageIcon(LOCATION + "\\resources\\Green_Frog_T.gif").getImage();
    Image green_frog_r = new ImageIcon(LOCATION + "\\resources\\Green_Frog_R.gif").getImage();
    Image green_frog_b = new ImageIcon(LOCATION + "\\resources\\Green_Frog_B.gif").getImage();
    Image green_frog_l = new ImageIcon(LOCATION + "\\resources\\Green_Frog_L.gif").getImage();

    Image blue_frog_t = new ImageIcon(LOCATION + "\\resources\\Blue_Frog_T.gif").getImage();
    Image blue_frog_r = new ImageIcon(LOCATION + "\\resources\\Blue_Frog_R.gif").getImage();
    Image blue_frog_b = new ImageIcon(LOCATION + "\\resources\\Blue_Frog_B.gif").getImage();
    Image blue_frog_l = new ImageIcon(LOCATION + "\\resources\\Blue_Frog_L.gif").getImage();

    Image red_frog_t = new ImageIcon(LOCATION + "\\resources\\Red_Frog_T.gif").getImage();
    Image red_frog_r = new ImageIcon(LOCATION + "\\resources\\Red_Frog_R.gif").getImage();
    Image red_frog_b = new ImageIcon(LOCATION + "\\resources\\Red_Frog_B.gif").getImage();
    Image red_frog_l = new ImageIcon(LOCATION + "\\resources\\Red_Frog_L.gif").getImage();

    Image snake_t = new ImageIcon(LOCATION + "\\resources\\Snake_T.gif").getImage();
    Image snake_r = new ImageIcon(LOCATION + "\\resources\\Snake_R.gif").getImage();
    Image snake_b = new ImageIcon(LOCATION + "\\resources\\Snake_B.gif").getImage();
    Image snake_l = new ImageIcon(LOCATION + "\\resources\\Snake_L.gif").getImage();
    Image snake_chain = new ImageIcon(LOCATION + "\\resources\\Snake_chain.gif").getImage();



    GameField(int quantityRows, int quantityColumns, int legthSnake, int delaySnake,
              int quantityGreenFrog, int quantityBlueFrog, int quantityRedFrog){

        widthFrame = quantityRows * STEP;
        heightFrame = quantityColumns * STEP;
        snake = new Snake(widthFrame, heightFrame, legthSnake, delaySnake, true);
        listFrog =  factoryFrog(quantityGreenFrog,  quantityBlueFrog, quantityRedFrog);
        mainTimer.start();
        addMouseListener(new myMouseAdapter());
        setFocusable(true);
        init();
        score = 0;
    }

    protected void paintComponent(Graphics gr) { // отрисовка всех компонентов
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.drawImage(background, 0, 0, null);
        BasicStroke pen = new BasicStroke(2);
        g.setStroke(pen);

        g.setColor(new Color(183, 153, 0));
        for (int coord_x = 0; coord_x <= widthFrame; coord_x += STEP) {
            g.drawLine(coord_x, 0, coord_x, heightFrame);
        }
        for (int coord_y = 0; coord_y <= heightFrame; coord_y += STEP) {
            g.drawLine(0, coord_y, widthFrame, coord_y);
        }
        g.setColor(new Color(255, 255, 255));
        g.drawString("SCORE: " + score, 0, heightFrame);

        for (Frog frog_iter : listFrog) {
            chekCollisionFrog();
            int dirFrog = frog_iter.getDirFrog();

            if (frog_iter.color.equals("Green")){

                switch (dirFrog){
                    case 3:
                        g.drawImage(green_frog_t, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 2:
                        g.drawImage(green_frog_r, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 0:
                        g.drawImage(green_frog_b, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 1:
                        g.drawImage(green_frog_l, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                }
            }

            if (frog_iter.color.equals("Blue")){

                switch (dirFrog){
                    case 3:
                        g.drawImage(blue_frog_t, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 2:
                        g.drawImage(blue_frog_r, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 0:
                        g.drawImage(blue_frog_b, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 1:
                        g.drawImage(blue_frog_l, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                }
            }


            if (frog_iter.color.equals("Red")){

                switch (dirFrog){
                    case 3:
                        g.drawImage(red_frog_t, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 2:
                        g.drawImage(red_frog_r, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 0:
                        g.drawImage(red_frog_b, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                    case 1:
                        g.drawImage(red_frog_l, frog_iter.getFrog_coord_X(), frog_iter.getFrog_coord_Y(), null);
                        break;
                }
            }
        }

        int dirSnake = snake.getDir();

        switch (dirSnake){
            case 3:
                g.drawImage(snake_t, snake.getSnake_coord_X()[0], snake.getSnake_coord_Y()[0], null);
                break;
            case 2:
                g.drawImage(snake_r, snake.getSnake_coord_X()[0], snake.getSnake_coord_Y()[0], null);
                break;
            case 0:
                g.drawImage(snake_b, snake.getSnake_coord_X()[0], snake.getSnake_coord_Y()[0], null);
                break;
            case 1:
                g.drawImage(snake_l, snake.getSnake_coord_X()[0], snake.getSnake_coord_Y()[0], null);
                break;
        }

        for (int part = 1; part <= snake.tail - 1; part++){
            g.drawImage(snake_chain, snake.snake_coord_X[part], snake.snake_coord_Y[part], null);
        }
    }

    // Производство лягушек
    public ArrayList factoryFrog(int quantityGreenFrog, int quantityBlueFrog, int quantityRedFrog){
        ArrayList<Frog> listFrog = new ArrayList();

        for (int iter = 0; iter != quantityGreenFrog + quantityBlueFrog + quantityRedFrog;){
            if (quantityGreenFrog != 0){
                listFrog.add(new Frog(widthFrame, heightFrame, "Green", snake, true));
                quantityGreenFrog--;
            }

            if (quantityBlueFrog != 0){
                listFrog.add(new Frog(widthFrame, heightFrame, "Blue", snake, true));
                quantityBlueFrog--;
            }

            if (quantityRedFrog != 0){
                listFrog.add(new Frog(widthFrame, heightFrame, "Red", snake, true));
                quantityRedFrog--;
            }

        }

        return listFrog;
    }

    // Проверка села ли змея лягушку, если да - счет игры увеличивается
    public void checkScore(){

        ListIterator<Frog> frogIterator = listFrog.listIterator();

        while(frogIterator.hasNext()) {

            Frog frog = frogIterator.next();

            if (snake.chekEattheFrog(frog)) {

                frog.frogStop();

                if (frog.color.equals("Green")) {
                    frogIterator.set(new Frog(widthFrame, heightFrame, "Green", snake, false));
                    snake.tail++;
                    score++;
                }
                if (frog.color.equals("Blue")) {
                    frogIterator.set(new Frog(widthFrame, heightFrame, "Blue", snake, false));
                    if (snake.tail > 3) snake.tail--;
                    score +=2;
                }
                if (frog.color.equals("Red")) {
                    snake.snakeStop();
                    mainTimer.stop();
                    JOptionPane.showMessageDialog(null, "Game over. Your score is: " + score, "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    //Проверка на столкновение лягушек, друг с другом
    public void chekCollisionFrog(){

                for(int i = 0; i < listFrog.size(); i++){
                    for (int j = i + 1; j < listFrog.size(); j++){
                        if (listFrog.get(i).frog_coord_X == listFrog.get(j).frog_coord_X &&
                             listFrog.get(i).frog_coord_Y == listFrog.get(j).frog_coord_Y){
                                int dirFrog = listFrog.get(i).getDirFrog();
                    switch (dirFrog) {
                        case 0:
                            listFrog.get(i).setFrog_coord_Y(listFrog.get(i).getFrog_coord_Y() - STEP);
                            break;
                        case 1:
                            listFrog.get(i).setFrog_coord_X(listFrog.get(i).getFrog_coord_X() + STEP);
                            break;
                        case 2:
                            listFrog.get(i).setFrog_coord_X(listFrog.get(i).getFrog_coord_X() - STEP);
                            break;
                        case 3:
                            listFrog.get(i).setFrog_coord_Y(listFrog.get(i).getFrog_coord_Y() + STEP);
                            break;
                    }
                }
            }
        }
    }


    public void init(){

        JFrame f = new JFrame("Snake");
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        JPanel topPanel = new JPanel();
        if(widthFrame > 800 && heightFrame > 600) {
            topPanel.setPreferredSize(new Dimension(800, 620));
        }else topPanel.setPreferredSize(new Dimension(widthFrame + 3, heightFrame + STEP));
        topPanel.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(widthFrame, heightFrame));
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
                for (Frog frog_iter : listFrog) {
                    frog_iter.frogResume();
                }
                jbtStart.setEnabled(false);
                jbtPause.setEnabled(true);
                jbtStop.setEnabled(true);
            }
        });
        jbtPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ea) {
                snake.snakeSuspend();
                for (Frog frog_iter : listFrog) {
                    frog_iter.frogSuspend();
                }
                jbtPause.setEnabled(false);
                jbtStart.setEnabled(true);
            }
        });
        jbtStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ea) {
                snake.snakeStop();
                for (Frog frog_iter : listFrog) {
                    frog_iter.frogStop();
                }
                jbtStop.setEnabled(false);
                jbtStart.setEnabled(false);
                jbtPause.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Game over. Your score is: " + score, "Message",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });
        botPanel.add(jbtStart);
        botPanel.add(jbtPause);
        botPanel.add(jbtStop);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(botPanel, BorderLayout.SOUTH);

        f.pack();
    }

    // слушает мышку
    private class myMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent me){
            snake.MousePressed(me);
        }
    }
    // слушает таймер с проверкой на столкновение змеии сама с собой и перерисовка графического элемента
    public void actionPerformed(ActionEvent e) {
        if (snake.chekCollisionTail()){
            snake.snakeStop();
            mainTimer.stop();
            JOptionPane.showMessageDialog(null, "Game over. Your score is: " + score, "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        checkScore();
        repaint();
    }
}


