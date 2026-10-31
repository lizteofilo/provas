public class Aluno {

    private String nome;
    private String respostas;

    public Aluno(String nome, String respostas) {
        this.nome = nome.trim().toUpperCase();
        this.respostas = respostas.trim().toUpperCase();
    }


    public static boolean respostasValidas(String respostas) {
        return respostas.trim().toUpperCase().matches("[VF]{10}");
    }

    public String getNome() {
        return nome;
    }

    public String getRespostas() {
        return respostas;
    }
}
