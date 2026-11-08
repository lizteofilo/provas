import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final String PASTA_GABARITOS = "gabaritos";
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        
        new File(Disciplina.PASTA).mkdirs();
        new File(Disciplina.PASTA_RESULTADOS).mkdirs();
        new File("boletins").mkdirs();
        new File(PASTA_GABARITOS).mkdirs();

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Criar disciplina");
            System.out.println("2 - Gerar resultado de uma disciplina");
            System.out.println("3 - Gerar boletins");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1; 
            }

            try {
                switch (opcao) {
                    case 1: criarDisciplina(); break;
                    case 2: gerarResultado(); break;
                    case 3: new GeradorBoletim("resultados", "boletins").gerar(); break;
                    case 0: System.out.println("Até mais!"); break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao acessar arquivo: " + e.getMessage());
            }
        } while (opcao != 0);
    }

   
    static void criarDisciplina() throws IOException {
        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = teclado.nextLine().trim();
        while (nomeDisciplina.isEmpty()) {
            System.out.print("O nome não pode ficar vazio. Nome da disciplina: ");
            nomeDisciplina = teclado.nextLine().trim();
        }
        Disciplina d = new Disciplina(nomeDisciplina);

        File arquivoGabarito = new File(PASTA_GABARITOS, nomeDisciplina + ".txt");
        System.out.println("\n --- Cadastro do Gabarito ---");
        criarGabarito(arquivoGabarito);

        System.out.println("Cadastre os alunos (deixe o nome em branco para terminar).");
        while (true) {
            System.out.print("Nome do aluno: ");
            String nome = teclado.nextLine();
            if (nome.trim().isEmpty()) break;

            System.out.print("Respostas (10 letras V ou F): ");
            String respostas = teclado.nextLine();
            while (!Aluno.respostasValidas(respostas)) {
                System.out.print("Inválido! Digite exatamente 10 letras V ou F: ");
                respostas = teclado.nextLine();
            }
            d.adicionarAluno(new Aluno(nome, respostas));
        }

        d.salvar();
        System.out.println("Disciplina salva em " + Disciplina.PASTA + "/" + d.getNome() + ".txt");
    }

    
    static void gerarResultado() throws IOException {
        File[] arquivos = new File(Disciplina.PASTA).listFiles();
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        Arrays.sort(arquivos); 

        System.out.println("Disciplinas:");
        for (int i = 0; i < arquivos.length; i++) {
            System.out.println((i + 1) + " - " + arquivos[i].getName().replace(".txt", ""));
        }
        System.out.print("Escolha: ");
        int escolha;
        try {
            escolha = Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            escolha = 0;
        }
        if (escolha < 1 || escolha > arquivos.length) {
            System.out.println("Opção inválida.");
            return;
        }
        String nomeDisciplina = arquivos[escolha - 1].getName().replace(".txt", "");

        String gabarito = obterGabarito(nomeDisciplina);
        if (gabarito == null) {
            return; 
        }

        Disciplina d = Disciplina.carregar(nomeDisciplina);
        d.gerarResultado(gabarito);
    }

    static String obterGabarito(String nomeDisciplina) throws IOException {
        File padrao = new File(PASTA_GABARITOS, nomeDisciplina + ".txt");

        while (true) {
            System.out.println("\nO gabarito de " + nomeDisciplina + " deve estar em:");
            System.out.println("  " + padrao.getAbsolutePath());
            System.out.print("Enter para usar esse arquivo, ou digite outro caminho: ");
            String caminho = teclado.nextLine().trim().replace("\"", "");

            File arquivo = caminho.isEmpty() ? padrao : new File(caminho);

            
            if (arquivo.isFile()) {
                String gabarito = lerGabarito(arquivo);
                if (Aluno.respostasValidas(gabarito)) {
                    System.out.println("Gabarito encontrado em: " + arquivo.getAbsolutePath());
                    System.out.println("Gabarito: " + gabarito);
                    return gabarito;
                }
                System.out.println("Gabarito inválido! O arquivo deve ter uma linha com 10 letras V ou F.");
            } else {
                System.out.println("Arquivo não encontrado: " + arquivo.getAbsolutePath());
            }

           
            System.out.println("1 - Tentar outro caminho");
            System.out.println("2 - Digitar o gabarito agora (será salvo em " + padrao.getPath() + ")");
            System.out.println("0 - Cancelar");
            System.out.print("Opção: ");
            String op = teclado.nextLine().trim();

            if (op.equals("2")) {
                return criarGabarito(padrao);
            } else if (!op.equals("1")) {
                return null;
            }
        }
    }

   
    static String lerGabarito(File arquivo) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(arquivo), "UTF-8"));
        String linha = in.readLine();
        in.close();
        if (linha == null) {
            return "";
        }
       
        return linha.replace("\uFEFF", "").trim().toUpperCase();
    }

   
    static String criarGabarito(File arquivo) throws IOException {
        System.out.print("Digite o gabarito (10 letras V ou F): ");
        String gabarito = teclado.nextLine().trim().toUpperCase();
        while (!Aluno.respostasValidas(gabarito)) {
            System.out.print("Inválido! Digite exatamente 10 letras V ou F: ");
            gabarito = teclado.nextLine().trim().toUpperCase();
        }

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(arquivo), "UTF-8"));
        out.println(gabarito);
        out.close();
        System.out.println("Gabarito salvo em: " + arquivo.getAbsolutePath());
        return gabarito;
    }
}