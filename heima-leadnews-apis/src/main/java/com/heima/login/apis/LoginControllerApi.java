package com.heima.login.apis;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;

/**
 * LoginControllerApi
 *
 * @author Paulson
 * @date 2020/2/14 21:04
 */
public interface LoginControllerApi {

    ResponseResult login(ApUser user);
}
