import java.util.ArrayList;
import java.util.List;

public class VeiculoService { // Serviço de veiculos no sistema de frotas usado de base de dados
    private List<Veiculo> veiculosDB; // Lista de veículos no sistema de frotas
 
    public List<Veiculo> getVeiculosDB() { // Retorna uma lista com todos os veículos no sistema de frotas 
        return veiculosDB; // Retorna uma lista com todos os veículos
    }

    public VeiculoService() { // Cria um serviço de veículos no sistema de frotas usando de base de dados
        this.veiculosDB = new ArrayList<>(); // Cria uma lista vazia para armazenar os veículos
    }
    
    public Veiculo getVeiculoDB(String placa) { // Pesquisa um veículo no sistema de frotas por placa e retorna o veículo correspondente
        for (Veiculo veiculo : veiculosDB) { // Para cada veículo na lista de veículos
            if (veiculo.getPlaca().equals(placa)) // Verifica se a placa do veículo pesquisado corresponde ao veículo da lista
                return veiculo; // Retorna o veículo correspondente
        }
        return null; // Retorna null caso nenhum veículo corresponda
    }

    public void removerVeiculoDB(String placa) { // Remove um veículo do sistema de frotas por placa
        for (Veiculo veiculo : veiculosDB) { // Para cada veículo na lista de veículos
            if (veiculo.getPlaca().equals(placa)) // Verifica se a placa do veículo pesquisado corresponde ao veículo da lista
                veiculosDB.remove(veiculo); // Remove o veículo correspondente
        }
    
    }

    public Veiculo save(Veiculo veiculo) throws Exception { // Cadastra um novo veículo no sistema de frotas
        if (veiculo == null || veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty() || veiculo.getAno() == 0 || veiculo.getModelo() == null || veiculo.getModelo().isEmpty() || veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) // Verifica se o veículo é nulo e lança uma exceção
            throw new Exception("Erro ao cadastrar veículo"); // excesão caso o veículo seja nulo
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) // Verifica se o campo modelo é vazio e lança uma exceção
            throw new Exception("Campo Modelo não pode ser em branco"); // excesão caso o campo modelo seja vazio
        veiculosDB.add(veiculo); // Adiciona um novo veículo na lista de veículos no sistema de frotas caso o veículo não seja nulo
        return veiculo; // Retorna o veículo
    } 
}

// nao aguento mais
// para colocar o ponteiro de resposta do lado, é só colocar o input dentro do sysout :D