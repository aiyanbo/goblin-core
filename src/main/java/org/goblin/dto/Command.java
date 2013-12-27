package org.goblin.dto;

import java.util.List;
import java.util.Map;

/**
 * Component:
 * Description:
 * Date: 13-12-27
 *
 * @author Andy Ai
 */
public class Command {
    private String name;
    private List<String> responses;
    private Map<String, String> mapping;
    private Map<String, String> context;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
