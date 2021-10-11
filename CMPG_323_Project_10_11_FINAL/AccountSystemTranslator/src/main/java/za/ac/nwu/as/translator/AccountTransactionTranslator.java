package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.AccountBalanceDto;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;

import java.util.List;

public interface AccountTransactionTranslator {
    List<AccountTransactionDto> getAllAccountTransaction();
    AccountTransactionDto create(AccountTransactionDto transaction);
//    AccountTransactionDto getAccountTransactionDtoByMemberId(Long memberId);
//    AccountTransaction getAccountTransactionByMemberId(Long memberId);

    AccountBalanceDto getAllBalance(Long memberId);
}
