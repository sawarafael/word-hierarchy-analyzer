package com.example.word_hierarchy_analyzer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AnalysisResult {
    private Map<String, Integer> wordCount;
    private long loadTimeMs;
    private long analysisTimeMs;
}
