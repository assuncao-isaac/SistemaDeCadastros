import java.util.Scanner;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String endereco;
    private String idade;
    private String peso;
    private String raca;
    static final String NAOINFORME = "Não informado";

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = NAOINFORME;
            System.out.println(NAOINFORME);
        } else {
            String regex = "[A-Za-z]+( [A-Za-z]+)+";
            if (!nome.matches(regex)) {
                throw new IllegalArgumentException("O pet precisa de nome e sobrenome (sem carcteres especiais ou números)");
            }
            this.nome = nome;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public boolean setTipo(String tipo) {
        Tipo cachorro = (Tipo.CACHORRO);
        Tipo gato = (Tipo.GATO);
        if (tipo.equalsIgnoreCase("cachorro")) {
            this.tipo = cachorro;
            return true;
        } else if (tipo.equalsIgnoreCase("gato")) {
            this.tipo = gato;
            return true;
        } else {
            System.out.println("informar o tipo do pet é obrigatório");
            return false;
        }
    }

    public String getSexo() {
        return sexo.getSexo();
    }

    public boolean setSexo(String sexo) {
        Sexo macho = (Sexo.MACHO);
        Sexo femea = (Sexo.FEMEA);
        if (sexo.equalsIgnoreCase("macho")) {
            this.sexo = macho;
            return true;
        } else if (sexo.equalsIgnoreCase("femea")) {
            this.sexo = femea;
            return true;
        } else {
            System.out.println("Informar o sexo do pet é obrigatorio");
            return false;
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco() {
        Scanner sc = new Scanner(System.in);
        StringBuilder enderecoTemp = new StringBuilder();
        System.out.println("Número da casa?");
        String casa = sc.nextLine();
        if (casa.isBlank()) {
            this.endereco = NAOINFORME;
        } else {
            enderecoTemp.append("Casa:").append(casa);
            System.out.println("Cidade?");
            enderecoTemp.append("Cidade:").append(sc.nextLine());
            System.out.println("Rua?");
            enderecoTemp.append("Rua:").append(sc.nextLine());
            if (enderecoTemp.isEmpty()) {
                throw new IllegalArgumentException("O pet precisa de um endereco");
            }
            this.endereco = enderecoTemp.toString();
        }
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        if (idade == null || idade.isBlank()) {
            this.idade = NAOINFORME;
        } else {
            double temp = Double.parseDouble(VerificadorDouble(idade));
            if (temp > 1) {
                double resto;
                resto = temp - (int) temp;
                temp -= resto;
            }
            if (temp > 20 || temp <= 0) {
                throw new IllegalArgumentException("Não aceito animais com mais de 20 anos ou com idade negativa");
            }
            this.idade = String.valueOf(temp);
        }
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        if (peso == null || peso.isBlank()) {
            this.peso = NAOINFORME;
        } else {
            double temp = Double.parseDouble(VerificadorDouble(peso));
            if (temp > 60 || temp < 0.5) {
                throw new IllegalArgumentException("O peso deve ser maior que 0.5kg ou menor que 60kg");
            }
            this.peso = String.valueOf(temp);
        }
    }

    private String VerificadorDouble(String peso) {
        String regex = "[0-9.,]+";
        if (peso.matches(regex)) {
            peso = peso.replace(",", ".");
        } else {
            throw new IllegalArgumentException("a idade pode ter apenas numeros pontos e virgula");
        }
        return peso;
    }

    public String getRaca() {
        return raca;
    }

    public boolean setRaca(String raca) {
        String regex = "[A-Za-z ]+";
        if (!raca.matches(regex)) {
            System.out.println("Obrigatorio informar uma raça valida");
            return false;
        }
        this.raca = raca;
        return true;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", endereco='" + endereco + '\'' +
                ", idade='" + idade + '\'' +
                ", peso='" + peso + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
}
