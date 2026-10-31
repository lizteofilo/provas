import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Disciplina {

    public static final String PASTA = "disciplinas";
    public static final String PASTA_RESULTADOS = "resultados";

    private String nome;
    private List<Aluno> alunos = new ArrayList<>();

    public Disciplina(String nome) {
        this.nome = nome.trim();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    
    public void salvar() throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(PASTA + "/" + nome + ".txt"), "UTF-8"));
        for (Aluno a : alunos) {
            out.println(a.getRespostas() + "\t" + a.getNome());
        }
        out.close();
    }

    
    public static Disciplina carregar(String nome) throws IOException {
        Disciplina d = new Disciplina(nome);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(PASTA + "/" + nome + ".txt"), "UTF-8"));
        String linha;
        while ((linha = in.readLine()) != null) {
            linha = linha.replace("\uFEFF", ""); 
            if (linha.trim().isEmpty()) continue;
            String[] partes = linha.split("\t");
            d.adicionarAluno(new Aluno(partes[1], partes[0]));
        }
        in.close();
        return d;
    }

    
    public List<Nota> corrigir(String gabarito) {
        List<Nota> notas = new ArrayList<>();
        for (Aluno a : alunos) {
            int acertos = Nota.calcular(a.getRespostas(), gabarito);
            notas.add(new Nota(a.getNome(), nome, acertos));
        }
        return notas;
    }

   
    public void gerarResultado(String gabarito) throws IOException {
        List<Nota> notas = corrigir(gabarito);

        
        Collections.sort(notas, (a, b) -> a.getAluno().compareTo(b.getAluno()));
        String arqAlfabetico = PASTA_RESULTADOS + "/" + nome + "_alfabetica.txt";
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(arqAlfabetico), "UTF-8"));
        System.out.println("\n--- " + nome + " (ordem alfabética) ---");
        for (Nota n : notas) {
            out.println(n.getAluno() + "\t" + n.getValor());
            System.out.printf("%-30s%d%n", n.getAluno(), n.getValor()); 
        }
        out.close();

        
        Collections.sort(notas, (a, b) -> b.getValor() - a.getValor());
        double soma = 0;
        String arqNotas = PASTA_RESULTADOS + "/" + nome + "_notas.txt";
        out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(arqNotas), "UTF-8"));
        System.out.println("\n--- " + nome + " (ordem de nota) ---");
        for (Nota n : notas) {
            out.println(n.getAluno() + "\t" + n.getValor());
            System.out.printf("%-30s%d%n", n.getAluno(), n.getValor());
            soma += n.getValor();
        }
        double media = notas.isEmpty() ? 0 : soma / notas.size();
        out.println();
        out.printf("Média da turma: %.2f%n", media);
        out.close();
        System.out.printf("Média da turma: %.2f%n", media);

        System.out.println("\nArquivos gerados:");
        System.out.println(new File(arqAlfabetico).getAbsolutePath());
        System.out.println(new File(arqNotas).getAbsolutePath());
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}