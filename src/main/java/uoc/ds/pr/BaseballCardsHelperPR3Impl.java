package uoc.ds.pr;

import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.model.Auction;
import uoc.ds.pr.model.Bid;
import uoc.ds.pr.model.CardCollector;

public class BaseballCardsHelperPR3Impl implements BaseballCardsHelperPR3 {
    private BaseballCardsPR3Impl baseballCards;

    public BaseballCardsHelperPR3Impl(BaseballCardsPR3Impl baseballCards) {
        this.baseballCards = baseballCards;
    }

    public int numCardCollectors() {
        if (this.baseballCards == null) return 0;

        return baseballCards.getCollectors().size();
    }

    public CardCollector getCardCollector(String cardCollectorId) {
        if (baseballCards == null) return null;
        return baseballCards.getCollectors().get(cardCollectorId);
    }

    public Auction getAuction(String auctionId) {
        if (baseballCards == null) return null;
        // Assumes getOpenAuctions() and getClosedAuctions() are available on pr3Impl
        Auction auction = baseballCards.getOpenAuctions().get(auctionId);
        if (auction == null) {
            auction = baseballCards.getClosedAuctions().get(auctionId);
        }
        return auction;
    }

    public int numOpenAuctions() {
        return baseballCards.getOpenAuctions().size();
    }

    public int numClosedAuctions() {
        return baseballCards.getClosedAuctions().size();
    }

    public int numCardsInWishlist(String cardCollectorId) {
        if (baseballCards == null) return 0;
        CardCollector collector = baseballCards.getCollectors().get(cardCollectorId);
        if (collector == null) return 0;
        return collector.getWishList().size();
    }

    public int numCardsByCardCollector(String cardCollectorId) {
        if (baseballCards == null) return 0;
        CardCollector collector = baseballCards.getCollectors().get(cardCollectorId);
        if (collector == null) return 0;
        return collector.getOwnedCards().size();
    }

    public Bid getCurrentWinner(String auctionId) {
        Auction auction = getAuction(auctionId); // Use this class's getAuction method

        return auction.getHighestBid();
    }

    public int numFollowers(String cardCollectorId) {
        if (baseballCards == null) return 0;
        CardCollector collector = baseballCards.getCollectors().get(cardCollectorId);
        if (collector == null) return 0;
        return collector.getFollowersList().size();
    }

    public int numFollowings(String cardCollectorId) {
        if (baseballCards == null) return 0;
        CardCollector collector = baseballCards.getCollectors().get(cardCollectorId);
        if (collector == null) return 0;
        return collector.getFollowingsList().size();
    }

}
