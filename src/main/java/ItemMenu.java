import java.io.Serializable;

/**
 * A classe ItemMenu representa um item disponível no menu.
 * Contém métodos para acessar e modificar o ID, nome, descrição e preço do item.
 *
 * @author Ramon Pedro Menz
 */
public class ItemMenu implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private double preco;

    /**
     * Construtor para criar um novo item do menu com informações específicas.
     *
     * @param id O ID único do item.
     * @param nome O nome do item.
     * @param descricao A descrição do item.
     * @param preco O preço do item.
     */
    public ItemMenu(int id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Obtém o ID do item.
     *
     * @return O ID do item.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do item.
     *
     * @param id O novo ID do item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do item.
     *
     * @return O nome do item.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do item.
     *
     * @param nome O novo nome do item.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do item.
     *
     * @return A descrição do item.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do item.
     *
     * @param descricao A nova descrição do item.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do item.
     *
     * @return O preço do item.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do item.
     *
     * @param preco O novo preço do item.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna uma representação em formato de string das informações do item.
     *
     * @return Uma string que representa o objeto ItemMenu.
     */
    @Override
    public String toString() {
        // Verifica se a descrição é longa e a formata adequadamente.
        if (getDescricao().length() > 40) {
            StringBuilder descricaoFormatada = new StringBuilder();
            String[] palavras = descricao.split("\\s+"); // Divide a descrição em palavras

            int contadorCaracteres = 0;
            for (String palavra : palavras) {
                if (contadorCaracteres + palavra.length() > 40) {
                    descricaoFormatada.append("\n    ");
                    contadorCaracteres = 0;
                }
                descricaoFormatada.append(palavra).append(" ");
                contadorCaracteres += palavra.length() + 1; // Considera o espaço entre as palavras
            }

            this.descricao = descricaoFormatada.toString().trim(); // Remove espaços extras no final
        }

        return "    ID: " + id + "\n    Nome: " + nome
                + "\n    Descrição: " + descricao + "\n    Preço: R$ " + preco;
    }
}
