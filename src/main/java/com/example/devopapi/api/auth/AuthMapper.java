package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AuthMapper {

    @InsertProvider(type = AuthProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") Users users);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    void createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Select("SELECT * FROM users WHERE email = #{email} AND is_deleted = FALSE")
    @Results(id = "authResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "student_card_id", property = "studentCardId"),
            @Result(column = "is_student", property = "isStudent"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "is_verified", property = "isVerified"),
            @Result(column = "verified_code", property = "verifiedCode"),
            @Result(column = "id", property = "roles", many = @Many(select = "loadUserRoles"))
    })
    Optional<Users> selectByEmail(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifiedCodeSql")
    @ResultMap("authResultMap")
    Optional<Users> selectByEmailAndVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildVerifySql")
    void verify(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateVerifiedCodeSql")
    boolean updateVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @Select("SELECT * FROM users WHERE email = #{email} AND is_deleted = FALSE AND is_verified = TRUE")
    @ResultMap("authResultMap")
    Optional<Users> loadUserByUsername(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserRolesSql")
    List<Role> loadUserRoles(@Param("userId") Integer userId);
}
