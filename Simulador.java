import java.util.*;

public class Simulador {

    public static List<Processo> gerarProcessos(int qtd) {
        List<Processo> processos = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= qtd; i++) {
            int chegada = rand.nextInt(10);
            int exec = rand.nextInt(9) + 1;
            processos.add(new Processo(i, chegada, exec));
        }
        return processos;
    }

    public static void executarComThreads(List<Processo> processos, int numThreads) {
        System.out.println("=== Simulação com " + numThreads + " Threads ===");

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads && i < processos.size(); i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                Processo p = processos.get(finalI);
                System.out.println("Thread " + Thread.currentThread().threadId() + " executando " + p);
                try {
                    Thread.sleep(p.tempoExecucao * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + Thread.currentThread().threadId() + " finalizou " + p);
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
