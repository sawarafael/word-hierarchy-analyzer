package com.example.word_hierarchy_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordHierarchyAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordHierarchyAnalyzerApplication.class, args);

		if (args.length > 0) {
			String sentence = args[1];

			System.out.println(sentence);
		}
	}


}
