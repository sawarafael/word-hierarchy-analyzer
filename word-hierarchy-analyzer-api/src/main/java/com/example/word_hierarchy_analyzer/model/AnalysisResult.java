package com.example.word_hierarchy_analyzer.model;

import java.util.Map;

public class AnalysisResult {
    private Map<String, Integer> wordCount;
    private long loadTimeMs;
    private long analysisTimeMs;

    public Map<String, Integer> getWordCount() {
        return wordCount;
    }

    public void setWordCount(Map<String, Integer> wordCount) {
        this.wordCount = wordCount;
    }

    public long getLoadTimeMs() {
        return loadTimeMs;
    }

    public void setLoadTimeMs(long loadTimeMs) {
        this.loadTimeMs = loadTimeMs;
    }

    public long getAnalysisTimeMs() {
        return analysisTimeMs;
    }

    public void setAnalysisTimeMs(long analysisTimeMs) {
        this.analysisTimeMs = analysisTimeMs;
    }

    public void addWordCount(String animal, int i) {

    }
}
