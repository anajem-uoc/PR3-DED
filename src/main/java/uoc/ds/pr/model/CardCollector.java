package uoc.ds.pr.model;

import edu.uoc.ds.adt.nonlinear.DictionaryAVLImpl;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.enums.CollectorLevel;

import java.time.LocalDate;

public class CardCollector {
    private String collectorId;
    private String name;
    private String surname;
    private LocalDate birthday;
    private double balance;
    private int points;
    private DictionaryAVLImpl<String, CatalogedCard> wishlist;
    private LinkedList<CatalogedCard> cards;
    private LinkedList<CardCollector> followers;
    private LinkedList<CardCollector> followings;
    private int fiveStarCardsCount;
    private CollectorLevel level;

    public CardCollector(String collectorId, String name, String surname, LocalDate birthday, double balance) {
        this.collectorId = collectorId;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.balance = balance;
        this.points = 0;
        this.wishlist = new DictionaryAVLImpl<>();
        this.cards = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.followings = new LinkedList<>();
        this.fiveStarCardsCount = 0;
        this.level = CollectorLevel.BRONZE;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void updatePoints(int points) {
        this.points += points;
    }

    public CollectorLevel getLevel() {
        if (points >= 10000) {
            return CollectorLevel.DIAMOND;
        } else if (points >= 5000) {
            return CollectorLevel.PLATINUM;
        } else if (points >= 2000) {
            return CollectorLevel.GOLD;
        } else if (points >= 500) {
            return CollectorLevel.SILVER;
        } else {
            return CollectorLevel.BRONZE;
        }
    }
    public void setLevel(CollectorLevel level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addCardToWishlist(CatalogedCard card) {
        this.wishlist.put(card.getCardId(), card);
    }

    public boolean isInWishlist(String cardId) {
        if (wishlist.get(cardId)!= null){
            return true;
        }
        return false;
    }

    public int numCardsInWishlist() {
        return wishlist.size();
    }

    public void addCard(CatalogedCard card) {
        this.cards.insertEnd(card);
    }

    public int numCards() {
        return cards.size();
    }

    public boolean hasCard(String cardId) {
        for (Iterator<CatalogedCard> it = cards.values(); it.hasNext(); ) {
            CatalogedCard card = it.next();
            if (card.getCardId().equals(cardId)) {
                return true;
            }
        }
        return false;
    }

    public DictionaryAVLImpl<String, CatalogedCard> getWishList() {
        return wishlist;
    }

    public LinkedList<CatalogedCard> getOwnedCards() {
        return cards;
    }

    public LinkedList<CardCollector> getFollowersList() {
        return followers;
    }

    public LinkedList<CardCollector> getFollowingsList() {
        return followings;
    }

    public int getFiveStarCardsCount() {
        return fiveStarCardsCount;
    }

    public void incrementFiveStarCardsCount() {
        this.fiveStarCardsCount++;
    }

    public void decrementFiveStarCardsCount() {
        if (this.fiveStarCardsCount > 0) {
            this.fiveStarCardsCount--;
        }
    }

    public void addFollower(CardCollector follower) {
        this.followers.insertEnd(follower);
    }

    public void addFollowing(CardCollector following) {
        this.followings.insertEnd(following);
    }

    public int numFollowers() {
        return followers.size();
    }

    public int numFollowings() {
        return followings.size();
    }

    public edu.uoc.ds.traversal.Iterator<CardCollector> getFollowers() {
        return followers.values();
    }

    public edu.uoc.ds.traversal.Iterator<CardCollector> getFollowings() {
        return followings.values();
    }

    public void decreaseBalance(double amount) {
        this.balance -= amount;
    }

    public int compareTo(CardCollector other) {
        // Descending order by fiveStarCardsCount
        return Integer.compare(other.fiveStarCardsCount, this.fiveStarCardsCount);
    }
}
