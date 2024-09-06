import { FormEvent, useState } from "react";
import {
  AnalysisRequest,
  AnalysisResult,
} from "../../base/interfaces/Analysis";

interface FormProps {
  onAnalyze: (data: AnalysisRequest) => Promise<AnalysisResult | null>;
}

export default function Form({ onAnalyze }: FormProps) {
  const [depth, setDepth] = useState<number>(2);
  const [sentence, setSentence] = useState<string>("");
  const [verbose, setVerbose] = useState<boolean>(false);
  const [result, setResult] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const analysisData: AnalysisRequest = { depth, sentence, verbose };
      const data = await onAnalyze(analysisData);
      if (data) setResult(data.result);
    } catch (err: any) {
      setError("Erro ao realizar a análise.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="sentence">Frase para Análise:</label>
          <input
            id="sentence"
            type="text"
            value={sentence}
            onChange={(e) => setSentence(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="depth">Profundidade:</label>
          <input
            id="depth"
            type="number"
            value={depth}
            onChange={(e) => setDepth(Number(e.target.value))}
            min={1}
            required
          />
        </div>
        <div>
          <label htmlFor="verbose">Verbose:</label>
          <input
            id="verbose"
            type="checkbox"
            checked={verbose}
            onChange={() => setVerbose(!verbose)}
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Analisando..." : "Analisar"}
        </button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}
      {result && <pre>{result}</pre>}
    </div>
  );
}
