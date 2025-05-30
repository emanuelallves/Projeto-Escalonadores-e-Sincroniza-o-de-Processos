public class Processo {
    int id;
    int tempoChegada;
    int tempoExecucao;
    int tempoRestante;
    int tempoEspera;
    int tempoTurnaround;

    public Processo(int id, int tempoChegada, int tempoExecucao) {
        this.id = id;
        this.tempoChegada = tempoChegada;
        this.tempoExecucao = tempoExecucao;
        this.tempoRestante = tempoExecucao;
    }

    @Override
    public String toString() {
        return "P" + id + " [chegada=" + tempoChegada + ", exec=" + tempoExecucao + "]";
    }
}