import javax.swing.JFrame;

public class GameFrame extends JFrame {

    // Creates the visible window for the game and adds the GamePanel
    // to it.

    public GameFrame() {

        setTitle("Arithmatics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
