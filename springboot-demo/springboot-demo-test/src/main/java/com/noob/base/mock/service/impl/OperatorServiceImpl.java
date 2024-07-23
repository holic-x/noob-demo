package com.noob.base.mock.service.impl;

import com.noob.base.mock.service.OperatorService;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mul(int a, int b) {
        return a*b;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }
}
