package listainvertida.telas;

import listainvertida.valueObject.AlunoVo;
import controladores.ControladorPrincipal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pablo Vicente
 */
public class Tela{
    //Atributos
    private Scanner teclado;
    
    //Construtor
    public Tela(){
        teclado = new Scanner(System.in);
    }
    
    //Metodos Operacionais
    public void exibirMenu(){
        int numeroAcao = -1;
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("========================== Bem Vindo ao Cadastro De Alunos ======================");
        System.out.println("---------------------------------------------------------------------------------\n");
        
        System.out.println("[1] Cadastrar Aluno");
        System.out.println("[2] Excluir Aluno");
        System.out.println("[3] Pesquisa Avançada Por Aluno");
        System.out.println("[4] Exibir todos dados");
        //System.out.println("[5] Atualizar Cadastro De Aluno");
        //System.out.println("[6] Carga de dados");
        System.out.println("[0] Encerrar\n");
        System.out.println("Por favor escolha umas das opções acima e digite o número correspondente.");
        boolean repetir;
        do {
            repetir = false;
            numeroAcao = pedeInt();
            if(numeroAcao>4){
                System.out.println("Opção inválida");
            }
        } while(!(numeroAcao >= 0 && numeroAcao <= 4) || repetir);

        switch(numeroAcao){
            case 1:
                this.exibirTelaCadastroAluno();
                break;
            case 2:
                this.exibirTelaExclusaoAlunos();
                break;
            case 3:
                this.exibirTelaPesquisaAvancada();
                break;
            case 4:
                this.exibirTabelaDados();
                break;
            /*case 5: 
                this.exibirTelaAtualizaçãoDadosAluno();
                break;*/
            case 0:
                break;    
        }
    }

