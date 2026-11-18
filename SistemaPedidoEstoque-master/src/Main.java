import br.com.sistema.estoque.Estoque;
import br.com.sistema.facade.PedidoFacade;
import br.com.sistema.observer.AlertaConsoleObserver;
import br.com.sistema.view.Menu;

public class Main {
    public static void main(String[] args) {
        Estoque.getInstance().adicionarObserver(new AlertaConsoleObserver());

        PedidoFacade facade = new PedidoFacade();
        Menu menu =  new Menu(facade);

        menu.iniciar();
    }

}