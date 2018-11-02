package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.loginlog.model.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName： LoginLogMapper
 * @Auther： 徐世洪
 * @Description: java类作用描述
 * @CreateDate： 2018-10-24
 * @Version: 1.0
 */
@Repository
public interface LoginLogMapper {

    /**
     * 添加登陆信息
     * @param loginLog
     */
    public int saveLoginLog(LoginLog loginLog);

    /**
     * 使用xxzjbh查询xxzjbh
     */
    public String selectLoginLogUser(String xxzjbh);

    /**
     * 按条件搜索登录信息
     * @param loginLog
     * @return
     */
    public List<LoginLog> selectLoginLog(LoginLog loginLog);

    /**
     * 统计总数
     * @param loginLog
     * @return
     */
    public int getLoginLogCount(LoginLog loginLog);
}
