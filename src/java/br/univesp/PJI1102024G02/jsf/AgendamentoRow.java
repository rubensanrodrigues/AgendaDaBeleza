package br.univesp.PJI1102024G02.jsf;

/**
 *
 * @author rubens
 */
public class AgendamentoRow {
    private AgendamentoItem tim;
    private AgendamentoItem seg;
    private AgendamentoItem ter;
    private AgendamentoItem qua;
    private AgendamentoItem qui;
    private AgendamentoItem sex;
    private AgendamentoItem sab;

    public AgendamentoItem getTim() {
        return tim;
    }

    public void setTim(AgendamentoItem ai) {
        this.tim = ai;
    }
    
    public AgendamentoItem getSeg() {
        return seg;
    }

    public void setSeg(AgendamentoItem ai) {
        this.seg = ai;
    }

    public AgendamentoItem getTer() {
        return ter;
    }

    public void setTer(AgendamentoItem ai) {
        this.ter = ai;
    }

    public AgendamentoItem getQua() {
        return qua;
    }

    public void setQua(AgendamentoItem ai) {
        this.qua = ai;
    }

    public AgendamentoItem getQui() {
        return qui;
    }

    public void setQui(AgendamentoItem ai) {
        this.qui = ai;
    }

    public AgendamentoItem getSex() {
        return sex;
    }

    public void setSex(AgendamentoItem ai) {
        this.sex = ai;
    }

    public AgendamentoItem getSab() {
        return sab;
    }

    public void setSab(AgendamentoItem ai) {
        this.sab = ai;
    }
    
    public void setByDay(int day, AgendamentoItem ai) {
        switch (day) {
            case 2:
                setSeg(ai);
                break;
            case 3:
                setTer(ai);
                break;
            case 4:
                setQua(ai);
                break;
            case 5:
                setQui(ai);
                break;
            case 6:
                setSex(ai);
                break;
            case 7:
                setSab(ai);
                break;
            default:
                break;
        }
    }
}
