package com.example.devopapi.api.user;

import com.github.pagehelper.Page;
import lombok.Setter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {

    @InsertProvider(type = UserProvider.class, method = "buildInertSql")
    void insert(@Param("u") Users users);

    @SelectProvider(type = UserProvider.class, method = "buildSelectById")
    @Results(id = "userResultMap", value = {
            @Result(column = "student_card_id", property = "studentCardId"),
            @Result(column = "is_student", property = "isStudent")
    })
    Optional<Users> selectById(@Param("id") Integer id);

//    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
//    @ResultMap("userResultMap")
//    Page<Users> select(Pageable pageable);

    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    List<Users> select();

    @Select("SELECT EXISTS(SELECT * FROM users WHERE id = #{id})")
    boolean exitsById(@Param("id") Integer id);

    @DeleteProvider(type = UserProvider.class, method = "buildDeletedByIdSql")
    void deletedById(@Param("id") Integer id);


    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);

}
