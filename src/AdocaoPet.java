import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
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
        String procura = "";
        while (procura.isEmpty()) {
            System.out.println("Qual o tipo do pet?\n1 - Cachorro\n2 - Gato");
            switch (sc.nextLine().trim()) {
                case "1":
                    procura = "cachorro";
                    break;
                case "2":
                    procura = "gato";
                    break;
                default:
                    System.out.println("Inválido");
                    break;
            }
        }
        File pastaPet = new File("petsCadastrados");
        ArrayList<File> petsTipo = new ArrayList<>();
        File[] pets = pastaPet.listFiles();

        if (pets != null) {
            int listagem = 1;
            for (File pet : pets) {
                boolean escreva = false;
                if (pet.getName().endsWith(".txt")) {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(pet))) {
                        StringBuilder linha = new StringBuilder();
                        while (leitor.read() != -1) {
                            linha.append(leitor.readLine().split(" - ", 2)[1]).append(" - ");
                            if (linha.toString().toLowerCase().contains(procura.toLowerCase())) {
                                escreva = true;
                            }

                        }
                        if (escreva) {
                            petsTipo.add(pet);
                            System.out.print(listagem +" "+ linha);
                            System.out.println();
                            listagem++;

                        }
                    } catch (IOException e) {
                        throw new FileNotFoundException("Arquivo não encontrado");
                    }
                }
            }
            if (listagem == 1) {
                System.out.println("Nenhum arquivo com o critério: \"" + procura + "\" encontrado");
            }
        }
        System.out.println("=========================================================");
//        else{
//            buscadorDePet(petsTipo);
//        }
    }


}
