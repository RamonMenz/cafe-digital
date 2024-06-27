import java.util.ArrayList;
import java.util.List;

/**
 * A classe MenuDigital representa o menu de itens disponíveis no Bomalu Café.
 * Permite adicionar, remover e exibir itens do menu.
 *
 * @author Ramon Pedro Menz
 */
public class MenuDigital {

    private final List<ItemMenu> itensMenu;

    /**
     * Construtor da classe MenuDigital. Inicializa a lista de itens do menu.
     */
    public MenuDigital() {
        this.itensMenu = new ArrayList<>();
    }

    /**
     * Adiciona um item ao menu.
     *
     * @param item O item a ser adicionado ao menu.
     */
    public void adicionarItemMenu(ItemMenu item) {
        itensMenu.add(item);
    }

    /**
     * Remove um item do menu.
     *
     * @param item O item a ser removido do menu.
     */
    public void removerItemMenu(ItemMenu item) {
        itensMenu.remove(item);
    }

    /**
     * Exibe todos os itens do menu na saída padrão.
     */
    public void exibirMenu() {
        for (ItemMenu item : itensMenu) {
            System.out.println(item);
            System.out.println("--------");
        }
    }

    /**
     * Obtém a lista de itens do menu.
     *
     * @return A lista de itens do menu.
     */
    public List<ItemMenu> getItensMenu() {
        return itensMenu;
    }

    /**
     * Retorna uma representação em string do objeto MenuDigital.
     *
     * @return Uma string que representa o objeto MenuDigital.
     */
    @Override
    public String toString() {
        return "(MenuDigital)\nItens do Menu: " + itensMenu;
    }
}
