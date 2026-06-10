/*
package steps_login;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.junit.jupiter.api.Assertions;

public class login_de_usuario_green {

    private String emailCadastrado;
    private String senhaCadastrada;

    private String emailInformado;
    private String senhaInformada;

    private boolean loginRealizado;
    private String mensagemErro;

    @Dado("que existe um usuário cadastrado com o e-mail {string}e senha {string}")
    public void queExisteUmUsuarioCadastradoComOEmailESenha(String email, String senha) {
        this.emailCadastrado = email;
        this.senhaCadastrada = senha;
    }

    @Dado("que existe um usuário cadastrado com o e-mail {string} e senha {string}")
    public void queExisteUmUsuarioCadastradoComOEmailESenhaComEspaco(String email, String senha) {
        this.emailCadastrado = email;
        this.senhaCadastrada = senha;
    }

    @Quando("informar o e-mail {string} e a senha {string}")
    public void informarOEmailEASenha(String email, String senha) {

        this.emailInformado = email;
        this.senhaInformada = senha;

        if (emailInformado.equals(emailCadastrado)
                && senhaInformada.equals(senhaCadastrada)) {

            loginRealizado = true;
            mensagemErro = null;

        } else {

            loginRealizado = false;
            mensagemErro = "E-mail ou senha inválidos";
        }
    }

    @Então("o sistema deve permitir o acesso")
    public void oSistemaDevePermitirOAcesso() {
        Assertions.assertTrue(loginRealizado);
    }

    @Então("o sistema deve impedir o acesso")
    public void oSistemaDeveImpedirOAcesso() {
        Assertions.assertFalse(loginRealizado);
    }

    @Então("exibir a mensagem {string}")
    public void exibirAMensagem(String mensagemEsperada) {
        Assertions.assertEquals(mensagemEsperada, mensagemErro);
    }
}
*/
