package com.simes.activiti.controller;

import com.simes.activiti.domain.TestPara;
import com.simes.core.util.request.SystemRequestParam;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/25 13:45
 */
@RestController
@RequestMapping("/activiti")
public class ActivitiController {
    @Autowired
    private ProcessEngine processEngine;
    protected final static Logger LOGGER = LoggerFactory.getLogger(ActivitiController.class);
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(@RequestBody SystemRequestParam<TestPara> requestParam) throws ParseException {
        TestPara para = requestParam.getBody();
        deploy("审批", "AskForLeave.bpmn");
        if (para.getProcessDefinitionKey() == null ) {
            return "部署流程Key不能为空！";
        }
        ProcessInstance processInstance = start(para.getProcessDefinitionKey(), para.getUserId());
        if (processInstance == null) {
            return "没有待处理的流程！";
        }
        processTask(processInstance, para.getUserId());

        return "ok";
    }

    private void processTask(ProcessInstance processInstance, String userId) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            TaskService taskService = processEngine.getTaskService();
            List<Task> taskList = taskService
                    .createTaskQuery()
                    .processInstanceId(processInstance.getProcessInstanceId())
//                    .taskAssignee(userId)
                    .list();
            for (Task task : taskList) {
                System.out.printf("taskid : %s", task.getId());
                System.out.println();
                FormService formService = processEngine.getFormService();
                TaskFormData taskFormData = formService.getTaskFormData(task.getId());
                List<FormProperty> formProperties = taskFormData.getFormProperties();
                HashMap<String, Object> varMap = getVariableMap(scanner, formProperties);
                taskService.complete(task.getId(), varMap);
                processInstance = processEngine
                        .getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getProcessInstanceId())
                        .singleResult();
            }
        }
    }

    private HashMap<String, Object> getVariableMap(Scanner scanner, List<FormProperty> formProperties) throws ParseException {
        String line = null;
        HashMap<String, Object> varMap = new HashMap<>(16);
        for (FormProperty formProperty : formProperties) {
            if (StringFormType.class.isInstance(formProperty.getType())) {
                System.out.printf("请输入%s : ", formProperty.getName());
                System.out.println();
                line = scanner.nextLine();
                varMap.put(formProperty.getId(), line);
            } else if (DateFormType.class.isInstance(formProperty.getType())) {
                System.out.printf("请输入%s 格式（yyyy-MM-dd）: ", formProperty.getName());
                System.out.println();
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                varMap.put(formProperty.getId(), dateFormat.parse(line));
            } else {
                LOGGER.info("{}类型暂不支持", formProperty.getType());
            }
            LOGGER.info("您输入的内容是【{}】", line);
        }
        return varMap;
    }

    private ProcessInstance start(String processDefinitionKey, String userId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .singleResult();
        if (instance == null) {
            HashMap<String, Object> variableMap = new HashMap<>(16);
            variableMap.put("user", userId);
            instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);
        } else if (instance != null) {
            System.out.printf("流程实例--id: %s,key: %s, name: %s",
                    instance.getProcessDefinitionId(), instance.getProcessDefinitionKey(),instance.getProcessDefinitionName());
            System.out.println();
            return instance;
        }
        return null;
    }

    private void deploy(String name, String bpmn) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        Deployment deployment = repositoryService
//                .createDeployment()
//                .name(name)
////                .key("vacation")
//                //从classpath的资源中加载，一次只能加载一个文件
//                .addClasspathResource(bpmn)
////                .addClasspathResource("AskForLeave.png")
//                .deploy();
//        LOGGER.info("部署id：{}", deployment.getId());
//        LOGGER.info("部署名称：{}", deployment.getName());
//        LOGGER.info("部署key：{}", deployment.getKey());
//        查询model列表
        List<Model> modelList = repositoryService
                .createModelQuery()
                .list();
        for (Model model : modelList) {
            System.out.printf("model   -- id: %s, name: %s",model.getId(), model.getName());
            System.out.println();
        }
//        查询部署的流程
        List<ProcessDefinition> processDefinitionList = repositoryService
                .createProcessDefinitionQuery()
                .latestVersion()
                .orderByDeploymentId()
                .desc()
                .list();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            System.out.printf("process -- id: %s, name: %s, key: %s", processDefinition.getId()
                    , processDefinition.getName(),processDefinition.getKey());
            System.out.println();
//            挂起/激活流程
//            repositoryService.suspendProcessDefinitionByKey(processDefinition.getKey());
//            repositoryService.activateProcessDefinitionByKey(processDefinition.getKey());
        }
    }
}
