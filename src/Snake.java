import java.awt.event.MouseEvent;

public class Snake implements Runnable {

    static final int STEP = 25;

    int widthFrame;
    int heightFrame;
    int dirSnake = 0; // направление движения змеи
    int tail;
    int[] snake_coord_X = new int[99];
    int[] snake_coord_Y = new int[99];
    Thread threadSnake;
    int delay;
    boolean suspendFlagSnake;


    Snake(int width, int height, int lengthSnake, int delaySnake, boolean flagStart) {

        threadSnake = new Thread(this);
        threadSnake.start();
        suspendFlagSnake = flagStart;
        widthFrame = width;
        heightFrame = height;
        tail = lengthSnake;
        delay = delaySnake;
        snake_coord_X[0] = 0;
        snake_coord_Y[0] = tail * STEP - STEP;
        for (int snakeBody = 1; snakeBody <= tail - 1; snakeBody++){
            snake_coord_X[snakeBody] = 0;
            snake_coord_Y[snakeBody] = snake_coord_Y[snakeBody - 1] - STEP;
        }
    }
    // изменение напряавления в зависимости от клика мыши
    public void MousePressed(MouseEvent e) {

        switch (dirSnake) {
            case 0:
                if (e.getButton() == MouseEvent.BUTTON1) dirSnake = 2;
                else dirSnake = 1;
                break;
            case 1:
                if (e.getButton() == MouseEvent.BUTTON1) dirSnake = 0;
                else dirSnake = 3;
                break;
            case 2:
                if (e.getButton() == MouseEvent.BUTTON1) dirSnake = 3;
                else dirSnake = 0;
                break;
            case 3:
                if (e.getButton() == MouseEvent.BUTTON1) dirSnake = 1;
                else dirSnake = 2;
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

    //проверка на съедание лягушки
    public boolean chekEattheFrog(Frog frog){
        if (frog.frog_coord_X == snake_coord_X[0] && frog.frog_coord_Y == snake_coord_Y[0]){
            return true;
        } else return false;
    }

    //проверка на столкновение змеи с собой
    public boolean chekCollisionTail(){

        for (int snakeBody = 1; snakeBody <= tail - 1; snakeBody++){
            if (snake_coord_X[0] == snake_coord_X[snakeBody] && snake_coord_Y[0] == snake_coord_Y[snakeBody]){
                return true;
            }
        }
        return false;
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

                if (snake_coord_X[0] >= widthFrame - STEP && dirSnake == 2) snake_coord_X[0] = -STEP;
                if (snake_coord_X[0] <= 0 && dirSnake == 1) snake_coord_X[0] = widthFrame;
                if (snake_coord_Y[0] >= heightFrame - STEP && dirSnake == 0) snake_coord_Y[0] = -STEP;
                if (snake_coord_Y[0] <= 0 && dirSnake == 3) snake_coord_Y[0] = heightFrame;

                switch (dirSnake) {
                    case 0:
                        snake_coord_Y[0] += STEP;
                        break;
                    case 1:
                        snake_coord_X[0] -= STEP;
                        break;
                    case 2:
                        snake_coord_X[0] += STEP;
                        break;
                    case 3:
                        snake_coord_Y[0] -= STEP;
                        break;
                }
                Thread.sleep(delay);

            } catch (InterruptedException e) {
                System.out.println("Exception Snake`s run!");
                e.printStackTrace();
            }
        }
    }

    public int getDir() {
        return dirSnake;
    }

    public void setDir(int dir) {
        this.dirSnake = dir;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int[] getSnake_coord_X() { return snake_coord_X; }

    public void setSnake_coord_X(int[] snake_coord_X) {
        this.snake_coord_X = snake_coord_X;
    }

    public int[] getSnake_coord_Y() {
        return snake_coord_Y;
    }

    public void setSnake_coord_Y(int[] snake_coord_Y) {
        this.snake_coord_Y = snake_coord_Y;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
