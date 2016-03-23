
import java.util.Random;


public class Frog implements Runnable {

    static final int STEP = 25;

    int widthFrame;
    int heightFrame;
    int frog_coord_X;
    int frog_coord_Y;
    int dirFrog; // направление змеи

    Snake snake;
    Thread threadFrog;
    boolean suspendFlagFrog;
    String color;


    Frog(int widthFrame, int heightFrame, String color, Snake snake, boolean startFlag) {

        this.widthFrame = widthFrame;
        this.heightFrame = heightFrame;
        this.color = color;
        this.snake = snake;
        setRandomPosition(snake);
        suspendFlagFrog = startFlag;
        threadFrog = new Thread(this);
        threadFrog.start();
    }

    synchronized void frogSuspend() {
        suspendFlagFrog = true;
    }

    synchronized void frogResume() {
        suspendFlagFrog = false;
        notify();
    }

    synchronized void frogStop() {
        suspendFlagFrog = true;
        Thread.interrupted();
    }

    // Случайная позиция на карте
    public void setRandomPosition(Snake snake) {

        Random random = new Random();
        frog_coord_X = (Math.round(random.nextInt(widthFrame) / STEP)) * STEP;
        frog_coord_Y = (Math.round(random.nextInt(heightFrame) / STEP)) * STEP;
        for (int iter_X = 0; iter_X < snake.getSnake_coord_X().length; iter_X++) {
            if (frog_coord_X == snake.getSnake_coord_X()[iter_X]) {
                for (int iter_Y = 0; iter_Y < snake.getSnake_coord_Y().length; iter_Y++) {
                    if (frog_coord_Y == snake.getSnake_coord_Y()[iter_Y]) {
                        setRandomPosition(snake);
                    }
                }
            }
        }
    }

    public void run() {

        while (true) {
            try {

                synchronized (this) {
                    while (suspendFlagFrog) {
                        wait();
                    }
                }
                // условия изменения направления в заисимости от нахождения змеи на карте
                if (frog_coord_X > snake.getSnake_coord_X()[0] && (snake.getDir() == 0 || snake.getDir() == 3)) dirFrog = 2;
                if (frog_coord_X < snake.getSnake_coord_X()[0] && (snake.getDir() == 0 || snake.getDir() == 3)) dirFrog = 1;
                if (frog_coord_X == snake.getSnake_coord_X()[0] && frog_coord_Y > snake.getSnake_coord_Y()[0]) dirFrog = 0;
                if (frog_coord_X == snake.getSnake_coord_X()[0] && frog_coord_Y < snake.getSnake_coord_Y()[0]) dirFrog = 3;
                if (frog_coord_Y > snake.getSnake_coord_Y()[0] && (snake.getDir() == 1 || snake.getDir() == 2)) dirFrog = 0;
                if (frog_coord_Y < snake.getSnake_coord_Y()[0] && (snake.getDir() == 1 || snake.getDir() == 2)) dirFrog = 3;
                if (frog_coord_Y == snake.getSnake_coord_Y()[0] && frog_coord_X > snake.getSnake_coord_X()[0]) dirFrog = 2;
                if (frog_coord_Y == snake.getSnake_coord_Y()[0] && frog_coord_X < snake.getSnake_coord_X()[0]) dirFrog = 1;

                // условия при которых лягушка не выпригивает за экран
                if (frog_coord_X >= widthFrame - STEP && dirFrog == 2) frog_coord_X = widthFrame - 50;
                if (frog_coord_X <= 0 && dirFrog == 1) frog_coord_X = STEP;
                if (frog_coord_Y >= heightFrame - STEP && dirFrog == 0) frog_coord_Y = heightFrame - 50;
                if (frog_coord_Y <= 0 && dirFrog == 3) frog_coord_Y = STEP;

                // изменения направления с шагом
                switch (dirFrog) {
                    case 0:
                        frog_coord_Y += STEP;
                        break;
                    case 1:
                        frog_coord_X -= STEP;
                        break;
                    case 2:
                        frog_coord_X += STEP;
                        break;
                    case 3:
                        frog_coord_Y -= STEP;
                        break;

                }
                Thread.sleep(snake.getDelay() * 3);

            } catch (InterruptedException e) {
                System.out.println("Exception Frog`s run!");
                e.printStackTrace();
            }
        }
    }

    public int getFrog_coord_X() {
        return frog_coord_X;
    }

    public void setFrog_coord_X(int frog_coord_X) {
        this.frog_coord_X = frog_coord_X;
    }

    public int getFrog_coord_Y() {
        return frog_coord_Y;
    }

    public void setFrog_coord_Y(int frog_coord_Y) {
        this.frog_coord_Y = frog_coord_Y;
    }

    public int getDirFrog() {
        return dirFrog;
    }

    public void setDirFrog(int dirFrog) {
        this.dirFrog = dirFrog;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}