package za.ac.nwu.as.logic.flow;

import za.ac.nwu.as.domain.dto.AccountBalanceDto;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

import java.util.List;

public interface FetchAccountTransactionFlow {

    List<AccountTransactionDto> getAllAccountTransactions();
//    AccountTransactionDto getAccountTransactionByMemberId(Long memberId);
//    AccountTransaction getAccountTransactionForMemberId(Long memberId);
//    AccountTransactionDto getAccountTransactionDtoByMemberId(Long memberId);
    AccountBalanceDto getAllBalance(Long memberId);

}
