import java.util.ArrayList;
/**
 * Write a description of class CarMaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarMaker
{
    private String name;
    private ArrayList<String> models;
    
    public CarMaker()
    {
        name = "";
        models = new ArrayList<String>();
    }
    
    public CarMaker(String newName, ArrayList<String> newModels)
    {
        name = newName;
        models = newModels;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public ArrayList<String> getModels()
    {
        return models;
    }
    
    public void setModels(ArrayList<String> newModels)
    {
        models = newModels;
    }
    
    public void addModel(String newModel)
    {
        models.add(newModel);
    }
    
    public String getModel(int index)
    {
        String model = "";
        if (index < 0 || index > models.size() - 1)
            return model;
        else
            model = models.get(index);
        return model;
    }
    
    public String makerInformation()
    {
        String model = models.get(0);
        for (int i = 1; i < models.size(); i++)
        {
            model += "," + models.get(i);
        }
        return name + "," + model;
    } 
    
    public void displayModel()
    {
        for (int i = 0; i < models.size(); i++)
        {
            int index = i + 1;
            System.out.println("(" + index + ")" + models.get(i));
        }
    }
}
