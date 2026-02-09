public enum Sexo {
    MACHO("Macho"), FEMEA("Fêmea");

    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
