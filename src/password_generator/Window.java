package password_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Window extends JFrame
{
    private FileClass file;
    private int lenght;
    private String password;
    private char letters[] = {'a','b','c','d','e','f','g','h','i','l','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char capital_letters[] = {'A','B','C','D','E','F','G','H','I','L','J','K','L','M','N','O','P','Q','R','S','T','U','V','w','x','y','z'};
    private char numbers[] = {'0','1','2','3','4','5','6','7','8','9'};
    private char specialChapter[] = {'@','?','!','#','/'};
    protected JTextArea preSavedPassword;        
    //A = 65;B = 90
    //a = 97;b = 122
    
    public Window() throws IOException
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//setta aspetto della finestra
            //SwingUtilities.updateComponentTreeUI(this); window  refresh
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setTitle("password generator");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(500,300));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        file = new FileClass("password.txt");
        Window parent = this;
        /*esempio pagina delle password
        JTextArea text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        text.setText("dassadasdasdas");
        text.setEditable(true );
        */
        
        //north panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.LINE_AXIS));
        
        northPanel.add(new JLabel("password:"));
        JTextField password_text = new JTextField(30);
        password_text.setEditable(false);
        northPanel.add(password_text);
        northPanel.add(Box.createHorizontalGlue());
        JButton saveButton = new JButton("save");
        saveButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                /*
                System.out.println("save");
                file.write(password_text.getText());
                */
                new SaveWindow(parent,password_text.getText(),file);
            }
        });
        northPanel.add(Box.createRigidArea(new Dimension(70,0)));
        northPanel.add(saveButton);
        northPanel.add(Box.createRigidArea(new Dimension(30,0)));
        
        add(northPanel,BorderLayout.NORTH);
        
        //center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        preSavedPassword = new JTextArea();
        JScrollPane scrollBar = new JScrollPane(preSavedPassword);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        preSavedPassword.setEditable(false);
        preSavedPassword.setVisible(true);
        Font textAreaFont = new Font("Helvetica",Font.PLAIN,12);
        preSavedPassword.setFont(textAreaFont);
        updateJTextArea(preSavedPassword, file.getAll());
        centerPanel.add(preSavedPassword,BorderLayout.CENTER);
        
        add(centerPanel,BorderLayout.CENTER);
        
        //east panel
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        JPanel checkBoxes = new JPanel(new GridLayout(3,0));
        
        //check boxes
        JCheckBox capital_letter_check = new JCheckBox("capital letter");
        checkBoxes.add(capital_letter_check);
        /*
        Checkbox numbers_check = new Checkbox("numbers");
        checkBoxes.add(numbers_check);
        *///second method
        JCheckBox numbers_check = new JCheckBox("numbers");
        
        checkBoxes.add(numbers_check);
        JCheckBox special_char_check = new JCheckBox("special char");
        checkBoxes.add(special_char_check);
        
        //slider
        JPanel sliderPanel = new JPanel(new BorderLayout());
        JSlider lenght_slider = new JSlider(JSlider.HORIZONTAL,0,20,10);
        JLabel sliderValue = new JLabel(""+lenght_slider.getValue());
        
        lenght_slider.addChangeListener(new ChangeListener() 
        {
            @Override
            public void stateChanged(ChangeEvent arg0) 
            {
                sliderValue.setText(""+lenght_slider.getValue());
                lenght = lenght_slider.getValue();
            }
        });
        lenght_slider.setMaximumSize(new Dimension(100,40));
        lenght_slider.setMinimumSize(new Dimension(100,40));
        lenght_slider.setPreferredSize(new Dimension(100,40));
        
        sliderValue.setMaximumSize(new Dimension(15,20));
        sliderValue.setPreferredSize(new Dimension(15,20));
        sliderValue.setMinimumSize(new Dimension(15,20));
        
        sliderPanel.setMaximumSize(new Dimension(115,40));
        sliderPanel.setMinimumSize(new Dimension(115,40));
        sliderPanel.setPreferredSize(new Dimension(115,40));
                
        sliderPanel.add(new JLabel("lenght"),BorderLayout.NORTH);
        sliderPanel.add(lenght_slider,BorderLayout.CENTER);
        sliderPanel.add(sliderValue,BorderLayout.EAST);
        
        //save button
        JButton generateButton = new JButton("generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                //System.out.println("capital Letter = ");
                password = generate_passwrod(lenght_slider.getValue(), numbers_check.isSelected(), capital_letter_check.isSelected() , special_char_check.isSelected());
                password_text.setText(password);
            }
        });
        
        eastPanel.add(checkBoxes,BorderLayout.NORTH);
        eastPanel.add(sliderPanel,BorderLayout.CENTER);
        eastPanel.add(generateButton,BorderLayout.PAGE_END);
        
        add(eastPanel,BorderLayout.EAST);
    }
    
    //A = 65;B = 90
    //a = 97;b = 122
    
    public String generate_passwrod(int lenght,boolean numbers,boolean capital_letters,boolean special_char)
    {
        ArrayList<char[]> possible_char = new ArrayList<char[]>();
        possible_char.add(this.letters);
        char[] charArray;
        if(numbers)
            possible_char.add(this.numbers);
        if(capital_letters)
            possible_char.add(this.capital_letters);
        if(special_char)
            possible_char.add(this.specialChapter);
        String password = "";
        int rand = 0;
        for(int i = 0 ; i < lenght ;i++)
        {
            //rand = (int)(Math.random()*26);
            //password += ""+(char)('a'+rand);
            //System.out.println("size = " + possible_char.size());
            rand = (int)(Math.random()*possible_char.size());
            switch(rand)
            {
                case 0:
                    /*
                    for(char c : possible_char.get(0))
                    {
                        System.out.print(c +",");
                    }
                    */
                    //System.out.println("arary 0 = " + possible_char.get(0).toString());
                    charArray = possible_char.get(rand);
                    rand = (int)(Math.random()*charArray.length);
                    //System.out.println("selected char(letter) = " + charArray[rand]);
                    password += "" + charArray[rand];
                    break;
                    
                case 1:
                    charArray = possible_char.get(rand);
                    rand = (int)(Math.random()*charArray.length);
                    //System.out.println("selected char(numbers) = " + charArray[rand]);
                    password += "" + charArray[rand];
                    break;
                    
                case 2:
                    charArray = possible_char.get(rand);
                    rand = (int)(Math.random()*charArray.length);
                    //System.out.println("selected char(capital letter) = " + charArray[rand]);
                    password += "" + charArray[rand];
                    break;
                    
                case 3:
                    charArray = possible_char.get(rand);
                    rand = (int)(Math.random()*charArray.length);
                    //System.out.println("selected char(special_char) = " + charArray[rand]);
                    password += "" + charArray[rand];
                    break;
                    
                default:
                    System.out.println("default");
                    break;
            }
        }
        return password;
    }
    
    public void updateJTextArea(JTextArea text , String[] array)
    {
        String str = "service and password\n"; 
        for(int i = 0 ; i < array.length ; i++)
            str += array[i] + "\n";
        text.setText(str);
    }
}