import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static int width;
    public static int height;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vvesty M:");
        int M = Integer.parseInt(reader.readLine());
        System.out.println("Vvesty N:");
        int N = Integer.parseInt(reader.readLine());
        width = M * 25;
        height = N * 25;
        new GameField(width, height);

    }
}
