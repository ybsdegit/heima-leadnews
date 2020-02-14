package com.heima.login.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;

/**
 * ApUserLoginService
 *
 * @author Paulson
 * @date 2020/2/14 20:27
 */
public interface ApUserLoginService {

    /**
     * 根据用户名和密码验证
     * @param user
     * @return
     */
    ResponseResult loginAuth(ApUser user);
}
