public enum Tipo {
    CACHORRO("Cachorro"), GATO("Gato");

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
