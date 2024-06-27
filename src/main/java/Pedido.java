import java.io.Serializable;

/**
 * A classe Pedido representa um pedido realizado por um cliente, contendo um identificador único,
 * um status, e um carrinho que armazena os itens selecionados.
 *
 * @author Ramon Pedro Menz
 */
public class Pedido implements Serializable {

    private int idPedido;
    private String status;
    private final Carrinho carrinho;

    /**
     * Cria um novo Pedido com status "Pendente" e um carrinho vazio.
     */
    public Pedido() {
        this.status = "Pendente";
        this.carrinho = new Carrinho();
    }

    /**
     * Cria um novo Pedido com um identificador específico, status "Pendente" e um carrinho vazio.
     *
     * @param idPedido O identificador único do pedido.
     */
    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.status = "Pendente";
        this.carrinho = new Carrinho();
    }

    /**
     * Obtém o identificador único do pedido.
     *
     * @return O identificador único do pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Define o identificador único do pedido.
     *
     * @param idPedido O identificador único a ser definido.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Obtém o status atual do pedido.
     *
     * @return O status atual do pedido.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status do pedido.
     *
     * @param status O status a ser definido.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o valor total do pedido com base nos itens do carrinho.
     *
     * @return O valor total do pedido.
     */
    public double getTotal() {
        return carrinho.getTotal();
    }

    /**
     * Obtém o carrinho associado ao pedido.
     *
     * @return O carrinho associado ao pedido.
     */
    public Carrinho getCarrinho() {
        return carrinho;
    }

    /**
     * Retorna uma representação em string do objeto Pedido, incluindo o ID do pedido,
     * status, itens do carrinho e o valor total.
     *
     * @return Uma representação em string do objeto Pedido.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(Pedido)\nID do Pedido: ").append(idPedido).append("\nStatus: ").append(status).append("\nItens:\n");

        for (ItemMenu item : carrinho.getItens()) {
            sb.append(item).append("\n\n");
        }

        sb.append("Total: R$ ").append(carrinho.getTotal());
        return sb.toString();
    }
}
