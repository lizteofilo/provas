public class Nota {

    private String aluno;
    private String disciplina;
    private int valor; 

    public Nota(String aluno, String disciplina, int valor) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
    }

    public static int calcular(String respostas, String gabarito) {
        if (respostas.equals("VVVVVVVVVV") || respostas.equals("FFFFFFFFFF")) {
            return 0;
        }
        int acertos = 0;
        for (int i = 0; i < 10; i++) {
            if (respostas.charAt(i) == gabarito.charAt(i)) {
                acertos++;
            }
        }
        return acertos;
    }

    public String getAluno() {
        return aluno;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public int getValor() {
        return valor;
    }
}
