package uoc.ds.pr.model;

public class Bid {
    private  String auctionId;
    private  String collectorId;
    private CardCollector cardCollector;
    private CatalogedCard catalogedCard;
    private double price;

    public Bid(CardCollector cardCollector, CatalogedCard catalogedCard, double price) {
        this.cardCollector = cardCollector;
        this.catalogedCard = catalogedCard;
        this.price = price;
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
}
