package com.simes.core.domain.test;

import com.simes.core.domain.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/6/21 17:56
 */
@Data
@Table(name = "sys_test")
@ApiModel(value = "测试类")
public class TestDO extends BaseDO {

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "日志时间")
    private LocalDateTime logTime;
}
