package za.ac.nwu.as.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.AccountBalanceDto;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;
import za.ac.nwu.as.translator.AccountTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {

    private final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository accountTransactionRepository){
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<AccountTransactionDto> getAllAccountTransaction() {
        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        try {
            for (AccountTransaction accountTransaction : accountTransactionRepository.findAll()) {
                accountTransactionDtos.add(new AccountTransactionDto(accountTransaction));
            }
        }
        catch (Exception e)
        {
            //TODO: Log
            throw new RuntimeException("Unable to read from the DataBase", e);
        }

        return accountTransactionDtos;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto)
    {
        try {
            AccountTransaction accountTransaction = accountTransactionRepository.save(accountTransactionDto.getAccountTransaction());
            return new AccountTransactionDto(accountTransaction);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from the DataBase", e);
        }
    }

//    @Override
//    public AccountTransactionDto getAccountTransactionDtoByMemberId(Long memberId)
//    {
//        try {
//            return accountTransactionRepository.getAccountTransactionDtoByMemberId(memberId);
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to read from the DataBase", e);
//        }
//    }
//
//    @Override
//    public AccountTransaction getAccountTransactionByMemberId(Long memberId) {
//        try {
//            return accountTransactionRepository.getAccountTransactionByMemberId(memberId);
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to read from the DataBase", e);
//        }
//    }

    @Override
    public AccountBalanceDto getAllBalance(Long memberId) {
        try {
            return accountTransactionRepository.getBalance(memberId);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from the DataBase", e);
        }
    }


}
