package com.coldchain.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldchain.demo.dao.tblAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ItblAccount extends BaseMapper<tblAccount> {
    @Select("select count(1) from tbl_account where account = #{account}")
    int PselectCbyAccount(String account);
    @Select("select pwd from tbl_account where account=#{account}")
    String PselectPbyAccount(String pwd);
    @Insert("Insert into tbl_account (phone,account,pwd) values(#{phone},#{account},#{pwd})")
    int addAccount(tblAccount tblAccount);
    @Select("select phone from tbl_account where account=#{account}")
    String queryPhone(String username);
}
