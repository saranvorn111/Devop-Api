package com.example.devopapi.api.accountType;

import com.example.devopapi.api.accountType.web.AccountTypeDto;
import com.example.devopapi.api.accountType.web.CreateNewAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    List<AccountTypeDto> toDto(List<AccountType> model);

    AccountType insert(CreateNewAccountDto createNewAccountDto);

    AccountType updateAccountType(AccountTypeDto accountTypeDto);

    AccountType accountTypeDtoToAccount(AccountTypeDto accountTypeDto);

    AccountTypeDto accountToAccountDto(AccountType accountType);
}
