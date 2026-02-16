import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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


        if (pastaPet != null) {
            petsTipo.addAll(Arrays.asList(pastaPet.listFiles()));
            petsTipo = busca(petsTipo, procura);
        }
        if (petsTipo.isEmpty()) {
            System.out.println("Nenhum arquivo com o critério: \"" + procura + "\" encontrado");
        } else {
            procura = "";
            int criterio;
            System.out.println("Quantidade de critérios de busca:");
            System.out.println("------- > 1");
            System.out.println("------- > 2");
            criterio = sc.nextInt();
            for (int i = 0; i <= criterio; i++) {
                while (procura.isEmpty()) {
                    System.out.println("Escolha o " + (i+1) + "* critério de busca:\n1 - Nome \n2 - Sexo\n3 - Idade\n4 - Peso\n5 - Raça\n6 - Endereço");
                    switch (sc.nextLine().trim()) {
                        case "1":
                            break;
                        case "2":
                            break;
                        case "3":
                            break;
                        case "4":
                            break;
                        case "5":
                            break;
                        case "6":
                            break;
                        default:
                            System.out.println("Inválido");
                            break;
                    }
                }
            }
        }
    }

    private static ArrayList<File> busca(ArrayList<File> pets, String procura) throws FileNotFoundException {
        ArrayList<File> petsTipo = new ArrayList<>();
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
                        System.out.print(petsTipo.size() + " " + linha);
                        System.out.println();
                    }
                } catch (IOException e) {
                    throw new FileNotFoundException("Arquivo não encontrado");
                }
            }
        }
        return petsTipo;
    }


}
