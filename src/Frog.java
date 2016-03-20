import java.util.Random;

public class Frog implements Runnable {
    int coord_X;
    int coord_Y;
    int dir;
    Main m = new Main();
    Thread threadFrog;
    static int[] infoSnake = new int[3];

    Frog(int widthFrame, int heightFrame){

        Random random = new Random();
        coord_X = (Math.round(random.nextInt(widthFrame)/25))*25;
        coord_Y = (Math.round(random.nextInt(heightFrame)/25))*25;
        threadFrog = new Thread(this);
        threadFrog.start();
    }
    public void coordinatsSnake (int coord_X[], int coord_Y[], int direction){
        infoSnake[0] = coord_X[0];
        infoSnake[1] = coord_Y[0];
        infoSnake[2] = direction;
    }

    public void run() {
        try {
            while (true) {

                if (coord_X > infoSnake[0] && (infoSnake[2] == 0 || infoSnake[2] == 3)) dir = 2;
                if (coord_X < infoSnake[0] && (infoSnake[2] == 0 || infoSnake[2] == 3)) dir = 1;
                if (coord_X == infoSnake[0] && coord_Y > infoSnake[1]) dir = 0;
                if (coord_X == infoSnake[0] && coord_Y < infoSnake[1]) dir = 3;
                if (coord_Y > infoSnake[1] && (infoSnake[2] == 1 || infoSnake[2] == 2)) dir = 0;
                if (coord_Y < infoSnake[1] && (infoSnake[2] == 1 || infoSnake[2] == 2)) dir = 3;
                if (coord_Y == infoSnake[1] && coord_X > infoSnake[0]) dir = 2;
                if (coord_Y == infoSnake[1] && coord_X < infoSnake[0]) dir = 1;

                if (coord_X >= m.width - 25 && dir == 2) coord_X = m.width - 50;
                if (coord_X <= 0 && dir == 1) coord_X = 25;
                if (coord_Y >= m.height - 25 && dir == 0) coord_Y = m.height - 50;
                if (coord_Y <= 0 && dir == 3) coord_Y = 25;

                switch (dir) {
                    case 0:
                        coord_Y += 25;
                        break;
                    case 1:
                        coord_X -= 25;
                        break;
                    case 2:
                        coord_X += 25;
                        break;
                    case 3:
                        coord_Y -= 25;
                        break;
                }
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Ошибка в ране лягушки");
            e.printStackTrace();
        }
    }
}
