package com.example.word_hierarchy_analyzer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HierarchyNode {
    private String name;
    private List<HierarchyNode> children;
}
