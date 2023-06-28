/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.service.impl;

import com.alipay.custom.customize.biz.manager.dto.UserDTO;
import com.alipay.custom.customize.biz.manager.service.UserService;
import com.alipay.custom.customize.biz.manager.utils.JwtUtils;
import com.alipay.custom.customize.biz.manager.utils.UserServiceUtil;
import com.alipay.custom.customize.common.base.enums.ResultCodeEnum;
import com.alipay.custom.customize.common.base.exception.ProductBizException;
import com.alipay.custom.customize.common.dal.dao.UserMapper;
import com.alipay.custom.customize.common.dal.domain.User;
import com.alipay.custom.customize.common.dal.domain.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruitu.xr
 * @version UserServiceImpl.java, v 0.1 2023年06月12日 12:09 ruitu.xr Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(String userName, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);

        Map<String, Object> map = new HashMap<>();
        //不存在该用户，则返回用户不存在信息
        if (CollectionUtils.isEmpty(users)) {
            map.put("state", false);
            map.put("msg", "用户未注册");
            return map;
        }

        //为该用户生成token，之后接口通过该token进行验证
        Map<String, String> payload = new HashMap<>();
        payload.put("id", String.valueOf(users.get(0).getId()));
        payload.put("userName", users.get(0).getUsername());

        String token = JwtUtils.getToken(payload);

        map.put("state", true);
        map.put("mag", "登录成功");
        map.put("token", token);
        return map;
    }

    @Override
    public void register(UserDTO userDTO) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(userDTO.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            String msg = "注册失败，该用户已存在";
            LOGGER.error(msg);
            throw new ProductBizException(ResultCodeEnum.REGISTER_ERROR, msg);
        }

        User user = UserServiceUtil.convertUserDTO2DO(userDTO);
        try {
            userMapper.insertSelective(user);
        } catch (Exception e) {
            LOGGER.error("用户信息插入数据库异常", e);
            throw new ProductBizException(ResultCodeEnum.INSERT_ERROR, e.getMessage());
        }

    }
}
