import javax.swing.*;

public class WindowFrame extends JFrame {
    public WindowFrame(){
        initUI();
    }

    public void initUI(){
        add(new Surface());

        setTitle("Ray-tracer");
        setSize(649,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
