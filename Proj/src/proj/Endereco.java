package proj;

public class Endereco {
    String cep, logradouro, complemento, cidade, uf;
    int numero;

    public Endereco(String cep, String logradouro, String complemento, String cidade, String uf, int numero) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
    }

    // Getters and setters

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Fim dos Getters and setters
}
