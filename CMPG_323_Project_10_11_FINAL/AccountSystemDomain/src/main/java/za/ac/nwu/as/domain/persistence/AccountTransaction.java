package za.ac.nwu.as.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "TRANSACTION", schema = "hr")

public class AccountTransaction implements Serializable {

    private Long transactionId;
    private Long memberId;
    private String transactionType;
    private Long amount;
    private LocalDate creationDate;

    private Set<TransDetails> transDetails;


    public AccountTransaction() {
    }



    public AccountTransaction(Long memberId, String transactionType,Long amount, LocalDate creationDate) {


        this.memberId = memberId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public AccountTransaction(Long transactionId, Long memberId, String transactionType,Long amount, LocalDate creationDate){
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.memberId = memberId;
        this.creationDate = creationDate;
    }

    @Id
    @SequenceGenerator(name = "GENERIC_SEQ", sequenceName = "HR.GENERIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERIC_SEQ")
    @Column(name = "TRANS_ID")
    public Long getTransId() {
        return transactionId;
    }

    public void setTransId(Long transId) {
        this.transactionId = transId;
    }



    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "TRANSTYPE")
    public String getTransactionType() {return transactionType;}

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "AMOUNT")
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @Column(name = "CREATION_DATE")
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    @OneToMany(targetEntity = TransDetails.class, fetch = FetchType.LAZY, mappedBy = "transactionType", orphanRemoval = true, cascade = CascadeType.PERSIST)

    public Set<TransDetails> getTransDetails()
    {
        return transDetails;
    }

    public void setTransDetails(Set<TransDetails> transDetails) {
        this.transDetails = transDetails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(memberId, that.memberId)&& Objects.equals(transactionType, that.transactionType) && Objects.equals(amount, that.amount) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, memberId,transactionType, amount, creationDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transId=" + transactionId +
                ", memberId="+ memberId + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
