import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdocaoPet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha;
        do {
            File file = new File("menu.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = bufferedReader.readLine()) != null) {
                    System.out.println(linha);
                }
                escolha = sc.nextInt();
                switch (escolha) {
                    case 1:
                        cadastrarNovoPet();
                        break;
                    case 2:
                        buscadorDePet();
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
            } catch (InputMismatchException e) {
                throw new IllegalArgumentException("Argumento inválido");
            }
        } while (escolha != 6);

    }


    private static void cadastrarNovoPet() {
        Scanner sc = new Scanner(System.in);
        Pet novoPet = new Pet();
        File file = new File("formulario.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int contador = 1;
            boolean obrigatorioOK;
            for (String linha : bufferedReader.readAllLines()) {
                System.out.println(linha);
                switch (contador) {
                    case 1:
                        novoPet.setNome(sc.nextLine());
                        break;
                    case 2:
                        do {
                            obrigatorioOK = novoPet.setTipo(sc.nextLine());
                        } while (!obrigatorioOK);
                        break;
                    case 3:
                        do {
                            obrigatorioOK = novoPet.setSexo(sc.nextLine());
                        } while (!obrigatorioOK);
                        break;
                    case 4:
                        novoPet.setEndereco();
                        break;
                    case 5:
                        novoPet.setIdade(sc.nextLine());
                        break;
                    case 6:
                        novoPet.setPeso(sc.nextLine());
                        break;
                    case 7:
                        do {
                            obrigatorioOK = novoPet.setRaca(sc.nextLine());
                        } while (!obrigatorioOK);
                        break;
                    default:
                        break;

                }
                contador++;
            }

            System.out.println(novoPet);
            Arquivos arquivos = new Arquivos();
            arquivos.criacaodeArquivo(novoPet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void buscadorDePet() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o tipo do pet?(Cachorro/Gato)");
        String procura = sc.nextLine().trim();
        while (!procura.equalsIgnoreCase("cachorro") && !procura.equalsIgnoreCase("gato")) {
            System.out.println("informar o tipo do pet é obrigatório");
            procura = sc.nextLine();
        }

        File pastaPet = new File("C:\\Users\\isaacassuncao\\IdeaProjects\\SistemaDeCadastros\\petsCadastrados");
        File[] pets = pastaPet.listFiles();


        if (pets != null) {
            int listagem = 1;
            for (File pet : pets) {
                if (pet.getName().endsWith(".txt")) {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(pet))) {
                        String linha;
                        while ((linha = leitor.readLine()) != null) {
                            if (linha.toLowerCase().contains(procura.toLowerCase())) {
                                BufferedReader leitor2 = new BufferedReader(new FileReader(pet));
                                System.out.print(listagem);
                                while ((linha = leitor2.readLine()) != null) {
                                    System.out.print(" - " + linha.split(" - ", 2)[1]);
                                }
                                listagem++;
                                System.out.println(" ");
                                leitor2.close();
                            }
                        }
                    } catch (IOException e) {
                        throw new FileNotFoundException("Arquivo não encontrado");
                    }

                }
            }
        }
        System.out.println("Escolha outro critério");
    }

}
