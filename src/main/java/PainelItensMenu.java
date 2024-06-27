import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.border.Border;

/**
 * O PainelItensMenu é responsável por exibir os itens do menu digital, permitindo que
 * o usuário os visualize e adicione ao carrinho.
 *
 * @author Ramon Pedro Menz
 */
public class PainelItensMenu extends JPanel {

    private final MenuItens menuItens;

    /**
     * Cria um PainelItensMenu.
     *
     * @param menuItens O menu principal ao qual este painel está associado.
     * @param menuDigital O menu digital contendo os itens a serem exibidos.
     */
    public PainelItensMenu(MenuItens menuItens, MenuDigital menuDigital) {
        this.menuItens = menuItens;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (ItemMenu item : menuDigital.getItensMenu()) {
            JPanel itemPanel = criarItemPanel(item);
            add(itemPanel);
        }
    }

    /**
     * Cria um painel para exibir um item do menu digital, incluindo nome, descrição,
     * ID, preço e botão para adicionar ao carrinho.
     *
     * @param item O ItemMenu a ser exibido no painel.
     * @return JPanel configurado para exibir as informações do item no menu digital.
     */
    private JPanel criarItemPanel(ItemMenu item) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        
        // Criação da borda composta
        Border linhaBorda = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border espacamentoBorda = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border bordaComposta = BorderFactory.createCompoundBorder(linhaBorda, espacamentoBorda);
        panel.setBorder(bordaComposta);
        
        JLabel labelNome = new JLabel("Nome: " + item.getNome());
        panel.add(labelNome);

        // Verifica se a descrição é longa e a formata adequadamente.
        if (item.getDescricao().length() > 50) {
            String descricao = item.getDescricao();
            StringBuilder descricaoFormatada = new StringBuilder("<html><body>");
            descricaoFormatada.append("Descrição: ");

            String[] palavras = descricao.split("\\s+"); // Divide a descrição em palavras

            int contadorCaracteres = 0;
            for (String palavra : palavras) {
                if (contadorCaracteres + palavra.length() > 50) {
                    descricaoFormatada.append("<br>");
                    contadorCaracteres = 0;
                }
                descricaoFormatada.append(palavra).append(" ");
                contadorCaracteres += palavra.length() + 1; // Considera o espaço entre as palavras
            }

            descricaoFormatada.append("</body></html>");

            JLabel labelDescricao = new JLabel(descricaoFormatada.toString());
            panel.add(labelDescricao);
        } else {
            JLabel labelDescricao = new JLabel("Descrição: " + item.getDescricao());
            panel.add(labelDescricao);
        }

        JLabel labelId = new JLabel("ID: " + item.getId());
        panel.add(labelId);
        
        DecimalFormat formatoPreco = new DecimalFormat("R$ 0.00");
        String precoFormatado = formatoPreco.format(item.getPreco());
        JLabel labelPreco = new JLabel("Preço: " + precoFormatado);
        panel.add(labelPreco);
        
        JButton botaoAdicionar = new JButton("Adicionar ao Carrinho");
        botaoAdicionar.addActionListener((ActionEvent e) -> menuItens.adicionarAoCarrinho(item));
        panel.add(botaoAdicionar);

        return panel;
    }

    /**
     * Cria e retorna um JScrollPane para o PainelItensMenu.
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
