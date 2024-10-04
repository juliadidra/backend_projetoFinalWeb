package Factory.repositories;

import Factory.models.Funcionario;
import Factory.models.Problema;
import Factory.models.Setor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemasRepository implements Repository<Problema, Integer>{
    @Override
    public void create(Problema problema) throws SQLException {
        String sql = "INSERT INTO Problemas(tipo, data, funcionario_codigo, setor_codigo) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, problema.getTipo());
        pstm.setTimestamp(2, java.sql.Timestamp.valueOf(problema.getData()));
        pstm.setInt(3, problema.getFuncionario().getCodigo());
        pstm.setInt(4, problema.getSetor().getCodigo());
        pstm.execute();
    }

    @Override
    public Problema read(Integer codigo) throws SQLException {
        String sql = "SELECT * FROM Problemas WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Funcionario funcionario = new FuncionarioRepository().read(rs.getInt("funcionario_codigo"));
            Setor setor = new SetorRepository().read(rs.getInt("setor_codigo"));
            return new Problema(rs.getInt("codigo"), rs.getString("tipo"), rs.getTimestamp("data").toLocalDateTime(), funcionario, setor);
        }
        return null;
    }

    @Override
    public void update(Problema problema) throws SQLException {
        String sql = "UPDATE Problemas SET tipo=?, data=?, funcionario_codigo=?, setor_codigo=? WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, problema.getTipo());
        pstm.setTimestamp(2, java.sql.Timestamp.valueOf(problema.getData()));
        pstm.setInt(3, problema.getFuncionario().getCodigo());
        pstm.setInt(4, problema.getSetor().getCodigo());
        pstm.setInt(5, problema.getCodigo());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Integer codigo) throws SQLException {
        String sql = "DELETE FROM Problemas WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        pstm.execute();
    }

    public List<Problema> findAll() throws SQLException {
        String sql = "SELECT * FROM Problemas";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Problema> problemas = new ArrayList<>();
        while (rs.next()) {
            Funcionario funcionario = new FuncionarioRepository().read(rs.getInt("funcionario_codigo"));
            Setor setor = new SetorRepository().read(rs.getInt("setor_codigo"));
            problemas.add(new Problema(rs.getInt("codigo"), rs.getString("tipo"), rs.getTimestamp("data").toLocalDateTime(), funcionario, setor));
        }
        return problemas;
    }
}
