import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLControl implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextArea statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta;
    private JTextField tf;
    private int WIDTH=800;
    private int HEIGHT=700;
    private JButton del,two;
    private JTextField firstLURL;
    private JTextField secondLURL;
    private JPanel resultPannel;


    public HTMLControl() {
        prepareGUI();
    }

    public static void main(String[] args) {
        HTMLControl swingControlDemo = new HTMLControl();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("HTML Link Puller");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(4, 1));
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mainFrame.add(mb);


        firstLURL = new JTextField();
        mainFrame.add(firstLURL);
        secondLURL = new JTextField();
        mainFrame.add(secondLURL);
        mainFrame.setJMenuBar(mb);
       // headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JTextArea();
        statusLabel.setSize(350, 100);


        resultPannel = new JPanel();
        resultPannel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(statusLabel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel((new BorderLayout()));

        controlPanel.setLayout(new FlowLayout());

        JButton goButton = new JButton("Go");

        goButton.setActionCommand("Go");

        goButton.addActionListener(new ButtonClickListener());

        controlPanel.add(goButton);
        resultPannel.add(scrollPane, BorderLayout.CENTER);
     //   resultPannel.add(scrollPane);
//        resultPannel.add(scrollPane);


        mainFrame.add(controlPanel);
        mainFrame.add(resultPannel);
      //  mainFrame.add(statusLabel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");



        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();

    }



    public void HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
           // URL url = new URL("https://www.milton.edu/");
            URL url = new URL(firstLURL.getText());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if (line.contains("href=\"") && line.contains("https") && line.contains(secondLURL.getText())){
                    int start = line.indexOf("href=\"") + 6;
                    int end = line.indexOf("\"", start);
                    String PartofLine = line.substring(start,end);
                    System.out.println("Links Found: " + PartofLine);
                    statusLabel.append("\n"+PartofLine);

                }

            }
            reader.close();
        } catch(Exception ex) {
            System.out.println("Error" + ex);
        }

    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println("*"+command);
            if (command.equals("test1")) {
                System.out.println("booom");
                ta.setText("booom");

            }
            if (command.equals("Go")){
                statusLabel.setText("Go Button clicked, finding results.");
                HtmlRead();

            }

        }
    }

}