    private void exibirTelaCadastroAluno() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("============================ Cadastro de Aluno ==================================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Digite a matricula: ");
        int matricula = pedeInt();
        if(ControladorPrincipal.getInstance().validaMatricula(matricula)==true){
            System.out.println("Matrícula já cadastrada!!");
        }else{
            System.out.println("Digite o nome: ");
            teclado.nextLine();
            String nomeVo = teclado.nextLine();
            System.out.println("Digite o curso: ");
            String cursoVo = teclado.nextLine().toUpperCase();
            System.out.println("Digite a cidade: ");
            String cidadeVo = teclado.nextLine().toUpperCase();
            System.out.println("Digite o IAA (x,xx): ");
            float iaaVo = pedeFloat();
            
            AlunoVo alunoVo = new AlunoVo();
            alunoVo.matricula=matricula;
            alunoVo.nome=nomeVo;
            alunoVo.curso=cursoVo;
            alunoVo.cidade=cidadeVo;
            alunoVo.iaa=iaaVo;
            ControladorPrincipal.getInstance().cadastrarAluno(alunoVo);
            System.out.println("Matricula cadastrada com sucesso!!");
        }
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }

    private void exibirTelaExclusaoAlunos() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("============================== Excluir Aluno ====================================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Digite a matricula: ");
        int matricula = pedeInt();
            System.out.println(ControladorPrincipal.getInstance().removerAluno(matricula));
            ControladorPrincipal.getInstance().exibirTelaInicial();
    }

    /*private void exibirTelaAtualizaçãoDadosAluno() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("============================== Atualizar Cadastro ===============================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Digite a matricula: ");
        int matricula = pedeInt();
        if(ControladorPrincipal.getInstance().validaMatricula(matricula)==false){
            System.out.println("Matrícula não cadastrada!!");
        }else{
            System.out.println("Digite o nome: ");
            teclado.nextLine();
            String nomeVo = teclado.nextLine();
            System.out.println("Digite o curso: ");
            String cursoVo = teclado.nextLine().toUpperCase();
            System.out.println("Digite a cidade: ");
            String cidadeVo = teclado.nextLine().toUpperCase();
            System.out.println("Digite o IAA (x,xx): ");
            float iaaVo = pedeFloat();
            
            AlunoVo alunoVo = new AlunoVo();
            alunoVo.matricula=matricula;
            alunoVo.nome=nomeVo;
            alunoVo.curso=cursoVo;
            alunoVo.cidade=cidadeVo;
            alunoVo.iaa=iaaVo;
            
            ControladorPrincipal.getInstance().atualizarCadastroAluno(alunoVo);
            System.out.println("Matricula atualizada com sucesso!!");
        }
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    */
    private void exibirTabelaDados() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("======================= Tabela de Alunos Cadastrados ============================");
        System.out.println("---------------------------------------------------------------------------------");
        ControladorPrincipal.getInstance().exibirTabelaDados();
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    
    public void exibirDados(String exibir){
        
        String[] dadosAluno = exibir.split(",");
        
        System.out.print("Matricula: "+dadosAluno[0]);
        printBranco(9-dadosAluno[0].length());
        
        System.out.print("Nome: " + dadosAluno[1]);
        printBranco(13-dadosAluno[1].length());
        
        System.out.print("Curso: " + dadosAluno[2]);
        printBranco(9-dadosAluno[2].length());
        
        System.out.print("Cidade: " + dadosAluno[3]);
        printBranco(9-dadosAluno[3].length());
        
        System.out.println("IAA: "+dadosAluno[4]);
        //System.out.println("---------------------------------------------------------------------------------");
    }
    
    private void printBranco(int qnt){
        for(int i=0;i<qnt;i++){
            System.out.print(" ");
        }
    }
    
    private int pedeInt(){
        boolean repetir;
        int inteiro=-1;
        do {
            repetir = false;
                try {
                    inteiro = teclado.nextInt();
                } catch(InputMismatchException e) {
                    repetir = true;
                    teclado.nextLine();
                    System.out.println("Digite um número inteiro!");
                }
        } while(repetir);
        return inteiro;    
    }
    
    private float pedeFloat(){
        boolean repetir;
        float numerofloat=-1;
        do {
            repetir = false;
                try {
                    numerofloat = teclado.nextFloat();
                } catch(InputMismatchException e) {
                    repetir = true;
                    teclado.nextLine();
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("======================= Digite um número neste formato: x,y ======================");
                    System.out.println("---------------------------------------------------------------------------------");
                }
                
                if(numerofloat<0||numerofloat>10){
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("======================= IAA inválido, (IAA varia de 0-10)========================");
                    System.out.println("---------------------------------------------------------------------------------");
                    
                }
        } while(repetir||numerofloat>10||numerofloat<0);
        return numerofloat;    
    }

    public void exibirTelaPesquisaAvancada(){
        int numeroAcao = -1;
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("========================== Bem Vindo a Pesquisa de Alunos =======================");
        System.out.println("---------------------------------------------------------------------------------\n");
        
        System.out.println("[1] Pesquisar Aluno pela matrícula");
        System.out.println("[2] Pesquisar Aluno por curso");
        System.out.println("[3] Pesquisar Aluno por cidade");
        System.out.println("[4] Pesquisar Aluno por intervalos de IAA");
        System.out.println("[5] Busca combinada Curso e Cidade");
        System.out.println("[6] Busca combinada Curso e intervalo IAA");
        System.out.println("[7] Busca combinada Cidade e Invervalo IAA");
        System.out.println("[0] Voltar ao menu principal\n");
        System.out.println("Por favor escolha umas das opções acima e digite o número correspondente.");
        boolean repetir;
        do {
            repetir = false;
            numeroAcao = pedeInt();
            if(numeroAcao>7){
                System.out.println("Opção inválida");
            }
        } while(!(numeroAcao >= 0 && numeroAcao <= 7) || repetir);

        switch(numeroAcao){
            case 1:
                this.exibirPesquisarAlunoPelaMatrícula();
                break;
            case 2:
                this.pesquisarAlunoPorCurso();
                break;
            case 3:
                this.pesquisarAlunoPorCidade();
                break;
            case 4:
                this.pesquisarAlunoPorIntervalosIAA();
                break;
            case 5: 
                this.buscaCombinadaCursoCidade();
                break;
            case 6: 
                this.buscaCombinadaCursoIntervaloIAA();
                break;    
            case 7: 
                this.buscaCombinadaCidadeInvervaloIAA();
                break;  
            case 0:
                ControladorPrincipal.getInstance().exibirTelaInicial();
                break;    
        }
    }

    private void exibirPesquisarAlunoPelaMatrícula() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("==================== Pesquisar Aluno pela matrícula =============================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Digite a matricula: ");
        int matricula = pedeInt();
        if(ControladorPrincipal.getInstance().validaMatricula(matricula)==true){
            ControladorPrincipal.getInstance().resultadoPesquisaMatricula(matricula);
        }else{
            System.out.println("Matrícula não cadastrada!!");
        }
        
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }

    private void pesquisarAlunoPorCurso() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("======================== Pesquisar Aluno Por Curso ==============================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print("Digite o Curso à ser pesquisado: ");
        teclado.nextLine();
        String curso = teclado.nextLine().toUpperCase();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunosPorCurso(curso);
        ControladorPrincipal.getInstance().exibirTelaInicial();
        
    }

    private void pesquisarAlunoPorCidade() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("========================== Pesquisar Aluno Por Cidade ===========================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print("Digite a Cidade à ser pesquisado: ");
        teclado.nextLine();
        String cidade = teclado.nextLine().toUpperCase();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunosPorCidade(cidade);
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    
    private void pesquisarAlunoPorIntervalosIAA() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("================== Pesquisar Aluno Por Intervalos de IAA ========================");
        System.out.println("---------------------------------------------------------------------------------");
        String faixaIAA =exibeFaixasIntervalosIAA();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunosPorIntervaloIAA(faixaIAA);
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    
    private String exibeFaixasIntervalosIAA(){
        String faixaIAA = "Sem Faixa";
        System.out.println("");
        System.out.println("[1] Faixa 1 - Intervalo IAA [>=0 a <3] - Péssimo");
        System.out.println("[2] Faixa 2 - Intervalo IAA [>=3 a <5] - Ruim   ");
        System.out.println("[3] Faixa 3 - Intervalo IAA [>=5 a <7] - Regular");
        System.out.println("[4] Faixa 4 - Intervalo IAA [>=7 a <9] - Bom    ");
        System.out.println("[5] Faixa 5 - Intervalo IAA [>=9 a <=10] - Ótimo  ");
        System.out.println("");
        System.out.print("Digite o Codigo correspondente a faixa à ser pesquisado: ");
        int codigo = pedeInt();
        System.out.println("---------------------------------------------------------------------------------");
        while((codigo<1||codigo>5)){
            
            System.out.println("Não correspode a nenhuma faixa cadastrada!");
            System.out.print("Digite o Codigo correspondente a faixa à ser pesquisado: ");
            codigo = pedeInt();
            System.out.println("---------------------------------------------------------------------------------");
        }
        switch(codigo){
            case 1:
                faixaIAA = "Faixa 1";
                break;
            case 2:
                faixaIAA = "Faixa 2";
                break;
            case 3:
                faixaIAA = "Faixa 3";
                break;
            case 4:
                faixaIAA = "Faixa 4";
                break;
            case 5:
                faixaIAA = "Faixa 5";
                break;
        }
        
        return faixaIAA;
    }
    
    private void buscaCombinadaCursoCidade(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("================== Pesquisar Aluno Por Curso e Cidade ===========================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print("Digite o Curso à ser pesquisado: ");
        teclado.nextLine();
        String curso = teclado.nextLine().toUpperCase();
        System.out.print("Digite a Cidade à ser pesquisada: ");
        String cidade = teclado.nextLine().toUpperCase();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunoPorCursoCidade(curso, cidade);
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    
    private void buscaCombinadaCursoIntervaloIAA(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("================= Pesquisar Aluno Por Curso e Invervalos IAA ====================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print("Digite o Curso à ser pesquisado: ");
        teclado.nextLine();
        String curso = teclado.nextLine().toUpperCase();
        String faixaIAA =exibeFaixasIntervalosIAA();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunoPorCursoFaixaIAA(curso, faixaIAA);
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }
    
    private void buscaCombinadaCidadeInvervaloIAA(){
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("================= Pesquisar Aluno Por Cidade e Invervalos IAA ===================");
        System.out.println("---------------------------------------------------------------------------------");    
        System.out.print("Digite a Cidade à ser pesquisada: ");
        teclado.nextLine();
        String cidade = teclado.nextLine().toUpperCase();
        String faixaIAA =exibeFaixasIntervalosIAA();
        System.out.println("----------------------------- Resultados ----------------------------------------");
        ControladorPrincipal.getInstance().pesquisarAlunoPorCidadeFaixaIAA(cidade, faixaIAA);
        ControladorPrincipal.getInstance().exibirTelaInicial();
    }    
    //Apagar depois Teste
    /*public void exbirDadosCursos(String dadosCursos){
        String[] dadosCurso = dadosCursos.split(",");
        System.out.println("Curso: " + dadosCurso[0]);
        System.out.println("Matriculas: "+ dadosCurso[1]);
    }*/
}
