import java.util.*;

public class Escalonador {

    public static void SJF(List<Processo> processos) {
        processos.sort(Comparator.comparingInt(p -> p.tempoExecucao));

        int tempoAtual = 0;
        for (Processo p : processos) {
            p.tempoEspera = Math.max(0, tempoAtual - p.tempoChegada);
            tempoAtual += p.tempoExecucao;
            p.tempoTurnaround = p.tempoEspera + p.tempoExecucao;
        }

        System.out.println("=== Resultados SJF ===");
        for (Processo p : processos) {
            System.out.println(p + " -> Espera: " + p.tempoEspera + ", Turnaround: " + p.tempoTurnaround);
        }
    }

    public static void RoundRobin(List<Processo> processos, int quantum) {
        Queue<Processo> fila = new LinkedList<>(processos);
        int tempoAtual = 0;

        System.out.println("=== Execução Round Robin ===");
        while (!fila.isEmpty()) {
            Processo p = fila.poll();

            if (p.tempoRestante > quantum) {
                System.out.println("P" + p.id + " executa por " + quantum);
                p.tempoRestante -= quantum;
                tempoAtual += quantum;
                fila.add(p);
            } else {
                System.out.println("P" + p.id + " executa por " + p.tempoRestante + " e finaliza.");
                tempoAtual += p.tempoRestante;
                p.tempoEspera = Math.max(0, tempoAtual - p.tempoChegada - p.tempoExecucao);
                p.tempoTurnaround = tempoAtual - p.tempoChegada;
                p.tempoRestante = 0;
            }
        }
    }
}
