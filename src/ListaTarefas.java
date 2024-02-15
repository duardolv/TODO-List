import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaTarefas {
    List<Tarefa> tarefas = new ArrayList<>();

    public void adcionarTarefa(Tarefa tarefa) {
        int i = 0;
        while (i < tarefas.size() && tarefas.get(i).getPrioridadeNivel() >= tarefa.getPrioridadeNivel()) {
            i++;
        }
        tarefas.add(i, tarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarNomesTarefas() {
        for (Tarefa tares : tarefas) {
            System.out.println(tares.getNome());

        }
    }

    public void removerTarefa(String nome) {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).getNome().equals(nome)) {
                tarefas.remove(i);
                System.out.println("tarefa removida com sucesso!!");
                return;
            }
        }
        System.out.println("tarefa não encontrada!");
    }

    public void listarTarefasPorPrioridade() {
        List<Tarefa> copiaTarefa = new ArrayList<>(tarefas);
        copiaTarefa.sort((t1, t2) -> t2.getPrioridadeNivel() - t1.getPrioridadeNivel());

        for (Tarefa tarefa : copiaTarefa) {
            System.out.println(tarefa.getNome() + " - Prioridade: " + tarefa.getPrioridadeNivel());
        }
    }

    public void listarTarefasPorStatus() {
        List<Tarefa> copiaTarefa = new ArrayList<>(tarefas);
        for (Tarefa tarefa : copiaTarefa) {
            System.out.println(tarefa.getNome() + " - Status: " + tarefa.getStatus());
        }
    }

    public void listarTarefasPorCategoria() {
        List<Tarefa> copiaTarefa = new ArrayList<>(tarefas);
        for (Tarefa tarefa : copiaTarefa) {
            System.out.println(tarefa.getNome() + " - Status: " + tarefa.getCategoria());
        }
    }

    public void filtrarTarefaPorData(Date dataFiltro) {
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getDataDeTermino().equals(dataFiltro)) {
                System.out.println(tarefa);
            }
        }
    }

    public int contarTarefasPorStatus(String status) {
        int count = 0;
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getStatus().equalsIgnoreCase(status)) {
                count++;
            }
        }
        return count;
    }

    public void atualizarTarefa(String selectNomeTarefa, Tarefa tarefaAtualizada) {
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            if (tarefa.getNome().equals(selectNomeTarefa)) {
                tarefas.set(i, tarefaAtualizada);
                System.out.println("Tarefa atualizada com sucesso!");
                return;
            }
        }
        System.out.println("tarefa não encontrada!!");
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @Override
    public String toString() {
        return "ListaTarefas{" +
                "tarefas=" + tarefas +
                '}';
    }
}
