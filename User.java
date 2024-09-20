import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Criar a classe Usuarios com os atributos nome e senha
public class User {
    private String nome;
    private String senha; 

    //Criar um construtor que recebe nome e senha
    public User(String nome, String senha) {
        this.nome = nome;
        this.senha = senhaCriptografada(senha);
    }

    //Criar um método que retorna o nome
    public String getNome() {
        return nome;
    }

    //Criar um método que retorna a senha
    public String getSenha() {
        return senha;
    }

    //Criar um método que add usuário
    public static void criarUsuario(User usuario) {
        try (FileWriter writer = new FileWriter("usuarios.txt", true)) {
            writer.write(usuario.getNome() + ";" + usuario.getSenha() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    //Criar um método que lista usuários
    public static List<User> listaUsuarios() {
        List<User> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    usuarios.add(new User(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    //Criar um método que altera usuário
    public static void alterarUsuario(String nomeAntigo, String nomeNovo) {
        List<User> usuarios = listaUsuarios(); 
        try (FileWriter writer = new FileWriter("usuarios.txt", false)) {
            //Olho a lista de usuários
            for (User usuario : usuarios) {
                //Se o nome do usuário for igual ao nomeAntigo executo
                if (usuario.getNome().equals(nomeAntigo)) {
                    writer.write(nomeNovo +  ";" + usuario.getNome() + "\n");
                //Garanto que o nome dos outros usuário não seja alterado
                } else {
                    writer.write(usuario.getNome() + ";" + nomeNovo + "\n");
                }
            }
        //Se não conseguir alterar o usuário, exibo a mensagem de erro
        } catch (IOException e) {
            System.out.println("Erro ao alterar usuário: " + e.getMessage());
        }
    }

    //Criar um método que exclui usuário
    public static void excluirUsuario(String nome) {
        List<User> usuarios = listaUsuarios();
        try (FileWriter writer = new FileWriter("usuarios.txt", false)) {
            //Olho a lista de usuários
            for (User usuario : usuarios) {
                //Se o nome do usuário for igual do nome que quero excluir, executo
                if (usuario.getNome().equals(nome)) {
                    writer.write(usuario.getNome() + ";" + usuario.getSenha() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    //Cria um método que criptografa a senha
    private static String senhaCriptografada(String senha) {
        //Cria um StringBuilder para armazenar a senha criptografada
        StringBuilder criptografada = new StringBuilder();
        //Percorre a senha e converte cada caractere para int
        for (char c : senha.toCharArray()) {
            criptografada.append((int) c).append(" ");
        }
        //Retorna a senha criptografada
        return criptografada.toString().trim();
    }
}
