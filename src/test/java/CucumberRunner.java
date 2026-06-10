import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
//@SelectClasspathResource("features/cadastro_de_novo_usuario.feature")
//@SelectClasspathResource("features/login_de_usuario.feature")
@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty"
)
public class CucumberRunner {}