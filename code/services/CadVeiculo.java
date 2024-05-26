import java.util.InputMismatchException;
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
                    if (veiculoService.getVeiculosDB().isEmpty()) { // Se a lista de veículos estiver vazia
                        scan.nextLine();
                    }
                    System.out.println("Pressione uma tecla para voltar ao menu"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
                    scan.nextLine(); // Limpa o buffer do teclado
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

        int tipoVeiculo;
        do {
        System.out.println("Qual o tipo de veículo");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        try { // Tratamento de erros para capturar o tipo de veículo
        tipoVeiculo = scan.nextInt();
        scan.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Tipo inválido. Digite um tipo");
            scan.nextLine();
            tipoVeiculo = 0;
            continue;
        } 
        if (tipoVeiculo <= 0 || tipoVeiculo > 2) { // checa se o tipo de veículo e 1 ou 2
            System.out.println("Tipo inválido. Digite um tipo");
            continue;
        }} while (tipoVeiculo <= 0 || tipoVeiculo > 2);


        System.out.println("Digite a marca do veículo");
        String marca = scan.nextLine();
        if (marca.trim().isEmpty()) {
            System.out.println("Marca inválida. Digite uma marca");
            return;
        }

        System.out.println("Digite o modelo do veículo");
        String modelo = scan.nextLine();
        if (modelo.trim().isEmpty()) { // checa se o campo modelo tem espaços em branco
            System.out.println("Modelo inválido. Digite um modelo");
            return;
        }
        
        System.out.println("Digite o ano do veículo");
        int ano;
        do { // Loop para capturar o ano do veículo 
        try { // Tratamento de erros para capturar o ano do veículo
        ano = scan.nextInt(); // captura o ano digitado pelo usuário
        scan.nextLine(); // limpa o buffer do teclado
        if (Integer.toString(ano).trim().isEmpty()) { // transforma o ano em uma string e checa se tem espaços em branco
            System.out.println("Ano inválido. Digite um ano de 4 digitos"); // usamos o método integer.toString() para transformar o ano em uma string, pois o método .trim não pode ser usado em um inteiro
            return;
        }
        if (ano == 0) { // se o ano for 0 o loop continua a ser executado
            System.out.println("Ano inválido. Digite um ano de 4 digitos");
        }
        } catch (InputMismatchException e) { // caso o ano seja um tipo invalido o loop continua a ser executado
            System.out.println("Ano inválido. Digite um ano de 4 digitos");
            scan.nextLine();
            ano = 0; // tive que inicializar a variável para que o loop "do" seja executado, pois entrando nesse catch, ele não setava a variavel ano e dava problema.
            continue; // pula para o proximo loop
        }
        } while (ano == 0); // enquanto o ano for 0 o loop continua a ser executado

        String placa;
        System.out.println("Digite a placa do veículo");
        placa = scan.nextLine();
        if (placa.trim().isEmpty()) {
            System.out.println("Placa inválida. Digite uma placa");
            return;
        }
        for (Veiculo veiculo : veiculoService.getVeiculosDB()) { // checa se a placa ja existe no sistema de frotas
            if (veiculo.getPlaca().equals(placa)) {
                System.out.println("Placa inválida. Veículo ja existente");
                System.out.println("Pressione uma tecla para voltar ao menu");
                scan.nextLine();
                return;
            }
        }

        if (tipoVeiculo == 1) {
            System.out.println("Digite o número de portas");
            int numeroPortas;
            do {
            try {
            numeroPortas = scan.nextInt();
            scan.nextLine();
            } catch (InputMismatchException e) { // caso o valor seja um tipo invalido o loop continua a ser executado
                scan.nextLine(); // limpa o buffer do teclado
                numeroPortas = 0;
            }
            if (numeroPortas == 0) {
                System.out.println("Numero de portas inválido. Digite um número de portas de 1 a 4");
            }
            if (Integer.toString(numeroPortas).trim().isEmpty()) {
                System.out.println("Numero de portas inválido. Digite um número de portas de 1 a 4");
            }
            } while (numeroPortas == 0);
            veiculoAdd = new Carro(marca, modelo, ano, placa, numeroPortas); // Cria um novo veículo com os dados informados
        } else {
            System.out.println("A moto possui partida elétrica: 1-Sim, 2-Não");
            System.out.println("Responda com 1 ou 2");
            int partidaEletrica;
            do {
            try {
            partidaEletrica = scan.nextInt();
            scan.nextLine();
            if (partidaEletrica <= 0 || partidaEletrica > 2) {
                System.out.println("Responda com 1 ou 2");
            }
            if (Integer.toString(partidaEletrica).trim().isEmpty()) {
                System.out.println("Responda com 1 ou 2");
            }
            } catch (InputMismatchException e) { // caso o valor seja um tipo invalido o loop continua a ser executado
                scan.nextLine(); // limpa o buffer do teclado
                partidaEletrica = 0;
            } 
            } while (partidaEletrica <= 0 || partidaEletrica > 2); // enquanto o valor for 0 ou maior que 2 o loop continua a ser executado
            boolean partida = partidaEletrica == 1 ? true : false; // Converte o valor 1 para true e o valor 2 para false
            veiculoAdd = new Moto(marca, modelo, ano, placa, partida); // Cria um novo veículo com os dados informados
        } 
        try { // Tratamento de erros para salvar o veículo
            System.out.println("Veículo cadastrado com sucesso"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
            veiculoService.save(veiculoAdd); // Salva o veículo no sistema de frotas
            System.out.println("Pressione uma tecla para voltar ao menu"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
            scan.nextLine(); // Limpa o buffer do teclado
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Imprime a mensagem de erro
            scan.nextLine(); // Limpa o buffer do teclado
        }
    }

    private static void imprimirVeiculos() { // Imprime todos os veículos no sistema de frotas 
        List<Veiculo> veiculos = veiculoService.getVeiculosDB(); // Retorna uma lista com todos os veículos
        if (veiculos.isEmpty()) { // Verifica se a lista de veículos é vazia
            System.out.println("Nenhum veículo encontrado");
            return;
        } else {
        for (Veiculo veiculo : veiculos) { // Para cada veículo na lista de veículos
            System.out.println(veiculo + " - Placa: " + veiculo.getPlaca()); // Imprime o veículo
            System.out.println("Tempo de uso: " + veiculo.calcularTempoDeUso() + " ano(s)"); // Imprime o tempo de uso
            System.out.println(veiculo.calcularImposto()); // Imprime o imposto
            }
        }
    }

    private static void pesquisarVeiculo() { // Pesquisa um veículo no sistema de frotas por placa e imprime o veículo correspondente
        System.out.println("Digite a placa do veículo");    
        String placa = scan.nextLine();
        Veiculo veiculo = veiculoService.getVeiculoDB(placa);
        System.out.println(veiculo);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado com placa " + placa);
        }
        System.out.println("Pressione uma tecla para voltar ao menu"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
        scan.nextLine(); // Limpa o buffer do teclado

    }

    private static void removerVeiculo() { // Remove um veículo do sistema de frotas
        System.out.println("Digite a placa do veículo");
        String placa = scan.nextLine();
        if (veiculoService.getVeiculoDB(placa) == null) {
            System.out.println("Veículo não encontrado com placa " + placa);
            System.out.println("Pressione uma tecla para voltar ao menu"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
            scan.nextLine(); // Limpa o buffer do teclado
            return;
        } else {
        Veiculo veiculo = veiculoService.getVeiculoDB(placa);
        veiculoService.getVeiculosDB().remove(veiculo);
        System.out.println("Veículo removido com sucesso!");
        System.out.println("Pressione uma tecla para voltar ao menu"); // Imprime uma mensagem de sucesso ao cadastrar o veículo
        scan.nextLine(); // Limpa o buffer do teclado
        }
    }
}
