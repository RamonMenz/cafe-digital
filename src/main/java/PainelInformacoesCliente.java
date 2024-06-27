import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * O PainelInformacoesCliente é responsável por exibir e capturar informações do cliente no menu.
 * Permite preencher o RA ou CPF, nome e número da mesa do cliente.
 *
 * @author Ramon Pedro Menz
 */
public class PainelInformacoesCliente extends JPanel {

    private final Cliente cliente;
    private final JTextField campoIdCliente;
    private final JTextField campoNomeCliente;
    private final JTextField campoMesa;
    private final JButton botaoVerPedidos;

    /**
     * Construtor da classe PainelInformacoesCliente.
     *
     * @param menuItens O objeto Menu associado a este painel.
     * @param pedidoAtual O pedido atual relacionado ao cliente.
     * @param cliente O objeto Cliente a ser exibido e atualizado no painel.
     */
    public PainelInformacoesCliente(MenuItens menuItens, Pedido pedidoAtual, Cliente cliente) {
        this.cliente = cliente != null ? cliente : new Cliente(); // Inicialize o cliente se não for fornecido

        setLayout(new GridLayout(4, 2));

        JLabel labelIdCliente = new JLabel("    RA ou CPF:");
        campoIdCliente = new JTextField();

        JLabel labelNomeCliente = new JLabel("    Nome:");
        campoNomeCliente = new JTextField();

        JLabel labelMesa = new JLabel("    Número da Mesa:");
        campoMesa = new JTextField();

        JButton botaoFinalizarPedido = getBotaoFinalizarPedido(menuItens, pedidoAtual);

        botaoVerPedidos = new JButton("Ver Pedidos");
        botaoVerPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    menuItens.mostrarPedidos();
                } else if (evt.getClickCount() == 2) {
                    menuItens.apagarPedidos();
                }
            }
        });

        add(labelIdCliente);
        add(campoIdCliente);
        add(labelNomeCliente);
        add(campoNomeCliente);
        add(labelMesa);
        add(campoMesa);
        add(botaoFinalizarPedido);
        add(botaoVerPedidos);
    }

    /**
     * Cria um JButton para finalizar o pedido. Este botão tem um ActionListener que verifica se o carrinho do pedido atual
     * não está vazio e se todas as informações do cliente foram preenchidas. Se ambas as condições forem atendidas, o cliente é salvo
     * e o pedido é finalizado. Caso contrário, uma mensagem de erro apropriada é exibida.
     *
     * @param menuItens O MenuItens que contém os itens do menu e os métodos para manipulá-los.
     * @param pedidoAtual O Pedido atual que está sendo processado.
     * @return JButton configurado para finalizar o pedido quando clicado.
     */
    private JButton getBotaoFinalizarPedido(MenuItens menuItens, Pedido pedidoAtual) {
        JButton botaoFinalizarPedido = new JButton("Finalizar Pedido");
        botaoFinalizarPedido.addActionListener((ActionEvent e) -> {
            if (!pedidoAtual.getCarrinho().isEmpty()) {
                if (!campoIdCliente.getText().isEmpty() && !campoNomeCliente.getText().isEmpty() && !campoMesa.getText().isEmpty()) {
                    salvarCliente();
                    menuItens.finalizarPedido();
                } else {
                    JOptionPane.showMessageDialog(menuItens, "Preencha todas as informações do cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(menuItens, "Adicione pelo menos um item ao seu carrinho.");
            }
        });
        return botaoFinalizarPedido;
    }

    /**
     * Salva as informações do cliente com base nos campos preenchidos no painel.
     */
    private void salvarCliente() {
        cliente.setIdCliente(campoIdCliente.getText());
        cliente.setNome(campoNomeCliente.getText());
        cliente.setMesa(campoMesa.getText());

        campoIdCliente.setText("");
        campoNomeCliente.setText("");
        campoMesa.setText("");
    }

    public JButton getBotaoVerPedidos() {
        return botaoVerPedidos;
    }
}
