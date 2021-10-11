package za.ac.nwu.as.domain.persistence;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;



@Entity
@Table(name = "TRANS_DETAILS", schema = "HR")

public class TransDetails implements Serializable {

    private Long transDetailId;
    private AccountTransaction transactionType; //Foreign key
    private String details;
    private LocalDate transactionDate;

    public TransDetails() {
    }

    public TransDetails(Long transactionId,AccountTransaction transactionType, String details,  LocalDate transactionDate) {
        this.transDetailId = transDetailId;
        this.transactionType = transactionType;
        this.details = details;
        this.transactionDate = transactionDate;
    }

    @Id
    @SequenceGenerator(name = "HR_GENERIC_SEQ", sequenceName = "HR_GENERIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HR_GENERIC_SEQ")
    @Column(name = "TRANS_DETAIL_ID")
    public Long getTransDetailId() {
        return transDetailId;  }


    @Column(name = "DETAILS")
    public String getDetails() {
        return details;
    }

    @Column(name = "TRANS_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANS_ID")

    public AccountTransaction getTransactionType(){
        return transactionType;
    }

    public void setTransactionType(AccountTransaction transType){
        this.transactionType = transactionType;
    }


    public void setTransDetailId(Long transDetailId) {
        this.transDetailId = transDetailId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransDetails that = (TransDetails) o;
        return Objects.equals(transDetailId, that.transDetailId) && Objects.equals(transactionType, that.transactionType) && Objects.equals(details, that.details) && Objects.equals(transactionDate, that.transactionDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(transDetailId,transactionType, details, transactionDate);
    }


    @Override
    public String toString() {
        return "TransDetails{" +
                "transDetailId=" + transDetailId +
                ", transactionType=" + transactionType +
                ", details='" + details + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
