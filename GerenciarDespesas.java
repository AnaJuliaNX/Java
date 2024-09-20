import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


// Crio a classe Despesas e seus atributos
public class GerenciarDespesas {
    private int id;
    private String categoria;
    private double valor;
    private String descricao;
    private LocalDate data;
   

    // Crio o construtor da classe Despesas
    public GerenciarDespesas(int id, String categoria, double valor, String descricao, LocalDate data) {
        this.id = id;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        
    }

    // Sobrecarga do construtor, tiro a descrição
    public GerenciarDespesas(String categoria, double valor, LocalDate data) {
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
            
    }

    // Crio os métodos Get = ler atributos e Set = atribuir ou modificar  
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    // Criar despesa no txt
    public static void adicionarDespesa(GerenciarDespesas despesa) {
        try (FileWriter writer = new FileWriter("despesas.txt", true)) {
            writer.write(despesa.getID() + ";" + despesa.getDescricao() + "; " + despesa.getValor() + "; " + despesa.getData() + "; "
                    + despesa.getCategoria() + "\n");
            System.out.println("Despesa adicionada.");
        } catch (IOException e) {
            System.out.println("Erro ao criar: " + e.getMessage());
        }
    }

    // Listar todas as despesas no txt
    public static List<GerenciarDespesas> listarDespesas() {
        List<GerenciarDespesas> despesas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("despesas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String categoria = partes[1];
                    double valor = Double.parseDouble(partes[2]);
                    String descricao = partes[3];
                    LocalDate data = LocalDate.parse(partes[4]);
                    despesas.add(new GerenciarDespesas(id, categoria, valor, descricao, data));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return despesas;
    }

    // Verifica se a despesa foi paga
    public static boolean foiPago(String descricao) {
        List<Pago> pagamentos = Pago.listarPags();
        for (Pago pagamento : pagamentos) {
            if (pagamento.getDespesa().equalsIgnoreCase(descricao)) {
                return true;
            }
        }
        return false;
    }
}