package br.univesp.PJI1102024G02.jsf.admin;

import br.univesp.PJI1102024G02.jsf.*;
import br.univesp.PJI1102024G02.dao.AgendamentoDAO;
import br.univesp.PJI1102024G02.helper.JSFHelper;
import br.univesp.PJI1102024G02.helper.TimeHelper;
import br.univesp.PJI1102024G02.model.Agendamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mainAdminMB")
@RequestScoped
@SuppressWarnings("unchecked")
public class MainAdminMB {
    private final List<AgendamentoRow> agendamentos;
    
    private Agendamento agendamento;

    public MainAdminMB() {
        agendamentos = new ArrayList();
        agendamento = new Agendamento();
    }

    public List<AgendamentoRow> getAgendamentos() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        for (int horario : TimeHelper.getHorariosAtendimentoInt()) {
            AgendamentoRow agendamentoRow = new AgendamentoRow();
            agendamentoRow.setTim(new AgendamentoItem(horario + ":00", ""));
            if (horario == 13) {
                agendamentoRow.setSeg(new AgendamentoItem("Almoço", "luncht"));
                agendamentoRow.setTer(new AgendamentoItem("Almoço", "luncht"));
                agendamentoRow.setQua(new AgendamentoItem("Almoço", "luncht"));
                agendamentoRow.setQui(new AgendamentoItem("Almoço", "luncht"));
                agendamentoRow.setSex(new AgendamentoItem("Almoço", "luncht"));
                agendamentoRow.setSab(new AgendamentoItem("Almoço", "luncht"));
            } else {
                cal.set(Calendar.HOUR_OF_DAY, horario);
                for (int diaTrabalho : TimeHelper.getDiasDeTrabalho()) {                    
                    cal.set(Calendar.DAY_OF_WEEK, diaTrabalho);
                    Agendamento agnd = agendamentoDAO.getByHorario(cal.getTimeInMillis());
                    if (agnd == null) {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem("Livre",""+cal.getTimeInMillis(), "empty"));
                    } else {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem(agnd.getNome(),"", "noempty"));
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
        System.out.println("Salvando cliente: " + agendamento.getNome());
        String strHorario = (String)JSFHelper.getSessionAttribute("horario");
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamento.setHorario(Long.valueOf(strHorario));
        agendamento.setChaveAcesso(new Date().getTime()+5000);
        agendamentoDAO.insert(agendamento);
        
        return "cadastroAgendamentoConfirmacao";
    }
    
    public String cadastro() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM hh:mm");
        String strHorario = JSFHelper.getRequestParameter("horario");
        JSFHelper.setSessionAttribute("horario", strHorario);
        JSFHelper.setSessionAttribute("strData","" + sdf.format(new Date(Long.parseLong(strHorario))));
        System.out.println("Horario param: " +strHorario);
        return "cadastroAgendamento";
    }
}