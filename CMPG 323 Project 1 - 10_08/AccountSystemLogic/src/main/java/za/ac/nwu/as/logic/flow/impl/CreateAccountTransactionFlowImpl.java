package za.ac.nwu.as.logic.flow.impl;


import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("createAccountTransactionFlowName")
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private final AccountTransactionTranslator accountTransactionTranslator;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator){
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransaction) {
        if (null == accountTransaction.getCreationDates()){
            accountTransaction.setCreationDates(LocalDate.now());
        }
        if (accountTransaction.getAmounts() >= 0){
            accountTransaction.setAmounts(-accountTransaction.getAmounts());
        }
//        else (Exception e);
        return accountTransactionTranslator.create(accountTransaction);
    }
}
