/*

package steps_cadastro;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.junit.jupiter.api.Assertions;

public class cadastro_de_novo_usuario_red {

    private String email;
    private String senha;
    private boolean cadastroRealizado;
    private String mensagemErro;

    @Dado("que informo um e-mail válido {string}")
    public void queInformoUmEmailValido(String email) {
        this.email = email;
    }

    @Dado("informo uma senha válida {string}")
    public void informoUmaSenhaValida(String senha) {
        this.senha = senha;
    }

    @Quando("solicitar o cadastro do usuário")
    public void solicitarOCadastroDoUsuario() {

        // Serviço ainda não implementado
        cadastroRealizado = false;
    }

    @Então("o sistema deve cadastrar o usuário com sucesso")
    public void oSistemaDeveCadastrarOUsuarioComSucesso() {
        Assertions.assertTrue(cadastroRealizado);
    }

    @Dado("que já existe um usuário cadastrado com o e-mail {string}")
    public void queJaExisteUmUsuarioCadastradoComOEmail(String email) {
        this.email = email;

        // Simulação futura
    }

    @Quando("solicitar o cadastro utilizando o e-mail {string}")
    public void solicitarOCadastroUtilizandoOEmail(String email) {

        // Serviço ainda não implementado
        cadastroRealizado = true;
        mensagemErro = null;
    }

    @Então("o sistema deve impedir o cadastro")
    public void oSistemaDeveImpedirOCadastro() {
        Assertions.assertFalse(cadastroRealizado);
    }

    @Então("exibir a mensagem {string}")
    public void exibirAMensagem(String mensagemEsperada) {
        Assertions.assertEquals(mensagemEsperada, mensagemErro);
    }

}


*/
