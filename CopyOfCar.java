
/**
 * Write a description of class CopyOfCar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CopyOfCar
{
    private String regs;
    private int year;
    private String[] colour;
    private String maker;
    private String model;
    private int price;

    public CopyOfCar()
    {
        regs = "";
        year = 0;
        colour = new String[3];
        maker = "";
        model = "";
        price = 0;
    }

    public CopyOfCar(String newRegs, int newYear, String[] newColour, String newMaker, String newModel, int newPrice)
    {
        regs = newRegs;
        year = newYear;
        colour = newColour;
        maker = newMaker;
        model = newModel;
        price = newPrice;
    }        

    public void setRegs(String newRegs)
    {
        if (newRegs.length() >= 1 && newRegs.length() <= 6)
            regs = newRegs;
    }

    public String getRegs()
    {
        return regs;
    }

    public void setYear(int newYear)
    {
        if (newYear > 1000 && newYear <=2021)
            year = newYear;
    }

    public int getYear()
    {
        return year;
    }

    public void setColour(String[] newColour)
    {
        colour = newColour;
    }
    
    public void setColour(String colour0, String colour1, String colour2)
    {
        colour[0] = colour0;
        colour[1] = colour1;
        colour[2] = colour2;
    }

    public String[] getColour()
    {
        return colour;
    }

    public void setMaker(String newMaker)
    {
        maker = newMaker;
    }

    public String getMaker()
    {
        return maker;
    }

    public void setModel(String newModel)
    {
        model = newModel;
    }

    public String getModel()
    {
        return model;
    }

    public void setPrice(int newPrice)
    {
        price = newPrice;
    }

    public int getPrice()
    {
        return price;
    }

    public String carInformation()
    {
        String colours = colour[0];
        for (int i = 1; i < colour.length; i++)
        {
            colours += "," + colour[i];
        }
        return regs + "," + year + "," + colours + "," + maker + "," + model + "," + price;
    }
    
    
}
