package com.noob.base.mq.service.impl;

import com.noob.base.mq.service.FlowService;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements FlowService {
    @Override
    public void handler() {
        System.out.println("flow service handle ......");
    }
}
