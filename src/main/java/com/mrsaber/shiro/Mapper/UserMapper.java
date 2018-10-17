package com.mrsaber.shiro.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT role_name FROM sys_role WHERE role_id IN (SELECT role_id FROM sys_user_role WHERE ur_user_id =1);")
    List<String> getRoleListByUserId(Integer id);

}
