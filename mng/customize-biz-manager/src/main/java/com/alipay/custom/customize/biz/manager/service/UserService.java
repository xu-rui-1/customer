package com.alipay.custom.customize.biz.manager.service;

import com.alipay.custom.customize.biz.manager.dto.UserDTO;

import java.util.Map;

/**
 * @author ruitu.xr
 * @version UserService.java, v 0.1 2023年06月12日 12:09 ruitu.xr Exp $
 */
public interface UserService {
    Map<String, Object> login(String userName, String password);

    void register(UserDTO userDTO);
}
