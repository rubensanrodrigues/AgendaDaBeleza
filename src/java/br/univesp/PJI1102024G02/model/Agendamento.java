package br.univesp.PJI1102024G02.model;

/**
 *
 * @author rubens
 */
public class Agendamento {
    private String contato;
    private String nome;
    private String observacao;
    private Long horario;
    private Long chaveAcesso;

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getHorario() {
        return horario;
    }

    public void setHorario(Long horario) {
        this.horario = horario;
    }

    public Long getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(Long chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "contato=" + contato + ", nome=" + nome + ", observacao=" + observacao + ", horario=" + horario + ", chaveAcesso=" + chaveAcesso + '}';
    }
}