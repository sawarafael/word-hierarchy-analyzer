package com.example.word_hierarchy_analyzer.model;

import java.util.List;

public class HierarchyNode {
    private String name;
    private List<HierarchyNode> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HierarchyNode> getChildren() {
        return children;
    }

    public void setChildren(List<HierarchyNode> children) {
        this.children = children;
    }
}
