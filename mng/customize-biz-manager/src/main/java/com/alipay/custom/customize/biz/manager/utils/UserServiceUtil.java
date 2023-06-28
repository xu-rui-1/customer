/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.utils;

import com.alipay.custom.customize.biz.manager.dto.UserDTO;
import com.alipay.custom.customize.common.dal.domain.User;
import org.springframework.beans.BeanUtils;

/**
 * @author ruitu.xr
 * @version UserServiceUtil.java, v 0.1 2023年06月12日 12:52 ruitu.xr Exp $
 */
public class UserServiceUtil {
    public static User convertUserDTO2DO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
