package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.Users;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    @InsertProvider(type = AuthProvider.class, method = "buildInsertSql")
    void register(@Param("u") Users users);
}
