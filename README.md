<div align="center">

# 📝 Corretor de Provas V/F

### ✅ Corrige provas · 📊 Calcula médias · 🎓 Gera boletins

<br>

![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Terminal](https://img.shields.io/badge/Interface-Terminal-4D4D4D?style=for-the-badge&logo=windowsterminal&logoColor=white)
![Arquivos](https://img.shields.io/badge/Dados-Arquivos%20.txt-2E86C1?style=for-the-badge&logo=files&logoColor=white)
![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-2ECC71?style=for-the-badge)

<br>

*Um programa em Java que corrige provas de **Verdadeiro ou Falso**,<br>
organiza as notas de cada disciplina e monta o **boletim** de cada aluno.* 📚

<br>

```
╔══════════════════════════════════════╗
║          CORRETOR DE PROVAS          ║
╠══════════════════════════════════════╣
║   1 - Criar disciplina               ║
║   2 - Gerar resultado                ║
║   3 - Gerar boletins                 ║
║   0 - Sair                           ║
╚══════════════════════════════════════╝
```

<br>

[✨ Funcionalidades](#-funcionalidades) •
[📁 Estrutura](#-estrutura-do-projeto) •
[🧩 Classes](#-classes) •
[▶️ Como executar](#️-como-executar) •
[🎬 Exemplo](#-exemplo-de-uso) •
[🧮 Cálculo](#-como-a-nota-é-calculada)

</div>

---

### 🚀 Em poucas palavras

> 1. O professor **cadastra as respostas** dos alunos de cada disciplina.
> 2. O programa **compara com o gabarito** e conta os acertos.
> 3. Saem as **listas de notas** (por nome e por nota) com a **média da turma**.
> 4. No final, cada aluno ganha seu **boletim** com média e resultado: ✅ **APROVADO** ou ❌ **REPROVADO**.

---

## ✨ Funcionalidades

| # | Funcionalidade | O que faz |
|---|---|---|
| 1️⃣ | **Criar disciplina** | Cadastra os alunos e as respostas deles. Gera um arquivo com o nome da disciplina. Dá para criar quantas disciplinas e alunos quiser. |
| 2️⃣ | **Gerar resultado** | Escolhe a disciplina, localiza o gabarito oficial e corrige as provas. Gera dois arquivos: um em **ordem alfabética** e outro em **ordem decrescente de nota** com a **média da turma**. Mostra tudo na tela. |
| 3️⃣ | **Gerar boletins** | Lê os resultados de todas as disciplinas e cria um arquivo para cada aluno com suas notas, **média** e **resultado final**. |

### 📏 Regras de correção
- Cada prova tem **10 questões** (V ou F).
- A nota é o **número de acertos** (de 0 a 10).
- ⚠️ Quem marcar **todas as questões com V** ou **todas com F** recebe **nota 0**.
- No boletim, média **≥ 7** significa ✅ **APROVADO**. Abaixo disso, ❌ **REPROVADO**.

---

## 📁 Estrutura do projeto

```
provas/
├── 📂 src/              → código-fonte (as 6 classes Java)
├── 📂 disciplinas/      → respostas dos alunos de cada disciplina
├── 📂 gabaritos/        → gabarito oficial de cada disciplina
├── 📂 resultados/       → notas corrigidas de cada disciplina
└── 📂 boletins/         → um boletim por aluno
```

> 💡 As pastas são criadas automaticamente na primeira execução, caso não existam.

---

## 🧩 Classes

| Classe | Responsabilidade |
|---|---|
| `Main` | Menu principal. Coordena as três funcionalidades. |
| `Aluno` | Guarda o nome e as respostas do aluno, e valida se são 10 letras V/F. |
| `Nota` | Guarda a nota de um aluno em uma disciplina e **calcula os acertos**. |
| `Disciplina` | Guarda os alunos, salva e carrega o arquivo, corrige as provas e gera os resultados. |
| `Boletim` | Junta as notas de um aluno, calcula a **média** e o **resultado final**. |
| `GeradorBoletim` | Lê todos os resultados e gera o boletim de cada aluno. |

---

## 📄 Formato dos arquivos

**`disciplinas/<disciplina>.txt`**: uma linha por aluno, com as respostas e o nome separados por um **tab**:
```
VFFFVVVFVF	BELTRANO DA SILVA
FFVFFFVVVF	FULANO DOS ANZÓIS PEREIRA
VVFFFVFFVV	CICRANO JOSÉ
```

**`gabaritos/<disciplina>.txt`**: uma única linha com as 10 respostas corretas:
```
VFFFVVVFVV
```

**`resultados/<disciplina>_alfabetica.txt`**: alunos em ordem alfabética:
```
BELTRANO DA SILVA	9
CICRANO JOSÉ	7
FULANO DOS ANZÓIS PEREIRA	4
```

**`resultados/<disciplina>_notas.txt`**: da maior para a menor nota, com a média no final:
```
BELTRANO DA SILVA	9
CICRANO JOSÉ	7
FULANO DOS ANZÓIS PEREIRA	4

Média da turma: 6,67
```

**`boletins/<NOME DO ALUNO>.txt`**:
```
Aluno: BELTRANO DA SILVA

Disciplina    Nota
matematica    9
portugues     9

Média: 9,00
Resultado: APROVADO
```

---

## ▶️ Como executar

### Requisitos
- **Java 8** ou superior (JDK)

### No VS Code
1. Abra a pasta **`provas`** (a pasta do projeto, não a `src`).
2. Abra o arquivo `src/Main.java`.
3. Clique em **Run** ▶️.

### Pelo terminal
Dentro da pasta `provas`:
```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Main
```

> 💡 **Windows:** se os acentos aparecerem estranhos no terminal, rode `chcp 65001` antes de executar o programa.

---

## 🎬 Exemplo de uso

```
===== MENU =====
1 - Criar disciplina
2 - Gerar resultado de uma disciplina
3 - Gerar boletins
0 - Sair
Opção: 2
Disciplinas:
1 - matematica
2 - portugues
Escolha: 1

O gabarito de matematica deve estar em:
  C:\...\provas\gabaritos\matematica.txt
Enter para usar esse arquivo, ou digite outro caminho:
Gabarito encontrado em: C:\...\provas\gabaritos\matematica.txt
Gabarito: VFFFVVVFVV

--- matematica (ordem de nota) ---
BELTRANO DA SILVA             9
CICRANO JOSÉ                  7
FULANO DOS ANZÓIS PEREIRA     4
Média da turma: 6,67
```

### 🗺️ Passo a passo recomendado
1. **Opção 1**: crie as disciplinas e cadastre os alunos (os mesmos alunos em todas).
2. Coloque o gabarito de cada disciplina na pasta `gabaritos`, com **o mesmo nome do arquivo da disciplina**. Você também pode digitar o gabarito pelo próprio programa na opção 2.
3. **Opção 2**: gere o resultado de **cada** disciplina.
4. **Opção 3**: gere os boletins de todos os alunos.

### 🔎 Sobre o gabarito (opção 2)
Depois que você escolhe a disciplina, o programa **informa onde o gabarito deve estar** (`gabaritos/<disciplina>.txt`). Então você pode:
- apertar **Enter** para usar esse arquivo;
- **digitar outro caminho** para usar um gabarito que está em outro lugar;
- se o arquivo não existir, **digitar as 10 respostas** na hora. O programa cria o arquivo de gabarito.

---

## 🧮 Como a nota é calculada

O programa compara as respostas do aluno com o gabarito, **questão por questão**:

```
Questão:   1  2  3  4  5  6  7  8  9  10
Gabarito:  V  F  F  F  V  V  V  F  V  V
Aluno:     V  F  F  F  V  V  V  F  V  F
           ✓  ✓  ✓  ✓  ✓  ✓  ✓  ✓  ✓  ✗   → 9 acertos
```

| Cálculo | Fórmula | Exemplo |
|---|---|---|
| Nota do aluno | nº de acertos (0 se marcou tudo V ou tudo F) | 9 |
| Média da turma | soma das notas ÷ nº de alunos | (9 + 7 + 4) ÷ 3 = **6,67** |
| Média do boletim | soma das notas do aluno ÷ nº de disciplinas | (9 + 9) ÷ 2 = **9,00** ✅ |

---

## 👩‍💻 Autoria

Trabalho desenvolvido para a disciplina de **POO**.

**Aluna:** Maria Eliza Teofilo
