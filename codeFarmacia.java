import java.util.ArrayList;
import java.util.Arrays;

public class Farmacia {

    private ArrayList<String> nomesMedicamentos;
    private ArrayList<Integer> diasParaVencimento;
    private ArrayList<Double> precos;

    public Farmacia(String[] nomes, int[] dias, double[] precosIniciais) {
        this.nomesMedicamentos = new ArrayList<>();
        this.diasParaVencimento = new ArrayList<>();
        this.precos = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {
            this.nomesMedicamentos.add(nomes[i]);
            this.diasParaVencimento.add(dias[i]);
            this.precos.add(precosIniciais[i]);
        }
    }

    public void mostrarMedicamentos() {
        System.out.println("\nLISTA DE MEDICAMENTOS");
        for (int i = 0; i < nomesMedicamentos.size(); i++) {
            System.out.println("Medicamento: " + nomesMedicamentos.get(i) + 
                               " | Preço: R$ " + precos.get(i) + 
                               " | Dias para vencer: " + diasParaVencimento.get(i));
        }
    }

    public double calcularMediaDiasVencimento() {
        if (diasParaVencimento.isEmpty()) return 0;
        int soma = 0;
        for (int dias : diasParaVencimento) {
            soma += dias;
        }
        return (double) soma / diasParaVencimento.size();
    }

    public void identificarProximosVencer() {
        System.out.println("\nMEDICAMENTOS PRÓXIMOS DO VENCIMENTO (< 5 DIAS)");
        for (int i = 0; i < nomesMedicamentos.size(); i++) {
            if (diasParaVencimento.get(i) < 5) {
                System.out.println("- " + nomesMedicamentos.get(i) + " (" + diasParaVencimento.get(i) + " dias restantes)");
            }
        }
    }

    public void aplicarDescontoProximosVencer() {
        for (int i = 0; i < diasParaVencimento.size(); i++) {
            if (diasParaVencimento.get(i) < 5) {
                double precoComDesconto = precos.get(i) * 0.5;
                precos.set(i, precoComDesconto);
            }
        }
        System.out.println("\n[SISTEMA] Desconto de 50% aplicado nos medicamentos com menos de 5 dias para o vencimento.");
    }

    public boolean venderMedicamento(String nome) {
        int index = nomesMedicamentos.indexOf(nome);
        if (index != -1) {
            nomesMedicamentos.remove(index);
            diasParaVencimento.remove(index);
            precos.remove(index);
            System.out.println("\n[VENDA] Medicamento '" + nome + "' vendido e removido do estoque com sucesso!");
            return true;
        } else {
            System.out.println("\n[ERRO] Medicamento '" + nome + "' não encontrado.");
            return false;
        }
    }

    public static void main(String[] args) {
        String[] nomes = {"Paracetamol", "Ibuprofeno", "Dipirona"};
        int[] dias = {3, 15, 2};
        double[] precos = {10.0, 20.0, 8.0};

        Farmacia farmacia = new Farmacia(nomes, dias, precos);

        farmacia.mostrarMedicamentos();
        System.out.println("\nMédia de dias para vencimento: " + farmacia.calcularMediaDiasVencimento());

        farmacia.identificarProximosVencer();
        farmacia.aplicarDescontoProximosVencer();
        farmacia.mostrarMedicamentos();
        farmacia.venderMedicamento("Paracetamol");
        farmacia.mostrarMedicamentos();
    }
}
