package CCH.exception;

public class ConfiguracaoNaoExistenteException extends Exception {
    public ConfiguracaoNaoExistenteException(){
        super();
    }

    public ConfiguracaoNaoExistenteException(String message){
        super(message);
    }
}
