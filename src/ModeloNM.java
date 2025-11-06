public class ModeloNM {
    public void executar(int numeroThreads) {
        System.out.println("\n--- Iniciando modelo N:M com " + numeroThreads + " threads ---");
        
        for (int i = 1; i <= numeroThreads; i++) {
            final int threadId = i;
            
            Thread thread = new Thread(() -> {
                System.out.println("Thread " + threadId + " executando no modelo N:M");
                
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            
            thread.start();
        }
        
        System.out.println("\n--- Modelo N:M criou " + numeroThreads + " threads ---");
    }
}