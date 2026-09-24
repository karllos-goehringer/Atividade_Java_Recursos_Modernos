package num7;

import java.util.*;
import java.util.stream.Collectors;

public class Funcionario {
    private String nome;
    private Double salario;
    private String departamento;

    public Funcionario(String nome, Double salario, String departamento) {
        this.nome = nome;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    static public Optional<Funcionario> buscarFuncionarioPorNome(List<Funcionario> lista, String nome){
        return lista.stream().filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome)).findFirst();
    }
    static public Optional<Double> buscarSalarioPorNome(List<Funcionario> lista, String nome){
        return buscarFuncionarioPorNome(lista,nome).map(Funcionario::getSalario).orElseThrow().describeConstable();
    }
    static public Optional<Funcionario> buscarMaiorSalarioPorDepartamento(List<Funcionario> lista, String departamento) {
        return lista.stream()
                .filter(funcionario -> Objects.equals(funcionario.getDepartamento(), departamento))
                .max(Comparator.comparingDouble(Funcionario::getSalario));
    }
    static public String retornarONomeDeTodosOsFuncionario(List<Funcionario> funcionarios){
        return funcionarios.stream().map(Funcionario::getNome).collect(Collectors.joining(", "));
    }
    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}

