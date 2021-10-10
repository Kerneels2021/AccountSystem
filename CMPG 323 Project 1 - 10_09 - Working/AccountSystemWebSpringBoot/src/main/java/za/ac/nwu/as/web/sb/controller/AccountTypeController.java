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
import za.ac.nwu.as.domain.dto.AccountBalanceDto;
import za.ac.nwu.as.domain.dto.AccountTransactionDto;
import za.ac.nwu.as.domain.persistence.AccountTransaction;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.as.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.as.repo.persistence.AccountTransactionRepository;

import java.util.List;


@RestController
@RequestMapping("account-system")

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


    @PostMapping("/add")
    @ApiOperation(value = "Creates a new Add Transaction.", notes = "creates a new add transaction in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Transaction was created successfully.", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<AccountTransactionDto>> createAdd(
            @ApiParam(value = "Request body to create a new Add Transaction.",required = true)
            @RequestBody AccountTransactionDto accountTransaction) {
        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.createAdd(accountTransaction);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    @ApiOperation(value = "Creates a new Withdrawal Transaction.", notes = "creates a new transaction in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Transaction was created successfully.", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<AccountTransactionDto>> createWithdraw(
            @ApiParam(value = "Request body to create a new Withdrawal Transaction.",required = true)
            @RequestBody AccountTransactionDto accountTransaction) {
        AccountTransactionDto accountTransactionResponse = createAccountTransactionFlow.createWithdraw(accountTransaction);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true, accountTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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

    @GetMapping("{memberId}")
    @ApiOperation(value = "Fetches the balances of a member.", notes = "Fetches the balance corresponding to the given memberId.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response =
                    GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not found", response =
                    GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response
                    = GeneralResponse.class)

    })

    public ResponseEntity<GeneralResponse<AccountBalanceDto>> getBalance(
                @ApiParam(value = "The member Id that identify each member.",
                        example = "1234",
                        name = "memberId",
                        required = true)
                @PathVariable("memberId")  Long memberId){
        AccountBalanceDto accountBalance = fetchAccountTransactionFlow.getAllBalance(memberId);
        GeneralResponse<AccountBalanceDto> response = new GeneralResponse<>(true, accountBalance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}



