package uoc.ds.pr.model;

import uoc.ds.pr.CardStatus;
import uoc.ds.pr.LoanStatus;

import java.time.LocalDate;
import java.util.Comparator;

public class Loan {

    public static final Comparator<Loan> CMP_ID = (l1, l2)->l1.getLoanId().compareTo(l2.getLoanId());
    private String loanId;
    private Entity entity;
    private CatalogedCard catalogedCard;
    private LocalDate date;
    private LocalDate expirationDate;
    private Worker worker;
    private LoanStatus status;

    public Loan(String loanId, Entity entity, CatalogedCard catalogedCard, LocalDate date, LocalDate expirationDate) {
        this(loanId);
        this.entity = entity;
        this.catalogedCard = catalogedCard;
        this.date = date;
        this.expirationDate = expirationDate;
        this.status = LoanStatus.IN_PROGRESS;
    }

    public Loan(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanId() {
        return loanId;
    }

    public Entity getEntity() {
        return entity;
    }

    public CatalogedCard getCatalogedCard() {
        return catalogedCard;
    }

    public LoanStatus getStatus() {
        return this.status;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public boolean isDelayed(LocalDate date) {
        return date.compareTo(expirationDate)>0;
    }

    public void setStatus(LoanStatus loanStatus) {
        this.status = loanStatus;
    }
}
