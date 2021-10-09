package za.ac.nwu.as.logic.flow.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class FetchAccountTransactionFlowImpl implements FetchAccountTransactionFlow {

    private final AccountTransactionTranslator accountTransactionTranslator;

    @Autowired
    public FetchAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator) {
        this.accountTransactionTranslator= accountTransactionTranslator;
    }


    @Override
    public List<AccountTransactionDto> getAllAccountTransactions() {
        return accountTransactionTranslator.getAllAccountTransaction();
    }

    @Override
    public AccountTransactionDto getAccountTransactionByMemberId(Long memberId){
        return accountTransactionTranslator.getAccountTransactionDtoByMemberId(memberId);
    }
//
//    public boolean methodToTest(){
//        return true;
//    }

}
