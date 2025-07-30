package com.noob.base.knowledge.designMode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ResponseProcessorFactory {

    private Map<String, Supplier<AbstractResponseProcessor>> processors = new HashMap<>();

    public ResponseProcessorFactory() {
        processors.put("database", DatabaseResponseProcessor::new);
        processors.put("es_search", ESSearchResponseProcessor::new);
        processors.put("resource", ResourceResponseProcessor::new);
        processors.put("composite", CompositeResponseProcessor::new);
    }

    public AbstractResponseProcessor getProcessor(String type) {
        Supplier<AbstractResponseProcessor> supplier = processors.get(type.toLowerCase());
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown processor type: " + type);
        }
        return supplier.get();
    }
}