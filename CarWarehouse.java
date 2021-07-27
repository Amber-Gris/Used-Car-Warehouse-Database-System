import java.util.Scanner;

/**
 * Write a description of class CarWarehouse here.
 *
 * @author (Xin Li)
 * @version (28/05)
 */
public class CarWarehouse
{
    private CarDB carDB;
    private MakerDB makerDB;

    public CarWarehouse()
    {
        carDB = new CarDB();
        makerDB = new MakerDB();
    }

    public void start()
    {
        carDB.readFile();
        makerDB.readFile();
        runMainMenu();
    }

    /**
     * display main menu
     */
    public void displayMainMenu()
    {
        System.out.println("Main menu:");
        System.out.println("(1) Search Cars");
        System.out.println("(2) Add Car");
        System.out.println("(3) Delete Car");
        System.out.println("(4) Edit Car");
        System.out.println("(5) Exit System");
    }

    /**
     * display sub menu
     */
    public void displaySubMenu()
    {
        System.out.println("Car Searching Options:");
        System.out.println("(1) By Registration Number");
        System.out.println("(2) By Car Make and Car Model");
        System.out.println("(3) By Car Age");
        System.out.println("(4) By Price (range)");
        System.out.println("(5) Back to Main Menu");
    }

    /**
     * return user input(string type)
     */
    public String userInput()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * return user input(int type)
     */
    public int inputNum()
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean valid = false;
        while(!valid)
        {
            try
            {
                int num = Integer.parseInt(input);
                valid = true;
            }
            catch (Exception exception)
            {
                System.out.println("Error, please enter an integer");
                input = sc.nextLine();
            }
        }
        return Integer.parseInt(input);
    }

    /**
     * run main menu
     */
    public void runMainMenu()
    {
        boolean end = false;
        String option = "";
        do
        {
            displayMainMenu();
            System.out.println("Please input your option: ");
            option = userInput();
            switch(option)
            {
                case "1": searchCars(); break;
                case "2": addCar(); break;
                case "3": deleteCar(); break;
                case "4": editCar(); break;
                case "5": end = true; break;
                default: System.out.println("Error, your option must be in 1-5");
            }
        }
        while(!end);
        carDB.writeFile();
    }

    /**
     * search car
     * case "1" in main menu
     */
    public void searchCars()
    {
        runSubMenu();
    }

    /**
     * input colors
     */
    public String[] inputColors()
    {
        String[] colors = new String[3];
        for (int i = 0; i < 3; i++)
        {
            System.out.println("Please enter colors(at least one color)");
            colors[i] = userInput();
            while (colors[0].trim().length() == 0 || colors[0].trim().contains(","))
            {
                System.out.println("At least one color and Con not contains comma");
                colors[0] = userInput(); 
            }
            if (i != 0)
            {
                while (colors[i].trim().contains(","))
                {
                    System.out.println("Con not contains comma");
                    colors[i] = userInput(); 
                }
            }
        }
        return colors;
    }

    /**
     * input price
     */
    public int inputPrice()
    {
        System.out.println("Please enter the price");
        String price = userInput();
        while (!priceCheck(price))
        {
            System.out.println("Invalid price, please enter again");
            price = userInput();
        }
        int carPrice = Integer.parseInt(price);
        return carPrice;
    }

    /**
     * add car
     * case "2" in main menu
     */
    public void addCar()
    {
        //input regs
        System.out.println("Please enter the registration number");
        String regs = userInput();
        while (!regsNumCheck(regs) || 
        carDB.findCarByRegs(regs).length() != 0)
        {
            System.out.println("Invalid registration number or repeated, please enter again");
            regs = userInput();
        }
        //input color
        String[] colors = inputColors();
        //select maker
        String makerName = getMakerName();
        //select model
        String modelName = getModelName(makerName);
        //input price
        int carPrice = inputPrice();
        //input year
        System.out.println("Please enter the made year of the car");
        String year = userInput();
        while(!(isNumber(year) && Integer.parseInt(year) > 1000 && Integer.parseInt(year) <= 2021))
        {
            System.out.println("Invalid made year, please enter again");
            year = userInput();
        }
        int madeYear = Integer.parseInt(year);
        carDB.addCar(regs, madeYear, colors, makerName, modelName, carPrice);
    }

    /**
     * delete car
     * case "3" in main menu
     */
    public void deleteCar()
    {
        System.out.println("Please enter the registration number");
        String regs = userInput();
        while (!regsNumCheck(regs))
        {
            System.out.println("Invalid registration number, please enter again");
            regs = userInput();
        }
        String targetCar = carDB.findCarByRegs(regs);
        if (targetCar.equals(""))
            System.out.println("Not found car");
        else
            carDB.deleteCarByRegs(regs);            
    }
    
    public void test(CarMaker maker)
    {
        maker.displayModel();
    }

    /**
     * edit car
     * case "4" in main menu
     */
    public void editCar()
    {
        System.out.println("Please enter the registration number");
        String regs = userInput();
        while (!regsNumCheck(regs))
        {
            System.out.println("Invalid registration number, please enter again");
            regs = userInput();
        }
        String targetCar = carDB.findCarByRegs(regs);
        if (targetCar.equals(""))
            System.out.println("Not found car");
        else
            System.out.print(targetCar);
        String[] colors = inputColors();
        int carPrice = inputPrice();
        carDB.editCarByRegs(regs, colors, carPrice);
    }

    /**
     * run the sub menu
     */
    public void runSubMenu()
    {
        boolean end = false;
        String option = "";
        do
        {
            displaySubMenu();
            System.out.println("Please input your option: ");
            option = userInput();
            switch(option)
            {
                case "1": byRegsNum(); break;
                case "2": byMakeAndModel(); break;
                case "3": byCarAge(); break;
                case "4": byPrice(); break;
                case "5": end = true; break;
                default: System.out.println("Error, your option must be in 1-5");
            }
        }
        while(!end);
    }

    /**
     * check the validation of regs number
     */
    public boolean regsNumCheck(String regsNum)
    {
        if (regsNum.length() == 0 && regsNum.length() > 6)
            return false;
        for (int i = 0; i < regsNum.length(); i++)
        {
            char c = regsNum.toUpperCase().charAt(i);
            if ((c < 'A' || c > 'Z') && (c < '0' || c > '9'))
                return false;
        }
        return true;
    }

    public boolean isNumber(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    /**
     * check price
     */
    public boolean priceCheck(String price)
    {
        if (isNumber(price) && Integer.parseInt(price) > 0)
            return true;
        else
            return false;
    }

    /**
     * case "1" in sub menu
     * search car by regs
     */
    public void byRegsNum()
    {
        System.out.println("Please input the registration number");
        String regsNum = userInput();
        while(!regsNumCheck(regsNum))
        {
            System.out.println("Invalid registration number, please input again");
            regsNum = userInput();
        }
        String result= carDB.findCarByRegs(regsNum);
        if (result.length() == 0)
            System.out.println("Error, the registration number is not found");
        else
            System.out.println(result);
    }

    /**
     * return the maker name
     */
    public String getMakerName()
    {
        makerDB.displayMakerName();
        System.out.println("Please choose a maker from the list");
        int makeChoice = inputNum();
        String makerName = makerDB.findMakerName(makeChoice - 1);
        while (makerName.equals(""))
        {
            System.out.println("Invalid choice, please enter again");
            makeChoice = inputNum();
            makerName = makerDB.findMakerName(makeChoice - 1);
        }
        makerDB.displayMakerModel(makeChoice - 1);
        return makerName;
    }

    /**
     * return the model name
     */
    public String getModelName(String makerName)
    {
        String modelChoice = userInput();
        String modelName = "";
        if (modelChoice.equalsIgnoreCase("ANY"))
            modelName = "ANY";
        else
        {
            boolean find = false;
            while (!find)
            {
                if (!isNumber(modelChoice))
                {
                    System.out.println("Invalid choice, please enter again");
                    modelChoice = userInput();
                }
                else
                {
                    int index = Integer.parseInt(modelChoice);
                    modelName = makerDB.findModel(makerName, index - 1);
                    if (modelName.equals(""))
                    {
                        System.out.println("Invalid choice, please enter again");
                        modelChoice = userInput();
                    }
                    else
                    {
                        find = true;
                    }
                }
            }
        }
        return modelName;
    }

    public void byMakeAndModel()
    {
        String makerName = getMakerName();
        String modelName = getModelName(makerName);
        String reslut = carDB.findCarByMakeAndModel(makerName, modelName);
        if (reslut.length() == 0)
            System.out.println("Error! Car Not Found!");
    }

    public void byCarAge()
    {
        System.out.println("Please enter the maximum age of cars");
        String maxAge = userInput();
        while(!(isNumber(maxAge) && Integer.parseInt(maxAge) >= 0))
        {
            System.out.println("Invalid maximum age of cars, please enter again");
            maxAge = userInput();
        }
        int earliestYear = 2021 - Integer.parseInt(maxAge);
        carDB.findCarByAge(earliestYear);        
    }    

    public void byPrice()
    {
        System.out.println("Please enter the minimum price of cars");
        String min = userInput();
        System.out.println("Please enter the maximum price of cars");
        String max = userInput();
        while (!(priceCheck(min) && priceCheck(max) && Integer.parseInt(min) < Integer.parseInt(max)))
        {
            System.out.println("Invalid price of cars, please enter the minimum and maximum price again");
            min = userInput();
            max = userInput();
        }        
        carDB.findCarByPrice(Integer.parseInt(min), Integer.parseInt(max));
    }
}
