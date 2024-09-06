package com.example.word_hierarchy_analyzer.controller;

import com.example.word_hierarchy_analyzer.model.AnalysisRequest;
import com.example.word_hierarchy_analyzer.model.AnalysisResult;
import com.example.word_hierarchy_analyzer.service.FrontendAnalyzerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/frontend")
public class FrontendAnalyzerController {

    private final FrontendAnalyzerService analyzerService;

    public FrontendAnalyzerController(FrontendAnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeSentence(@RequestBody AnalysisRequest request) {
        if (request.getSentence() == null || request.getSentence().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        AnalysisResult analysisResult = analyzerService.analyzePhrase(request.getSentence(), request.getDepth());

        return ResponseEntity.ok(analysisResult);
    }
}
