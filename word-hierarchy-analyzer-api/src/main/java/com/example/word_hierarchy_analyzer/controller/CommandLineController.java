package com.example.word_hierarchy_analyzer.controller;

import com.example.word_hierarchy_analyzer.model.AnalysisResult;
import com.example.word_hierarchy_analyzer.service.HierarchyAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CommandLineController implements CommandLineRunner {

    @Autowired
    private HierarchyAnalyzerService analyzerService;

    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/dicts/hierarchy.json";
        int depth = Integer.parseInt(args[1]);
        String phrase = args[3];
        boolean verbose = args.length > 4 && "--verbose".equals(args[4]);

        analyzerService.loadHierarchy(filePath);
        AnalysisResult result = analyzerService.analyzePhrase(phrase, depth);

        result.getWordCount().forEach((word, count) ->
                System.out.println(word + " = " + count));

        if (verbose) {
            System.out.println("Tempo de carregamento dos parâmetros: " + result.getLoadTimeMs() + "ms");
            System.out.println("Tempo de verificação da frase: " + result.getAnalysisTimeMs() + "ms");
        }
    }
}
