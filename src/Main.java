import javax.swing.*;

public class Main {
    public static int width = 506;
    public static int height = 529;

    public static void main(String[] args) {
        JFrame f = new JFrame("Snake");
        f.setSize(width, height);
        f.add(new GameField());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
