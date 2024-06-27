import java.io.Serializable;

/**
 * A classe PedidoCompleto representa um pedido completo, que inclui informações sobre o cliente e
 * os itens do pedido. Esta classe é serializável para permitir a persistência em arquivos.
 *
 * @author Ramon Pedro Menz
 */
public class PedidoCompleto implements Serializable {

    private final Cliente cliente;
    private final Pedido pedido;

    /**
     * Cria um novo PedidoCompleto com as informações do cliente e do pedido associados.
     *
     * @param cliente O cliente associado ao pedido.
     * @param pedido O pedido associado.
     */
    public PedidoCompleto(Cliente cliente, Pedido pedido) {
        this.cliente = cliente;
        this.pedido = pedido;
    }

    /**
     * Obtém o cliente associado ao pedido completo.
     *
     * @return O cliente associado ao pedido completo.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtém o pedido associado ao pedido completo.
     *
     * @return O pedido associado ao pedido completo.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Retorna uma representação em string do objeto PedidoCompleto, incluindo informações
     * sobre o cliente e os itens do pedido.
     *
     * @return Uma representação em string do objeto PedidoCompleto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n------------------------------------------------------------------------\n");
        if (cliente != null) {
            sb.append(cliente).append("\n");
        } else {
            sb.append("Cliente: [Nenhum cliente associado]\n");
        }
        sb.append("\n");
        sb.append(pedido);
        sb.append("\n------------------------------------------------------------------------\n");

        return sb.toString();
    }
}
