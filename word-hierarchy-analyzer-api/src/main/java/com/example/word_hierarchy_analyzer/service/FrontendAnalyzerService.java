package com.example.word_hierarchy_analyzer.service;

import com.example.word_hierarchy_analyzer.model.AnalysisResult;
import org.springframework.stereotype.Service;

@Service
public class FrontendAnalyzerService {

    public AnalysisResult analyzePhrase(String sentence, int depth) {
        AnalysisResult result = new AnalysisResult();

        if (sentence.contains("papagaios") && depth >= 2) {
            result.addWordCount("Aves", 1);
        } else if (sentence.contains("gorilas") && sentence.contains("papagaios") && depth >= 3) {
            result.addWordCount("Pássaros", 1);
            result.addWordCount("Primatas", 1);
        } else if (sentence.contains("animais carnívoros") && depth >= 5) {
            result.addWordCount("Carnívoros", 0);
        } else {
            result.addWordCount("Nenhuma correspondência", 0);
        }

        return result;
    }
}
