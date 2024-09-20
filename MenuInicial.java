import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MenuInicial {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int numero;

        do {
            System.out.println("\nControle as despesas com Ana S2");
            System.out.println("1- Entrar despesa");
            System.out.println("2- Criar pagamento");
            System.out.println("3- Listar despesas em aberto no período");
            System.out.println("4- Listar despesas pagas no período");
            System.out.println("5- Gerenciar categoria de despesa");
            System.out.println("6- Gerenciar usuários");
            System.out.println("7- Sair");
            System.out.print("Selecione uma opção: ");
            numero = scanner.nextInt();
            scanner.nextLine(); // Consumir o '\n' deixado pelo nextInt()

            switch (numero) {
                case 1:
                    entrarDespesa();
                    break;
                case 2:
                    anotarPagamento();
                    break;
                case 3:
                    listarDespesasAberto();
                    break;
                case 4:
                    listarDespesasPagas();
                    break;
                case 5:
                    gerenciarCategoria();
                    break;
                case 6:
                    gerenciarUsuarios();
                    break;
                case 7:
                    System.out.println("Encerrando operações");
                    break;

                default:
                    System.out.println("Opção inexistente");
                    break;
            }
        } while (numero != 7);
    }

    private void entrarDespesa() {
        System.out.println("Inserindo despesa");

        System.out.print("ID da despesa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir o '\n' deixado pelo nextInt()

        System.out.print("Categoria da despesa: ");
        String categoria = scanner.nextLine();

        System.out.print("Valor da despesa: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir o '\n' deixado pelo nextDouble()

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        LocalDate data = capturarData("Data de vencimento (YYYY-MM-DD): ");

        GerenciarDespesas despesa = new GerenciarDespesas(id, categoria, valor, descricao, data);
        GerenciarDespesas.adicionarDespesa(despesa);
    }

    private void anotarPagamento() {
        System.out.println("Salvar pagamento");

        System.out.print("Nome da despesa: ");
        String despesa = scanner.nextLine();

        System.out.print("Valor do pagamento: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        LocalDate dataPag = capturarData("Data do pagamento (YYYY-MM-DD): ");

        Pago pagamento = new Pago(despesa, valor, dataPag);
        Pago.criarPago(pagamento);
        System.out.println("Pagamento salvo");
    }

    private void listarDespesasAberto() {
        System.out.println("\nListar Despesas em Aberto");
        List<GerenciarDespesas> despesas = GerenciarDespesas.listarDespesas();

        LocalDate dataInicio = capturarData("Digite a data de início (YYYY-MM-DD): ");
        LocalDate dataFim = capturarData("Digite a data de fim (YYYY-MM-DD): ");

        boolean achou = false;
        for (GerenciarDespesas despesa : despesas) {
            if (!GerenciarDespesas.foiPago(despesa.getDescricao()) &&
                    (despesa.getData().isEqual(dataInicio) || despesa.getData().isAfter(dataInicio)) &&
                    (despesa.getData().isEqual(dataFim) || despesa.getData().isBefore(dataFim))) {
                System.out.println("Descrição: " + despesa.getDescricao() + ", Valor: " + despesa.getValor() +
                        ", Vencimento: " + despesa.getData() + ", Categoria: " + despesa.getCategoria());
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma despesa encontrada");
        }
    }

    private void listarDespesasPagas() {
        System.out.println("Listando despesas pagas");
        List<Pago> despesas = Pago.listarPags();

        LocalDate dataPesquisa = capturarData("Data da despesa (YYYY-MM-DD): ");

        boolean achou = false;
        for (Pago despesa : despesas) {
            if (despesa.getDataPagar().isEqual(dataPesquisa)) {
                System.out.println(", Valor Pago: " + despesa.getValorPago() + ", Data de Pagamento: " + despesa.getDataPagar());
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma despesa encontrada para a data informada");
        }
    }

    private void gerenciarCategoria() {
        int numero;

        do {
            System.out.println("\nGerenciar categoria de despesa");
            System.out.println("1- Adicionar");
            System.out.println("2- Editar");
            System.out.println("3- Listar");
            System.out.println("4- Deletar");
            System.out.println("5- Voltar");
            System.out.print("Selecione uma opção: ");
            numero = scanner.nextInt();
            scanner.nextLine();

            switch (numero) {
                case 1:
                    adicionarCategoria();
                    break;
                case 2:
                    editarCategoria();
                    break;
                case 3:
                    listarCategoria();
                    break;
                case 4:
                    deletarCategoria();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inexistente");
                    break;
            }
        } while (numero != 5);
    }

    private void adicionarCategoria() {
        System.out.println("Adicionando categoria de despesa");
        String categoria = scanner.next();
        Categoria.criarCategoria(categoria);
        System.out.println("Nova categoria adicionada");
    }

    private void editarCategoria() {
        System.out.print("Digite a Categoria atual de despesa: ");
        String categoriaAnterior = scanner.next();

        System.out.print("Digite a nova Categoria de despesa: ");
        String novaCategoria = scanner.next();

        Categoria.alterarCategoria(categoriaAnterior, novaCategoria);
        System.out.println("Categoria editada");
    }

    private void listarCategoria() {
        System.out.println("\nCategorias de despesas:");
        List<String> categorias = Categoria.listarCategoria();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria de despesa encontrada");
        } else {
            for (String categoria : categorias) {
                System.out.println("- " + categoria);
            }
        }
    }

    private void deletarCategoria() {
        System.out.print("Digite a categoria a ser excluída: ");
        String categoria = scanner.next();
        Categoria.excluirCategoria(categoria);
        System.out.println("Categoria excluída");
    }

    private void gerenciarUsuarios() {
        int numero;

        do {
            System.out.println("\nGerenciar usuários");
            System.out.println("1- Adicionar");
            System.out.println("2- Editar");
            System.out.println("3- Listar");
            System.out.println("4- Deletar");
            System.out.println("5- Voltar");
            System.out.print("Selecione uma opção: ");
            numero = scanner.nextInt();
            scanner.nextLine();

            switch (numero) {
                case 1:
                    adicionarUsuario();
                    break;
                case 2:
                    editarUsuario();
                    break;
                case 3:
                    listarUsuario();
                    break;
                case 4:
                    deletarUsuario();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inexistente");
                    break;
            }
        } while (numero != 5);
    }

    private void adicionarUsuario() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        User usuario = new User(nome, senha);
        User.criarUsuario(usuario);
        System.out.println("Novo usuário adicionado");
    }

    private void editarUsuario() {
        System.out.print("Digite o nome atual: ");
        String nomeAntigo = scanner.nextLine();

        System.out.print("Digite o novo nome: ");
        String nomeNovo = scanner.nextLine();

        User.alterarUsuario(nomeAntigo, nomeNovo);
        System.out.println("Usuário editado");
    }

    private void listarUsuario() {
        System.out.println("\nUsuários cadastrados:");
        List<User> usuarios = User.listaUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado");
        } else {
            for (User usuario : usuarios) {
                System.out.println("Nome: " + usuario.getNome());
            }
        }
    }

    private void deletarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        User.excluirUsuario(nome);
        System.out.println("Usuário excluído");
    }

    private LocalDate capturarData(String mensagem) {
        LocalDate data = null;
        while (true) {
            System.out.print(mensagem);
            String dataStr = scanner.nextLine();
            try {
                data = LocalDate.parse(dataStr);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }
        return data;
    }
}