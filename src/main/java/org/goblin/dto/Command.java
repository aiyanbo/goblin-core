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
    private Integer contextIndex;
    private List<String> responses;
    private Map<String, String> mapping;
    private Map<String, String> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContextIndex() {
        return contextIndex;
    }

    public void setContextIndex(Integer contextIndex) {
        this.contextIndex = contextIndex;
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

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
