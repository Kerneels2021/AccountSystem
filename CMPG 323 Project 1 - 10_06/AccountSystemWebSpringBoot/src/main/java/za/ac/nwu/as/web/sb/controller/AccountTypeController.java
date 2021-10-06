package za.ac.nwu.as.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;

import java.util.List;


@RestController
@RequestMapping("account-transaction")

public class AccountTypeController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private final CreateAccountTransactionFlow createAccountTransactionFlow;

    @Autowired
    public AccountTypeController(FetchAccountTransactionFlow fetchAccountTransactionFlow,
                                @Qualifier("createAccountTransactionFlowName") CreateAccountTransactionFlow createAccountTransactionFlow)
    {
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
        this.createAccountTransactionFlow = createAccountTransactionFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the transactions on an account.", notes = "Returns a list of transactions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transactions returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response =
                    GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response =
                    GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response
                    = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll() {
        List<AccountTransactionDto> accountTransactions = fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Creates a new Transaction.", notes = "creates a new transaction in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Transaction was created successfully.", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<AccountTransactionDto>> create(
            @ApiParam(value = "Request body to create a new Transaction.",required = true)
            @RequestBody AccountTransactionDto accountTransaction) {
        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.create(accountTransaction);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{memberId}")
    @ApiOperation(value = "Fetches the specified transactions of a member.", notes = "Fetches the transaction corresponding to the given memberId.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response =
                    GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not found", response =
                    GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response
                    = GeneralResponse.class)

    })

    public ResponseEntity<GeneralResponse<AccountTransactionDto>> getAccountTransaction(
                @ApiParam(value = "The member Id that identify each member.",
                        example = "1234",
                        name = "memberId",
                        required = true)
                @PathVariable("memberId") final Long memberId) {
        AccountTransactionDto accountType = fetchAccountTransactionFlow.getAccountTransactionByMemberId(memberId);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PutMapping("/transaction/{memberId}")
//    Employee updateTransaction(@RequestBody AccountTransaction newAccountTransaction, @PathVariable Long memberId) {
//
//        return repository.findById(id).map(employee -> {
//            employee.setFirstName(newEmployee.getFirstName());
//            employee.setLastName(newEmployee.getLastName());
//            employee.setEmail(newEmployee.getEmail());
//            return repository.save(employee);
//        }).orElseGet(() -> {
//            newEmployee.setId(id);
//            return repository.save(newEmployee);
//        });
//    }
//
//    @PutMapping("/transaction/{memberId}")
//    @ApiOperation(value = "Updates a  Transaction.", notes = "updates a new transaction in the DB.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "The Transaction was created successfully.", response = GeneralResponse.class),
//            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
//            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
//
//    public ResponseEntity<GeneralResponse<AccountTransactionDto>> update(
//            @ApiParam(value = "Request body to update a new Transaction.",required = true)
//            @RequestBody AccountTransactionDto newAccountTransactionDto) {
//        return AccountTransactionRepository.findById(memberId).map(AccountTransactionDto ->
//        {
//            AccountTransaction.setAmount(newAccountTransaction.setAmount(););})
//        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.update(accountTransaction);
//        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true, accountTransactionResponse);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }



}



