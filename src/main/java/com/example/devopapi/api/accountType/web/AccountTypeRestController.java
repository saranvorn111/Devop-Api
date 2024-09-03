package com.example.devopapi.api.accountType.web;

import com.example.devopapi.api.accountType.AccountType;
import com.example.devopapi.api.accountType.AccountTypeService;
import com.example.devopapi.api.base.BestRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public BestRest<?> findAll(){
        List<AccountTypeDto> accountlist = accountTypeService.findAll();
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Account type find success.")
                .data(accountlist)
                .build();
    }

    @PostMapping
    public BestRest<?> insert(@RequestBody CreateNewAccountDto createNewAccountDto){
        AccountTypeDto accountTypeDtos = accountTypeService.insert(createNewAccountDto);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Account type insert success.")
                .data(accountTypeDtos)
                .build();
    }

    @GetMapping("/{id}")
    public BestRest<?> findAccountById(@PathVariable Integer id){
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeById(id);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Account type find success.")
                .data(accountTypeDto)
                .build();
    }

}
