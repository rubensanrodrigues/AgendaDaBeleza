package br.univesp.PJI1102024G02.dao;

import br.univesp.PJI1102024G02.db.DBConnect;
import br.univesp.PJI1102024G02.model.Agendamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubens
 */
public class AgendamentoDAO {

    public void save(Agendamento a) {
        if (a.getChaveAcesso() == null) {

            Long chaveAcesso = new Date().getTime();
            a.setChaveAcesso(chaveAcesso);
            System.out.println(a.toString());
            insert(a);
        } else {
            System.out.println(a.toString());
            update(a);
        }
    }

    public Agendamento insert(Agendamento a) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO agendamento (contato, nome, observacao, horario, chaveacesso) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, a.getContato());
            preparedStatement.setString(2, a.getNome());
            preparedStatement.setString(3, a.getObservacao());
            preparedStatement.setLong(4, a.getHorario());
            preparedStatement.setLong(5, a.getChaveAcesso());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERR: AgendamentoDAO.insert " + e.getMessage());
            a = null;
        } catch (Exception e) {
            System.out.println("ERR: AgendamentoDAO.insert " + e.getMessage());
            a = null;
        }

        return a;
    }

    public Agendamento update(Agendamento a) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("UPDATE agendamento SET contato=?, nome=?, observacao=?, horario=? WHERE chaveacesso=?");
            preparedStatement.setString(1, a.getContato());
            preparedStatement.setString(2, a.getNome());
            preparedStatement.setString(3, a.getObservacao());
            preparedStatement.setLong(4, a.getHorario());
            preparedStatement.setLong(5, a.getChaveAcesso());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERR: AgendamentoDAO.update " + e.getMessage());
            a = null;
        } catch (Exception e) {
            System.out.println("ERR: AgendamentoDAO.update " + e.getMessage());
            a = null;
        }

        return a;
    }

    public void delete(Agendamento a) {
        delete(a.getChaveAcesso());
    }
    
    public void delete(Long chaveAcesso) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("DELETE FROM agendamento WHERE chaveacesso=?");
            preparedStatement.setLong(1, chaveAcesso);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERR: AgendamentoDAO.update " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERR: AgendamentoDAO.update " + e.getMessage());
        }
    }    

    public Agendamento getLastByContato(String contato) {
        Agendamento a = null;
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT contato, nome, observacao, horario, chaveacesso FROM agendamento WHERE contato=? ORDER BY horario DESC");
            preparedStatement.setString(1, contato);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                a = fill(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    public Agendamento getByChaveAcesso(Long chaveAcesso) {
        Agendamento a = null;
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT contato, nome, observacao, horario, chaveacesso FROM agendamento WHERE chaveacesso=?");
            preparedStatement.setLong(1, chaveAcesso);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                a = fill(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    public Agendamento getByHorario(Long horario) {
        Agendamento a = null;
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT contato, nome, observacao, horario, chaveacesso FROM agendamento WHERE horario=?");
            preparedStatement.setLong(1, horario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                a = fill(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    public List<Agendamento> getAllByHorario(Long horario) {
        List<Agendamento> a = new ArrayList<Agendamento>();
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT contato, nome, observacao, horario, chaveacesso FROM agendamento WHERE horario=?");
            preparedStatement.setLong(1, horario);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                a.add(fill(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    private Agendamento fill(ResultSet rs) {
        Agendamento a = new Agendamento();
        try {
            a.setContato(rs.getString("contato"));
            a.setNome(rs.getString("nome"));
            a.setObservacao(rs.getString("observacao"));
            a.setHorario(rs.getLong("horario"));
            a.setChaveAcesso(rs.getLong("chaveacesso"));
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            a = null;
        }

        return a;
    }
}
