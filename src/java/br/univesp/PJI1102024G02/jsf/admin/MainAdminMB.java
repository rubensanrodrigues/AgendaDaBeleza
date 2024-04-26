package br.univesp.PJI1102024G02.jsf.admin;

import br.univesp.PJI1102024G02.jsf.*;
import br.univesp.PJI1102024G02.dao.AgendamentoDAO;
import br.univesp.PJI1102024G02.helper.JSFHelper;
import br.univesp.PJI1102024G02.helper.TimeHelper;
import br.univesp.PJI1102024G02.model.Agendamento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "mainAdminMB")
@RequestScoped
@SuppressWarnings("unchecked")
public class MainAdminMB {
    private final List<AgendamentoRow> agendamentos;
    
    private Agendamento agendamento;
    
    private static final String STR_HORARIO = "horario";
    private static final String STR_DATA_FORMATADA = "strDataFormatada";
    private static final String STR_CHAVE_ACESSO =  "chaveAcesso";

    public MainAdminMB() {
        agendamentos = new ArrayList<>();
        agendamento = new Agendamento();
    }
    
    private String getFormtedStringFromAagendamentos(List<Agendamento> agnds) {
        String strAgnds = "";
        for (Agendamento agnd : agnds) {
            strAgnds = strAgnds.concat(agnd.getNome().concat("  "));
        }

        return strAgnds.trim().replace("  ", ", ");
    }

    public List<AgendamentoRow> getAgendamentos() {
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
                    List <Agendamento> agnds = agendamentoDAO.getAllByHorario(cal.getTimeInMillis());
                    String strAgnds = getFormtedStringFromAagendamentos(agnds);
                    if (strAgnds.length() == 0) {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem("Livre",""+cal.getTimeInMillis(), "empty"));
                    } else {
                        agendamentoRow.setByDay(diaTrabalho, new AgendamentoItem(strAgnds,""+cal.getTimeInMillis(), "noempty"));
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

    public List<Agendamento> getHorarioAgendamentos() {
        String strHorario = (String)JSFHelper.getSessionAttribute(STR_HORARIO);
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        return agendamentoDAO.getAllByHorario(Long.valueOf(strHorario));
    }
    
    public String detalharHorario() {
        String strHorario = JSFHelper.getRequestParameter(STR_HORARIO);
        String strDataFormatada = TimeHelper.getDataFormatada(Long.valueOf(strHorario));
        
        JSFHelper.setSessionAttribute(STR_HORARIO, strHorario);
        JSFHelper.setSessionAttribute(STR_DATA_FORMATADA, strDataFormatada);
        
        return "detalheHorario";
    }
 
    public String deletar() {
        String strChaveAcesso = JSFHelper.getRequestParameter(STR_CHAVE_ACESSO);
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamentoDAO.delete(Long.valueOf(strChaveAcesso));
        
        return "detalheHorario";
    }
    
    public String salvar() {
        String strHorario = (String)JSFHelper.getSessionAttribute(STR_HORARIO);
        String strChaveAcesso = (String)JSFHelper.getSessionAttribute(STR_CHAVE_ACESSO);
        
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamento.setHorario(Long.valueOf(strHorario));
        if (! strChaveAcesso.isEmpty()) {
            agendamento.setChaveAcesso(Long.valueOf(strChaveAcesso));
        }
        agendamentoDAO.save(agendamento);
        
        return "cadastroAgendamentoConfirmacao";
    }
    
    public String cadastro() {
        String strHorario = JSFHelper.getRequestParameter(STR_HORARIO);
        String strDataFormatada = TimeHelper.getDataFormatada(Long.valueOf(strHorario));
        
        JSFHelper.setSessionAttribute(STR_HORARIO, strHorario);
        JSFHelper.setSessionAttribute(STR_DATA_FORMATADA,strDataFormatada);
        JSFHelper.setSessionAttribute(STR_CHAVE_ACESSO, "");
        
        return "cadastroAgendamento";
    }
    
    public String editar() {
        String strChaveAcesso = JSFHelper.getRequestParameter(STR_CHAVE_ACESSO);
        Long chaveAcesso = Long.valueOf(strChaveAcesso);
        
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        agendamento = agendamentoDAO.getByChaveAcesso(chaveAcesso);
        
        String strHorario = String.valueOf(agendamento.getHorario());
        String strDataFormatada = TimeHelper.getDataFormatada(Long.valueOf(strHorario));
        
        JSFHelper.setSessionAttribute(STR_HORARIO, strHorario);
        JSFHelper.setSessionAttribute(STR_DATA_FORMATADA, strDataFormatada);
        JSFHelper.setSessionAttribute(STR_CHAVE_ACESSO, strChaveAcesso);
        
        return "cadastroAgendamento";
    }
}