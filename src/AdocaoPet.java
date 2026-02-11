import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdocaoPet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        do {
            File file = new File("menu.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = bufferedReader.readLine()) != null){
                    System.out.println(linha);
                }
                escolha = sc.nextInt();
                switch (escolha){
                    case 1:
                        cadastrarNovoPet();
                    break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (InputMismatchException e){
                throw new IllegalArgumentException("Argumento inválido");
            }
        }while(escolha <= 0 || escolha > 6);

    }


        private static void cadastrarNovoPet() {
        Scanner sc = new Scanner(System.in);
            Pet novoPet = new Pet();
            File file = new File("formulario.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                System.out.println(bufferedReader.readLine());
                novoPet.setNome(sc.nextLine());
                System.out.println(bufferedReader.readLine());
                novoPet.setTipo(sc.nextLine());
                System.out.println(bufferedReader.readLine());
                novoPet.setSexo(sc.nextLine());
                System.out.println(bufferedReader.readLine());
                novoPet.setEndereco();
                System.out.println(bufferedReader.readLine());
                novoPet.setIdade(sc.nextLine());
                System.out.println(bufferedReader.readLine());
                novoPet.setPeso(sc.nextLine());
                System.out.println(bufferedReader.readLine());
                novoPet.setRaca(sc.nextLine());
                System.out.println(novoPet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
