package com.example.devopapi.api.accountType;

import com.example.devopapi.api.user.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AccountTypeMapper {

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    void insert(@Param("a") AccountType accountType);

    @SelectProvider(type = AccountTypeProvider.class, method = "buildFindByIdSql")
    Optional<AccountType> findAccountTypeById(@Param("id") Integer id);

    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateSql")
    AccountType update(@Param("a") AccountType accountType);

}
