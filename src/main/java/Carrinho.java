import java.util.ArrayList;
import java.io.Serializable;

/**
 * A classe Carrinho representa o carrinho de compras de um cliente.
 * Contém métodos para adicionar, remover, calcular o total e limpar itens do carrinho.
 *
 * @author Ramon Pedro Menz
 */
public class Carrinho implements Serializable {

    private final ArrayList<ItemMenu> itens;

    /**
     * Construtor padrão para inicializar um novo Carrinho vazio.
     */
    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    /**
     * Obtém a lista de itens no carrinho.
     *
     * @return Lista de itens no carrinho.
     */
    public ArrayList<ItemMenu> getItens() {
        return itens;
    }

    /**
     * Adiciona um item ao carrinho.
     *
     * @param item O item a ser adicionado ao carrinho.
     */
    public void adicionarItem(ItemMenu item) {
        itens.add(item);
    }

    /**
     * Remove um item do carrinho.
     *
     * @param item O item a ser removido do carrinho.
     */
    public void removerItem(ItemMenu item) {
        itens.remove(item);
    }

    /**
     * Calcula o total do carrinho somando os preços de todos os itens.
     *
     * @return O total do carrinho.
     */
    public double getTotal() {
        double total = 0;
        for (ItemMenu item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    /**
     * Limpa todos os itens do carrinho.
     */
    public void limparCarrinho() {
        itens.clear();
    }

    /**
     * Verifica se o carrinho está vazio.
     *
     * @return true se o carrinho estiver vazio, false caso contrário.
     */
    boolean isEmpty() {
        return itens.isEmpty();
    }
}
