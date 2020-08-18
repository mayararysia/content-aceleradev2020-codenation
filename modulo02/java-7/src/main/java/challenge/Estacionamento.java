package challenge;

public class Estacionamento {
    private EstacionamentoRepositorio repositorio;

    public Estacionamento() {
        this.repositorio = new EstacionamentoRepositorio();
    }

    public void estacionar(Carro carro) {
        this.repositorio.add(carro);
    }

    public int carrosEstacionados() {
        return this.repositorio.obterQuantidadeCarrosEstacionados();
    }

    public boolean carroEstacionado(Carro carro) {
        return this.repositorio.findCarro(carro);
    }
}
