package uoc.ds.pr.model;

import uoc.ds.pr.enums.AuctionType;

public class Auction {
    private String id;
    private CatalogedCard catalogedCard;
    private Worker worker;
    private AuctionType auctionType;
    private double price;
    private boolean closed;

    public Auction(String id, CatalogedCard catalogedCard, Worker worker, AuctionType auctionType, double price) {
        this.id = id;
        this.catalogedCard = catalogedCard;
        this.worker = worker;
        this.auctionType = auctionType;
        this.price = price;
        this.closed = false;
    }

    public String getId() {
        return id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        this.closed = true;
    }

    public CatalogedCard getCatalogedCard() {
        return catalogedCard;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public double getPrice() {
        return price;
    }
}
