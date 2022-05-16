package password_generator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main 
{
    
    public static void main(String[]args) throws IOException
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    new Window();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /*
        FileClass file = new FileClass("password.txt");
        file.modifyRow("dsfsdbnbvnt", "padelle");
        System.out.println("check = "+ file.serviceCheck("padelle"));
        System.out.println("" + file.getAll().toString());
        */
    }
}
