import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {
        ListaTarefas listaTarefas = Persistencia.carregar();
        if (listaTarefas == null) {
            listaTarefas = new ListaTarefas();
        }
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Remover tarefa");
            System.out.println("3 - Listar tarefas");
            System.out.println("4 - Filtrar por data");
            System.out.println("5 - Consultar status das tarefas");
            System.out.println("6 - Atualizar tarefa");
            System.out.println("7 - Salvar e Sair");
            System.out.println("Escolha uma opção");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao < 1 || opcao > 7) {
                System.out.println("opção Inválida, por favor escolha uma opção entre 1 e 7");

                continue;
            }

            if (opcao == 7) {
                Persistencia.salvar(listaTarefas);
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        System.out.println("Nome não pode ser vazio!");
                        continue;
                    }

                    System.out.println("Descrição: ");
                    String descricao = scanner.nextLine();
                    if (descricao.isEmpty()) {
                        System.out.println("Descrição não pode ser vazia!");
                        continue;
                    }

                    System.out.println("Data de termino (dd/MM/yyyy): ");
                    Date dataTermino = null;
                    try {
                        dataTermino = dateFormat.parse(scanner.nextLine());
                    } catch (ParseException e) {
                        System.out.println("Data inválida por favor digite a data no formato dd/MM/yyyy.");
                        continue;
                    }

                    System.out.println("Prioridade (1-5, 1: menos importante, 5: mais importante): ");
                    int prioridade = scanner.nextInt();
                    scanner.nextLine();
                    if (prioridade < 1 || prioridade > 5) {
                        System.out.println("Prioridade inválida. Por favor, insira um número entre 1 e 5.");
                        continue;
                    }

                    System.out.println("Categoria: ");
                    String categoria = scanner.nextLine();
                    if (categoria.isEmpty()) {
                        System.out.println("categoria não pode ser vazia");
                        continue;
                    }

                    System.out.println("Status (ToDo, Doing, Done): ");
                    String status = scanner.nextLine();
                    if (!status.equalsIgnoreCase("ToDo") && !status.equalsIgnoreCase("Doing") && !status.equalsIgnoreCase("Done")) {
                        System.out.println("Status inválido. Por favor, insira ToDo, Doing ou Done.");
                        continue;
                    }

                    Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
                    listaTarefas.adcionarTarefa(tarefa);
                    break;

                case 2:
                    System.out.println("escolha alguma dessas tarefas para remover: ");
                    listaTarefas.listarNomesTarefas();
                    String nomeTarefa = scanner.nextLine();
                    listaTarefas.removerTarefa(nomeTarefa);
                    break;

                case 3:
                    System.out.println(" Como deseja listar as Tarefas?");
                    System.out.println("1 - listar tudo");
                    System.out.println("2 - por prioridade");
                    System.out.println("3 - por status");
                    System.out.println("4 - por categoria");

                    int escolhaComoListar = scanner.nextInt();
                    scanner.nextLine();
                    if (escolhaComoListar < 1 || escolhaComoListar > 4) {
                        System.out.println("opção Inválida, por favor escolha uma opção entre 1 e 4");

                        continue;
                    }

                    switch (escolhaComoListar) {
                        case 1:
                            System.out.println(listaTarefas);
                            break;
                        case 2:
                            listaTarefas.listarTarefasPorPrioridade();
                            break;
                        case 3:
                            listaTarefas.listarTarefasPorStatus();
                            break;
                        case 4:
                            listaTarefas.listarTarefasPorCategoria();
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Digite a data (dd/MM/yyyy): ");
                    Date dataFiltro;
                    try {
                        dataFiltro = dateFormat.parse(scanner.nextLine());
                    } catch (ParseException e) {
                        System.out.println("Data inválida por favor digite a data no formato dd/MM/yyyy.");
                        continue;
                    }
                    listaTarefas.filtrarTarefaPorData(dataFiltro);
                    break;

                case 5:
                    System.out.println("Tarefas concluidas (Done): " + listaTarefas.contarTarefasPorStatus("Done"));
                    System.out.println("Tarefas para fazer (toDo): " + listaTarefas.contarTarefasPorStatus("toDo"));
                    System.out.println("Tarefas sendo feitas (Doing): " + listaTarefas.contarTarefasPorStatus("Doing"));
                    break;

                case 6:
                    System.out.println("Selecione a tarefa que deseja atualizar: ");
                    listaTarefas.listarNomesTarefas();
                    String selectNomeTarefa = scanner.nextLine();
                    if (selectNomeTarefa.isEmpty()) {
                        System.out.println("Tarefa não encontrada por favor tente novamente!");
                        continue;
                    }

                    System.out.println("Nome: ");
                    String nomeAtualizado = scanner.nextLine();
                    if (nomeAtualizado.isEmpty()) {
                        System.out.println("Nome não pode ser vazio!");
                        continue;
                    }

                    System.out.println("Descrição: ");
                    String descricaoAtualizada = scanner.nextLine();
                    if (descricaoAtualizada.isEmpty()) {
                        System.out.println("Descrição não pode ser vazia!");
                        continue;
                    }

                    System.out.println("Data de termino (dd/MM/yyyy): ");
                    Date dataTerminoAtualizada = null;
                    try {
                        dataTerminoAtualizada = dateFormat.parse(scanner.nextLine());
                    } catch (ParseException e) {
                        System.out.println("Data inválida por favor digite a data no formato dd/MM/yyyy.");
                        continue;
                    }

                    System.out.println("Prioridade (1-5, 1: menos importante, 5: mais importante): ");
                    int prioridadeAtualizada = scanner.nextInt();
                    scanner.nextLine();
                    if (prioridadeAtualizada < 1 || prioridadeAtualizada > 5) {
                        System.out.println("Prioridade inválida. Por favor, insira um número entre 1 e 5.");
                        continue;
                    }

                    System.out.println("Categoria: ");
                    String categoriaAtualizada = scanner.nextLine();
                    if (categoriaAtualizada.isEmpty()) {
                        System.out.println("Categoria não pode ser vazia!");
                        continue;
                    }

                    System.out.println("Status (ToDo, Doing, Done): ");
                    String statusAtualizado = scanner.nextLine();
                    if (!statusAtualizado.equalsIgnoreCase("ToDo") && !statusAtualizado.equalsIgnoreCase("Doing") && !statusAtualizado.equalsIgnoreCase("Done")) {
                        System.out.println("Status inválido. Por favor, insira ToDo, Doing ou Done.");
                        continue;
                    }

                    Tarefa tarefaAtualizada = new Tarefa(nomeAtualizado, descricaoAtualizada, dataTerminoAtualizada, prioridadeAtualizada, categoriaAtualizada, statusAtualizado);
                    listaTarefas.atualizarTarefa(selectNomeTarefa, tarefaAtualizada);
            }
        }
    }
}
