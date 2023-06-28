/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.controller;

import com.alipay.custom.customize.biz.manager.config.RabbitMqConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version RabbitMqTestController.java, v 0.1 2023年06月20日 17:33 ruitu.xr Exp $
 */
@RestController
@Api(tags = "RabbitMq测试")
@RequestMapping("/rabbitmq")
public class RabbitMqTestController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    @ApiOperation("rabbitMq发送消息")
    public void sendMessage() {
        String msg = "This is a test";
        System.out.println(msg);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY, msg, new CorrelationData());
    }
}
