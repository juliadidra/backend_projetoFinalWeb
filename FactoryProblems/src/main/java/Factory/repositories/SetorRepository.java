package Factory.repositories;

import Factory.models.Setor;

import java.security.Key;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository implements Repository<Setor, Integer> {
    public static final SetorRepository current = new SetorRepository();

//    private SetorRepository() {}

    @Override
    public void create(Setor setor) throws SQLException {
        String sql = "INSERT INTO Setores(nome) VALUES(?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getNome());
        pstm.execute();
    }

    @Override
    public Setor read(Integer codigo) throws SQLException {
        String sql = "SELECT * FROM Setores WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Setor(rs.getInt("codigo"), rs.getString("nome"));
        }
        return null;
    }

    @Override
    public void update(Setor setor) throws SQLException {
        String sql = "UPDATE Setores SET nome=? WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getNome());
        pstm.setInt(2, setor.getCodigo());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Integer codigo) throws SQLException {
        String sql = "DELETE FROM Setores WHERE codigo=?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        pstm.execute();
    }

    public List<Setor> findAll() throws SQLException {
        String sql = "SELECT * FROM Setores";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Setor> setores = new ArrayList<>();
        while (rs.next()) {
            setores.add(new Setor(rs.getInt("codigo"), rs.getString("nome")));
        }
        return setores;
    }
}
