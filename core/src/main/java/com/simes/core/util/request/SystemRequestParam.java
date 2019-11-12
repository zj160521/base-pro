package com.simes.core.util.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@ApiModel(value="统一请求对象封装",description = "")
public class SystemRequestParam<T> implements Serializable{

    /**
	 * 序列化相关
	 */
	private static final long serialVersionUID = -986313544438254258L;
	/**
     * 请求自定义header
     */
    @ApiModelProperty(value = "请求自定义header", name = "headers")
    @Valid
    private SystemRequestHeader headers = new SystemRequestHeader();
    /**
     * 自定义pageBean对象
     */
    @ApiModelProperty(value = "请求pageBean对象", name = "pageBean")
    @Valid
    private SystemRequestPageBean pageBean;
    /**
     * 请求body
     */
    @ApiModelProperty(value = "请求body对象", name = "body")
    @Valid
    private T body;
    
    public SystemRequestHeader getHeaders() {
        return headers;
    }

    public void setHeaders(SystemRequestHeader headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

	public SystemRequestPageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(SystemRequestPageBean pageBean) {
		this.pageBean = pageBean;
	}
}
