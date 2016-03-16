

import java.awt.event.MouseEvent;

public class Snake {

    private static int dir = 2;
    int tail = 5;
    int[] snake_X = new int[tail];
    int[] snake_Y = new int[tail];
    Main m = new Main();

    Snake(int x, int y) {
        snake_X[0] = x;
        snake_Y[0] = y;

        for (int body = 1; body <= tail - 1; body++){
            snake_X[body] = 0;
            snake_Y[body] = snake_Y[0] - 25;
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


    public void move() {

        for (int body = tail - 1; body > 0; body--) {
            snake_X[body] = snake_X[body - 1];
            snake_Y[body] = snake_Y[body - 1];
        }
        if (snake_X[0] >= m.width - 50 && dir == 2) snake_X[0] = -25;
        if (snake_X[0] <= 0 && dir == 1) snake_X[0] = m.width - 3;
        if (snake_Y[0] >= m.height - 25 && dir == 0) snake_Y[0] = -25;
        if (snake_Y[0] <= 0 && dir == 3) snake_Y[0] = m.height;


        switch (dir) {
            case 0:
                snake_Y[0] += 25;
                break;
            case 1:
                snake_X[0] -= 25;
                break;
            case 2:
                snake_X[0] += 25;
                break;
            case 3:
                snake_Y[0] -= 25;
                break;
        }
    }
}
