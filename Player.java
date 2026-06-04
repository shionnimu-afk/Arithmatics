import java.util.ArrayList;

// Represents a player. Holds all the information about a player,
// including the player's number, score, deck, and cards.

public class Player {

    private int number;
    private int numCards;
    private int score;
    private ArrayList<Card> deck;
    private ArrayList<Card> boardCards;

    // Creates a player with a number, score,
    // deck, the number of cards they have, and the cards on the board.
    // Then, creates card objects based on the number of cards a player
    // should have, and takes the first three cards in deck and adds them
    // to the cards on the board.

    public Player(int number) {
        this.number = number;
        numCards = 1000;
        score = 0;
        deck = new ArrayList<Card>();
        boardCards = new ArrayList<Card>();

        for(int i = 0; i < numCards; i++) { // assigns random values to each Card and adds it to deck.
          Card card = new Card();
          deck.add(card);
        }

        for(int i = 0; i < 3; i++) {
            boardCards.add(deck.remove(i));
        }
    }

    // Replaces the specified card on the board with the top card
    // from the player's deck.

    public void replaceBoardCard(int i) {
        boardCards.set(i, deck.remove(0));
    }

    // Increases the player's score by num.

    public void increaseScore(int num) {
        score += num;
    }

    // Returns the player's deck.

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Returns the player's number.

    public int getPlayerNum() {
        return number;
    }

    // Returns the player's cards on the board.

    public ArrayList<Card> getBoardCards() {
        return boardCards;
    }

    // Returns the player's score.

    public int getScore() {
        return score;
    }




}
