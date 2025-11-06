import java.util.Scanner;

public class Menu {
    public void executarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("\n=== COMPARAÇÃO DE MODELOS DE THREADS ===");
            System.out.println("1. Testar com 10 threads");
            System.out.println("2. Testar com 50 threads");
            System.out.println("3. Testar com 100 threads");
            System.out.println("4. Testar com 500 threads");
            System.out.println("5. Testar com 1000 threads");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            if (opcao == 1) {
                testarModelos(10);
            } else if (opcao == 2) {
                testarModelos(50);
            } else if (opcao == 3) {
                testarModelos(100);
            } else if (opcao == 4) {
                testarModelos(500);
            } else if (opcao == 5) {
                testarModelos(1000);
            } else if (opcao == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
    }
    
    public void testarModelos(int numThreads) {
        System.out.println("\n--- Testando com " + numThreads + " threads ---");
        
        long inicioNM = System.currentTimeMillis();
        ModeloNM modeloNM = new ModeloNM();
        modeloNM.executar(numThreads);
        long fimNM = System.currentTimeMillis();
        
        long inicio11 = System.currentTimeMillis();
        ModeloUmParaUm modelo11 = new ModeloUmParaUm();
        modelo11.executar(numThreads);
        long fim11 = System.currentTimeMillis();
        
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("\nModelo N:M: " + (fimNM - inicioNM) + "ms");
        System.out.println("\nModelo 1:1: " + (fim11 - inicio11) + "ms");
    }
}