import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SimuladorGUI extends JFrame {
    private JTextArea outputArea;
    private JButton gerarProcessosBtn, executarSJFBtn, executarRRBtn;
    private JTextField quantumField;
    private List<Processo> processos;

    public SimuladorGUI() {
        setTitle("Simulador de Escalonamento");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);

        JPanel painelControle = new JPanel();

        gerarProcessosBtn = new JButton("Gerar Processos");
        executarSJFBtn = new JButton("Executar SJF");
        executarRRBtn = new JButton("Executar Round Robin");
        quantumField = new JTextField("3", 5);

        painelControle.add(gerarProcessosBtn);
        painelControle.add(executarSJFBtn);
        painelControle.add(new JLabel("Quantum:"));
        painelControle.add(quantumField);
        painelControle.add(executarRRBtn);

        add(painelControle, BorderLayout.NORTH);

        gerarProcessosBtn.addActionListener(e -> gerarProcessos());
        executarSJFBtn.addActionListener(e -> executarSJF());
        executarRRBtn.addActionListener(e -> executarRR());
    }

    private void gerarProcessos() {
        processos = Simulador.gerarProcessos(6);
        outputArea.append("=== Processos Gerados ===\n");
        for (Processo p : processos) {
            outputArea.append(p + "\n");
        }
    }

    private void executarSJF() {
        if (processos == null) return;
        List<Processo> copia = new ArrayList<>();
        for (Processo p : processos) {
            copia.add(new Processo(p.id, p.tempoChegada, p.tempoExecucao));
        }
        Escalonador.SJF(copia);
        outputArea.append("\n=== Resultados SJF ===\n");
        for (Processo p : copia) {
            outputArea.append(p + " -> Espera: " + p.tempoEspera + ", Turnaround: " + p.tempoTurnaround + "\n");
        }
        Metrica.calcularMetricas(copia, "SJF");
    }

    private void executarRR() {
        if (processos == null) return;
        int quantum = Integer.parseInt(quantumField.getText());
        List<Processo> copia = new ArrayList<>();
        for (Processo p : processos) {
            copia.add(new Processo(p.id, p.tempoChegada, p.tempoExecucao));
        }
        outputArea.append("\n=== Execução Round Robin ===\n");
        Escalonador.RoundRobin(copia, quantum);
        for (Processo p : copia) {
            outputArea.append(p + " -> Espera: " + p.tempoEspera + ", Turnaround: " + p.tempoTurnaround + "\n");
        }
        Metrica.calcularMetricas(copia, "Round Robin");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimuladorGUI gui = new SimuladorGUI();
            gui.setVisible(true);
        });
    }
}
