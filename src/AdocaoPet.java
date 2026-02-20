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
                        alterandoDados();
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


    private static void alterandoDados() {
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
        String tipo = "";
        String sexo;
        while (tipo.isEmpty()) {
            System.out.println("Qual o tipo do pet?\n1 - Cachorro\n2 - Gato");
            switch (sc.nextLine().trim()) {
                case "1":
                    tipo = "cachorro";
                    break;
                case "2":
                    tipo = "gato";
                    break;
                default:
                    System.out.println("Inválido");
                    break;
            }
        }
        File pastaPet = new File("petsCadastrados");
        ArrayList<File> petsTipo = new ArrayList<>();

        String procura = "";
        Pet pet = new Pet();
        if (pastaPet != null) {
            petsTipo.addAll(Arrays.asList(pastaPet.listFiles()));
            petsTipo = busca(petsTipo, tipo);
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
            sc.nextLine();
            for (int i = 0; i < criterio; i++) {
                boolean repetir = false;
                do {
                    System.out.println("Escolha o " + (i + 1) + "* critério de busca:\n1 - Nome \n2 - Sexo\n3 - Idade\n4 - Peso\n5 - Raça\n6 - Endereço");
                    switch (sc.nextLine().trim()) {
                        case "1":
                            System.out.println("Nome para busca:");
                            pet.setNome(sc.nextLine());
                            procura = pet.getNome();
                            break;
                        case "2":
                            System.out.println("Sexo para busca:");
                            pet.setSexo(sc.nextLine());
                            procura = pet.getSexo();
                            break;
                        case "3":
                            System.out.println("Idade para busca:");
                            pet.setIdade(sc.nextLine());
                            procura = pet.getIdade();
                            break;
                        case "4":
                            System.out.println("Peso para busca:");
                            pet.setPeso(sc.nextLine());
                            procura = pet.getPeso();
                            break;
                        case "5":
                            System.out.println("Raça para busca:");
                            pet.setRaca(sc.nextLine());
                            procura = pet.getRaca();
                            break;
                        case "6":
                            System.out.println("Endereço para busca:");
                            pet.setEndereco();
                            procura = pet.getEndereco();
                            break;
                        default:
                            System.out.println("Inválido");
                            repetir = true;
                            break;
                    }
                } while (repetir);
                petsTipo = busca(petsTipo, procura);
            }
            int petEscolhido;
            do {
                System.out.println("Qual pet corresponde ao de sua procura ?");
                petEscolhido = sc.nextInt() - 1;

            } while (!petsTipo.get(petEscolhido).exists());
            alterandoDados(petsTipo.get(petEscolhido), tipo, buscaSilenciosa(petsTipo.get(petEscolhido)));
        }
    }

    private static ArrayList<File> busca(ArrayList<File> pets, String procura) throws FileNotFoundException {
        ArrayList<File> petsTipo = new ArrayList<>();
        boolean escreva;
        for (File pet : pets) {
            escreva = false;
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
    private static String buscaSilenciosa(File petPalterar) throws FileNotFoundException {
        String sexo = "";
                try (BufferedReader leitor = new BufferedReader(new FileReader(petPalterar))) {
                    StringBuilder linha = new StringBuilder();
                    while (leitor.read() != -1) {
                        linha.append(leitor.readLine().split(" - ", 2)[1]).append(" - ");
                        if (linha.toString().toLowerCase().contains("macho".toLowerCase())) {
                            sexo = "macho";
                        }

                    }
                    if (sexo.isEmpty()) {
                        sexo = "femea";
                    }
                } catch (IOException e) {
                    throw new FileNotFoundException("Arquivo não encontrado");
                }
        return sexo;
    }

    private static void alterandoDados(File petPalterar, String tipo, String sexo) {
        System.out.println("!!!ALTERANDO OS DADOS DO PET!!!!");
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
                        novoPet.setTipo(tipo);
                        System.out.println(tipo);
                        break;
                    case 3:
                        novoPet.setSexo(sexo);
                        System.out.println(sexo);
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
            arquivos.excluirArquivo(petPalterar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
