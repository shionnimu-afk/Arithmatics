
// Represents a card. Each card has a number, a width, and a height.

public class Card {

    private int num;
    private int cardWidth;
    private int cardHeight;

    // Creates a card object by setting the dimensions of the card
    // and assigning a random number from 1-7 for the card's number.

    public Card() {
    num = (int)(Math.random() * 7) + 1;
    cardWidth = 100;
    cardHeight = 170;
    }

    // Returns the number of the card.

    public int getNum() {
        return num;
    }

    // Returns the width of the card.

    public int getCardWidth() {
        return cardWidth;
    }

    // Returns the height of the card.

    public int getCardHeight() {
        return cardHeight;
    }
}
