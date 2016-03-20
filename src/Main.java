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


        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(width + 3, height + 3));
        topPanel.setLayout(new BorderLayout());
        GameField gf = new GameField();
        gf.setPreferredSize(new Dimension(width, height));
        JScrollPane scroll = new JScrollPane(gf);
        topPanel.add(scroll, BorderLayout.CENTER);


        JPanel botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(1, 1, 30, 10));

        JButton jbtStart = new JButton("Start");
        JButton jbtPause = new JButton("Pause");
        JButton jbtStop = new JButton("Stop");
        jbtStart.setActionCommand("Start");
        jbtPause.setActionCommand("Pause");
        jbtStop.setActionCommand("Stop");
        jbtStart.addActionListener(gf);
        jbtPause.addActionListener(gf);
        jbtStop.addActionListener(gf);

        botPanel.add(jbtStart);
        botPanel.add(jbtPause);
        botPanel.add(jbtStop);


        JFrame f = new JFrame("Snake");
        if (width >= 800 && height >= 660) f.setPreferredSize(new Dimension(800, 660));
        else f.setPreferredSize(new Dimension(width + 19, height + 66));
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(botPanel, BorderLayout.SOUTH);
        f.pack();

    }
}
