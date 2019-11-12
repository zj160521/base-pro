package com.simes.core.util.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@ApiModel(value="统一分页PageBean对象")
public class SystemRequestPageBean implements Serializable{
	/**
	 * 序列化相关
	 */
	private static final long serialVersionUID = 3798899846518791927L;
	@ApiModelProperty(value = "分页请求第几页", name = "page",example="1")
	@Min(value = 1, message = "分页从1开始")
	private Integer page=1;
	@ApiModelProperty(value = "分页请求每页数量", name = "pageSize",example="10")
	@Min(value = 1, message = "每页数量不能小于1")
	private Integer pageSize=10;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
