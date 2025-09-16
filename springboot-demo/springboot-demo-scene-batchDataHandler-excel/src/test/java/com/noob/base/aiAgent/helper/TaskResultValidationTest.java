package com.noob.base.aiAgent.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4ClassRunner.class)
public class TaskResultValidationTest {

    @Test
    public void testAllSuccessNoEntities() {
        TaskResultValidation<String> result = TaskResultValidation.allSuccess();
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("任务执行全部成功", result.getMessage());
        Assert.assertNotNull(result.getSuccessList());
        Assert.assertTrue(result.getSuccessList().isEmpty());
        Assert.assertNotNull(result.getFailList());
        Assert.assertTrue(result.getFailList().isEmpty());
        Assert.assertEquals(result.getSuccessList(), result.getAllEntities());
        Assert.assertTrue(result.toString().contains("success=true"));
    }

    @Test
    public void testAllSuccessWithEntities() {
        List<String> successEntities = Arrays.asList("A", "B");
        TaskResultValidation<String> result = TaskResultValidation.allSuccess(successEntities, "全部OK");
        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(result.getMessage().contains("全部OK"));
        Assert.assertEquals(successEntities, result.getSuccessList());
        Assert.assertNotNull(result.getFailList());
        Assert.assertTrue(result.getFailList().isEmpty());
        Assert.assertEquals(successEntities, result.getAllEntities());
        Assert.assertTrue(result.toString().contains("success=true"));
    }

    @Test
    public void testPartialSuccess() {
        List<String> successEntities = Arrays.asList("A", "B");
        List<String> failEntities = Arrays.asList("C");
        TaskResultValidation<String> result = TaskResultValidation.partialSuccess(successEntities, failEntities, "部分成功");
        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(result.getMessage().contains("部分成功"));
        Assert.assertEquals(successEntities, result.getSuccessList());
        Assert.assertEquals(failEntities, result.getFailList());
        List<String> all = result.getAllEntities();
        Assert.assertEquals(3, all.size());
        Assert.assertTrue(all.containsAll(Arrays.asList("A", "B", "C")));
        Assert.assertTrue(result.toString().contains("success=true"));
    }

    @Test
    public void testFailureNoEntities() {
        TaskResultValidation<String> result = TaskResultValidation.failure("失败啦");
        Assert.assertFalse(result.isSuccess());
        Assert.assertTrue(result.getMessage().contains("失败啦"));
        Assert.assertNotNull(result.getSuccessList());
        Assert.assertTrue(result.getSuccessList().isEmpty());
        Assert.assertNotNull(result.getFailList());
        Assert.assertTrue(result.getFailList().isEmpty());
        Assert.assertTrue(result.getAllEntities().isEmpty());
        Assert.assertTrue(result.toString().contains("success=false"));
    }

    @Test
    public void testFailureWithEntities() {
        List<String> failEntities = Arrays.asList("X", "Y");
        TaskResultValidation<String> result = TaskResultValidation.failure(failEntities, "有失败");
        Assert.assertFalse(result.isSuccess());
        Assert.assertTrue(result.getMessage().contains("有失败"));
        Assert.assertNotNull(result.getSuccessList());
        Assert.assertTrue(result.getSuccessList().isEmpty());
        Assert.assertEquals(failEntities, result.getFailList());
        Assert.assertEquals(failEntities, result.getAllEntities());
        Assert.assertTrue(result.toString().contains("success=false"));
    }

    @Test
    public void testImmutabilityOfLists() {
        List<String> successEntities = new ArrayList<>();
        successEntities.add("A");
        TaskResultValidation<String> result = TaskResultValidation.allSuccess(successEntities, "msg");
        // 修改原始list不影响内部list
        successEntities.clear();
        Assert.assertEquals(1, result.getSuccessList().size());
    }
}