
import java.io.IOException;

public class Main {

    static int quantityRows;
    static int quantityColumns;
    static int lengthSnake;
    static int delaySnake;
    static int quantityGreenFrog;
    static int quantityBlueFrog;
    static int quantityRedFrog;

    public static void main(String[] args) throws IOException {
        try {
            if (Integer.parseInt(args[0]) < 10) System.out.println("Number of lines cannot be less than 10");
            quantityRows = Integer.parseInt(args[0]);

            if (Integer.parseInt(args[1]) < 10) System.out.println("Number of columns cannot be less than 10");
            quantityColumns = Integer.parseInt(args[1]);

            if (Integer.parseInt(args[2]) < 3) System.out.println("The snake's length cannot be less than 3");
            lengthSnake = Integer.parseInt(args[2]);

            if (Integer.parseInt(args[3]) < 100) System.out.println("The snake's delay cannot be less than 100");
            delaySnake = Integer.parseInt(args[3]);

            if (Integer.parseInt(args[4]) < 0) System.out.println("A number of green frogs cannot be less than 0");
            quantityGreenFrog = Integer.parseInt(args[4]);

            if (Integer.parseInt(args[5]) < 0) System.out.println("A number of blue frogs cannot be less than 0");
            quantityBlueFrog = Integer.parseInt(args[5]);

            if (Integer.parseInt(args[6]) < 0) System.out.println("A number of red frogs cannot be less than 0");
            quantityRedFrog = Integer.parseInt(args[6]);
        }catch (Exception e){
            System.out.println("A wrong parameter value entered, enter a value again.");
        }

/*        quantityColumns = 100;
        quantityRows = 100;
        lengthSnake = 10;
        delaySnake = 300;
        quantityGreenFrog = 5;
        quantityBlueFrog = 5;
        quantityRedFrog = 2;*/


        new GameField(quantityColumns, quantityRows, lengthSnake, delaySnake, quantityGreenFrog,
                quantityBlueFrog, quantityRedFrog);

    }
}
