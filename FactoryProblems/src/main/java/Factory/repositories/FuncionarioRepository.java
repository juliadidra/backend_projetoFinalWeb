package Factory.repositories;

import Factory.models.Funcionario;
import Factory.models.Setor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements Repository<Funcionario, Integer> {
    public static final FuncionarioRepository current = new FuncionarioRepository();

//    private FuncionarioRepository() {}

    @Override
    public void create(Funcionario funcionario) throws SQLException {

        String sql = "INSERT INTO Funcionarios(nome, email, senha, setor_codigo) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, funcionario.getNome());
        pstm.setString(2, funcionario.getEmail());
        pstm.setString(3, funcionario.getSenha());
        pstm.setInt(4, funcionario.getSetor().getCodigo());
        pstm.execute();
    }

    @Override
    public Funcionario read(Integer codigo) throws SQLException {
        String sql = "SELECT * FROM Funcionarios WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Setor setor = new SetorRepository().read(rs.getInt("setor_codigo"));
            return new Funcionario(rs.getInt("codigo"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    setor);
        }
        return null;
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionarios SET nome=?, email=?, senha=?, setor_codigo=? WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, funcionario.getNome());
        pstm.setString(2, funcionario.getEmail());
        pstm.setString(3, funcionario.getSenha());
        pstm.setInt(4, funcionario.getSetor().getCodigo());
        pstm.setInt(5, funcionario.getCodigo());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Integer codigo) throws SQLException {
        String sql = "DELETE FROM Funcionarios WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        pstm.execute();
    }

    public List<Funcionario> findAll() throws SQLException {
        String sql = "SELECT * FROM Funcionarios";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Setor setor = new SetorRepository().read(rs.getInt("setor_codigo"));
            funcionarios.add(new Funcionario(rs.getInt("codigo"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"), setor));
        }
        return funcionarios;
    }




}
