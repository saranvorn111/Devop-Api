package com.example.devopapi.api.accountType;

import com.example.devopapi.api.accountType.web.AccountTypeDto;
import com.example.devopapi.api.accountType.web.CreateNewAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{

    private final AccountTypeMapper accountTypeMapper;

    private final AccountMapStruct accountMapStruct;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
//        List<AccountTypeDto> accountTypeDtoList = accountTypes.stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName()))
//                .toList();
        return accountMapStruct.toDto(accountTypes);
    }

    @Override
    public AccountTypeDto insert(CreateNewAccountDto createNewAccountDto) {
        AccountType accountType = accountMapStruct.insert(createNewAccountDto);
        accountTypeMapper.insert(accountType);
        return accountMapStruct.accountToAccountDto(accountType);
    }

    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        AccountType accountType = accountTypeMapper.findAccountTypeById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Account type with %d not found", id)
                ));
        return accountMapStruct.accountToAccountDto(accountType);
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeDto accountTypeDto) {
        AccountType accountType = accountMapStruct.updateAccountType(accountTypeDto);
        accountTypeMapper.update(accountType);
        return accountMapStruct.accountToAccountDto(accountType);
    }


}
