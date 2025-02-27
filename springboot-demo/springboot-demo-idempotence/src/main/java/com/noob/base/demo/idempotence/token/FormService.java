package com.noob.base.demo.idempotence.token;

import com.noob.base.demo.model.entity.FormData;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ⑤ 基于【防重token验证】实现幂等性
 */
public class FormService {
    private Set<String> usedTokens = new HashSet<>();

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void submitForm(String token, FormData data) {
        if (usedTokens.contains(token)) {
            throw new RuntimeException("表单已提交");
        }
        saveFormData(data); // 保存表单数据
        usedTokens.add(token); // 记录Token
    }

    // 模拟保存表单数据
    private void saveFormData(FormData data) {
        // ... 保存表单数据 ...
    }
}