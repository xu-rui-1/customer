package com.alipay.custom.customize.biz.manager.service.es;

import com.alipay.custom.customize.biz.manager.dto.HotelDTO;
import com.alipay.custom.customize.common.base.web.PageResult;
import com.alipay.custom.customize.common.base.web.RequestParams;

import java.util.List;

/**
 * @author ruitu.xr
 * @version HotelService.java, v 0.1 2023年06月28日 16:10 ruitu.xr Exp $
 */
public interface HotelService {
    PageResult<List<HotelDTO>> search(RequestParams params);

    PageResult<List<HotelDTO>> searchLocal();
}
