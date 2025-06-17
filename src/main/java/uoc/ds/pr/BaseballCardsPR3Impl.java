package uoc.ds.pr;

import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.enums.*;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import java.time.LocalDate;

public class BaseballCardsPR3Impl implements BaseballCardsPR3 {
    private BaseballCardsPR2Impl pr2Impl;

    public BaseballCardsPR3Impl() {
        this.pr2Impl = new BaseballCardsPR2Impl();
    }

    @Override
    public void addEntity(String entityId, String name, String address) {
        pr2Impl.addEntity(entityId, name, address);
    }

    @Override
    public void addWorker(String workerId, String name, String surname, WorkerRole role) {
        pr2Impl.addWorker(workerId, name, surname, role);
    }

    @Override
    public void storeCard(String cardId, String playerId, int publicationYear, String collection, CardStatus state, CardRating cardRating) {
        pr2Impl.storeCard(cardId, playerId, publicationYear, collection, state, cardRating);
    }

    @Override
    public CatalogedCard catalogCard(String workerId) throws NoCardException, WorkerNotFoundException, WorkerNotAllowedException {
        return pr2Impl.catalogCard(workerId);
    }

    @Override
    public Loan lendCard(String loanId, String entityId, String cardId, String workerId, LocalDate date, LocalDate expirationDate) 
            throws EntityNotFoundException, CatalogedCardNotFoundException, WorkerNotFoundException, NoCardException, 
            CatalogedCardAlreadyLoanedException, MaximumNumberOfLoansException, WorkerNotAllowedException, CatalogedCardAlreadyAuctionedException {
        return pr2Impl.lendCard(loanId, entityId, cardId, workerId, date, expirationDate);
    }

    @Override
    public Loan givebackCard(String loanId, LocalDate date) throws LoanNotFoundException {
        return pr2Impl.givebackCard(loanId, date);
    }

    @Override
    public int timeToBeCataloged(String cardId, int boxPreparationTime, int cardCatalogTime) 
            throws StoredCardNotFoundException, InvalidLotPreparationTimeException, InvalidCatalogTimeException {
        return pr2Impl.timeToBeCataloged(cardId, boxPreparationTime, cardCatalogTime);
    }

    @Override
    public Iterator<CatalogedCard> getAllCardsByPlayer(String player) throws NoCardException {
        return pr2Impl.getAllCardsByPlayer(player);
    }

    @Override
    public Iterator<CatalogedCard> getAllCardsByCollection(String collection) throws NoCardException {
        return pr2Impl.getAllCardsByCollection(collection);
    }

    @Override
    public Iterator<Loan> getAllLoansByEntity(String entityId) throws NoLoanException {
        return pr2Impl.getAllLoansByEntity(entityId);
    }

    @Override
    public Iterator<Loan> getAllLoansByState(String entityId, LoanStatus state) throws NoLoanException, CatalogedCardNotFoundException {
        return pr2Impl.getAllLoansByState(entityId, state);
    }

    @Override
    public Iterator<Loan> getAllLoansByCard(String cardId) throws NoLoanException {
        return pr2Impl.getAllLoansByCard(cardId);
    }

    @Override
    public Entity getEntityTheMost() throws NoEntityException {
        return pr2Impl.getEntityTheMost();
    }

    @Override
    public CatalogedCard getMostShownCard() throws NoCardException {
        return pr2Impl.getMostShownCard();
    }

    @Override
    public BaseballCardsHelper getBaseballCardsHelper() {
        return pr2Impl.getBaseballCardsHelper();
    }

    @Override
    public CardCollector addCollector(String collectorId, String name, String surname, LocalDate birthday, double balance) {
        return null;
    }

    @Override
    public Iterator getWorkersByRole(WorkerRole role) throws NoWorkerException {
        return null;
    }

    @Override
    public CollectorLevel getLevel(String collectorId) throws CardCollectorNotFoundException {
        return null;
    }

    @Override
    public void addAuction(String auctionId, String cardId, String workerId, AuctionType auctionType, double price) throws CatalogedCardNotFoundException, WorkerNotFoundException, WorkerNotAllowedException, CatalogedCardAlreadyLentException, AuctionAlreadyExistsException, AuctionAlreadyExists4CardException {

    }

    @Override
    public void addOpenBid(String auctionId, String collectorId, double price) throws CardCollectorNotFoundException, AuctionNotFoundException, AuctionClosedException, BidPriceTooLowException {

    }

    @Override
    public void addClosedBid(String auctionId, String collectorId) throws CardCollectorNotFoundException, AuctionClosedException, AuctionNotFoundException {

    }

    @Override
    public Bid award(String auctionId, String workerId) throws AuctionNotFoundException, AuctionClosedException, WorkerNotFoundException, WorkerNotAllowedException, NoBidException, InvalidBidException {
        return null;
    }

    @Override
    public void addToWishlist(String cardId, String collectorId) throws CatalogedCardNotFoundException, CardCollectorNotFoundException, CatalogedCardAlreadyInWishlistException, CardAlreadyInOwnCollectionException {

    }

    @Override
    public boolean isInWishlist(String cardId, String collectorId) throws CatalogedCardNotFoundException, CardCollectorNotFoundException {
        return false;
    }

    @Override
    public void addCollectionToWishlist(String collectionId, String collectorId) throws CollectionNotFoundException, CardCollectorNotFoundException {

    }

    @Override
    public Bid getAuctionWinner(String auctionId) throws AuctionNotFoundException, AuctionStillOpenException {
        return null;
    }

    @Override
    public Iterator best5CollectorsByRareCards() throws NoCardCollectorException {
        return null;
    }

    @Override
    public void addFollower(String collectorId, String collectorFollowerId) throws FollowerNotFound, FollowedException {

    }

    @Override
    public Iterator<CardCollector> getFollowers(String collectorId) throws FollowerNotFound, FollowedException {
        return null;
    }

    @Override
    public Iterator<CardCollector> getFollowings(String collectorId) throws CardCollectorNotFoundException, NoFollowedException {
        return null;
    }

    @Override
    public Iterator<CardCollector> recommendations(String collectorId) throws CardCollectorNotFoundException, NoFollowedException {
        return null;
    }

    @Override
    public BaseballCardsHelperPR3 getBaseballCardsHelperPR3() {
        return null;
    }
}
