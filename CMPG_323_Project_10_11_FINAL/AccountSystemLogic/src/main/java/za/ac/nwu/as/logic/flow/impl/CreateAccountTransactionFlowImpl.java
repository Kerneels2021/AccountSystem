package za.ac.nwu.as.logic.flow.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("createAccountTransactionFlowName")
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private final AccountTransactionTranslator accountTransactionTranslator;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator){
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public AccountTransactionDto createAdd(AccountTransactionDto accountTransactionAdd) {


        if (LOGGER.isInfoEnabled()){
            String logoutput = "null";
            if (null!= accountTransactionAdd){
                logoutput = accountTransactionAdd.toString();

            }
            LOGGER.info("Transactions to Add {} ",accountTransactionAdd,logoutput);
        }


        if (null == accountTransactionAdd.getCreationDates()){

            accountTransactionAdd.setCreationDates(LocalDate.now());
            LOGGER.info(" {}",accountTransactionAdd);
        }
        try{
            if (accountTransactionAdd.getAmounts() > 0){


                accountTransactionAdd.setAmounts(accountTransactionAdd.getAmounts());
            }
            } catch (Exception e) {

            LOGGER.warn("Added values can't be negative {}",accountTransactionAdd.getAmounts());

                throw new RuntimeException(e);
            }

        return accountTransactionTranslator.create(accountTransactionAdd);
    }

    @Override
    public AccountTransactionDto createWithdraw(AccountTransactionDto accountTransactionWD) {

        LOGGER.info("Transactions to deduct {}",accountTransactionWD);

        if (null == accountTransactionWD.getCreationDates()){
            accountTransactionWD.setCreationDates(LocalDate.now());
        }


        try{

                if (accountTransactionWD.getAmounts() > 0){

                    accountTransactionWD.setAmounts(-accountTransactionWD.getAmounts());
            }
            } catch (Exception e) {

                throw new RuntimeException(e);
            }


        return accountTransactionTranslator.create(accountTransactionWD);
    }
}
