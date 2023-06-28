/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.common.base.constant;

/**
 * @author ruitu.xr
 * @version ProductConstant.java, v 0.1 2023年06月21日 17:44 ruitu.xr Exp $
 */
public class ProductConstant {

    public static final String MAPPING_TEMPLATE = "{\n"
            + "  \"mappings\": {\n"
            + "    \"properties\": {\n"
            + "      \"id\":{\n"
            + "        \"type\": \"keyword\"\n"
            + "      },\n"
            + "      \"productName\": {\n"
            + "        \"type\": \"text\",\n"
            + "        \"analyzer\": \"ik_max_word\",\n"
            + "        \"copy_to\": \"all\"\n"
            + "      },\n"
            + "      \"productPrice\": {\n"
            + "        \"type\": \"double\",\n"
            + "        \"copy_to\": \"all\"\n"
            + "      },\n"
            + "      \"operator\": {\n"
            + "        \"type\": \"keyword\",\n"
            + "        \"index\": false\n"
            + "      },\n"
            + "      \"gmtCreate\": {\n"
            + "        \"type\":\"date\",\n"
            + "        \"copy_to\": \"all\"\n"
            + "      },\n"
            + "      \"gmtModified\": {\n"
            + "        \"type\":\"date\",\n"
            + "        \"copy_to\": \"all\"\n"
            + "      },\n"
            + "      \"all\": {\n"
            + "        \"type\": \"text\",\n"
            + "        \"analyzer\": \"ik_max_word\"\n"
            + "      }\n"
            + "    }\n"
            + "  }\n"
            + "}";
}
