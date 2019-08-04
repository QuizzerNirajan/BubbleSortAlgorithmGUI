
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Font.ITALIC;
import static java.lang.Integer.parseInt;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.SwingConstants.CENTER;

public class Bubble implements ActionListener{

    private JFrame frame;
    private JTextField numberField,elementField;
    private JButton elementButton, numberButton, reset, sort;
    private JLabel numberLabel, text, text1;
    private JButton[] l;
    private JPanel panel, panel1, p3;
    private Font font;

    private int[] number;
    private int num;
    private static int i,  y = 10, count2;
    private static int count;
    private int p;


    private ImageIcon image;

    Bubble() {
        super();
        i = 0;
        count = 0;
        frame = new JFrame("BubbleSort");
        frame.setSize(1980, 1080);
        frame.setLayout(null);
        addComponents();
        buttonListener(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        font = new Font("Helvetica", Font.BOLD, 30);
    }

    private void addComponents() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 120);
        panel.setBackground( new Color(188, 88,250));
        frame.getContentPane().add(panel);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(1000, 0, 1900, 120);
        panel1.setBackground( new Color(188, 88,250));
        frame.getContentPane().add(panel1);

        p3 = new JPanel();
        p3.setLayout(null);
        p3.setBounds(0, 125, 1980, 800);
        p3.setBackground(Color.CYAN);
        frame.getContentPane().add(p3);
        p3.setVisible(false);

        numberLabel = new JLabel("Enter the amount of numbers ");
        numberLabel.setBounds(100, 25, 250, 40);
        numberLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
        panel.add(numberLabel);

        numberField = new JTextField();
        numberField.setBounds(400, 25, 150, 40);
        numberField.setFont(new Font("helvetica", Font.BOLD, 15));
        numberField.setHorizontalAlignment(CENTER);
        panel.add(numberField);

        numberButton = new JButton("Done");
        numberButton.setBounds(570, 25, 100, 40);
        numberButton.setFont(new Font("helvetica", Font.BOLD|ITALIC, 20));
        panel.add(numberButton);

        elementField = new JTextField();
        elementField.setBounds(50, 25, 200, 40);
        elementField.setFont(new Font("helvetica", Font.BOLD, 15));
        elementField.setHorizontalAlignment(CENTER);
        panel1.add(elementField);
        elementField.setEnabled(false);


        elementButton = new JButton("Okay");
        elementButton.setBounds(320, 25, 100, 40);
        elementButton.setFont(new Font("helvetica", Font.BOLD|ITALIC, 20));
        panel1.add(elementButton);
        elementButton.setEnabled(false);

        sort = new JButton("Sort");
        sort.setBounds(900, 950, 100, 50);
        sort.setFont(font);
        frame.add(sort);
        sort.setVisible(false);
        sort.setEnabled(false);

        reset = new JButton("Reset");
        reset.setBounds(825, 25, 150, 50);
        reset.setFont(new Font( "Helvetica",Font.CENTER_BASELINE, 30));
        reset.setBackground(Color.CYAN);
        frame.add(reset);

        text   = new JLabel();
        text.setText("");
        text.setBounds(90, 75, 200, 30);
        text.setFont(new Font("Helvetica", ITALIC, 15));
        text.setBackground(Color.BLUE);
        panel1.add(text);

        text1   = new JLabel();
        text1.setText("");
        text1.setBounds(70, 75, 200, 30);
        text1.setFont(new Font("Helvetica", ITALIC, 15));
        text1.setBackground(Color.CYAN);
        panel.add(text1);

