import java.util.*;

// Card class representing a playing card
class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

// CardCollection class to manage cards
class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        this.cards = new HashSet<>(); // Using HashSet to store unique cards
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }
}

// Main class to test the functionality
public class CardManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Adding some sample cards
        collection.addCard(new Card("Hearts", "Ace"));
        collection.addCard(new Card("Hearts", "King"));
        collection.addCard(new Card("Spades", "Queen"));
        collection.addCard(new Card("Diamonds", "Jack"));
        collection.addCard(new Card("Clubs", "10"));

        // User input to search for a symbol
        System.out.print("Enter symbol to search for (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        List<Card> foundCards = collection.getCardsBySymbol(symbol);

        // Display results
        if (foundCards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards found:");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }
    }
}
