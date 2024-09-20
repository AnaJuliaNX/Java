import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class Pago {
    private String despesa;
    private double valor; //Valor pago na despesa 
    private LocalDate dataPagar; // data do pagamento da despesa 

    public Pago(String despesa, double valorPago, LocalDate dataPagar) {
        this.despesa = despesa;
        this.valor = valorPago;
        this.dataPagar = dataPagar;
    }

    // Get = ler atributos e Set = atribuir ou modificar
    public String getDespesa() {
        return despesa;
    }
    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }

    public double getValorPago() {
        return valor;
    }
    public void setValorPago(double valorPago) {
        this.valor = valorPago;
    }

    public LocalDate getDataPagar() {
        return dataPagar;
    }
    public void setDataPagar(LocalDate dataPagamento) {
        this.dataPagar = dataPagamento;
    }

    // Salvar pagamento no arquivo txt
    public static void criarPago(Pago pagamento) {
        try (FileWriter writer = new FileWriter("pagamentos.txt", true)) {
            writer.write(pagamento.getDespesa() + ";" + pagamento.getValorPago() + ";"
                    + pagamento.getDataPagar() + "\n");
            System.out.println("Pagamento salvo.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pagamento: " + e.getMessage());
        }
    }

    // Listar os pagamentos no arquivo txt
    public static List<Pago> listarPags() {
        List<Pago> pagtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pagamentos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    String despesa = partes[0];
                    double valorPago = Double.parseDouble(partes[1]);
                    LocalDate dataPagar = LocalDate.parse(partes[2]);
                    pagtos.add(new Pago(despesa, valorPago, dataPagar));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return pagtos;
    }
}