import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeradorBoletim {

    private String pastaResultados;
    private String pastaBoletins;

    public GeradorBoletim(String pastaResultados, String pastaBoletins) {
        this.pastaResultados = pastaResultados;
        this.pastaBoletins = pastaBoletins;
    }

    
    public void gerar() throws IOException {
        File[] arquivos = new File(pastaResultados).listFiles(
                (dir, nome) -> nome.endsWith("_alfabetica.txt"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum resultado encontrado. Gere os resultados das disciplinas primeiro.");
            return;
        }

        Map<String, Boletim> boletins = new LinkedHashMap<>();

        for (File arq : arquivos) {
            String disciplina = arq.getName().replace("_alfabetica.txt", "");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(arq), "UTF-8"));
            String linha;
            while ((linha = in.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split("\t");
                String aluno = partes[0];
                int valor = Integer.parseInt(partes[1].trim());

                if (!boletins.containsKey(aluno)) {
                    boletins.put(aluno, new Boletim(aluno));
                }
                boletins.get(aluno).adicionarNota(new Nota(aluno, disciplina, valor));
            }
            in.close();
        }

        for (Boletim b : boletins.values()) {
            b.salvar(pastaBoletins);
            System.out.println("------------------------------");
            System.out.print(b.gerarTexto());
        }
        System.out.println("------------------------------");
        System.out.println(boletins.size() + " boletins gerados na pasta " + pastaBoletins);
    }
}