        image = new ImageIcon("circle.png");

    }


    private void buttonListener(Bubble b) {
        numberButton.addActionListener(b);
        elementButton.addActionListener(b);
        sort.addActionListener(b);
        reset.addActionListener(b);
        mouseListener(sort);
        mouseListener(numberButton);
        mouseListener(elementButton);


    }
    private void mouseListener(JButton button)
    {

        button.addMouseListener(new MouseAdapter() {
            Color oldcolor = button.getBackground();

            public void mouseEntered(MouseEvent me) {
                if(button.isEnabled()) {
                    oldcolor = button.getBackground();
                    button.setBackground(Color.magenta);
                }
            }

            public void mouseExited(MouseEvent me) {
                button.setBackground(oldcolor);

            }
        });

    }


    public void actionPerformed(ActionEvent e) {
        String s;
        if (e.getSource() == numberButton) {

            try {
                num = parseInt(numberField.getText());
                if(num>15)
                {
                    numberField.setText("");
                    s = "Enter a number less than 16";
                    text1.setText(s);
                }

                else {
                    number = new int[parseInt(numberField.getText())];

                    numberField.setText("");
                    text1.setText("");
                    numberField.setEnabled(false);
                    numberButton.setEnabled(false);
                    elementButton.setEnabled(true);
                    elementField.setEnabled(true);
                }

            }
            catch (Exception ep)
            {
                numberField.setText("");
                s = "Please enter a number";
                text1.setText(s);

            }

        }

        if (e.getSource() == elementButton) {
            if (((elementField.getText().equals("")))){
                elementField.setText("");
                s = "Please enter a number";
                text.setText(s);
                System.out.println(" okay this");
            }
            else {
                try {
                    number[i] = parseInt(elementField.getText());
                    String c = Integer.toString(number[i]);

                    i++;
                    if (i == num) {
                        elementButton.setEnabled(false);
                        elementField.setEnabled(false);
                        elementField.setText("");
                        sort.setEnabled(true);
                        sort.setVisible(true);
                        p3.setVisible(true);
                        createArrayLabel();


                    }
                    elementField.setText("");
                    s = c + " inserted";
                    text.setText(s);
                }
                catch(Exception ep)
                {
                    elementField.setText("");
                    System.out.println(" okay this");
                    s = "Please enter a number";
                    text.setText(s);
                }
            }
        }
        if (e.getSource() == sort) {
//
            Sort(number, num);
            sort.setEnabled(false);
        }
        if(e.getSource() == reset)
        {
            frame.getContentPane().removeAll();
            frame.getContentPane().repaint();
            addComponents();
            buttonListener(this);
            number = null;
            i = 0;
            y = 10;
            count = 0;
        }
    }
    private void createArrayLabel() {
        count++;
        Font font1 = new Font("helvetica", Font.BOLD, 12);
        int x = 50;
        l = new JButton[num];
        for (int k = 0; k < num; k++) {
            l[k] = new JButton(Integer.toString(number[k]), image);
//            l[k].setContentAreaFilled(false);
//            l[k].setBorder(BorderFactory.createEmptyBorder());
//            l[k].setFocusable(true);
            l[k].setForeground(Color.RED);
            l[k].setBackground(Color.YELLOW);
            if(num<10) {
                l[k].setBounds(x, y, 120, 40);
                x += 160; p = 50;
                l[k].setFont(font1);
            }
            else
            {
                l[k].setBounds(x, y, 80, 30);
                x += 90;
                p=40;
                l[k].setFont(font1);


            }
            System.out.print(number[k] + " ");
            p3.add(l[k]);

        }

        System.out.println();


        try {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println("Noice Garyy");

        }

        p3.paint(p3.getGraphics());
        for (int k = 0; k < num; k++)
        {
            l[k].setBackground(Color.WHITE);
        }
        if(count==num+1)
        {
            for (int k = 0; k < num; k++)
            {
                l[k].setBackground(Color.GREEN);

            }

        }
        y+=p;

    }




    private void Sort(int[] arr, int n) {
        boolean swap = true;
        int i, temp;

        while (swap) {

            if(swap)
                createArrayLabel();

            swap = false;

            for (i = 0; i < n - 1; i++) {
                l[i].setBackground(Color.BLUE);
                l[i+1].setBackground(Color.BLUE);
                try{
                    Thread.sleep(1000);
                }

                catch(Exception e)
                {

                }
                p3.paint(p3.getGraphics());

                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    l[i].setText(Integer.toString(arr[i]));
                    arr[i + 1] = temp;
                    l[i+1].setText(Integer.toString(temp));
                    swap = true;
                }
                try{
                    Thread.sleep(1000);
                }

                catch(Exception e)
                {

                }


                p3.paint(p3.getGraphics());
                l[i].setBackground(Color.WHITE);
                l[i+1].setBackground(Color.WHITE);
            }
            n--;


        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Bubble::new);
    }
}
