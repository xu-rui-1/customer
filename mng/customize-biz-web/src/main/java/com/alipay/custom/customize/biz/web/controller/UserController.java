/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.controller;

import com.alipay.custom.customize.biz.manager.dto.UserDTO;
import com.alipay.custom.customize.biz.manager.service.UserService;
import com.alipay.custom.customize.biz.web.accesscontrol.Permission;
import com.alipay.custom.customize.biz.web.utils.UserConvertUtil;
import com.alipay.custom.customize.biz.web.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户相关接口
 *
 * @author ruitu.xr
 * @version UserController.java, v 0.1 2023年06月12日 12:08 ruitu.xr Exp $
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public boolean register(@RequestBody UserVO userVO) {
        try {
            UserDTO userDTO = UserConvertUtil.convertUserVo2DTO(userVO);
            userService.register(userDTO);
            return true;
        } catch (Exception e) {
            LOGGER.error("注册失败", e);
            return false;
        }
    }
}
