package za.ac.nwu.as.repo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.dto.AccountBalanceDto;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.persistence.TransDetails;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

//
//    @Query(value = "SELECT " +
//            "  at" +
//            "  FROM " +
//            "      AccountTransaction at" +
//            "  WHERE at.memberId = :memberId ")
//    AccountTransaction getAccountTransactionByMemberId(Long memberId);


    @Query(value = "SELECT new za.ac.nwu.as.domain.dto.AccountBalanceDto(at.memberId, " +
            " SUM(at.amount))" +
            " FROM AccountTransaction at" +
            "  WHERE at.memberId = :memberId " +
            " GROUP BY at.memberId")
    AccountBalanceDto getBalance(Long memberId);

//
//    @Query(value = "SELECT new za.ac.nwu.as.domain.dto.AccountTransactionDto( " +
//
//            "           at.memberId," +
//            "           at.transactionType," +
//            "           at.amount," +
//            "           at.creationDate )" +
//            "       FROM " +
//            "           AccountTransaction at" +
//            "       WHERE at.memberId   =   :memberId ")
//    AccountTransactionDto getAccountTransactionDtoByMemberId(Long memberId);
//









}
