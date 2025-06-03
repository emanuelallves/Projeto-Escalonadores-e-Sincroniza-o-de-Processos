import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Processo> processos = Simulador.gerarProcessos(6);

        System.out.println("=== Processos Gerados ===");
        for (Processo p : processos) {
            System.out.println(p);
        }

        List<Processo> processosSJF = new ArrayList<>();
        for (Processo p : processos) {
            processosSJF.add(new Processo(p.id, p.tempoChegada, p.tempoExecucao));
        }

        List<Processo> processosRR = new ArrayList<>();
        for (Processo p : processos) {
            processosRR.add(new Processo(p.id, p.tempoChegada, p.tempoExecucao));
        }

        Escalonador.SJF(processosSJF);

        Escalonador.RoundRobin(processosRR, 3);

        Simulador.executarComThreads(processos, 2);
        Simulador.executarComThreads(processos, 4);
        Simulador.executarComThreads(processos, 6);

        Escalonador.SJF(processosSJF);
        Metrica.calcularMetricas(processosSJF, "SJF");

        Escalonador.RoundRobin(processosRR, 3);
        Metrica.calcularMetricas(processosRR, "Round Robin");
    }
}
