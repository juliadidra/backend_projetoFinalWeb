package Factory.controllers;

import Factory.models.Funcionario;
import Factory.models.Problema;
import Factory.models.ProblemaDTO;
import Factory.models.Setor;
import Factory.repositories.FuncionarioRepository;
import Factory.repositories.ProblemasRepository;
import Factory.repositories.SetorRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FabricaController {

    private final FuncionarioRepository funcionarioRepository = FuncionarioRepository.current;
    private final ProblemasRepository problemasRepository = new ProblemasRepository();
    private final SetorRepository setorRepository = SetorRepository.current;

    // Cadastro de setores (Escritório)
    @PostMapping("/setores")
    public void criarSetor(@RequestBody Setor setor) throws SQLException {
        setorRepository.create(setor);
    }

    // Listar todos os setores (Escritório)
    @GetMapping("/setores")
    public List<Setor> listarSetores() throws SQLException {
        return setorRepository.findAll();
    }

    // Cadastro de funcionários (Escritório)
    @PostMapping("/funcionarios")
    public void criarFuncionario(@RequestBody Funcionario funcionario) throws SQLException {
        funcionarioRepository.create(funcionario);
    }

    // Listar todos os funcionários (Escritório)
    @GetMapping("/funcionarios")
    public List<Funcionario> listarFuncionarios() throws SQLException {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/problemas")
    public List<Problema> listarProblemas() throws SQLException {
        return problemasRepository.findAll();
    }

    // Relatório de problemas por setor e por dia (Escritório)
    @GetMapping("/setores/{setorId}/problemas")
    public List<Problema> listarProblemasPorSetor(@PathVariable("setorId") int setorId) throws SQLException {
        Setor setor = setorRepository.read(setorId);
        return problemasRepository.findAll();  // Aqui você pode adicionar um filtro de acordo com a lógica do relatório
    }

    // Reporte de problema (Fábrica)
    @PostMapping("/problemas")
    public void reportarProblema(@RequestBody ProblemaDTO problemaDTO) throws SQLException {
        Funcionario funcionario = funcionarioRepository.read(problemaDTO.getFuncionarioId());
        Setor setor = setorRepository.read(problemaDTO.getSetorId());
        Problema problema = new Problema(problemaDTO.getTipo(), LocalDateTime.now(), funcionario, setor);
        problemasRepository.create(problema);
    }

    // Atualização de um setor
    @PutMapping("/setores/{codigo}")
    public void atualizarSetor(@PathVariable("codigo") int codigo, @RequestBody Setor setor) throws SQLException {
        setor.setCodigo(codigo);
        setorRepository.update(setor);
    }

    // Atualização de um funcionário
    @PutMapping("/funcionarios/{codigo}")
    public void atualizarFuncionario(@PathVariable("codigo") int codigo, @RequestBody Funcionario funcionario) throws SQLException {
        funcionario.setCodigo(codigo);
        funcionarioRepository.update(funcionario);
    }

    // Remover um setor
    @DeleteMapping("/setores/{codigo}")
    public void deletarSetor(@PathVariable("codigo") int codigo) throws SQLException {
        setorRepository.delete(codigo);
    }

    // Remover um funcionário
    @DeleteMapping("/funcionarios/{codigo}")
    public void deletarFuncionario(@PathVariable("codigo") int codigo) throws SQLException {
        funcionarioRepository.delete(codigo);
    }

//    @GetMapping("/funcionarios/{funcionarioId}/problemas")
//    public List<Problema> listarProblemasPorFuncionario(@PathVariable("funcionarioId") int funcionarioId) throws SQLException {
//        Funcionario funcionario = funcionarioRepository.read(funcionarioId);
//        if (funcionario != null) {
//            return problemasRepository.findByFuncionario(funcionarioId);
//        }
//        return null;  // Ou lançar uma exceção apropriada se o funcionário não for encontrado
//    }
}

