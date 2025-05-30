import java.util.List;

public class Metrica {

    public static void calcularMetricas(List<Processo> processos, String algoritmo) {
        int totalEspera = 0;
        int totalTurnaround = 0;
        int makespan = 0;

        for (Processo p : processos) {
            totalEspera += p.tempoEspera;
            totalTurnaround += p.tempoTurnaround;
            int fim = p.tempoChegada + p.tempoTurnaround;
            if (fim > makespan) {
                makespan = fim;
            }
        }

        double mediaEspera = (double) totalEspera / processos.size();
        double mediaTurnaround = (double) totalTurnaround / processos.size();

        System.out.println("\n=== Métricas para " + algoritmo + " ===");
        System.out.printf("Tempo médio de espera: %.2f\n", mediaEspera);
        System.out.printf("Tempo médio de turnaround: %.2f\n", mediaTurnaround);
        System.out.println("Tempo total de execução (makespan): " + makespan);
    }
}