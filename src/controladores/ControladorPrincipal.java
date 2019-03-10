/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import listainvertida.valueObject.AlunoVo;
import listainvertida.telas.Tela;
import entidades.Aluno;
import mapeadores.MapeadorCidades;
import mapeadores.MapeadorAlunos;
import mapeadores.MapeadorCursos;
import mapeadores.MapeadorIAA;
import entidades.Cidade;
import entidades.Curso;
import entidades.IAA;

/**
 *
 * @author pablo
 */
public class ControladorPrincipal {
    private Tela tela;
    private MapeadorIAA mapeadorIAA;
    private MapeadorAlunos mapeadorDados;
    private MapeadorCursos mapeadorCursos;
    private MapeadorCidades mapeadorCidades;
    private static ControladorPrincipal instance;
    //
    public ControladorPrincipal(){
        this.tela = new Tela();
        this.mapeadorIAA = new MapeadorIAA();
        this.mapeadorDados = new MapeadorAlunos();
        this.mapeadorCursos = new MapeadorCursos();
        this.mapeadorCidades = new MapeadorCidades();
    }
    
    public void cadastrarAluno(AlunoVo alunoVo){
        Aluno novo = new Aluno(alunoVo.matricula, alunoVo.nome, alunoVo.curso, alunoVo.cidade, alunoVo.iaa);
        mapeadorDados.put(novo);
        //Faz o mapeamento da Matricula do Aluno para tabelas de indexação curso
        if(validaCurso(alunoVo.curso)){
            Curso curso = mapeadorCursos.get(alunoVo.curso);
            curso.setQntAlunosCurso(curso.getQntAlunosCurso()+1);
            curso.getMatriculas().add(alunoVo.matricula);
            mapeadorCursos.remove(curso.getNomeCurso());
            mapeadorCursos.put(curso);
        }else{
            Curso curso = new Curso(alunoVo.curso);
            curso.setQntAlunosCurso(1);
            curso.getMatriculas().add(alunoVo.matricula);
            mapeadorCursos.put(curso);
        }
        //Faz o mapeamento da Matricula do aluno para tabelas de indexação cidade
        if(validaCidade(alunoVo.cidade)){
            Cidade cidade = mapeadorCidades.get(alunoVo.cidade);
            cidade.setQntAlunosCidade(cidade.getQntAlunosCidade()+1);
            cidade.getMatriculas().add(alunoVo.matricula);
            mapeadorCidades.remove(cidade.getNomeCidade());
            mapeadorCidades.put(cidade);
        }else{
            Cidade cidade = new Cidade(alunoVo.cidade);
            cidade.setQntAlunosCidade(1);
            cidade.getMatriculas().add(alunoVo.matricula);
            mapeadorCidades.put(cidade);
        }
        //Mapea IAA para Tabela indexação
        if(validaFaixaIAA(defineFaixaIAA(alunoVo.iaa))){
            IAA iaa = mapeadorIAA.get(defineFaixaIAA(alunoVo.iaa));
            iaa.setQntMatriculasFaixa(iaa.getQntMatriculasFaixa()+1);
            iaa.getMatriculas().add(alunoVo.matricula);
            mapeadorIAA.remove(defineFaixaIAA(alunoVo.iaa));
            mapeadorIAA.put(iaa);
        }else{
            IAA iaa = new IAA(defineFaixaIAA(alunoVo.iaa));
            iaa.setQntMatriculasFaixa(1);
            iaa.getMatriculas().add(alunoVo.matricula);
            mapeadorIAA.put(iaa);
        }
    }
    
