import java.util.Random;

public class Frog implements Runnable {

    static int[] snake_coord_X;
    static int[] snake_coord_Y;
    static int snakeDirection;
    static int widthFrame;
    static int heightFrame;


    int frog_coord_X;
    int frog_coord_Y;
    int dir;
    Thread threadFrog;
    boolean suspendFlagFrog;
    String color;


    Frog(int widthFrame, int heightFrame, String color) {

        this.widthFrame = widthFrame;
        this.heightFrame = heightFrame;
        this.color = color;
        suspendFlagFrog = false;
        threadFrog = new Thread(this);
        threadFrog.start();
    }

    public static void infoSnake(int coord_X[], int coord_Y[], int direction) {

        snake_coord_X = new int[coord_X.length];
        snake_coord_Y = new int[coord_Y.length];

        for (int iter = 0; iter < coord_X.length; iter++) {
            snake_coord_X[iter] = coord_X[iter];
        }
        for (int iter = 0; iter < coord_Y.length; iter++) {
            snake_coord_Y[iter] = coord_Y[iter];
        }
        snakeDirection = direction;
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

    public void setRandomPosition() {
        Random random = new Random();
        frog_coord_X = (Math.round(random.nextInt(widthFrame) / 25)) * 25;
        frog_coord_Y = (Math.round(random.nextInt(heightFrame) / 25)) * 25;
        for (int iter_X = 0; iter_X < snake_coord_X.length; iter_X++) {
            if (frog_coord_X == snake_coord_X[iter_X]) {
                for (int iter_Y = 0; iter_Y < snake_coord_Y.length; iter_Y++) {
                    if (frog_coord_Y == snake_coord_Y[iter_Y]) {
                        setRandomPosition();
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

                if (frog_coord_X > snake_coord_X[0] && (snakeDirection == 0 || snakeDirection == 3)) dir = 2;
                if (frog_coord_X < snake_coord_X[0] && (snakeDirection == 0 || snakeDirection == 3)) dir = 1;
                if (frog_coord_X == snake_coord_X[0] && frog_coord_Y > snake_coord_Y[0]) dir = 0;
                if (frog_coord_X == snake_coord_X[0] && frog_coord_Y < snake_coord_Y[0]) dir = 3;
                if (frog_coord_Y > snake_coord_Y[0] && (snakeDirection == 1 || snakeDirection == 2)) dir = 0;
                if (frog_coord_Y < snake_coord_Y[0] && (snakeDirection == 1 || snakeDirection == 2)) dir = 3;
                if (frog_coord_Y == snake_coord_Y[0] && frog_coord_X > snake_coord_X[0]) dir = 2;
                if (frog_coord_Y == snake_coord_Y[0] && frog_coord_X < snake_coord_X[0]) dir = 1;

                if (frog_coord_X >= widthFrame - 25 && dir == 2) frog_coord_X = widthFrame - 50;
                if (frog_coord_X <= 0 && dir == 1) frog_coord_X = 25;
                if (frog_coord_Y >= heightFrame - 25 && dir == 0) frog_coord_Y = heightFrame - 50;
                if (frog_coord_Y <= 0 && dir == 3) frog_coord_Y = 25;

                switch (dir) {
                    case 0:
                        frog_coord_Y += 25;
                        break;
                    case 1:
                        frog_coord_X -= 25;
                        break;
                    case 2:
                        frog_coord_X += 25;
                        break;
                    case 3:
                        frog_coord_Y -= 25;
                        break;
                }
                Thread.sleep(600);

            } catch (InterruptedException e) {
                System.out.println("Ошибка в ране лягушки");
                e.printStackTrace();
            }
        }
    }
}