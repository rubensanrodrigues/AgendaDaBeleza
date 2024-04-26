package br.univesp.PJI1102024G02.jsf;

import br.univesp.PJI1102024G02.dao.AgendamentoDAO;
import br.univesp.PJI1102024G02.helper.JSFHelper;
import br.univesp.PJI1102024G02.helper.TimeHelper;
import br.univesp.PJI1102024G02.model.Agendamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mainPublicMB")
@RequestScoped
@SuppressWarnings("unchecked")
public class MainPublicMB implements Serializable {
    
    private Agendamento agendamento;
    
    private static final String STR_HORARIO = "horario";

    public MainPublicMB() {
        agendamento = new Agendamento();
    }

    public List<AgendamentoRow> getAgendamentos() {
        List<AgendamentoRow> agendamentos = new ArrayList<>();
        Calendar cal = TimeHelper.getFirsCurrentWeekTimeCalendar();
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        for (int horario : TimeHelper.getHorariosAtendimentoInt()) {
            AgendamentoRow agendamentoRow = new AgendamentoRow();
            agendamentoRow.setTim(new AgendamentoItem(horario + ":00", ""));
            if (horario == 13) {
                String dados = "Almoço";
                String cssClass = "luncht";
                agendamentoRow.setSeg(new AgendamentoItem(dados, cssClass));
                agendamentoRow.setTer(new AgendamentoItem(dados, cssClass));
                agendamentoRow.setQua(new AgendamentoItem(dados, cssClass));
                agendamentoRow.setQui(new AgendamentoItem(dados, cssClass));
                agendamentoRow.setSex(new AgendamentoItem(dados, cssClass));
                agendamentoRow.setSab(new AgendamentoItem(dados, cssClass));
            } else {
                cal.set(Calendar.HOUR_OF_DAY, horario);
                for (int diaTrabalho : TimeHelper.getDiasDeTrabalho()) {                    
                    cal.set(Calendar.DAY_OF_WEEK, diaTrabalho);
                    Agendamento agnd = agendamentoDAO.getByHorario(cal.getTimeInMillis());
                    if (agnd == null) {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem("Livre",""+cal.getTimeInMillis(), "empty"));
                    } else {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem("Agendado","", "noempty"));
                    }
                }
            }
            agendamentos.add(agendamentoRow);
        }
        
        return agendamentos;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
    
    public String salvar() {
        String strHorario = (String)JSFHelper.getSessionAttribute(STR_HORARIO);
        
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamento.setHorario(Long.valueOf(strHorario));
        agendamentoDAO.save(agendamento);
        
        return "cadastroAgendamentoConfirmacao";
    }
    
    public String cadastro() {
        String strHorario = JSFHelper.getRequestParameter(STR_HORARIO);
        String strDataFormatada = TimeHelper.getDataFormatada(Long.valueOf(strHorario));
        
        JSFHelper.setSessionAttribute(STR_HORARIO, strHorario);
        JSFHelper.setSessionAttribute("strDataFormatada", strDataFormatada);
        
        return "cadastroAgendamento";
    }
}