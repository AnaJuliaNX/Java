import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Pessoa {
    String nome;
    String telefone;

    Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
}

public class CrudTx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        
        carregarArquivo(listaPessoas);

        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar pessoa");
            System.out.println("2. Editar pessoa");
            System.out.println("3. Excluir pessoa");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch(opcao) {
                case 1:  
                    System.out.println("Insira o nome da pessoa: ");
                    String nome = scanner.nextLine();

                    System.out.println("Insira o telefone da pessoa: ");
                    String telefone = scanner.nextLine();

                    // adiciona uma nova pessoa com os dados que foram passados
                    listaPessoas.add(new Pessoa(nome, telefone));
                    break;

                case 2:
                    // listaPessoas.size() mostra numericamente quantas pessoas que estão cadastradas
                    System.out.println("Insira o número da pessoa a editar (1-" + listaPessoas.size() + "):");
                    int pessoaEditar = scanner.nextInt();
                    scanner.nextLine();  

                    System.out.println("Insira o novo nome da pessoa: ");
                    String novoNome = scanner.nextLine();

                    System.out.println("Insira o novo telefone da pessoa: ");
                    String novoTelefone = scanner.nextLine();

                    listaPessoas.set(pessoaEditar-1, new Pessoa(novoNome, novoTelefone));
                    break;

                case 3:  
                    // listaPessoas.size() mostra numericamente quantas pessoas que estão cadastradas
                    System.out.println("Insira o número da pessoa a excluir (1-" + listaPessoas.size() + "):");
                    int pessoaExcluir = scanner.nextInt();

                    listaPessoas.remove(pessoaExcluir-1);
                    break;

                case 4: 
                    System.out.println("Saindo...");
                    scanner.close();
                    break;

                default:  
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);

        salvarArquivo(listaPessoas);
        
    }

    public static void salvarArquivo(ArrayList<Pessoa> listaPessoas) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pessoas.txt"));
            for (Pessoa p : listaPessoas) {
                writer.write("***Nome***\n" + p.nome + "\n***Telefone***\n" + p.telefone + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarArquivo(ArrayList<Pessoa> listaPessoas) {
        try {
            //Cria o arquivo com o nome "pessoa.txt", como criasse um atributo do tipo arquivo, atributo do tipo arquivo
            BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"));
            //Método do atributo reader para guardar o que foi lido no readLine
            String line = reader.readLine();
            // Fica em um loop enquanto houver linhas para serem lidas no arquivo
            while (line != null) {
                // equals é um método da classe string, porque o line é da classe string 
                if (line.equals("***Nome***")) {
                    String nome = reader.readLine();
                    reader.readLine(); // pula a linha do telefone
                    String telefone = reader.readLine();
                    // adiciona no arquivo
                    listaPessoas.add(new Pessoa(nome, telefone));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // O arquivo não existe ainda, então vamos ignorar essa exceção.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
