import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persistencia {
    private static final String arquivo = "src/tarefas.txt";

    public static ListaTarefas carregar() {
        ListaTarefas listaTarefas = new ListaTarefas();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(", ");
                if (partes.length == 6) {
                    String nomeDaTarefa = partes[0].split(": ")[1];
                    String descricao = partes[1].split(": ")[1];
                    Date dataDeVencimento = new SimpleDateFormat("dd/MM/yyyy").parse(partes[2].split(": ")[1]);
                    int prioridadeNivel = Integer.parseInt(partes[3].split(": ")[1]);
                    String categoria = partes[4].split(": ")[1];
                    String status = partes[5].split(": ")[1];
                    Tarefa tarefa = new Tarefa(nomeDaTarefa, descricao, dataDeVencimento, prioridadeNivel, categoria, status);
                    listaTarefas.adcionarTarefa(tarefa);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaTarefas;
    }

    public static void salvar(ListaTarefas listaTarefas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            for (Tarefa tarefa : listaTarefas.getTarefas()) {
                writer.println("Nome: " + tarefa.getNome() + ", " +
                        "Descrição: " + tarefa.getDescricao() + ", " +
                        "Data de término: " + new SimpleDateFormat("dd/MM/yyyy").format(tarefa.getDataDeTermino()) + ", " +
                        "Prioridade: " + tarefa.getPrioridadeNivel() + ", " +
                        "Categoria: " + tarefa.getCategoria() + ", " +
                        "Status: " + tarefa.getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
