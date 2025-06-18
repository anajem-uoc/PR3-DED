package uoc.ds.pr;

import edu.uoc.ds.adt.nonlinear.DictionaryAVLImpl;
import edu.uoc.ds.adt.nonlinear.PriorityQueue;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.enums.*;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.DSArray;
import uoc.ds.pr.util.OrderedVector;

import java.time.LocalDate;

import static uoc.ds.pr.enums.LoanStatus.IN_PROGRESS;

public class BaseballCardsPR3Impl implements BaseballCardsPR3 {
    private static final int MAX_BEST_COLLECTORS = 5;
    private BaseballCardsPR2Impl pr2Impl;
    private DictionaryAVLImpl<String, CardCollector> collectors;
    private DictionaryAVLImpl<String, Auction> openAuctions;
    private DictionaryAVLImpl<String, Auction> closedAuctions;
    private OrderedVector<CardCollector> best5CollectorsOrderedVector;

    public BaseballCardsPR3Impl() {
        this.pr2Impl = new BaseballCardsPR2Impl();
        this.collectors = new DictionaryAVLImpl<>();
        this.openAuctions = new DictionaryAVLImpl<>();
        this.closedAuctions = new DictionaryAVLImpl<>();
        this.best5CollectorsOrderedVector = new OrderedVector<>(MAX_BEST_COLLECTORS, (c1, c2) -> Integer.compare(c2.getFiveStarCardsCount(), c1.getFiveStarCardsCount()));
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
        CardCollector newCollector = new CardCollector(collectorId, name, surname, birthday, balance);
        this.collectors.put(collectorId, newCollector);

        this.best5CollectorsOrderedVector.update(newCollector); // Add and let OrderedVector sort/manage size.
        return newCollector;
    }

    @Override
    public Iterator<Worker> getWorkersByRole(WorkerRole role) throws NoWorkerException {
        Iterator<Worker> allWorkers =  this.pr2Impl.workerRepository.getWorkers().values();
        List<Worker> filteredWorkers = new LinkedList<>();

        while (allWorkers.hasNext()) {
            Worker worker = allWorkers.next();
            if (worker.getRole() == role) {
                filteredWorkers.insertEnd(worker);
            }
        }

        if (filteredWorkers.isEmpty()) {
            throw new NoWorkerException();
        }
        return filteredWorkers.values();
    }

    @Override
    public CollectorLevel getLevel(String collectorId) throws CardCollectorNotFoundException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector collector = this.collectors.get(collectorId);

