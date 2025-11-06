public class ModeloUmParaUm {
    public void executar(int numeroThreads) {
        System.out.println("\n--- Iniciando modelo 1:1 com " + numeroThreads + " threads ---");
        
        for (int i = 1; i <= numeroThreads; i++) {
            final int threadId = i;
            
            Thread thread = new Thread(() -> {
                System.out.println("Thread " + threadId + " executando no modelo 1:1");
                
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            
            thread.start();
        }
        
        System.out.println("\n--- Modelo 1:1 criou " + numeroThreads + " threads ---");
    }
}