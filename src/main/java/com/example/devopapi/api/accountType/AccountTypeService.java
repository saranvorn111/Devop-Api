package com.example.devopapi.api.accountType;

import com.example.devopapi.api.accountType.web.AccountTypeDto;
import com.example.devopapi.api.accountType.web.CreateNewAccountDto;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeDto> findAll();
    AccountTypeDto insert(CreateNewAccountDto createNewAccountDto);

    AccountTypeDto findAccountTypeById(Integer id);

    AccountTypeDto updateAccountType(AccountTypeDto accountTypeDto);
}
