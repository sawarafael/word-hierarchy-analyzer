"use client";
import { AnalysisRequest, AnalysisResult } from "@app/base/interfaces/Analysis";
import { Button, Checkbox, Input, Spacer } from "@nextui-org/react";
import { FormEvent, useState } from "react";

interface FormProps {
  onAnalyze: (data: AnalysisRequest) => Promise<AnalysisResult | null>;
}

export default function Form({ onAnalyze }: FormProps) {
  const [depth, setDepth] = useState<number>(2);
  const [sentence, setSentence] = useState<string>("");
  const [verbose, setVerbose] = useState<boolean>(true);
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
    <div
      style={{
        padding: "20px",
        maxWidth: "750px",
        margin: "0 auto",
        textAlign: "center",
        background: "gray",
        borderRadius: "10px",
      }}
    >
      <h1>Analisador de Palavras por Hierarquia</h1>
      <form
        onSubmit={handleSubmit}
        style={{
          display: "flex",
          flexDirection: "column",
          border: "1px solid white",
          borderRadius: "5px",
          padding: "20px",
          margin: "0 auto",
          width: "90%",
          backgroundColor: "white",
          gap: "1rem",
        }}
      >
        <div>
          <Input
            label="Frase para Análise"
            placeholder="Digite a frase"
            value={sentence}
            onChange={(e) => setSentence(e.target.value)}
            required
            fullWidth
            style={{
              color: "dark",
              border: "1px solid white",
              borderRadius: "5px",
              borderColor: "gray",
              padding: "10px",
              width: "70%",
            }}
          />
        </div>
        <Spacer y={1} />
        <div>
          <Input
            label="Profundidade da Análise"
            type="number"
            value={String(depth)}
            onChange={(e: any) => setDepth(e.target.value)}
            min="1"
            required
            fullWidth
            style={{
              color: "dark",
              border: "1px solid white",
              borderRadius: "5px",
              borderColor: "gray",
              padding: "10px",
              width: "70%",
            }}
          />
        </div>
        <Spacer y={1} />
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            border: "1px solid white",
            borderRadius: "5px",
            padding: "20px",
            margin: "0 auto",
            width: "10%",
          }}
        >
          <Checkbox
            size="md"
            isSelected={verbose}
            onChange={() => setVerbose(!verbose)}
          >
            Verbose
          </Checkbox>
        </div>
        <Spacer y={1.5} />
        <Button
          type="submit"
          disabled={loading}
          style={{
            width: "30%",
            margin: "0 auto",
            padding: "10px",
            borderRadius: "5px",
            backgroundColor: "gray",
            color: "white",
            borderColor: "white",
            border: "1px solid white",
            cursor: "pointer",
            transition: "all 0.3s ease-in-out",
          }}
        >
          {loading ? "Analisando..." : "Analisar"}
        </Button>
      </form>

      {error && (
        <h1
          style={{
            color: "#CE2029",
            marginTop: "20px",
            textAlign: "center",
            fontWeight: "bold",
          }}
        >
          {error}
        </h1>
      )}
      {result && (
        <div style={{ marginTop: "20px" }}>
          <h2 style={{ color: "white" }}>Resultado:</h2>
          <pre style={{ color: "white" }}>{result}</pre>
        </div>
      )}
    </div>
  );
}
