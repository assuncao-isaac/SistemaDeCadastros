import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Arquivos {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    static LocalDateTime dt = LocalDateTime.now();

      void criacaodeArquivo(Pet pet) {
        String data = formatter.format(dt);
        String nomeComposto = pet.getNome();
        nomeComposto = nomeComposto.replace(" ", "").toUpperCase();
        String nomeArquivo = String.format("%s-%s.txt", data, nomeComposto);


        File file = new File("C:\\Users\\isaacassuncao\\IdeaProjects\\SistemaDeCadastros\\petsCadastrados\\" + nomeArquivo);
        try (FileWriter fileWriter = new FileWriter(file)) {
            String[] strings = new String[]{pet.getNome(), pet.getTipo().getDescricao(), pet.getSexo(), pet.getEndereco(), String.format("%s anos", pet.getIdade()), String.format("%s KG", pet.getPeso()), String.format("Raça: %s", pet.getRaca())};
            for (int i = 0; i <= strings.length - 1; i++) {
                fileWriter.write((i + 1) + " - " + strings[i] + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Erro na criação do arquivo");
        }
    }
    void excluirArquivo(File file){
        if (file.delete()) {
            System.out.println("Arquivo renomeado!");
        } else {
            System.out.println("Não foi possível apagar o arquivo.");
        }
    }

}
