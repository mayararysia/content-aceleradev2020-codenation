package challenge;

import java.util.ArrayList;
import java.util.List;

public class EstacionamentoRepositorio {
    private List<Carro> filaCarros;

    public EstacionamentoRepositorio() {
        this.filaCarros = new ArrayList<>();
    }

    public void add(Carro carro) {
        boolean motoristaIdoso = true;
        int position = -1;
        this.lancarExceptionCarroMotorista(carro);
        if (verificarEstacionamentoLotado()) {
            while (motoristaIdoso) {
                position++;
                if (position == this.filaCarros.size()) break;
                if (this.filaCarros.get(position).getMotorista().getIdade() > 55) continue;
                else {
                    motoristaIdoso = false;
                    break;
                }
            }

            if (!motoristaIdoso) {
                this.filaCarros.remove(position);
            } else {
                throw new EstacionamentoException("Todos as vagas ocupadas são de seniores(as)");
            }
        }
        this.filaCarros.add(carro);
    }

    public boolean findCarro(Carro carro) {
        this.lancarExceptionCarroMotorista(carro);
        return Validador.verificarValidadeLista(this.filaCarros) && this.filaCarros.stream().anyMatch(car ->
                car.getMotorista().getHabilitacao().equals(carro.getMotorista().getHabilitacao()));
    }

    public int obterQuantidadeCarrosEstacionados() {
        return Validador.verificarListaInvalida(this.filaCarros) ? 0 : this.filaCarros.size();
    }

    private void lancarExceptionCarroMotorista(Carro carro) {
        if (carro == null || carro.getMotorista() == null)
            throw new EstacionamentoException("Não é permitido carro autônomo!");
        if (carro.getMotorista().getPontos() <= 0 || carro.getMotorista().getPontos() > 20)
            throw new EstacionamentoException("Pontuação da carteira inválida. Não é permitido estacionar com esse motorista!");
        if (carro.getMotorista().getNome() == null || carro.getMotorista().getNome().equals(""))
            throw new NullPointerException("Não é permitido motorista sem nome!");
        if (carro.hashCode() == 0 || carro.getPlaca() == null || carro.getCor() == null
                || carro.getPlaca().equals("") || carro.getCor().toString().equals(""))
            throw new NullPointerException("Não é permitido carro sem placa e sem cor!");
        if (carro.getMotorista().getIdade() <= 0)
            throw new IllegalArgumentException("Não é permitido idade nula!");
        if (carro.getMotorista().getIdade() < 18)
            throw new EstacionamentoException("Não é permitido motorista menor de idade!");
        if (carro.getMotorista().hashCode() == 0 || carro.getMotorista().getHabilitacao() == null ||
                carro.getMotorista().getHabilitacao().equals(""))
            throw new NullPointerException("Não é permitido motorista sem habilitação!");
        if (carro.getMotorista().getPontos() < 0)
            throw new IllegalArgumentException("Não é permitido pontos negativos!");
    }

    private boolean verificarEstacionamentoLotado() {
        return filaCarros.size() == 10;
    }
}
