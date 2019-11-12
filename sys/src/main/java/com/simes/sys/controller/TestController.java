package com.simes.sys.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.simes.core.controller.BaseController;
import com.simes.core.domain.test.TestDO;
import com.simes.core.domain.test.TestPara;
import com.simes.core.util.request.SystemRequestParam;
import com.simes.core.util.response.BaseResponse;
import com.simes.sys.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@RestController
@RequestMapping("/test")
@Api(tags = "test",description = "测试相关接口")
public class TestController extends BaseController {
    @Autowired
    private UserService service;
    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiOperation(value = "获取测试信息", notes = "参数可传name(测试名称)、logTime(记录时间)")
    public BaseResponse get(@RequestBody SystemRequestParam<TestPara> requestParam) {
        LOGGER.info("get测试信息！");
        List<TestDO> list = service.getInfo(requestParam);
        return BaseResponse.success(list);
    }

    @RequestMapping(value = "/insertMongoMsg", method = RequestMethod.POST)
    @ApiOperation(value = "插入测试信息", notes = "")
    public BaseResponse insertMongoMsg(@RequestBody SystemRequestParam<TestPara> requestParam) {
        LOGGER.info("mongo测试信息插入！");
        MongoCollection<Document> collection = mongoTemplate.getCollection("test_mongo");
        collection.insertOne(new Document("name", "test").
                append("age", 20));
        FindIterable<Document> findIterable = collection.find();
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            Document document = (Document)cursor.next();
            Object name = document.get("name");
            System.out.println(name.toString());
        }
        return BaseResponse.success();
    }

}
