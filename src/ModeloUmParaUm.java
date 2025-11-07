import java.util.ArrayList;
import java.util.List;

public class ModeloUmParaUm {
    public void executar(int numero_threads) {
        System.out.println("\n--- Iniciando modelo 1:1 com " + numero_threads + " threads ---");

        List<Thread> threads_plataforma = new ArrayList<>();

        for (int indice_thread = 1; indice_thread <= numero_threads; indice_thread++) {
            final int id_thread_plataforma = indice_thread;

            Thread thread_plataforma = new Thread(() -> {
                System.out.println("Thread " + id_thread_plataforma + " executando no modelo 1:1");

                try {
                    realizar_tarefa_computacional();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            threads_plataforma.add(thread_plataforma);
            thread_plataforma.start();
        }

        for (Thread thread_plataforma : threads_plataforma) {
            try {
                thread_plataforma.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n--- Modelo 1:1 finalizado ---");
    }

    private void realizar_tarefa_computacional() throws InterruptedException {
        long resultado = 0;
        for (int iteracao = 0; iteracao < 1000000; iteracao++) {
            resultado += Math.sqrt(iteracao) * Math.sin(iteracao);
        }
        Thread.sleep(100);
    }
}
