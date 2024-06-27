import java.io.Serializable;

/**
 * A classe Cliente representa as informações de um cliente que faz um pedido.
 * Contém métodos para acessar e modificar o ID do cliente, nome e mesa.
 *
 * @author Ramon Pedro Menz
 */
public class Cliente implements Serializable {

    private String idCliente;
    private String nome;
    private String mesa;

    /**
     * Construtor padrão para criar um novo cliente sem informações.
     */
    public Cliente() {

    }

    /**
     * Construtor para criar um novo cliente com informações específicas.
     *
     * @param idCliente O ID único do cliente.
     * @param nome O nome do cliente.
     * @param mesa O número da mesa do cliente.
     */
    public Cliente(String idCliente, String nome, String mesa) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.mesa = mesa;
    }

    /**
     * Obtém o ID do cliente.
     *
     * @return O ID do cliente.
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente.
     *
     * @param idCliente O novo ID do cliente.
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número da mesa do cliente.
     *
     * @return O número da mesa do cliente.
     */
    public String getMesa() {
        return mesa;
    }

    /**
     * Define o número da mesa do cliente.
     *
     * @param mesa O novo número da mesa do cliente.
     */
    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    /**
     * Retorna uma representação em formato de string das informações do cliente.
     *
     * @return Uma string que representa o objeto Cliente.
     */
    @Override
    public String toString() {
        return "(Cliente)\nID do Cliente: " + idCliente + "\nNome: " + nome + "\nMesa: " + mesa;
    }
}
