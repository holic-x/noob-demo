package com.noob.base.knowledge.controller;

import com.noob.base.knowledge.designMode.AbstractResponseProcessor;
import com.noob.base.knowledge.designMode.RequestContext;
import com.noob.base.knowledge.designMode.ResponseProcessorFactory;
import com.noob.base.knowledge.entity.response.KnowledgeBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/knowledge/facade")
public class KnowledgeFacadeController {

    private ResponseProcessorFactory processorFactory = new ResponseProcessorFactory();

    @GetMapping("/getData/{type}")
    public ResponseEntity<KnowledgeBaseResponse> getData(
            @PathVariable String type,
            @RequestParam Map<String, String> params) {

        RequestContext context = new RequestContext(params);
        AbstractResponseProcessor processor = processorFactory.getProcessor(type);

        KnowledgeBaseResponse response = processor.process(context);
        return ResponseEntity.ok()
                .header("X-Response-Type", type)
                .body(response);
    }
}