package Factory.models;

import java.time.LocalDateTime;

public class Problema {
    private int codigo;
    private String tipo; // Tipo de problema
    private LocalDateTime data;
    private Funcionario funcionario;

    public Problema() {}

    public Problema(int codigo, String tipo, LocalDateTime data, Funcionario funcionario, Setor setor) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.data = data;
        this.funcionario = funcionario;
        this.setor = setor;
    }

    // Construtor sem o código (para criação de novos problemas)
    public Problema(String tipo, LocalDateTime data, Funcionario funcionario, Setor setor) {
        this.tipo = tipo;
        this.data = data;
        this.funcionario = funcionario;
        this.setor = setor;
    }


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    private Setor setor;

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Setor getSetor() {
        return setor;
    }
}
