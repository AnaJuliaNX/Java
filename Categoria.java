import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Crio a classe Categoria e seus atributos
public class Categoria {
    private static final String FILE = "despesas.txt";

    // Salvando uma nova categoria de despesa no arquivo txt
    public static void criarCategoria(String categoria) {
        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write(categoria + "\n");
            System.out.println("Categoria de despesa salvo");
        } catch (IOException e) {
            System.out.println("Erro ao salvar Categoria: " + e.getMessage());
        }
    }

    // Listar todas as categorias de despesas no arquivo txt
    public static List<String> listarCategoria() {
        List<String> categorias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length > 0) {
                    String categoria = partes[partes.length - 1].trim();
                    if (!categorias.contains(categoria)) {
                        categorias.add(categoria);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return categorias;
    }

    public static void alterarCategoria(String categoriaAnterior, String categoriaNovo) {
        List<String> linhas = new ArrayList<>();
        boolean achado = false;

        // Ler todas as linhas do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length > 0 && partes[partes.length - 1].trim().equalsIgnoreCase(categoriaAnterior)) {
                    partes[partes.length - 1] = categoriaNovo;
                    linha = String.join(";", partes);
                    achado = true;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Escrever todas as linhas de volta no arquivo
        try (FileWriter writer = new FileWriter(FILE, false)) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
            if (achado) {
                System.out.println("Categoria alterada");
            } else {
                System.out.println("Categoria não encontrada");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void excluirCategoria(String categoria) {
        List<String> categorias = listarCategoria();
        boolean achado = false;
        try (FileWriter writer = new FileWriter(FILE, false)) {
            for (String tip : categorias) {
                if (tip.equalsIgnoreCase(categoria)) {
                    writer.write(tip + "\n");
                } else {
                   achado = true;
                }
            }
            if (achado) {
                System.out.println("Categoria excluído");
            } else {
                System.out.println("Categoria não encontrado");
            }
        } catch (IOException e) {
            System.out.println("Erro ao excluir Categoria: " + e.getMessage());
        }
    }
}
