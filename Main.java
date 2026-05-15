import javax.swing.*;
import java.awt.*;

interface Mediator {
    void copiarTexto(Janela origem);
}

class JanelaMediator implements Mediator {
    private Janela janela1;
    private Janela janela2;

    public void setJanela1(Janela janela1) {
        this.janela1 = janela1;
    }

    public void setJanela2(Janela janela2) {
        this.janela2 = janela2;
    }

    @Override
    public void copiarTexto(Janela origem) {
        if (origem == janela1) {
            janela2.setTextos(janela1.getTexto1(), janela1.getTexto2());
        } else {
            janela1.setTextos(janela2.getTexto1(), janela2.getTexto2());
        }
    }
}

class Janela extends JFrame {
    private JTextField campo1;
    private JTextField campo2;
    private JButton botao;
    private Mediator mediator;

    public Janela(String titulo, Mediator mediator) {
        super(titulo);
        this.mediator = mediator;

        campo1 = new JTextField(20);
        campo2 = new JTextField(20);
        botao = new JButton("Copiar para outra janela");

        setLayout(new FlowLayout());

        add(new JLabel("Texto 1:"));
        add(campo1);

        add(new JLabel("Texto 2:"));
        add(campo2);

        add(botao);

        botao.addActionListener(e -> mediator.copiarTexto(this));

        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public String getTexto1() {
        return campo1.getText();
    }

    public String getTexto2() {
        return campo2.getText();
    }

    public void setTextos(String texto1, String texto2) {
        campo1.setText(texto1);
        campo2.setText(texto2);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaMediator mediator = new JanelaMediator();

            Janela janela1 = new Janela("Janela 1", mediator);
            Janela janela2 = new Janela("Janela 2", mediator);

            mediator.setJanela1(janela1);
            mediator.setJanela2(janela2);

            janela1.setLocation(400, 300);
            janela2.setLocation(750, 300);

            janela1.setVisible(true);
            janela2.setVisible(true);
        });
    }
}
