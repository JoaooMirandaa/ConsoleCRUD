import java.io.*;
import java.util.*;

public class App {
    private static final String ARQUIVO_DADOS = "dados.txt";

    public static void main(String[] args) {
        System.out.println("Salvar Palavras em um arquivo de texto!");

        int opcao = 0;
        Scanner teclado = new Scanner(System.in);
        List<String> registros = carregarRegistros();

        while (opcao != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Consultar");
            System.out.println("3. Atualizar");
            System.out.println("4. Deletar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine(); //quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarRegistro(teclado, registros); 
                    break;
                case 2:
                    consultarRegistros(registros);
                    break;
                case 3:
                    atualizarRegistros(registros, teclado);
                    break;
                case 4:
                    deletarRegistro(teclado, registros);
                    break;
                case 5:
                    salvarRegistros(registros);
                    System.out.println("Encerrando o programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        teclado.close();
    }

    private static List<String> carregarRegistros() {
        List<String> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registros.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar registros: " + e.getMessage());
        }
        return registros;
    }

    private static void cadastrarRegistro(Scanner teclado, List<String> registros) {
        System.out.print("Digite o nome do aluno: ");
        String nome = teclado.nextLine();
        System.out.print("Digite a idade do aluno: ");
        int idade = teclado.nextInt();
        teclado.nextLine(); // Consumir a quebra de linha após o nextInt()
        System.out.print("Digite o curso do aluno: ");
        String curso = teclado.nextLine();

        String registro = nome + ", " + idade + " anos, curso: " + curso;
        registros.add(registro);
        System.out.println("Registro cadastrado com sucesso! Seu ídice é: " + (registros.size() - 1));
    }

    private static void consultarRegistros(List<String> registros) {
        System.out.println("\nRegistros:");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }

    private static void atualizarRegistros(List<String> registros, Scanner teclado) {
        System.out.print("Digite o índice do registro que deseja atualizar: ");
        int indice = teclado.nextInt();
        teclado.nextLine(); // Consumir a quebra de linha após o nextInt()
    
        if (indice >= 0 && indice < registros.size()) {
            System.out.print("Digite o novo nome do aluno: ");
            String novoNome = teclado.nextLine();
            System.out.print("Digite a nova idade do aluno: ");
            int novaIdade = teclado.nextInt();
            teclado.nextLine(); // Consumir a quebra de linha após o nextInt()
            System.out.print("Digite o novo curso do aluno: ");
            String novoCurso = teclado.nextLine();
    
            String registroAtualizado = novoNome + ", " + novaIdade + " anos, curso: " + novoCurso;
            registros.set(indice, registroAtualizado);
            System.out.println("Registro atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhum registro foi atualizado.");
        }
    }

    private static void deletarRegistro(Scanner teclado, List<String> registros) {
        System.out.print("Digite o indice do usuario que deseja deletar: ");
        int indice = teclado.nextInt();
        teclado.nextLine();
        
        if (indice >= 0 && indice < registros.size()){
            registros.remove(indice);
            System.out.println("Usuario deletado com sucesso!");        
        }else{
            System.out.println("Indice inválido. Nenhum registro foi deletado");
        }                     
    }

    private static void salvarRegistros(List<String> registros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("caminho/para/o/arquivo.txt"))) {
            for (int i = 0; i < registros.size(); i++) {
                String registro = i + ": " + registros.get(i);
                writer.write(registro);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar registros: " + e.getMessage());
            
        }
    }
}