package br.univesp.PJI1102024G02.jsf;

/**
 *
 * @author rubens
 */
public class AgendamentoItem {
    private String dados;
    private String param;
    private String cssClass;

    public AgendamentoItem(String dados, String cssClass) {
        this.dados = dados;
        this.cssClass = cssClass;
    }

    public AgendamentoItem(String dados, String param, String cssClass) {
        this.dados = dados;
        this.param = param;
        this.cssClass = cssClass;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
