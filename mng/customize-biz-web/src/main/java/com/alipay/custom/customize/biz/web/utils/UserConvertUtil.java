/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.utils;

import com.alipay.custom.customize.biz.manager.dto.UserDTO;
import com.alipay.custom.customize.biz.web.vo.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * @author ruitu.xr
 * @version UserConvertUtil.java, v 0.1 2023年06月12日 12:49 ruitu.xr Exp $
 */
public class UserConvertUtil {
    public static UserDTO convertUserVo2DTO(UserVO userVO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO, userDTO);
        return userDTO;
    }
}
