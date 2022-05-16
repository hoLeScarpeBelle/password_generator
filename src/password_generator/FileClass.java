package password_generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileClass 
{
    private String filename;

    public FileClass(String filename) throws IOException 
    {
        this.filename = filename;
        File file = new File(filename);
        if(!file.exists() && !file.isDirectory())
        {
            file.createNewFile();
            //BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            //writer.write("");
        }
        
    }
    
    public void write(String str)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
            writer.write(str + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("error = " + e.toString());
        }
    }
            
    public String searchPasswordByService(String service)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = "";
            String spliteLine[];
            while(true)
            {
                line = reader.readLine();
                if(line == null)
                    break;
                spliteLine = line.split("=");
                if(spliteLine[0].equals(service))
                {
                    return spliteLine[0];
                }
            }
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println(""+e.toString());
        }   
        return null;
    }
    
    public boolean serviceCheck(String str)
    {
        String result = searchPasswordByService(str);
        if(result != null)
            return true;
        else
            return false;
    }
    
    public String[] getAll()
    {   
        try
        {
            String[] result = new String[getRowLenght()];
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            for(int i = 0 ; i < result.length ; i++ )
            {
                result[i] = reader.readLine();
            }
            reader.close();
            return result;
        }
        catch(Exception e)
        {
            System.out.println("error =" + e.toString());
        }
        return null;
    }
    
    public void deleteRow(String service)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            File tempFile = new File("tempFile.txt");
            tempFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String row = "";
            String[] splitRow;
            while(true)
            {
                row = reader.readLine();
                if(row == null)
                {
                    break;
                }
                splitRow = row.split("=");
                if(!splitRow[0].equals(service))
                {
                    writer.write(service);
                    writer.newLine();
                    writer.flush();
                }
            }
            writer.close();
            reader.close();
            transferData(tempFile, new File(filename));
            tempFile.delete();
        }
        catch(Exception e)
        {
            System.out.println("error = " + e.toString());
        }
        
    }
    
    public void modifyRow(String service , String newPassword)
    {
        try 
        {
            File tempFile = new File("tempFile.txt");
            tempFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String row = "";
            String[] splitRow;
            while(true)
            {
                row = reader.readLine();
                if(row == null)
                    break;
                splitRow = row.split("=");
                if(service.equals(splitRow[0]))
                {
                    writer.write(service + "=" + newPassword);
                    writer.newLine();
                }
                else
                {
                    writer.write(row);
                    writer.newLine();
                }
                writer.flush();
            }
            writer.close();
            reader.close();
            transferData(tempFile, new File(filename));
            tempFile.delete();
            
        } catch (Exception e) 
        {
            System.out.println("error = " + e.toString());
        }
    }
    
    public void transferData(File copyFile , File pasteFile)
    {
        try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(pasteFile));
        BufferedReader reader = new BufferedReader(new FileReader(copyFile));
        String row;
        while (true) {
            row = reader.readLine();
            if (row == null) {
                break;
            }
            writer.write(row);
            writer.newLine();
            writer.flush();
        }
        writer.close();
        reader.close();
        }
        catch(Exception e)
        {
            System.out.println("error = " + e.toString());
        }
    }
    public int getRowLenght()
    {
        try 
        {
            int i = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String str = "";
            while(true)
            {
                str = reader.readLine();
                if(str == null)
                    break;
                i++;
            }
            reader.close();
            return i;
        } catch (Exception e) 
        {
            System.out.println("error = " + e.toString());
        }
        return 0;
    }
}
