export default async function handler(
  req: { method: string; body: { depth: any; sentence: any; verbose: any } },
  res: {
    status: (arg0: number) => {
      (): any;
      new (): any;
      json: { (arg0: { message?: any; result?: any }): void; new (): any };
    };
  }
) {
  if (req.method !== "POST") {
    return res.status(405).json({ message: "Método não permitido" });
  }

  const { depth, sentence, verbose } = req.body;

  try {
    const apiResponse = await fetch("http://localhost:8080/api/analyze", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ depth, sentence, verbose }),
    });

    if (!apiResponse.ok) {
      throw new Error("Erro ao chamar a API de análise.");
    }

    const data = await apiResponse.json();
    res.status(200).json({ result: data.result });
  } catch (error: any) {
    res.status(500).json({ message: error.message });
  }
}