    public String removerAluno(int matricula){
        String retorno = "Matrícula excluida com sucesso!";
        if(!validaMatricula(matricula)){
            retorno="Matricula não encontrada!";
        }else{
            Integer mat = matricula;
            //Mapeia os dados
            Curso curso = mapeadorCursos.get(mapeadorDados.get(matricula).getCurso());
            Cidade cidade = mapeadorCidades.get(mapeadorDados.get(matricula).getCidade());
            IAA iaa = mapeadorIAA.get(defineFaixaIAA(mapeadorDados.get(matricula).getIaa()));
                    
            //Mapea a quantidade matriculas cadastradas
            int qntAlunosCurso= curso.getQntAlunosCurso();
            int qntAlunosCidade = cidade.getQntAlunosCidade();
            int qntAlunosIAA = iaa.getQntMatriculasFaixa();
            
            //Remove tablea indexação cursos
            if(qntAlunosCurso==1){
                mapeadorCursos.remove(curso.getNomeCurso());
            }else{
                curso.setQntAlunosCurso(curso.getQntAlunosCurso()-1);
                curso.getMatriculas().remove(mat);
                mapeadorCursos.remove(curso.getNomeCurso());
                mapeadorCursos.put(curso);
            }
            //Remove tabela de indexação de cidade
            if(qntAlunosCidade==1){
                mapeadorCidades.remove(cidade.getNomeCidade());
            }else{
                cidade.setQntAlunosCidade(cidade.getQntAlunosCidade()-1);
                cidade.getMatriculas().remove(mat);
                mapeadorCidades.remove(cidade.getNomeCidade());
                mapeadorCidades.put(cidade);
            }
            //Remove tabela indexação IAA
            if(qntAlunosIAA==1){
                mapeadorIAA.remove(iaa.getNomeFaixa());
            }else{
                iaa.setQntMatriculasFaixa(iaa.getQntMatriculasFaixa()-1);
                iaa.getMatriculas().remove(mat);
                mapeadorIAA.remove(iaa.getNomeFaixa());
                mapeadorIAA.put(iaa);
            }
            mapeadorDados.remove(matricula);
        }
        return retorno;
    }
    /*
    public void atualizarCadastroAluno(AlunoVo alunoVo){
        int matricula = alunoVo.matricula;
        removerAluno(matricula);
        cadastrarAluno(alunoVo);
    }*/
    
    public void exibirTabelaDados(){
        for(Aluno dadoExibir: mapeadorDados.getList().values()){
            resultadoPesquisaMatricula(dadoExibir.getMatricula());
        }
    }
    
    public void resultadoPesquisaMatricula(int matricula){
        Aluno aluno = mapeadorDados.get(matricula);
        
        int matrículaV =aluno.getMatricula();
        String nomeV = aluno.getNome();
        String cursoV = aluno.getCurso();
        String cidadeV = aluno.getCidade();
        Float iaaV = aluno.getIaa();
        
        String dadosAExibir = matrículaV+","+nomeV+","+cursoV+","+cidadeV+","+iaaV;
        tela.exibirDados(dadosAExibir);
        
    }
    
    public boolean validaMatricula(int matricula){
        boolean retorno = false;
        retorno = mapeadorDados.get(matricula) != null;
        return retorno;
    }
    
    private boolean validaCurso(String curso){
        boolean retorno = false;
        retorno = mapeadorCursos.get(curso)!=null;
        return retorno;
    }
    
    private boolean validaCidade(String cidade){
        boolean retorno = false;
        retorno = mapeadorCidades.get(cidade)!=null;
        return retorno;
    }
    
    private boolean validaFaixaIAA(String faixaIAA){
        boolean retorno = false;
        retorno = mapeadorIAA.get(faixaIAA)!=null;
        return retorno;
    }
    
    private String defineFaixaIAA(float iaa){
        String faixaIAA = "Sem faixa";
        
        if(iaa>=9.0000f){
            faixaIAA = "Faixa 5";
        }else if(iaa>=7.0000f){
            faixaIAA = "Faixa 4";
        }else if(iaa>=5.0000f){
            faixaIAA = "Faixa 3";
        }else if(iaa>=3.0000f){
            faixaIAA = "Faixa 2";
        }else {
            faixaIAA = "Faixa 1";
        }
        return faixaIAA;
    }
    
