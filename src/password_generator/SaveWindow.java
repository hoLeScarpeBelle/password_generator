/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_generator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author user
 */
public class SaveWindow extends JFrame
{

    public SaveWindow(Window parent,String password,FileClass file) 
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
        setVisible(true);
        setLocationRelativeTo(parent);
        setSize(new Dimension(200,100));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel centerPanel =new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("servizio") , BorderLayout.WEST);
        
        //cambiare
        //JTextField service_text = new JTextField();
        JComboBox<String> service_text = new JComboBox<>();
        updateComboBox(service_text,file.getAll());
        service_text.setEditable(true);
        centerPanel.add(service_text,BorderLayout.CENTER);
        add(centerPanel,BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(new JLabel("do you realy want to save?"),BorderLayout.NORTH);
        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String service = (String)service_text.getSelectedItem();
                if(file.serviceCheck(service))
                    file.modifyRow(service , password);
                else
                    file.write(service_text.getSelectedItem() + "=" + password);
                parent.updateJTextArea(parent.preSavedPassword, file.getAll());
                dispose();
            }
        });
        southPanel.add(save,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);
    }
    
    public void updateComboBox(JComboBox<String> comboBox , String[] array)
    {
        comboBox.removeAllItems();
        String[] splitRow;
        for(int i = 0;i < array.length;i++)
        {
            splitRow = array[i].split("=");
            System.out.println("service = " + splitRow[0]);
            comboBox.addItem(splitRow[0]);
        }    
    }
}
