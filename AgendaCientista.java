import java.util.Scanner; // Import necessário para utilizar Scanner

class AgendaCientista {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Inicializa o Scanner para ler a entrada do usuário
        ListaLinear agenda = new ListaLinear(10); // Capacidade da lista = 10

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar experimento");
            System.out.println("2. Remover experimento");
            System.out.println("3. Buscar experimento por complexidade");
            System.out.println("4. Mostrar todos os experimentos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir o "\n" que sobra após a entrada de um número

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do experimento: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a complexidade (número inteiro): ");
                    int complexidade = scanner.nextInt();
                    agenda.adicionarExperimento(nome, complexidade);
                    break;

                case 2:
                    System.out.print("Digite o nome do experimento a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    agenda.removerExperimento(nomeRemover);
                    break;

                case 3:
                    System.out.print("Digite a complexidade a ser buscada: ");
                    int complexidadeBuscar = scanner.nextInt();
                    agenda.buscarPorComplexidade(complexidadeBuscar);
                    break;

                case 4:
                    agenda.mostrarExperimentos();
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close(); // Fechar o Scanner antes de sair
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

class Experimento {
    String nome;
    int complexidade;

    public Experimento(String nome, int complexidade) {
        this.nome = nome;
        this.complexidade = complexidade;
    }

    @Override
    public String toString() {
        return "Experimento: " + nome + " | Complexidade: " + complexidade;
    }
}

class ListaLinear {
    private Experimento[] lista;
    private int tamanho;

    public ListaLinear(int capacidade) {
        lista = new Experimento[capacidade];
        tamanho = 0;
    }

    // Função recursiva para adicionar experimento na posição correta (de acordo com a complexidade)
    private void inserirRecursivo(int indice, Experimento novoExp) {
        if (indice == tamanho || lista[indice].complexidade <= novoExp.complexidade) {
            for (int i = tamanho; i > indice; i--) {
                lista[i] = lista[i - 1];
            }
            lista[indice] = novoExp;
            tamanho++;
        } else {
            inserirRecursivo(indice + 1, novoExp);
        }
    }

    public void adicionarExperimento(String nome, int complexidade) {
        if (tamanho == lista.length) {
            System.out.println("A lista está cheia!");
            return;
        }
        Experimento novoExp = new Experimento(nome, complexidade);
        inserirRecursivo(0, novoExp);
    }

    // Remover experimento pelo nome
    public void removerExperimento(String nome) {
        for (int i = 0; i < tamanho; i++) {
            if (lista[i].nome.equals(nome)) {
                for (int j = i; j < tamanho - 1; j++) {
                    lista[j] = lista[j + 1];
                }
                lista[tamanho - 1] = null;
                tamanho--;
                System.out.println("Experimento " + nome + " removido.");
                return;
            }
        }
        System.out.println("Experimento não encontrado.");
    }

    // Buscar experimentos por complexidade
    public void buscarPorComplexidade(int complexidade) {
        boolean encontrado = false;
        for (int i = 0; i < tamanho; i++) {
            if (lista[i].complexidade == complexidade) {
                System.out.println(lista[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum experimento com a complexidade " + complexidade + " foi encontrado.");
        }
    }

    // Mostrar todos os experimentos
    public void mostrarExperimentos() {
        if (tamanho == 0) {
            System.out.println("Nenhum experimento na lista.");
        } else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println(lista[i]);
            }
        }
    }
}
