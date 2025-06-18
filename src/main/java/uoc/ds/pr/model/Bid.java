package uoc.ds.pr.model;

import uoc.ds.pr.enums.CollectorLevel;

public class Bid {
    private  CollectorLevel collectorLevel;
    private  String auctionId;
    private  String collectorId;
    private CardCollector cardCollector;
    private CatalogedCard catalogedCard;
    private double price;

    public Bid(String auctionId, String collectorId, CardCollector collector) {
        this.cardCollector = collector;
        this.collectorId = collectorId;
        this.auctionId = auctionId;
        this.collectorLevel = collector.getLevel();
    }

    public Bid(String auctionId, String collectorId, CardCollector collector, double price) {
        this.cardCollector = collector;
        this.collectorId = collectorId;
        this.auctionId = auctionId;
        this.price = price;
    }

    public CardCollector getCardCollector() {
        return cardCollector;
    }

    public CatalogedCard getCatalogedCard() {
        return catalogedCard;
    }

    public double getPrice() {
        return price;
    }

    public void setCatalogedCard(CatalogedCard catalogedCard) {
        this.catalogedCard = catalogedCard;
    }
}
