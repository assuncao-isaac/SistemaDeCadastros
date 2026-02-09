import java.util.Scanner;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String endereco;
    private double idade;
    private double peso;
    private String raca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O pet precisa de nome e sobrenome");
        }
        String regex = "[A-Za-z]+( [A-Za-z]+)+";
        if (!nome.matches(regex)){
            throw new IllegalArgumentException("O pet não pode ter nome com caracteres especiais ou números e precisa de nome sobrenome");
        }
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        Tipo cachorro = (Tipo.CACHORRO);
        Tipo gato = (Tipo.GATO);
        if (tipo.equalsIgnoreCase("cachorro")){
            this.tipo = cachorro;
        }
        else if (tipo.equalsIgnoreCase("gato")){
            this.tipo = gato;
        }
        else {
            throw new IllegalArgumentException("O pet só pode ser gatou ou cachorro");
        }
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        Sexo macho = (Sexo.MACHO);
        Sexo femea = (Sexo.FEMEA);
        if (sexo.equalsIgnoreCase("macho")){
            this.sexo = macho;
        }
        else if (sexo.equalsIgnoreCase("femea")){
            this.sexo = femea;
        }
        else {
            throw new IllegalArgumentException("O pet só pode ser Macho ou Fêmea");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco() {
        Scanner sc = new Scanner(System.in);
        StringBuilder enderecoTemp = new StringBuilder();
        System.out.println("Número da casa?");
        enderecoTemp.append("Casa: ").append(sc.nextLine()).append(", ");
        System.out.println("Cidade?");
        enderecoTemp.append("Cidade: ").append(sc.nextLine()).append(", ");
        System.out.println("Rua?");
        enderecoTemp.append("Rua: ").append(sc.nextLine());
        if (enderecoTemp.isEmpty()){
            throw new IllegalArgumentException("O pet precisa de um endereco");
        }
        this.endereco = enderecoTemp.toString();
        System.out.println(endereco);
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = Double.parseDouble(VerificadorDouble(idade));
        if (this.idade > 1){
            double resto;
            resto = this.idade - (int)this.idade;
            this.idade -= resto;
        }
        if (this.idade > 20 || this.idade <= 0){
            throw new IllegalArgumentException("Não aceito animais com mais de 20 anos ou com idade negativa");
        }
        System.out.println(this.idade);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = Double.parseDouble(VerificadorDouble(peso));
        if (this.peso > 60 || this.peso < 0.5){
            throw  new IllegalArgumentException("O peso deve ser maior que 0.5kg ou menor que 60kg");
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

    public void setRaca(String raca) {
        String regex = "[A-Za-z ]+";
        if (!raca.matches(regex)){
            throw new IllegalArgumentException("O pet não pode ter nome com caracteres especiais ou números e precisa de nome sobrenome");
        }
        this.raca = raca;
    }
}
