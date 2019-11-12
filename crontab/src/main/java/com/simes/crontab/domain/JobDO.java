package com.simes.crontab.domain;


import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.simes.core.domain.BaseDO;
import org.springframework.context.annotation.Primary;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/23 13:53:33
 */
@Data
@Table(name = "sys_task")
@ApiModel(value = "定时任务表实体类")
public class JobDO implements Serializable {
    private static final long serialVersionUID = -88622021119254311L;

    @Id
    @ApiModelProperty(value = "主键ID")
    private String id;
    @ApiModelProperty(value = "任务名")
    private String jobName;
    @ApiModelProperty(value = "任务描述")
    private String description;
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;
    @ApiModelProperty(value = "包名+类名")
    private String beanClass;
    @ApiModelProperty(value = "任务状态")
    private String jobStatus;
    @ApiModelProperty(value = "任务分组")
    private String jobGroup;
    @ApiModelProperty(value = "创建者")
    private String createUser;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新者")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}