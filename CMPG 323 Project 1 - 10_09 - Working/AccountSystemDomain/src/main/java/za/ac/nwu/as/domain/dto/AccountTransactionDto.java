package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.AccountTransaction;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;


@ApiModel(value = "AccountTransaction",
        description = "A DTO that represents the AccountTransaction")


public class AccountTransactionDto implements Serializable {


    private Long memberId;
    private String transactionType;
    private Long amount;
    private LocalDate creationDate;


    public AccountTransactionDto(){}

    public AccountTransactionDto(Long memberId, String transactionType,Long amount, LocalDate creationDate)
    {

        this.memberId = memberId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.creationDate = creationDate;
    }


    public AccountTransactionDto(AccountTransaction transactionType)
    {
        this.setMemberId(transactionType.getMemberId());
        this.setTransactionsType(transactionType.getTransactionType());
        this.setAmounts(transactionType.getAmount());
        this.setCreationDates(transactionType.getCreationDate());

    }

    @ApiModelProperty(position = 1,
            value = "MemberId",
            name = "The Member Id",
            notes = "The number of the members Id",
            dataType = "java.lang.Long",
            example = "1234",
            required = true)
    public Long getMemberId(){
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 2,
            value = "Transaction Type",
            name = "TransactionType",
            notes = "Type Withdrawal or Adding",
            dataType = "java.lang.String",
            example = "Add/Withdraw",
            required = true)
    public String getTransactionsType() {
        return transactionType;
    }
    public void setTransactionsType(String transactionType) {
        this.transactionType = transactionType;
    }

    @ApiModelProperty(position = 3,
            value = "Transaction amount",
            name = "amount",
            notes = "The amount of the transaction",
            dataType = "java.lang.Long",
            example = "2000",
            required = true)
    public Long getAmounts() {return amount;}

    public void setAmounts(Long amount) {
        this.amount = amount;
    }


    @ApiModelProperty(position = 4,
            value = "Transaction Creation Date",
            name = "Transaction CreationDate",
            notes = "The date on which the transaction was done",
            dataType = "java.lang.String",
            example = "2020-01-01",
            allowEmptyValue = true)
    public LocalDate getCreationDates() {
        return creationDate;
    }

    public void setCreationDates(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    @JsonIgnore
    public AccountTransaction getAccountTransaction() {
        return new AccountTransaction(getMemberId(), getTransactionsType(), getAmounts(),getCreationDates());
    }


    @Override
    public int hashCode() {
        return Objects.hash(memberId,transactionType, amount, creationDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                ", memberId=" + memberId +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }


}
