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

        width = M * 25 + 3;
        height = N * 25;
        JFrame f = new JFrame("Snake");
        GameField gf = new GameField();
        gf.setPreferredSize(new Dimension(width, height));
        f.setSize(width, height + 70);
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());


        JScrollPane s = new JScrollPane(gf);


        JButton jbtStart = new JButton("Start");
        JButton jbtStop = new JButton("Stop");


        f.add(gf);
        f.add(jbtStart);
        f.add(jbtStop);
        f.add(s, BorderLayout.CENTER);
        f.setVisible(true);

    }
}
