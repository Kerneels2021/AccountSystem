package za.ac.nwu.as.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountBalance",
        description = "A DTO that presents the AccountBalance")

public class AccountBalanceDto implements Serializable {
    private Long memberId;
    private Long balance;

    public AccountBalanceDto() {
    }

    public AccountBalanceDto(Long memberId, Long balance) {
        this.memberId = memberId;
        this.balance = balance;
    }


    @ApiModelProperty(position = 1,
            value = "MemberId",
            name = "The Member Id",
            notes = "The number of the members Id",
            dataType = "java.lang.Long",
            example = "1234",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 2,
            value = "Balance amount",
            name = "balance",
            notes = "The current balance",
            dataType = "java.lang.Long",
            example = "2000",
            required = true)
    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBalanceDto)) return false;
        AccountBalanceDto that = (AccountBalanceDto) o;
        return getMemberId().equals(that.getMemberId()) && balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), balance);
    }

    @Override
    public String toString() {
        return "AccountBalanceDto{" +
                "memberId=" + memberId +
                ", balance=" + balance +
                '}';
    }
}
