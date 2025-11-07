import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ModeloNM {
    public void executar(int numero_threads) {
        System.out.println("\n--- Iniciando modelo N:M com " + numero_threads + " threads virtuais ---");

        long tempo_inicio = System.currentTimeMillis();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int indice_thread = 1; indice_thread <= numero_threads; indice_thread++) {
            final int id_thread_virtual = indice_thread;

            executor.submit(() -> {
                System.out.println("Thread virtual " + id_thread_virtual + " executando no modelo N:M");

                try {
                    realizar_tarefa_computacional();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tempo_fim = System.currentTimeMillis();
        System.out.println("--- Modelo N:M finalizado - " + (tempo_fim - tempo_inicio) + "ms ---");
    }

    private void realizar_tarefa_computacional() throws InterruptedException {
        long resultado = 0;
        for (int iteracao = 0; iteracao < 1000000; iteracao++) {
            resultado += Math.sqrt(iteracao) * Math.sin(iteracao);
        }
        Thread.sleep(100);
    }
}
