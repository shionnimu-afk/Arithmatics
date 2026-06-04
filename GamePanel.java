import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represents the game screen. Draws the game, stores information about
// the players, game state, and other values.

public class GamePanel extends JPanel implements MouseListener {

    private Player player1;
    private Player player2;
    private int turn; // always 1 or 2
    private int target;
    private int middleNum;
    private int scoreGoal;
    private boolean gameOver;

    // Creates the GamePanel, sets the screen size, allows the GamePanel
    // to receive mouse inputs, and starts the game.

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        addMouseListener(this);
        play();
    }

    // Runs when the mouse is clicked, and finds the coordinates of the
    // click relative to the game screen. If the position of the click is
    // valid, the card is played.

    public void mousePressed(MouseEvent e) {
        if(gameOver) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        int cardClick = clickInCard(x, y);
        if(cardClick != -1) {
            if (turn == 1 && cardClick <= 2) {
                playBoardCard(player1, cardClick);
                turn = 2;
            }
            else if (turn == 2 && cardClick >= 3) {
                playBoardCard(player2, cardClick-3);
                turn = 1;
            }
        }
    }

    // Checks if the position of the click is valid. If valid, returns a specific index
    // depending on which card is clicked. Otherwise, returns -1.

    private int clickInCard(int x, int y) {
        Card card = new Card();
        if (x >= 70 && x <= 70 + card.getCardWidth() && y >= 50 && y <= 50 + card.getCardHeight()) {
            return 0; // top left card
        }
        if(x >= 70 && x <= 70 + card.getCardWidth() && y >= 230 && y <= 230 + card.getCardHeight()) {
            return 1; // middle left card
        }
        if(x >= 70 && x <= 70 + card.getCardWidth() && y >= 410 && y <= 410 + card.getCardHeight()) {
            return 2; // bottom left card
        }

        if (x >= 640 && x <= 640 + card.getCardWidth() && y >= 50 && y <= 50 + card.getCardHeight()) {
            return 3; // top right card
        }
        if(x >= 640 && x <= 640 + card.getCardWidth() && y >= 230 && y <= 230 + card.getCardHeight()) {
            return 4; // middle right card
        }
        if(x >= 640 && x <= 640 + card.getCardWidth() && y >= 410 && y <= 410 + card.getCardHeight()) {
            return 5; // bottom right card
        }
            return -1; // did not click a card
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Starts the game by creating both players, initializing the starting
    // values, and allowing the game to begin.

    public void play() {
        turn = 1;
        player1 = new Player(1);
        player2 = new Player(2);
        middleNum = 0;
        target = 10;
        scoreGoal = 5;
        gameOver = false;
        repaint();
    }

    // Draws the game screen, which includes the player's cards,
    // the middle number, the target number, the player's scores,
    // and any other visuals like the background color depending on
    // the player's turn.

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int i = 0; i < 3; i++) {
            Card card = player1.getBoardCards().get(i);
            int x = 70;
            int y = 180 * i + 50;
            g.setColor(Color.decode("#d64242"));
            g.fillRect(x, y, card.getCardWidth(), card.getCardHeight());
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.setColor(Color.BLACK);
            g.drawString("" + card.getNum(), x+36, y+100);
        }
        for(int i = 0; i < 3; i++) {
            Card card = player2.getBoardCards().get(i);
            int x = 640;
            int y = 180 * i + 50;
            g.setColor(Color.decode("#6387d6"));
            g.fillRect(x, y, card.getCardWidth(), card.getCardHeight());
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.setColor(Color.BLACK);
            g.drawString("" + card.getNum(), x+36, y+100);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 250));
        g.drawString("" + middleNum, 300, 400);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Target Number: " + target, 250, 50);

        g.drawString("" + player1.getScore(), 20, 320);
        g.drawString("" + player2.getScore(), 765, 320);

        if(turn == 1) {
            setBackground(Color.decode("#ffb1ad"));
        }
        else {
            setBackground(Color.decode("#a2defc"));
        }
        if(gameOver) {
            if(player1.getScore() == scoreGoal) {
                g.drawString("Player 1 wins!", 340, 150);
            }
            else {
                g.drawString("Player 2 wins!", 340, 10);
            }
        }
    }

    // Plays a card depending on the player and the index of the card on
    // the board, and increases the middle number by the amount on the card.
    // Then, replaces the card with the top card from the player's deck.

    public void playBoardCard(Player player, int cardIndex) {
        Card card = player.getBoardCards().get(cardIndex);
        middleNum += card.getNum();
        player.replaceBoardCard(cardIndex);

        if(middleNum == target) {
            player.increaseScore(1);
            middleNum = 0;
        }
        if(middleNum > target) {
            middleNum -= 10;
        }
        if(player1.getScore() == scoreGoal) {
            gameOver = true;
        }
        else if(player2.getScore() == scoreGoal) {
            gameOver = true;
        }


        repaint();
    }





}