        return collector.getLevel();
    }

    @Override
    public void addAuction(String auctionId, String cardId, String workerId, AuctionType auctionType, double price)
            throws CatalogedCardNotFoundException, WorkerNotFoundException,
            WorkerNotAllowedException, CatalogedCardAlreadyLentException,
            AuctionAlreadyExistsException, AuctionAlreadyExists4CardException {

        /*if (this.openAuctions.containsKey(auctionId) || this.closedAuctions.containsKey(auctionId)) {
            throw new AuctionAlreadyExistsException();
        }*/

        /*for (Auction auc : this.openAuctions.values()) {
            if (auc.getCardId().equals(cardId)) {
                throw new AuctionAlreadyExists4CardException();
            }
        }
        // Also check closedAuctions, as a card might be in a closed-type auction that's still "active"
        for (Auction auc : this.closedAuctions.values()) {
            if (auc.getCardId().equals(cardId)) {
                throw new AuctionAlreadyExists4CardException();
            }
        }*/

        Worker worker = pr2Impl.getBaseballCardsHelper().getWorker(workerId);
        if (worker == null) {
            throw new WorkerNotFoundException();
        }

        if (worker.getRole() != WorkerRole.AUCTIONEER && worker.getRole() != WorkerRole.CATALOGER) {
            throw new WorkerNotAllowedException();
        }

        try {
            Iterator<Loan> loans = pr2Impl.getAllLoansByCard(cardId);
            while (loans.hasNext()) {
                if (loans.next().getStatus() == IN_PROGRESS) {
                    throw new CatalogedCardAlreadyLentException();
                }
            }
        } catch (NoLoanException e) {
            // No loans for this card
        }


        Player dummyPlayer = new Player("dummyPlayerId", "Dummy Player Name");
        CatalogedCard cardForAuction = new CatalogedCard(cardId);
        Auction newAuction = new Auction(auctionId, cardForAuction, worker, auctionType, price);

        if (auctionType == AuctionType.OPEN_BID) {
            this.openAuctions.put(auctionId, newAuction);
        } else if (auctionType == AuctionType.CLOSED_BID) {
            this.closedAuctions.put(auctionId, newAuction);
        }

    }

    @Override
    public void addOpenBid(String auctionId, String collectorId, double price)
            throws CardCollectorNotFoundException, AuctionNotFoundException,
            AuctionClosedException, BidPriceTooLowException {

        /*if (!this.openAuctions.containsKey(auctionId)) {
            throw new AuctionNotFoundException();
        }*/
        //Atencion!
        Auction auction = this.openAuctions.get(auctionId);

        if (auction.getStatus() != Auction.AuctionStatus.OPEN) {
            throw new AuctionClosedException();
        }

        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector collector = this.collectors.get(collectorId);


        Bid currentHighestBid = auction.getHighestBid();
        if (currentHighestBid != null) {
            if (price <= currentHighestBid.getPrice()) {
                throw new BidPriceTooLowException();
            }
        } else {

            if (auction.getBids().isEmpty()) {
                if (price < auction.getCurrentOrStartingPrice()) {
                    throw new BidPriceTooLowException();
                }
            }
        }

        // Consolidated check:
        if (currentHighestBid == null) { // First bid
            if (price < auction.getCurrentOrStartingPrice()) {
                throw new BidPriceTooLowException();
            }
        } else { // Subsequent bids
            if (price <= currentHighestBid.getPrice()) {
                throw new BidPriceTooLowException();
            }
        }

        Bid newBid = new Bid(auctionId, collectorId, collector, price);
        auction.addBid(newBid);
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

        if (this.pr2Impl.cardRepository.getCatalogedCard(cardId) == null){
            throw new CatalogedCardNotFoundException();
        }

        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector collector = this.collectors.get(collectorId);

        Player dummyPlayer = new Player("dummyPlayerWishlist", "Dummy Player");
        CatalogedCard card = new CatalogedCard(cardId);

        Iterator<CatalogedCard> ownedCardsIterator = collector.getOwnedCards().values();
        while (ownedCardsIterator.hasNext()) {
            if (ownedCardsIterator.next().getCardId().equals(cardId)) {
                throw new CardAlreadyInOwnCollectionException();
            }
        }

        if (collector.getWishList().containsKey(cardId)) {
            throw new CatalogedCardAlreadyInWishlistException();
        }

        collector.getWishList().put(cardId, card); // Storing placeholder card
    }

    @Override
    public boolean isInWishlist(String cardId, String collectorId) throws CatalogedCardNotFoundException, CardCollectorNotFoundException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector collector = this.collectors.get(collectorId);

        return collector.getWishList().containsKey(cardId);
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
    public BaseballCardsHelperPR3 getBaseballCardsHelperPR3() {
        return new BaseballCardsHelperPR3Impl(this);
    }

    public DictionaryAVLImpl<String, CardCollector> getCollectors() {
        return this.collectors;
    }

    public DictionaryAVLImpl<String, Auction> getOpenAuctions() {
        return this.openAuctions;
    }

    public DictionaryAVLImpl<String, Auction > getClosedAuctions() {
        return this.closedAuctions;
    }
    @Override
    public void addFollower(String collectorId, String collectorFollowerId) throws FollowerNotFound, FollowedException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new FollowedException();
        }
        CardCollector followed = this.collectors.get(collectorId);

        if (!this.collectors.containsKey(collectorFollowerId)) {
            throw new FollowerNotFound();
        }
        CardCollector follower = this.collectors.get(collectorFollowerId);

        if (collectorId.equals(collectorFollowerId)) {
            throw new FollowedException();
        }

        boolean alreadyFollowing = false;
        Iterator<CardCollector> currentFollowings = follower.getFollowingsList().values();
        while (currentFollowings.hasNext()) {
            if (currentFollowings.next().getCollectorId().equals(followed.getCollectorId())) {
                alreadyFollowing = true;
                break;
            }
        }

        if (alreadyFollowing) {
            throw new FollowedException();
        }

        followed.getFollowersList().insertEnd(follower);
        follower.getFollowingsList().insertEnd(followed);
    }

    @Override
    public Iterator<CardCollector> getFollowers(String collectorId) throws FollowerNotFound, FollowedException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new FollowedException();
        }
        CardCollector collector = this.collectors.get(collectorId);
        LinkedList<CardCollector> followersList = collector.getFollowersList();

        if (followersList.isEmpty()) {
            throw new FollowerNotFound();
        }

        return followersList.values();
    }

    @Override
    public Iterator<CardCollector> getFollowings(String collectorId) throws CardCollectorNotFoundException, NoFollowedException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector collector = this.collectors.get(collectorId);
        LinkedList<CardCollector> followingsList = collector.getFollowingsList();

        if (followingsList.isEmpty()) {
            throw new NoFollowedException();
        }
        return followingsList.values();
    }

    @Override
    public Iterator<CardCollector> recommendations(String collectorId)
            throws CardCollectorNotFoundException, NoFollowedException {
        if (!this.collectors.containsKey(collectorId)) {
            throw new CardCollectorNotFoundException();
        }
        CardCollector user = this.collectors.get(collectorId);

        DictionaryAVLImpl<String, CardCollector> recommendationsMap = new DictionaryAVLImpl<>();
        LinkedList<CardCollector> userFollowings = user.getFollowingsList();

        if (userFollowings.isEmpty()) {
            throw new NoFollowedException();
        }

        Iterator<CardCollector> firstLevelIter = userFollowings.values();
        while (firstLevelIter.hasNext()) {
            CardCollector firstLevelFollowing = firstLevelIter.next();

            Iterator<CardCollector> secondLevelIter = firstLevelFollowing.getFollowingsList().values();
            while (secondLevelIter.hasNext()) {
                CardCollector secondLevelFollowing = secondLevelIter.next();

                if (secondLevelFollowing.getCollectorId().equals(user.getCollectorId())) {
                    continue;
                }

                boolean alreadyFollowsSecondLevel = false;
                Iterator<CardCollector> userFollowingsCheckIter = user.getFollowingsList().values();
                while (userFollowingsCheckIter.hasNext()) {
                    if (userFollowingsCheckIter.next().getCollectorId().equals(secondLevelFollowing.getCollectorId())) {
                        alreadyFollowsSecondLevel = true;
                        break;
                    }
                }
                if (alreadyFollowsSecondLevel) {
                    continue;
                }

                if (!recommendationsMap.containsKey(secondLevelFollowing.getCollectorId())) {
                    recommendationsMap.put(secondLevelFollowing.getCollectorId(), secondLevelFollowing);
                }
            }
        }

        if (recommendationsMap.isEmpty()) {
            throw new NoFollowedException();
        }

        return recommendationsMap.values();
    }
}
