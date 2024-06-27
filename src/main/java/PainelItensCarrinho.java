import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.border.Border;

/**
 * O PainelItensCarrinho é responsável por exibir os itens presentes no carrinho do pedido.
 * Oferece a funcionalidade de remover itens do carrinho.
 *
 * @author Ramon Pedro Menz
 */
public class PainelItensCarrinho extends JPanel {

    private final MenuItens menuItens;
    private final Pedido pedidoAtual;
    private static final int ALTURA_MAXIMA = 140;
    private static final int LARGURA_MAXIMA = 500;

    /**
     * Construtor da classe PainelItensCarrinho.
     *
     * @param menuItens O objeto Menu associado a este painel.
     * @param pedidoAtual O pedido atual relacionado ao carrinho.
     */
    public PainelItensCarrinho(MenuItens menuItens, Pedido pedidoAtual) {
        this.menuItens = menuItens;
        this.pedidoAtual = pedidoAtual;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Atualiza o painel exibindo os itens presentes no carrinho.
     */
    public void atualizarPainel() {
        removeAll();

        for (ItemMenu item : pedidoAtual.getCarrinho().getItens()) {
            JPanel itemPanel = criarItemPanel(item);
            add(itemPanel);
        }

        revalidate();
        repaint();
    }

    /**
     * Cria um painel representando um item no carrinho, exibindo suas informações e
     * oferecendo a opção de removê-lo do carrinho.
     *
     * @param item O ItemMenu a ser exibido no painel.
     * @return JPanel configurado para exibir as informações do item no carrinho.
     */
    private JPanel criarItemPanel(ItemMenu item) {
        JPanel panel = getjPanel();

        JLabel labelNome = new JLabel("Nome: " + item.getNome());
        panel.add(labelNome);
        JLabel labelId = new JLabel("ID: " + item.getId());
        panel.add(labelId);
        
        DecimalFormat formatoPreco = new DecimalFormat("R$ 0.00");
        String precoFormatado = formatoPreco.format(item.getPreco());
        JLabel labelPreco = new JLabel("Preço: " + precoFormatado);
        panel.add(labelPreco);
        
        JButton botaoAdicionar = new JButton("Remover do Carrinho");
        botaoAdicionar.addActionListener((ActionEvent e) -> menuItens.removerDoCarrinho(item));
        panel.add(botaoAdicionar);

        return panel;
    }

    /**
     * Cria um JPanel com layout de grade, dimensão máxima definida e borda composta.
     * Este painel é utilizado como base para outros componentes que serão adicionados a ele.
     *
     * @return JPanel com layout de grade, dimensão máxima e borda composta.
     */
    private static JPanel getjPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        Dimension maxDimension = new Dimension(LARGURA_MAXIMA, ALTURA_MAXIMA);
        panel.setMaximumSize(maxDimension);

        // Criação da borda composta
        Border linhaBorda = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border espacamentoBorda = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border bordaComposta = BorderFactory.createCompoundBorder(linhaBorda, espacamentoBorda);
        panel.setBorder(bordaComposta);
        return panel;
    }

    /**
     * Cria e retorna um JScrollPane para o PainelItensCarrinho.
     *
     * @return JScrollPane configurado para este painel.
     */
    public JScrollPane criarScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }
}
