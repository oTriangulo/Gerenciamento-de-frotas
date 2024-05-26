import java.util.List;
import java.util.Scanner;

public class CadVeiculo {
    private static Scanner scan; // Scanner para capturar os dados digitados pelo usuário
    private static VeiculoService veiculoService; // Serviço de veículos

    public static void main(String[] args) { 
        scan = new Scanner(System.in); // Scanner para capturar os dados digitados pelo usuário
        veiculoService = new VeiculoService(); // Criando o serviço de veículos no sistema de frotas usando de base de dados
        int opcao; // Variável para armazenar a opção escolhida
        do { // Loop infinito para mostrar o menu e permitir o usuário escolher uma opção
            System.out.println("Sistema de Gerenciamento de Frotas"); 
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Listar todos os Veículos");
            System.out.println("3 - Pesquisar Veículo por Placa");
            System.out.println("4 - Remover Veículo");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scan.nextInt(); // Captura a opção escolhida pelo usuário e armazena na variável opcao
            scan.nextLine(); // Limpa o buffer do teclado
            switch (opcao) { // Executa a opção escolhida pelo usuário e encerra o loop se 0 for escolhido
                case 1: 
                    save(); // Cadastra um novo veículo no sistema de frotas
                    break; // Encerra o switch caso 1 seja escolhido
                case 2:
                    imprimirVeiculos(); // Imprime todos os veículos no sistema de frotas
                    break; // Encerra o switch caso 2 seja escolhido
                case 3:
                    pesquisarVeiculo(); // Pesquisa um veículo no sistema de frotas por placa
                    break; // Encerra o switch caso 3 seja escolhido
                case 4:
                    removerVeiculo(); // Remove um veículo do sistema de frotas
                    break; // Encerra o switch caso 4 seja escolhido
                case 0:
                    System.out.println("Volte logo!"); // Encerra o sistema de frotas
                    break; // Encerra o switch caso 0 seja escolhido
                default:
                    System.out.println("Opção inválida!"); // Aviso de que a opção escolhida é inválida
                    break; 
            }
        } while (opcao != 0); // Encerra o loop se 0 for escolhido
    } // Este while é feito de maneira diferente do que estamos acostumados, ele faz algo "do" enquanto a condição for verdadeira "while"

    public static void save() { // Cadastra um novo veículo no sistema de frotas, é chamado por meio da opção "Cadastrar Novo Veículo" no menu
        Veiculo veiculoAdd; // Variável para armazenar o veículo a ser adicionado

        System.out.println("Qual o tipo de veículo");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        int tipoVeiculo = scan.nextInt();
        scan.nextLine();

        System.out.println("Digite a marca do veículo");
        String marca = scan.nextLine();
        System.out.println("Digite o modelo do veículo");
        String modelo = scan.nextLine();
        System.out.println("Digite o ano do veículo");
        int ano;
        do {
        ano = scan.nextInt();
        scan.nextLine();
        if (ano == 0) {
            System.out.println("Ano inválido. Digite um ano de 4 digitos");
        }
        } while (ano == 0); {
        System.out.println("Digite a placa do veículo");
        String placa = scan.nextLine();

        if (tipoVeiculo == 1) {
            System.out.println("Digite o número de portas");
            int numeroPortas;
            do {
            numeroPortas = scan.nextInt();
            scan.nextLine();
            if (numeroPortas == 0) {
                System.out.println("Numero de portas inválido. Digite um número de portas de 1 a 4");
            }
            } while (numeroPortas == 0);
            veiculoAdd = new Carro(marca, modelo, ano, placa, numeroPortas);
        } else {
            System.out.println("A moto possui partida elétrica: 1-Sim, 2-Não");
            int partidaEletrica = scan.nextInt();
            scan.nextLine();
            boolean partida = partidaEletrica == 1 ? true : false;
            veiculoAdd = new Moto(marca, modelo, ano, placa, partida);
        } 
        try {
            veiculoService.save(veiculoAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scan.nextLine();
        }}
    }

    private static void imprimirVeiculos() { // Imprime todos os veículos no sistema de frotas 
        List<Veiculo> veiculos = veiculoService.getVeiculosDB(); // Retorna uma lista com todos os veículos
        for (Veiculo veiculo : veiculos) { // Para cada veículo na lista de veículos
            System.out.println(veiculo); // Imprime o veículo
            System.out.println("Tempo de uso: " + veiculo.calcularTempoDeUso() + " ano(s)"); // Imprime o tempo de uso
            System.out.println(veiculo.calcularImposto()); // Imprime o imposto
        }
    }

    private static void pesquisarVeiculo() { // Pesquisa um veículo no sistema de frotas por placa e imprime o veículo correspondente
        System.out.println("Digite a placa do veículo");    
        String placa = scan.nextLine();
        Veiculo veiculo = veiculoService.getVeiculoDB(placa);
        System.out.println(veiculo);

    }

    private static void removerVeiculo() { // Remove um veículo do sistema de frotas
        System.out.println("Digite a placa do veículo");
        String placa = scan.nextLine();
        Veiculo veiculo = veiculoService.getVeiculoDB(placa);
        veiculoService.getVeiculosDB().remove(veiculo);
        System.out.println("Veículo removido com sucesso!");
    }
}
