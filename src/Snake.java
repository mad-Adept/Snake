import java.awt.event.MouseEvent;

public class Snake implements Runnable {

    static int widthFrame;
    static int heightFrame;
    int dir = 0;
    int tail = 2;
    int[] snake_coord_X = new int[99];
    int[] snake_coord_Y = new int[99];
    Thread threadSnake;
    boolean suspendFlagSnake;


    Snake(int width, int height) {

        threadSnake = new Thread(this);
        threadSnake.start();
        suspendFlagSnake = false;
        widthFrame = width;
        heightFrame = height;
        snake_coord_X[0] = 0;
        snake_coord_Y[0] = 125;
        for (int snakeBody = 1; snakeBody <= tail - 1; snakeBody++){
            snake_coord_X[snakeBody] = 0;
            snake_coord_Y[snakeBody] = snake_coord_Y[snakeBody - 1] - 25;
        }
    }

    public void MousePressed(MouseEvent e) {

        switch (dir) {
            case 0:
                if (e.getButton() == MouseEvent.BUTTON1) dir = 2;
                else dir = 1;
                break;
            case 1:
                if (e.getButton() == MouseEvent.BUTTON1) dir = 0;
                else dir = 3;
                break;
            case 2:
                if (e.getButton() == MouseEvent.BUTTON1) dir = 3;
                else dir = 0;
                break;
            case 3:
                if (e.getButton() == MouseEvent.BUTTON1) dir = 1;
                else dir = 2;
                break;
        }


    }
    synchronized void snakeSuspend(){
        suspendFlagSnake = true;
    }

    synchronized void snakeResume(){
        suspendFlagSnake = false;
        notify();
    }
    synchronized void snakeStop(){
        suspendFlagSnake = true;
        Thread.interrupted();
    }

    public boolean chekEattheFrog(Frog frog){
        if (frog.frog_coord_X == snake_coord_X[0] && frog.frog_coord_Y == snake_coord_Y[0]){
            return true;
        } else return false;
    }


    public void run() {

        while(true) {
            try {
                    synchronized (this){
                        while (suspendFlagSnake){
                            wait();
                        }
                    }

                for (int body = tail - 1; body > 0; body--) {
                    snake_coord_X[body] = snake_coord_X[body - 1];
                    snake_coord_Y[body] = snake_coord_Y[body - 1];
                }

                if (snake_coord_X[0] >= widthFrame - 25 && dir == 2) snake_coord_X[0] = -25;
                if (snake_coord_X[0] <= 0 && dir == 1) snake_coord_X[0] = widthFrame;
                if (snake_coord_Y[0] >= heightFrame - 25 && dir == 0) snake_coord_Y[0] = -25;
                if (snake_coord_Y[0] <= 0 && dir == 3) snake_coord_Y[0] = heightFrame;

                switch (dir) {
                    case 0:
                        snake_coord_Y[0] += 25;
                        break;
                    case 1:
                        snake_coord_X[0] -= 25;
                        break;
                    case 2:
                        snake_coord_X[0] += 25;
                        break;
                    case 3:
                        snake_coord_Y[0] -= 25;
                        break;
                }
                Thread.sleep(100);

            } catch (InterruptedException e) {
                System.out.println("Ошибка в ране змеи");
                e.printStackTrace();
            }
        }
    }
}
