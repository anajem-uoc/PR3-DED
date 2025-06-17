package uoc.ds.pr.model;

import uoc.ds.pr.enums.AuctionType;
import uoc.ds.pr.util.OrderedVector;


public class Auction {

    public enum AuctionStatus {
        OPEN,
        CLOSED_FOR_BIDS, // Changed from CLOSED_WAITING_AWARD to match common auction phases
        AWARDED,
        CANCELLED
    }

    private String id;
    private String cardId;
    private String workerId;
    private AuctionType auctionType;
    private double currentOrStartingPrice;
    private OrderedVector<Bid> bids;
    private Bid winningBid;
    private CatalogedCard card;
    private Worker worker;
    private AuctionStatus status;

    private boolean openForBidding;
    private boolean awarded;
    private boolean cancelled;

    public Auction(String id, CatalogedCard card, Worker worker, AuctionType auctionType, double price) {
        this.id = id;
        this.card = card;
        this.worker = worker;
        this.auctionType = auctionType;
        this.currentOrStartingPrice = price;

        if (card != null) {
            this.cardId = card.getCardId();
        }
        if (worker != null) {
            this.workerId = worker.getId();
        }

        this.bids = new OrderedVector<>(200, (c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()));

        this.openForBidding = true;
        this.awarded = false;
        this.cancelled = false;

        this.winningBid = null;
        this.status = AuctionStatus.OPEN;
    }

    // Getters
    public String getId() {
        return id;
    }

    public boolean isClosed() {
        if (status != AuctionStatus.OPEN){
            return true;
        }
        return false;
    }
    public String getCardId() {
        return cardId;
    }

    public AuctionStatus getStatus() {
        return status;
    }
    public String getWorkerId() {
        return workerId;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public double getCurrentOrStartingPrice() {
        return currentOrStartingPrice;
    }

    public OrderedVector<Bid> getBids() {
        return bids;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public CatalogedCard getCard() {
        return card;
    }

    public Worker getWorker() {
        return worker;
    }

    public boolean isOpenForBidding() {
        return this.openForBidding;
    }

    public boolean isAwarded() {
        return this.awarded;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public void markAsAwarded() {
        this.openForBidding = false;
        this.awarded = true;
        this.cancelled = false;
    }

    public void markAsCancelled() {
        this.openForBidding = false;
        this.awarded = false;
        this.cancelled = true;
    }

    public void closeForBidding() {
        this.openForBidding = false;
    }

    public void addBid(Bid bid) {
        if (bid != null && this.openForBidding) {
            this.bids.update(bid);
        }
    }

    public Bid getHighestBid() {
        if (this.bids.isEmpty()) {
            return null;
        }
        return this.bids.elementAt(0);
    }
}
