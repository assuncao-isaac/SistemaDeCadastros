import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdocaoPet {
    public static void main(String[] args) {

        menu();

    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        do {
            File file = new File("menu.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = bufferedReader.readLine()) != null){
                    System.out.println(linha);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                escolha = sc.nextInt();
            }catch (InputMismatchException e){
                throw new IllegalArgumentException("Argumento inválido");
            }
        }while(escolha <= 0 || escolha > 6);
    }

    private static void formulario() {
        File file = new File("formulario.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
