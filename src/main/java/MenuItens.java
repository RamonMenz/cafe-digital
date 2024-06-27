import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A classe Menu representa a interface gráfica do menu digital do Bomalu Café.
 * Permite adicionar itens ao carrinho, remover itens, finalizar pedidos e visualizar pedidos anteriores.
 *
 * @author Ramon Pedro Menz
 */
public class MenuItens extends JFrame {

    private final Pedido pedidoAtual;
    private final Cliente cliente;
    private int numeroPedido = obterUltimoIdPedido() + 1;
    private final PainelItensCarrinho painelItensCarrinho;
    private static final String ARQUIVO_PEDIDOS = "listaClientes.bin";

    /**
     * Construtor da classe Menu. Inicializa a interface gráfica e os componentes principais.
     */
    public MenuItens() {
        super("Menu Digital - Cafeteria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        MenuDigital menuDigital = new MenuDigital(); // Inicializa o menu digital
        menuDigital.adicionarItemMenu(new ItemMenu(1, "Café Americano", "Café forte", 7.5));
        menuDigital.adicionarItemMenu(new ItemMenu(2, "Cappuccino", "Café com leite vaporizado e espuma de leite", 9.0));
        menuDigital.adicionarItemMenu(new ItemMenu(3, "Latte", "Café espresso com uma pequena quantidade de leite vaporizado e espuma de leite", 3.5));
        menuDigital.adicionarItemMenu(new ItemMenu(4, "Croissant", "Croissant fresco", 12.0));
        menuDigital.adicionarItemMenu(new ItemMenu(5, "Pão de Queijo", "Pão de queijo quentinho", 10.0));
        menuDigital.adicionarItemMenu(new ItemMenu(6, "Muffin de Blueberry", "Muffin com pedaços de blueberry", 9.5));
        menuDigital.adicionarItemMenu(new ItemMenu(7, "Sanduíche de Frango com Pesto", "Sanduíche com peito de frango grelhado e molho pesto", 7.0));
        menuDigital.adicionarItemMenu(new ItemMenu(8, "Suco de Laranja Natural", "Suco de laranja fresco", 6.0));
        pedidoAtual = new Pedido(numeroPedido);

        PainelItensMenu painelItensMenu = new PainelItensMenu(this, menuDigital);
        JScrollPane scrollPaneItensMenu = painelItensMenu.criarScrollPane();

        painelItensCarrinho = new PainelItensCarrinho(this, pedidoAtual);
        JScrollPane scrollPaneItensCarrinho = painelItensCarrinho.criarScrollPane();

        cliente = new Cliente();
        PainelInformacoesCliente painelInformacoesCliente = new PainelInformacoesCliente(this, pedidoAtual, cliente);

        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPaneItensCarrinho, painelInformacoesCliente);
        splitPaneVertical.setResizeWeight(0.7);

        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneItensMenu, splitPaneVertical);
        splitPaneHorizontal.setResizeWeight(0.5);

        add(splitPaneHorizontal);
        setVisible(true);
    }

    /**
     * Adiciona um item ao carrinho.
     *
     * @param item O item a ser adicionado ao carrinho.
     */
    protected void adicionarAoCarrinho(ItemMenu item) {
        pedidoAtual.getCarrinho().adicionarItem(item);
        painelItensCarrinho.atualizarPainel();
    }

    /**
     * Remove um item do carrinho.
     *
     * @param item O item a ser removido do carrinho.
     */
    protected void removerDoCarrinho(ItemMenu item) {
        pedidoAtual.getCarrinho().removerItem(item);
        painelItensCarrinho.atualizarPainel();
    }

    /**
     * Finaliza o pedido, exibindo um diálogo com o total e salvando o pedido.
     */
    protected void finalizarPedido() {
        JOptionPane.showMessageDialog(this, "Pedido finalizado!\nTotal: R$ " + pedidoAtual.getTotal());
        
        pedidoAtual.setIdPedido(numeroPedido);
        salvarPedido();
        numeroPedido++;
        
        pedidoAtual.getCarrinho().limparCarrinho();
        painelItensCarrinho.atualizarPainel();
    }

    /**
     * Carrega os pedidos salvos no arquivo.
     *
     * @return Uma lista de objetos PedidoCompleto.
     */
    private ArrayList<PedidoCompleto> carregarPedidos() {
        ArrayList<PedidoCompleto> pedidos = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(ARQUIVO_PEDIDOS)))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                pedidos = (ArrayList<PedidoCompleto>) obj;
            }
        } catch (IOException | ClassNotFoundException ex) { // O arquivo ainda não existe ou está vazio
            System.out.println("Nenhum pedido encontrado.");
        }

        return pedidos;
    }

    /**
     * Salva o pedido atual no arquivo de pedidos.
     */
    protected void salvarPedido() {
        ArrayList<PedidoCompleto> pedidos = carregarPedidos(); // Carrega os pedidos existentes

        PedidoCompleto pedidoCompleto = new PedidoCompleto(cliente, pedidoAtual);
        pedidos.add(pedidoCompleto);

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(ARQUIVO_PEDIDOS)))) {
            oos.writeObject(pedidos); // Salva a lista atualizada de pedidos no arquivo
        } catch (IOException ex) {
            Logger.getLogger(MenuItens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra os pedidos salvos.
     */
    protected void mostrarPedidos() {
        ArrayList<PedidoCompleto> pedidos = carregarPedidos();

        if (!pedidos.isEmpty()) {
            for (PedidoCompleto pedidoCompleto : pedidos) {
                System.out.println(pedidoCompleto);
            }
        } else {
            System.out.println("Nenhum pedido encontrado.");
        }
    }

    /**
    * Apaga a lista de pedidos, salvando uma lista vazia no arquivo.
    * Imprime uma mensagem indicando que a lista de pedidos foi apagada.
    */
    protected void apagarPedidos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(ARQUIVO_PEDIDOS)))) {
            oos.writeObject(new ArrayList<>()); // Cria uma lista vazia e salva no arquivo
            System.out.println("Lista de pedidos apagada.");
        } catch (IOException ex) {
            Logger.getLogger(MenuItens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtém o ID do último pedido salvo no arquivo.
     *
     * @return O ID do último pedido ou 0 se nenhum pedido foi salvo.
     */
    private int obterUltimoIdPedido() {
        ArrayList<PedidoCompleto> pedidos = carregarPedidos();

        if (!pedidos.isEmpty()) {
            // Ordena os pedidos por ID em ordem decrescente
            pedidos.sort((p1, p2) -> Integer.compare(p2.getPedido().getIdPedido(), p1.getPedido().getIdPedido()));
            return pedidos.get(0).getPedido().getIdPedido();
        } else {
            return 0; // Nenhum pedido foi salvo
        }
    }

    /**
     * Método principal que inicia a aplicação Swing.
     *
     * @param args Os argumentos da linha de comando (não utilizado).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuItens::new);
    }
}
