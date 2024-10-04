package Factory.models;

import java.util.List;

public class Setor {
    private int codigo;
    private String nome;
    private List<Funcionario> funcionarios;

    public Setor() {}

    // Construtor com todos os atributos
    public Setor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

}
