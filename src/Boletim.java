import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Boletim {

    private String aluno;
    private List<Nota> notas = new ArrayList<>();

    public Boletim(String aluno) {
        this.aluno = aluno;
    }

    public void adicionarNota(Nota nota) {
        notas.add(nota);
    }

    public double getMedia() {
        if (notas.isEmpty()) return 0;
        double soma = 0;
        for (Nota n : notas) {
            soma += n.getValor();
        }
        return soma / notas.size();
    }

    public String getResultado() {
        return getMedia() >= 7 ? "APROVADO" : "REPROVADO";
    }

    
    public String gerarTexto() {
        int largura = "Disciplina".length();
        for (Nota n : notas) {
            largura = Math.max(largura, n.getDisciplina().length());
        }
        largura += 4;
        String formato = "%-" + largura + "s%s\n";
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno: ").append(aluno).append("\n\n");
        sb.append(String.format(formato, "Disciplina", "Nota"));
        for (Nota n : notas) {
            sb.append(String.format(formato, n.getDisciplina(), n.getValor()));
        }
        sb.append("\nMédia: ").append(String.format("%.2f", getMedia())).append("\n");
        sb.append("Resultado: ").append(getResultado()).append("\n");
        return sb.toString();
    }

    
    public void salvar(String pasta) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(pasta + "/" + aluno + ".txt"), "UTF-8"));
        out.print(gerarTexto());
        out.close();
    }

    public String getAluno() {
        return aluno;
    }
}