    public void pesquisarAlunosPorCurso(String curso){
        if(validaCurso(curso)){
            for(int matricula : mapeadorCursos.get(curso).getMatriculas()){
                resultadoPesquisaMatricula(matricula);
            }
        }
    }
    
    public void pesquisarAlunosPorCidade(String cidade){
        if(validaCidade(cidade)){
            for(int matricula : mapeadorCidades.get(cidade).getMatriculas()){
                resultadoPesquisaMatricula(matricula);
            }
        }
    }
    
    public void pesquisarAlunosPorIntervaloIAA(String faixaiAA){
        if(validaFaixaIAA(faixaiAA)){
            for(int matricula : mapeadorIAA.get(faixaiAA).getMatriculas()){
                resultadoPesquisaMatricula(matricula);
            }
        }
            
    }
    
    public void pesquisarAlunoPorCursoCidade(String curso, String cidade){
        if(validaCurso(curso)&&validaCidade(cidade)){
            int qntAlunosCidade = mapeadorCidades.get(cidade).getQntAlunosCidade();
            int qntAlunosCurso = mapeadorCursos.get(curso).getQntAlunosCurso();
            //Verifica se lista de alunos cadastradas naquela cidade é maior que lista de alunos cadastrados no curso
            if(qntAlunosCidade>=qntAlunosCurso){
                for(int matricula : mapeadorCidades.get(cidade).getMatriculas()){
                    if(mapeadorDados.get(matricula).getCurso().equals(curso)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }
            }else {//if(qntAlunosCurso>qntAlunosCidade)
                for(int matricula : mapeadorCursos.get(curso).getMatriculas()){
                    if(mapeadorDados.get(matricula).getCidade().equals(cidade)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }
            }
        }
    }
    
    public void pesquisarAlunoPorCursoFaixaIAA(String curso, String faixaIAA){
        if(validaCurso(curso)&&validaFaixaIAA(faixaIAA)){
            int qntAlunosCurso = mapeadorCursos.get(curso).getQntAlunosCurso();
            int qntAlunosFaixaIAA = mapeadorIAA.get(faixaIAA).getQntMatriculasFaixa();
            if(qntAlunosCurso>=qntAlunosFaixaIAA){
                for(int matricula : mapeadorCursos.get(curso).getMatriculas()){
                    if(defineFaixaIAA(mapeadorDados.get(matricula).getIaa()).equals(faixaIAA)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }
            }else{//qntAlunosFaixaIAA>qntAlunosCurso
                for(int matricula : mapeadorIAA.get(faixaIAA).getMatriculas()){
                    if(mapeadorDados.get(matricula).getCurso().equals(curso)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }    
            }
        }
    }
    
    public void pesquisarAlunoPorCidadeFaixaIAA(String cidade, String faixaIAA){
        if(validaCidade(cidade)&&validaFaixaIAA(faixaIAA)){
            int qntAlunosCidade = mapeadorCidades.get(cidade).getQntAlunosCidade();
            int qntAlunosFaixaIAA = mapeadorIAA.get(faixaIAA).getQntMatriculasFaixa();
            
            if(qntAlunosCidade>=qntAlunosFaixaIAA){
                for(int matricula : mapeadorCidades.get(cidade).getMatriculas()){
                    if(defineFaixaIAA(mapeadorDados.get(matricula).getIaa()).equals(faixaIAA)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }                
            }else{//qntAlunosFaixaIAA>qntAlunosCidade
                for(int matricula : mapeadorIAA.get(faixaIAA).getMatriculas()){
                    if(mapeadorDados.get(matricula).getCidade().equals(cidade)){
                        resultadoPesquisaMatricula(matricula);
                    }
                }    
            }
        }
        
    }
    
    public void exibirTelaInicial(){
        tela.exibirMenu();
    }
    
    public static ControladorPrincipal getInstance() {
        if(instance == null){
            instance = new ControladorPrincipal();
        }
        return instance;
    }
    
    
    //Apagar depois
    /*public String exibirTabelaDadosCursos(){
        String retorno = "Vazio";
        for(Curso cursosExibir: mapeadorCursos.getList().values()){
            String nomeCurso = cursosExibir.getNomeCurso();
            if(cursosExibir.getQntAlunosCurso()==0){
                retorno = nomeCurso+","+"Vazio";
            }else{    
                for(int matricula :cursosExibir.getMatriculas()){
                    retorno = retorno+matricula+"-";
                }
            }
        }
        return retorno;
    }    
    
    public void exibirTabelaCursosTeste(){
        tela.exbirDadosCursos(exibirTabelaDadosCursos());
    }*/
    
    //Apenas para atualizad informações em caso de bugs na persistencia
    /*public void atualizarInformaçõesBanco(){
        for(Aluno dadoAluno :mapeadorDados.getList().values()){
            int matriculaAluno =dadoAluno.getMatricula();
            String nomeCursoAluno = dadoAluno.getCurso();
            String nomeCidadeAluno = dadoAluno.getCidade();
            String faixaAlunoIAA = defineFaixaIAA(dadoAluno.getIaa());
            //Atualiza persistencia na tabela de indexação de curso
            if(validaCurso(nomeCursoAluno)){
                Curso cursoAluno = mapeadorCursos.get(nomeCursoAluno);
                if(!cursoAluno.getMatriculas().contains(matriculaAluno)){
                    cursoAluno.setQntAlunosCurso(cursoAluno.getQntAlunosCurso()+1);
                    cursoAluno.getMatriculas().add(matriculaAluno);
                    mapeadorCursos.remove(nomeCursoAluno);
                    mapeadorCursos.put(cursoAluno);
                }///Fim if lista matriculas do curso
            }else{
                Curso cursoNovo = new Curso(nomeCursoAluno);
                cursoNovo.setQntAlunosCurso(1);
                cursoNovo.getMatriculas().add(matriculaAluno);
                mapeadorCursos.put(cursoNovo);
            }//fim do else de alunos sem cursos cadastrados
            
            //Atualiza persistencia na tabela de indexação de Cidades
            if(validaCidade(nomeCidadeAluno)){
                Cidade cidadeAluno = mapeadorCidades.get(nomeCidadeAluno);
                if(!cidadeAluno.getMatriculas().contains(matriculaAluno)){
                    cidadeAluno.setQntAlunosCidade(cidadeAluno.getQntAlunosCidade()+1);
                    cidadeAluno.getMatriculas().add(matriculaAluno);
                    mapeadorCidades.remove(nomeCidadeAluno);
                    mapeadorCidades.put(cidadeAluno);
                }
            }else{
                Cidade cidadeNovo = new Cidade(nomeCidadeAluno);
                cidadeNovo.setQntAlunosCidade(1);
                cidadeNovo.getMatriculas().add(matriculaAluno);
                mapeadorCidades.put(cidadeNovo);
            }
            //Atualiza tabela de indexação de IAA
            if(validaFaixaIAA(faixaAlunoIAA)){
                IAA iaa = mapeadorIAA.get(faixaAlunoIAA);
                if(!iaa.getMatriculas().contains(matriculaAluno)){
                    iaa.setQntMatriculasFaixa(iaa.getQntMatriculasFaixa()+1);
                    iaa.getMatriculas().add(matriculaAluno);
                    mapeadorIAA.remove(iaa.getNomeFaixa());
                    mapeadorIAA.put(iaa);
                }
            }else{
                IAA iaa = new IAA(faixaAlunoIAA);
                iaa.setQntMatriculasFaixa(1);
                iaa.getMatriculas().add(matriculaAluno);
                mapeadorIAA.put(iaa);
            }
       }
    }*/
}
