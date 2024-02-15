import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarefa {
    String nome;
    String descricao;
    Date dataDeTermino;
    int prioridadeNivel;
    String categoria;
    String status;

    public Tarefa(String nome, String descricao, Date dataDeTermino, int prioridadeNivel, String categoria, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeTermino = dataDeTermino;
        this.prioridadeNivel = prioridadeNivel;
        this.categoria = categoria;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public int getPrioridadeNivel() {
        return prioridadeNivel;
    }

    public String getStatus() {
        return status;
    }


    public String getCategoria() {
        return categoria;
    }

    public Date getDataDeTermino() {
        return dataDeTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "\n" + "Nome: " + nome + "\n" +
                "Descrição: " + descricao + "\n" +
                "Data de término: " + dateFormat.format(dataDeTermino) + "\n" +
                "Prioridade: " + prioridadeNivel + "\n" +
                "Categoria: " + categoria + "\n" +
                "Status: " + status + "\n\n";
    }
}
