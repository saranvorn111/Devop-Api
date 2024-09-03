package com.example.devopapi.api.accountType;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {

    private static final String tableName = "account_types";

    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*" );
            FROM(tableName);
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{a.name}");

        }}.toString();
    }

    public String buildFindByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}");

        }}.toString();
    }

    public String buildUpdateSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name","#{a.name}");
            WHERE("id = #{a.id}");

        }}.toString();
    }
}
