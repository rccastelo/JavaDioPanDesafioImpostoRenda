import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    private static Hashtable<Integer, Faixa> faixas;

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        double renda = leitor.nextDouble();
        double imposto = 0;

        carregarFaixas();

        double valorRestante = renda;
        double valorFaixaAnterior = 0;
        double deltaFaixa = 0;

        int qtdFaixas = faixas.size();

        for (int i = 0;i < qtdFaixas; i++) {
            Faixa f = faixas.get(i);

            if (f.tipo.equalsIgnoreCase("menorIgual")) {
                deltaFaixa = f.valor - valorFaixaAnterior;

                if (valorRestante > deltaFaixa) {
                    imposto += (deltaFaixa * (f.percentual / 100));
                    valorRestante -= deltaFaixa;
                    valorFaixaAnterior = f.valor;
                } else {
                    imposto += (valorRestante * (f.percentual / 100));
                    //System.out.println("saida 1");
                    break;
                }
            } else if (f.tipo.equalsIgnoreCase("maiorQue")) {
                imposto += (valorRestante * (f.percentual / 100));
                //System.out.println("saida 2");
                break;
            }
        }

        if (imposto == 0) {
            System.out.println("Isento");
        } else {
            System.out.printf("R$ %.2f%n", imposto);
        }

        leitor.close();
    }

    private static void carregarFaixas() {
        faixas = new Hashtable<>();

        faixas.put(0, (new Faixa("menorIgual", 2000.0, 0.0)));
        faixas.put(1, (new Faixa("menorIgual", 3000.0, 8.0)));
        faixas.put(2, (new Faixa("menorIgual", 4500.0, 18.0)));
        faixas.put(3, (new Faixa("maiorQue", 4500.0, 28.0)));
    }
}

class Faixa {
    public String tipo;
    public double valor;
    public double percentual;

    public Faixa(String tipo, double valor, double percentual) {
        this.tipo = tipo;
        this.valor = valor;
        this.percentual = percentual;
    }
}
