package uoc.ds.pr.model;

import uoc.ds.pr.enums.CardRating;
import uoc.ds.pr.enums.CardStatus;

import java.time.LocalDate;

public class StoredCard extends AbstractCard {

    private LocalDate cardStorageDate;

    public StoredCard(String cardId, String player, int publicationYear, String collection, CardStatus status) {
        super(cardId, player, publicationYear, collection, status);
    }

    public StoredCard(String cardId) {
        super(cardId);
    }

    public StoredCard(String cardId, String player, int publicationYear, String collection, CardStatus status, CardRating cardRating) {
        super(cardId, player, publicationYear, collection, status, cardRating);
    }


    public String getCardId() {
        return super.getCardId();
    }
}
