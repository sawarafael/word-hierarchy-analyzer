package com.example.service;

import com.example.word_hierarchy_analyzer.model.HierarchyNode;
import com.example.word_hierarchy_analyzer.model.AnalysisResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HierarchyAnalyzerService {
    private HierarchyNode root;

    public void loadHierarchy(String filePath) throws IOException {
        long startTime = System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        root = mapper.readValue(new File(filePath), HierarchyNode.class);
        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("Tempo de carregamento dos parâmetros: " + loadTime + "ms");
    }

    public AnalysisResult analyzePhrase(String phrase, int depth) {
        long startTime = System.currentTimeMillis();
        Map<String, Integer> wordCount = new HashMap<>();
        // Implementar a lógica de análise aqui
        long analysisTime = System.currentTimeMillis() - startTime;
        AnalysisResult result = new AnalysisResult();
        result.setWordCount(wordCount);
        result.setLoadTimeMs(0); // Defina o tempo de carregamento aqui
        result.setAnalysisTimeMs(analysisTime);
        return result;
    }
}
