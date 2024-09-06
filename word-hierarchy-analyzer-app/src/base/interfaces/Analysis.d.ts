export interface AnalysisRequest {
  depth: number;
  sentence: string;
  verbose: boolean;
}

export interface AnalysisResult {
  result: string;
}
