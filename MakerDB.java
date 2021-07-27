import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class MakerDB here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MakerDB
{
    private ArrayList<CarMaker> makers;
    
    public MakerDB()
    {
        makers = new ArrayList<CarMaker>();
    }
    
    public MakerDB(ArrayList<CarMaker> newMakers)
    {
        makers = newMakers;
    }
    
    public ArrayList<CarMaker> getMakers()
    {
        return makers;
    }
    
    public void setMakers(ArrayList<CarMaker> newMakers)
    {
        makers = newMakers;
    }
    
    public void displayMakers()
    {
        for (int i = 0; i < makers.size(); i++)
        {
            System.out.println(makers.get(i).makerInformation());
        }
    }
    
    public void displayMakerModel(int index)
    {
        makers.get(index).displayModel();
    }
    
    public String getMakerName(int index)
    {
        return makers.get(index).getName();
    }
    
    public String findMakerName(int index)
    {
        String makerName = "";
        if (index < 0 || index > makers.size() - 1)
            return makerName;
        else
            makerName = makers.get(index).getName();
        return makerName;
    }
    
    public int getModelSize(int index)
    {
        return makers.get(index).getModels().size();
    }
    
    public String getModel(int makerIndex, int modelIndex)
    {
        return makers.get(makerIndex).getModels().get(modelIndex);
    }
    
    public String findModel(String makerName, int index)
    {
        String model = "";
        for (CarMaker maker: makers)
        {
            if (maker.getName().equals(makerName))
                model = maker.getModel(index);
        }
        return model;
    }
    
    public void displayMakerName()
    {
        for (int i = 0; i < makers.size(); i++)
        {
            int index = i + 1;
            System.out.println("(" + index + ")" + makers.get(i).getName());
        }
    }
    
    public void readFile()
    {
        try
        {
            FileReader file = new FileReader("carmakers.txt"); //open file
            Scanner scanner = new Scanner(file); //read file by line
            while(scanner.hasNextLine())
            {
                //create object by line
                String oneLine = scanner.nextLine();
                String[] data = oneLine.split(",");
                CarMaker maker = new CarMaker();
                //insert all data into car object
                maker.setName(data[0]);
                for (int i = 1; i < data.length; i++)
                {
                    maker.addModel(data[i]);
                }
                makers.add(maker);
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
}
