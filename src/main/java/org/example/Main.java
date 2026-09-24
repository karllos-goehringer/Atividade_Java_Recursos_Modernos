package org.example;

import num5.BuscarPorID;
import num7.Funcionario;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 2
        Predicate<Integer> verificadorPar = numero -> numero % 2 == 0;
        Consumer<String> consumerCaixaAlta = string -> System.out.println(string.toUpperCase());
        // 3
        List<Integer> lista = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            lista.add(i);
        }
        lista.stream().filter(num -> num % 3 == 0).forEach(System.out::println);
        // 4
        List<String> nomesAlunos = List.of(
                "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
                "Fernanda", "Gabriel", "Helena", "Igor", "Julia",
                "Kleber", "Laura", "Marcelo", "Natalia", "Otavio",
                "Paula", "Rafael", "Silvia", "Thiago", "Ursula",
                "Vinicius", "Wagner", "Xavier", "Yara", "Zeca",
                "Alice", "Bernardo", "Camila", "Diogo", "Elisa"
        );
        List<String> nomesAlunosCaixaAlta = nomesAlunos.stream().map(String::toUpperCase).toList();
        System.out.println(nomesAlunosCaixaAlta);
        //5
        BuscarPorID buscar = new BuscarPorID();
        buscar.buscarPorIdc(nomesAlunos,3).ifPresentOrElse(
                valor -> System.out.println("Valor encontrado: " + valor),
                () -> System.out.println("Nenhum valor encontrado")
        );
        //6

        List<String> produtos = List.of(
                "Notebook", "Smartphone", "Teclado Mecânico", "Mouse Gamer", "Monitor Ultrawide",
                "Cadeira Ergonômica", "Headset Bluetooth", "Webcam 4K", "Microfone Condensador", "SSD NVMe 1TB",
                "Placa de Vídeo", "Processador i7", "Memória RAM 16GB", "Placa-Mãe", "Gabinete Gamer",
                "Fonte 650W", "Water Cooler", "Roteador Wi-Fi 6", "Hub USB-C", "Impressora Multifuncional",
                "Tablet", "Smartwatch", "Caixa de Som Bluetooth", "Projetor Portátil", "Kindle",
                "Console de Videogame", "Controle Sem Fio", "Dock Station", "Filtro de Linha", "Pendrive 128GB"
        );
        List<String> produtosFiltradosETratados = produtos.stream().filter(produto -> produto.length() > 10).map(String::toUpperCase).sorted().toList();
        System.out.println(produtosFiltradosETratados);
        //7
        List<Funcionario> funcionariosLista = List.of(
                new Funcionario("Ana", 2500.00, "RH"),
                new Funcionario("Bruno", 3200.50, "TI"),
                new Funcionario("Carlos", 1500.00, "Operações"),
                new Funcionario("Diana", 4500.00, "Financeiro"),
                new Funcionario("Eduardo", 1800.75, "Vendas"),
                new Funcionario("Fernanda", 5500.00, "Marketing"),
                new Funcionario("Gabriel", 2100.00, "TI"),
                new Funcionario("Helena", 6200.00, "Diretoria"),
                new Funcionario("Igor", 1900.00, "Operações"),
                new Funcionario("Julia", 3800.00, "RH"),
                new Funcionario("Kleber", 4100.25, "TI"),
                new Funcionario("Laura", 7500.00, "Financeiro"),
                new Funcionario("Marcos", 2800.00, "Vendas"),
                new Funcionario("Natalia", 3100.00, "Marketing"),
                new Funcionario("Otavio", 8500.00, "Diretoria"),
                new Funcionario("Patricia", 4700.00, "Vendas"),
                new Funcionario("Rafael", 2600.00, "Operações"),
                new Funcionario("Silvia", 5900.50, "TI"),
                new Funcionario("Thiago", 2300.00, "RH"),
                new Funcionario("Ursula", 9200.00, "Financeiro"),
                new Funcionario("Victor", 1750.00, "Operações"),
                new Funcionario("Zelia", 10500.00, "Diretoria")
        );
        double totalFolha = funcionariosLista.stream()
                .mapToDouble(Funcionario::getSalario)
                .sum();
        System.out.println("Total da folha: "+ totalFolha);
        Funcionario maiorpago = funcionariosLista.stream().max(Comparator.comparingDouble(Funcionario::getSalario)).orElse(null);
        System.out.println("Mais bem pago recebe:" + maiorpago.getSalario());

        double mediaSalarial = funcionariosLista.stream()
                .mapToDouble(Funcionario::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Média dos funcionários: " + mediaSalarial);
        Optional<Double> salarioPorNome = Funcionario.buscarSalarioPorNome(funcionariosLista,"Silvia");
        Optional<Funcionario> buscarPorNome = Funcionario.buscarFuncionarioPorNome(funcionariosLista,"Thiago");
        System.out.println(salarioPorNome);
        System.out.println(buscarPorNome);
        //9
        Map<String, List<Funcionario>> funcionariosPorDepartamento = funcionariosLista.stream().collect(Collectors.groupingBy(Funcionario::getDepartamento));
        System.out.println(funcionariosPorDepartamento);
        List<Funcionario> equipeTI = funcionariosPorDepartamento.getOrDefault("TI", Collections.emptyList());
        List<Funcionario> equipeVendas = funcionariosPorDepartamento.getOrDefault("Vendas", Collections.emptyList());
        List<Funcionario> equipeMarketing = funcionariosPorDepartamento.getOrDefault("Marketing", Collections.emptyList());
        List<Funcionario> equipeOperacoes = funcionariosPorDepartamento.getOrDefault("Operações", Collections.emptyList());
        List<Funcionario> equipeDiretoria = funcionariosPorDepartamento.getOrDefault("Diretoria", Collections.emptyList());
        List<Funcionario> equipeRH = funcionariosPorDepartamento.getOrDefault("RH", Collections.emptyList());
        List<Funcionario> equipeFinanceiro = funcionariosPorDepartamento.getOrDefault("Financeiro", Collections.emptyList());

        Optional<Funcionario> maiorSalarioTI = Funcionario.buscarMaiorSalarioPorDepartamento(equipeTI,"TI");
        Optional<Funcionario> maiorSalarioVendas = Funcionario.buscarMaiorSalarioPorDepartamento(equipeVendas,"Vendas");
        Optional<Funcionario> maiorSalarioMarketing = Funcionario.buscarMaiorSalarioPorDepartamento(equipeMarketing,"Marketing");
        Optional<Funcionario> maiorSalarioOperacoes = Funcionario.buscarMaiorSalarioPorDepartamento(equipeOperacoes,"Operações");
        Optional<Funcionario> maiorSalarioDiretoria = Funcionario.buscarMaiorSalarioPorDepartamento(equipeDiretoria,"Diretoria");
        Optional<Funcionario> maiorSalarioRH = Funcionario.buscarMaiorSalarioPorDepartamento(equipeRH,"RH");
        Optional<Funcionario> maiorSalarioFinanceiro = Funcionario.buscarMaiorSalarioPorDepartamento(equipeFinanceiro,"Financeiro");

        maiorSalarioTI.ifPresent(funcionario -> System.out.println("Maior salário TI -> "+funcionario.toString()));
        maiorSalarioVendas.ifPresent(funcionario -> System.out.println("Maior salário Vendas -> "+funcionario.toString()));
        maiorSalarioMarketing.ifPresent(funcionario -> System.out.println("Maior salário Marketing -> "+funcionario.toString()));
        maiorSalarioOperacoes.ifPresent(funcionario -> System.out.println("Maior salário Operacoes -> "+funcionario.toString()));
        maiorSalarioDiretoria.ifPresent(funcionario -> System.out.println("Maior salário Diretoria -> "+funcionario.toString()));
        maiorSalarioRH.ifPresent(funcionario -> System.out.println("Maior salário RH -> "+funcionario.toString()));
        maiorSalarioFinanceiro.ifPresent(funcionario -> System.out.println("Maior salário Financeiro -> "+funcionario.toString()));

        System.out.println("Equipe TI:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeTI));
        System.out.println("Equipe Vendas:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeVendas));
        System.out.println("Equipe Marketing:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeMarketing));
        System.out.println("Equipe Operacoes:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeOperacoes));
        System.out.println("Equipe Diretoria:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeDiretoria));
        System.out.println("Equipe RH:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeRH));
        System.out.println("Equipe Financeiro:\n"+Funcionario.retornarONomeDeTodosOsFuncionario(equipeFinanceiro));
    }
}