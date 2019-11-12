package com.simes.core.controller;

import com.github.pagehelper.PageHelper;
import com.simes.core.util.request.SystemRequestPageBean;
import com.simes.core.util.request.SystemRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class BaseController {
    protected final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    public void page(SystemRequestParam requestParam) {
        SystemRequestPageBean pageBean = requestParam.getPageBean();
        PageHelper.startPage(pageBean.getPage(),pageBean.getPageSize());
        PageHelper.orderBy("create_time desc");
    }
}
