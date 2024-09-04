package com.example.word_hierarchy_analyzer.controller;

import com.example.word_hierarchy_analyzer.model.AnalysisResult;
import com.example.word_hierarchy_analyzer.service.HierarchyAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Map;

@Controller
public class CommandLineController implements CommandLineRunner {

    private final HierarchyAnalyzerService analyzerService;

    public CommandLineController(HierarchyAnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 3 || !args[0].equals("analyze")) {
            System.out.println("Comando inválido. Uso correto: java -jar cli.jar analyze --depth <n> \"{frase}\" --verbose (opcional)");
            return;
        }

        long startParamTime = System.currentTimeMillis();

        int depth = 0;
        try {
            int depthIndex = Arrays.asList(args).indexOf("--depth");
            if (depthIndex == -1 || depthIndex + 1 >= args.length) {
                throw new IllegalArgumentException("O parâmetro --depth é obrigatório.");
            }
            depth = Integer.parseInt(args[depthIndex + 1]);
        } catch (NumberFormatException e) {
            System.out.println("Erro: O valor de profundidade (--depth) deve ser um número inteiro.");
            return;
        }

        StringBuilder sentenceBuilder = new StringBuilder();
        for (String arg : args) {
            if (!arg.startsWith("--") && !arg.equals("analyze") && !arg.equals(String.valueOf(depth))) {
                sentenceBuilder.append(arg).append(" ");
            }
        }
        String sentence = sentenceBuilder.toString().trim();

        if (sentence.isEmpty()) {
            System.out.println("Erro: A frase para análise não foi encontrada.");
            return;
        }

        boolean verbose = Arrays.asList(args).contains("--verbose");
        long paramLoadingTime = System.currentTimeMillis() - startParamTime;
        long startVerifyTime = System.currentTimeMillis();

        AnalysisResult analysisResult = analyzerService.analyzePhrase(sentence, depth);

        long sentenceVerifyTime = System.currentTimeMillis() - startVerifyTime;

        System.out.println("Resultado da análise:");
        analysisResult.getWordCount().forEach((word, count) -> System.out.println(word + " = " + count));

        if (verbose) {
            System.out.println("Tempo de carregamento dos parâmetros: " + paramLoadingTime + "ms");
            System.out.println("Tempo de verificação da frase: " + sentenceVerifyTime + "ms");
        }
    }
}


