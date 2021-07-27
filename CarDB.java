import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class CarDB here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CarDB
{
    private ArrayList<Car> cars;
    
    public CarDB()
    {
        cars = new ArrayList<Car>();
    }
    
    public CarDB(ArrayList<Car> newCars)
    {
        cars = newCars;
    }
    
    public ArrayList<Car> getCars()
    {
        return cars;
    }
    
    public void setCars(ArrayList<Car> newCars)
    {
        cars = newCars;
    }
    
    public void displayCars()
    {
        for (int i = 0; i < cars.size(); i++)
        {
            System.out.println(cars.get(i).carInformation());
        }
    }
    
    public void readFile()
    {
        try
        {
            FileReader file = new FileReader("usedcars.txt"); //open file
            Scanner scanner = new Scanner(file); //read file by line
            while(scanner.hasNextLine())
            {                
                try
                {
                //create object by line
                String oneLine = scanner.nextLine();
                String[] data = oneLine.split(",");
                Car car = new Car();
                //insert all data into car object
                car.setRegs(data[0]);
                car.setYear(Integer.parseInt(data[1]));
                car.setColour(data[2], data[3], data[4]);
                car.setMaker(data[5]);
                car.setModel(data[6]);
                car.setPrice(Integer.parseInt(data[7]));
                cars.add(car);
                }
                catch(Exception e)
                {
                    System.out.println("Error");
                }
               
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
    
    public String findCarByRegs(String regs)
    {
        for (Car car: cars)
        {
            if (car.getRegs().equalsIgnoreCase(regs))
                return car.carInformation();                        
        }
        return "";
    }
    
    public void deleteCarByRegs(String regs)
    {
        for (Car car: cars)
        {
            if (car.getRegs().equalsIgnoreCase(regs))
                cars.remove(car);                        
        }
    }
    
    public void editCarByRegs(String regs, String[] colors, int price)
    {
        for (Car car: cars)
        {
            if (car.getRegs().equalsIgnoreCase(regs))
            {
                car.setColour(colors);
                car.setPrice(price);
            }                                       
        }
    }
    
    public String findCarByMakeAndModel(String make, String model)
    {
        for (Car car: cars)
        {
            if (!model.equals("ANY"))
            {
                if (car.getMaker().equals(make) && car.getModel().equals(model))
                {
                    System.out.println(car.carInformation()); 
                    return "find";
                }
            }
            else
            {
                if (car.getMaker().equals(make))
                {
                    System.out.println(car.carInformation());
                    return "find";
                }
            }
        }
        return "";
    }

    public void findCarByAge(int earliestYear)
    {
        boolean find = false;
        for (Car car: cars)
        {
            if (car.getYear() >= earliestYear)
            {
                find = true;
                System.out.println(car.carInformation());    
            }
        }
        if (!find)
        {
            System.out.println("No suitable car was found");
        }
    }
    
    public void findCarByPrice(int min, int max)
    {
        boolean find = false;
        for (Car car: cars)
        {
            if (car.getPrice() >= min && car.getPrice() <= max)
            {
                find = true;
                System.out.println(car.carInformation());    
            }
        }
        if (!find)
        {
            System.out.println("No suitable car was found");
        }
    }
    
    public void addCar(String newRegs, int newYear, String[] newColour, String newMaker, String newModel, int newPrice)
    {
        Car car = new Car();
        car.setRegs(newRegs);
        car.setYear(newYear);
        car.setColour(newColour);
        car.setMaker(newMaker);
        car.setModel(newModel);
        car.setPrice(newPrice);
        cars.add(car);
    }
    
    public void writeFile()
    {
        try
        {
            PrintWriter pw = new PrintWriter("usedcars.txt");
            for (Car car: cars)
            {
                pw.println(car.carInformation());
            }
            pw.close();
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }
    }
}
