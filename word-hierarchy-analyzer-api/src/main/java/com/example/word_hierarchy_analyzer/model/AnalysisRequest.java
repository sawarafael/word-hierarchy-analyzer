package com.example.word_hierarchy_analyzer.model;

public class AnalysisRequest {
    private String sentence;
    private int depth;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
