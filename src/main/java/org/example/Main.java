package org.example;
// CA1
import java.io. * ;
import java.util.*;




public class Main {
    public static void main(String[] args) {

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList= new ArrayList<>();

        loadPassengerDataFromFile( passengerList, fileName);

        displayAllPassengers( passengerList );


    // Assignment: Implement and test the following methods.
    // See the description of each method in the CA1 Specification PDF file from Moodle
    System.out.println("\nQuestion 1\nPassenger Names");
    String [] passengerNames =getPassengerNames(passengerList);
    System.out.println(Arrays.toString(passengerNames));

    System.out.println("\nQuestion 2\nPassengers with the name William");
    ArrayList <Passenger> passengerContainingNames =getPassengersContainingNames(passengerList, "William");
    System.out.println(passengerContainingNames);

    System.out.println("\nQuestion 3\nPassengers older than 60");
    ArrayList <Passenger> OlderThanPassengers = getPassengersOlderThan(passengerList, 60);
    System.out.println(OlderThanPassengers);

    System.out.println("\nQuestion 4\nPassengers by gender");
    //I was unsure whether this question was asking for the count of just a list of passengers with that gender,
    // so I had it return the list and print out the count
    ArrayList <Passenger> countByGender =countPassengersByGender(passengerList, "Female");
    System.out.println(countByGender);

    System.out.println("\nQuestion 5\nSum of Fares");
    double sumOfFares =sumFares(passengerList);
    System.out.println("The sum of fares is: €" +sumOfFares);

    System.out.println("\nQuestion 6\nMale Survivors");
    ArrayList <String> maleSurvivorsList =maleSurvivors(passengerList);
    System.out.println(maleSurvivorsList);

    System.out.println("\nQuestion 7\nTicket Owner of Ticket 'PP 9549'");
    Passenger ticketOwner =ticketOwner(passengerList,"PP 9549");
    System.out.println(ticketOwner);
    System.out.println("\nTicket Owner of fake Ticket 'PP 9541'");
    Passenger ticketOwner2 =ticketOwner(passengerList,"PP 9541");
    System.out.println(ticketOwner2);

    System.out.println("\nQuestion 8\nAverage Age");
    int avgAge =averageAge(passengerList);
    System.out.println(avgAge);

    System.out.println("\nQuestion 9\nPassengers by Class");
    ArrayList <Passenger> classPassengers=getPassengersByTicketClass(passengerList,PassengerClass.FIRST);
    System.out.println(classPassengers);

    System.out.println("\nQuestion 10\nSort Passengers by Id");
    ArrayList <Passenger> sortedPassengersId =sortPassengersByPassengerId(passengerList);
    System.out.println(sortedPassengersId);

    System.out.println("\nQuestion 11\nSort Passengers by Name");
    ArrayList <Passenger> sortedPassengersName =sortPassengersByName(passengerList);
    System.out.println(sortedPassengersName);

    System.out.println("\nQuestion 12\nSort Passengers by Age then Name");
    ArrayList <Passenger> sortedPassengersByAgeThenName =sortPassengersByAgeThenName(passengerList);
    System.out.println(sortedPassengersByAgeThenName);

    //The question said "Passenger Number" which isn't a thing, so I assumed passenger id
    System.out.println("\nQuestion 13\nSort Passengers by Gender then Passenger Id");
    ArrayList <Passenger> sortedPassengersByGenderThenNumber =sortPassengersByGenderThenPassengerNumber(passengerList);
    System.out.println(sortedPassengersByGenderThenNumber);

    System.out.println("\nQuestion 14\nSort Passengers by Fare then Survival");
    ArrayList <Passenger> sortedPassengersByFareThenSurvival =sortPassengersByFareThenSurvival(passengerList);
    System.out.println(sortedPassengersByFareThenSurvival);

    System.out.println("\nQuestion 15\nSort Passengers by Ticket Class");
    ArrayList <Passenger> sortedPassengersByTicketClass =sortPassengersByTicketClass(passengerList);
    System.out.println(sortedPassengersByTicketClass);

    System.out.println("\nQuestion 16\nSort Passengers by Age");
    ArrayList <Passenger> sortedPassengersByAge =sortPassengersByAge(passengerList);
    System.out.println(sortedPassengersByAge);

    System.out.println("\nQuestion 17\nSort Passengers by Ticket Number Lambda");
    ArrayList <Passenger> sortedPassengersByTicketNumber =sortPassengersByTicketNumberLambda(passengerList);
    System.out.println(sortedPassengersByTicketNumber);

    System.out.println("\nQuestion 18\nSort Passengers by Ticket Number Static");
    ArrayList <Passenger> sortedPassengersByTicketNumberStatic =sortPassengersByTicketNumberStatic(passengerList);
    System.out.println(sortedPassengersByTicketNumberStatic);

    System.out.println("\nQuestion 19\nFind Passenger by Ticket Number");
    //I created a passenger to test if it wasn't in the array
    Passenger testWrong = new Passenger ("101",0,3,"Braund", "male", 22, 1, 0, "NoTicket", 7.25, "NoCabin","S");
    findPassengerByTicketNumber(passengerList,passengerList.get(5));

    System.out.println("\nQuestion 20\nFind Passenger by Id");
    findPassengerByPassengerId(passengerList,passengerList.get(5));

    System.out.println("Finished, Goodbye!");
    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile( ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+"))
        {

            // skip past the first line, as it has field names (not data)
            if(sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext())
            {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId +", " + name);

                passengerList.add(
                        new Passenger( passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses,parentsAndChildren,ticketNumber,
                                fare, cabin, embarkedAt)
                );
            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " +fileName+ " may not exist." + exception);
        }
    }

    public static void displayAllPassengers( ArrayList<Passenger> passengerList ) {
        System.out.println("Displaying all passengers:");
        for( Passenger passenger : passengerList)
        {
            System.out.println(passenger);
        }
    }

    //CA Questions Start Here
    public static String [] getPassengerNames(ArrayList<Passenger> passengerList) {
        String[] passengerNames = new String[passengerList.size()];
        //Create a new list and cycling through the original, add the names
        for (Passenger passenger : passengerList)
        {
            passengerNames[passengerList.indexOf(passenger)] = passenger.getName();
        }
        return passengerNames;
    }

    public static ArrayList <Passenger> getPassengersContainingNames(ArrayList<Passenger> passengerList, String nameIn) {
        ArrayList <Passenger> passengerContainingName= new ArrayList <Passenger>();
        //Create a new array list and cycling through the original; check of the name matches the one brought in
        for( Passenger passenger : passengerList)
        {
            if(passenger.getName().contains(nameIn))
            {
                passengerContainingName.add(passenger);
            }
        }

        return passengerContainingName;
    }

    public static ArrayList <Passenger> getPassengersOlderThan(ArrayList<Passenger> passengerList, int ageIn) {
        ArrayList <Passenger> passengersOlderThan  = new ArrayList <Passenger>();
        //Create a new array list and cycling through the original; if the taken in age matches the age of the passenger, then add the passenger to the new list
        for( Passenger passenger : passengerList)
        {
            if(passenger.getAge() > ageIn)
            {
                passengersOlderThan.add(passenger);
            }
        }

        return passengersOlderThan;
    }

    //The wording of this question was confusing, it said to return a list, but it was called a count, I decided to return the list of passengers who fit the criteria while also printing out how many there were.
    public static ArrayList <Passenger> countPassengersByGender(ArrayList<Passenger> passengerList, String gender) {
        ArrayList <Passenger> countGender = new ArrayList<Passenger>();
        int count = 0;
        //Create a new array list and cycling through the original; check if each passenger matches with the inputted gender,
        //Then increase the count and add this passenger to the list
        for( Passenger passenger : passengerList)
        {
            if(passenger.getGender().equals(gender.toLowerCase()))
            {
                countGender.add(passenger);
                count++;
            }
        }
        System.out.println("The count for the number of " +gender+ " passengers equals "+ count);
        return countGender;
    }

    public static double sumFares (ArrayList <Passenger> passengerList) {
        double sum = 0;
        //This loops through all passengers and adds their fares to a total which is returned at the end
        for( Passenger passenger : passengerList)
        {
            sum += passenger.getFare();
        }

        return sum;
    }

    public static ArrayList <String>  maleSurvivors(ArrayList <Passenger> passengerList) {
        ArrayList <String> maleSurvivorsList = new ArrayList <String>();
        //Create a new array list and cycling through the original; checks if the passenger is male and survived then adds their name to the list
        for( Passenger passenger : passengerList)
        {
            if(passenger.getGender().equals("male") && passenger.getSurvived() == 1)
            {
                maleSurvivorsList.add(passenger.getName());
            }
        }

        return maleSurvivorsList;
    }

    public static Passenger ticketOwner(ArrayList<Passenger> passengerList, String ticketNumber) {
        Passenger PassengerInfo = null;
        //Creates an empty passenger to store the found passenger
        //Loops through all passengers to and compares their ticket number with the inputted one, once found they are stored in the empty passenger to be returned
        for( Passenger passenger : passengerList)
        {
            if(ticketNumber.equals(passenger.getTicketNumber()))
            {
                PassengerInfo = passenger;
            }
        }
        return PassengerInfo;
    }

    public static int averageAge (ArrayList <Passenger> passengerList) {
        int averageAge = 0;
        //Creates an empty total and loops through every passenger, it adds the passengers age to the total
        for( Passenger passenger : passengerList)
        {
            averageAge += passenger.getAge();
        }
        //Then I divide the average age by the amount of people in the list
        averageAge /= passengerList.size();
        return averageAge;
    }

    public static ArrayList <Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengerList,PassengerClass ticketClass) {
        ArrayList <Passenger> classPassengers = new ArrayList <Passenger>();
        //Create a new array list and cycling through the original; I check if the passengers class is equal to the inputted one, if so I then add it to the new list
        for( Passenger passenger : passengerList)
        {
            if(ticketClass.equals(passenger.getPassengerClass()))
            {
                classPassengers.add(passenger);
            }
        }
        return classPassengers;
    }

    public static ArrayList <Passenger> sortPassengersByPassengerId(ArrayList <Passenger> passengerList) {
        //I clone the original list as to not disrupt it
        ArrayList <Passenger> SortedByPassengerId = (ArrayList<Passenger>) passengerList.clone();
        //Then using the sort function, I call the override compareTo method in the Passenger class to sort the id's
        Collections.sort(SortedByPassengerId);
        return SortedByPassengerId;
    }

    //The comparators i use using the Comparator utility are from visual studio code suggestions, they are similar to the lamda version of comparators
    // I also looked at some multiple field comparing on 
    public static ArrayList <Passenger> sortPassengersByName(ArrayList <Passenger> passengerList) {
        ArrayList <Passenger> SortedByPassengerName = (ArrayList<Passenger>) passengerList.clone();
        //I create a new comparator and name it appropriately, the "comparator.comparing" will then use whats in the brackets to sort the  custom objects, in this case it gets the names
        Comparator<Passenger> passengerNameComparator = Comparator.comparing(Passenger::getName);
        //This will sort the new created list using the comparator above
        Collections.sort(SortedByPassengerName,passengerNameComparator);
        return SortedByPassengerName;
    }

    public static ArrayList <Passenger> sortPassengersByAgeThenName(ArrayList <Passenger> passengerList) {
        ArrayList <Passenger> SortedByPassengerAgeThenName = (ArrayList<Passenger>) passengerList.clone();
        //A similar process to the above method but this time it uses .then comparing to allow for more requirements by then comparing the names
        Comparator<Passenger> passengerAgeThenNameComparator =
                Comparator.comparing(Passenger::getAge)
                .thenComparing(Passenger::getName);
        Collections.sort(SortedByPassengerAgeThenName,passengerAgeThenNameComparator);
        return SortedByPassengerAgeThenName;
    }

    public static ArrayList <Passenger> sortPassengersByGenderThenPassengerNumber(ArrayList <Passenger> passengerList) {
        ArrayList <Passenger> SortedByPassengerGenderThenNumber = (ArrayList<Passenger>) passengerList.clone();
        Comparator<Passenger> passengerGenderThenNumberComparator =
                Comparator.comparing(Passenger::getGender)
                .thenComparing(Passenger::getPassengerId);
        Collections.sort(SortedByPassengerGenderThenNumber,passengerGenderThenNumberComparator);
        return SortedByPassengerGenderThenNumber;
    }

    public static ArrayList <Passenger> sortPassengersByFareThenSurvival(ArrayList <Passenger> passengerList) {
        ArrayList <Passenger> SortedByFareThenSurvival = (ArrayList<Passenger>) passengerList.clone();
        Comparator<Passenger> FareThenSurvivalComparator =
                Comparator.comparing(Passenger::getFare)
                .thenComparing(Passenger::getSurvived);
        Collections.sort(SortedByFareThenSurvival,FareThenSurvivalComparator);
        return SortedByFareThenSurvival;
    }

    public static ArrayList <Passenger> sortPassengersByTicketClass(ArrayList <Passenger> passengerList) {
        ArrayList <Passenger> SortedByTicketClass = (ArrayList<Passenger>) passengerList.clone();
        Comparator<Passenger> TicketClassComparator =
                Comparator.comparing(Passenger::getPassengerClass);
        Collections.sort(SortedByTicketClass,TicketClassComparator);
        return SortedByTicketClass;
    }

    public static ArrayList<Passenger> sortPassengersByAge(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> sortedByAge = (ArrayList<Passenger>) passengerList.clone();
        //implements a new compare to for integers to compare the ages, then sorts them
        Collections.sort(sortedByAge, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger passenger1, Passenger otherPassenger) {
                return Integer.compare(passenger1.getAge(), otherPassenger.getAge());}
        });
        return sortedByAge;
    }

    public static ArrayList <Passenger> sortPassengersByTicketNumberLambda(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> sortedByTicketNumber = (ArrayList<Passenger>) passengerList.clone();
        //The lambda comparator (underlined it shows the comparator.comparing method I use) it compares the ticket numbers as strings
        sortedByTicketNumber.sort((Passenger passenger1, Passenger passenger2) -> passenger1.getTicketNumber().compareTo(passenger2.getTicketNumber()));

        return sortedByTicketNumber;
    }

    public static ArrayList <Passenger> sortPassengersByTicketNumberStatic(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> sortedByTicketNumberStatic = (ArrayList<Passenger>) passengerList.clone();
        //this uses the static method in the passenger class to sort the passengers in order of ticket number
        sortedByTicketNumberStatic.sort(Passenger ::compareByTicketNumber);

        return sortedByTicketNumberStatic;
    }

    public static void findPassengerByTicketNumber(ArrayList <Passenger> passengerList, Passenger passengerIn) {
        int passengerByTicketNumber; //empty int for the index of the passenger

        ArrayList<Passenger> sortedByTicketNumberStatic = (ArrayList<Passenger>) passengerList.clone();
        sortedByTicketNumberStatic.sort(Passenger ::compareByTicketNumber);

        //uses the binary search method to search for the inputted ticket number, it uses the static sorting method from a previous question
        passengerByTicketNumber = Collections.binarySearch(sortedByTicketNumberStatic, passengerIn, Passenger ::compareByTicketNumber);
        //if the index is -1 then it is not in the array
        if(passengerByTicketNumber <0)
        {

            System.out.println("Value Not Found in array");
        }
        else
        {
            System.out.println("Value Found at index " + passengerByTicketNumber);
        }

    }

    public static void findPassengerByPassengerId(ArrayList <Passenger> passengerList, Passenger passengerIn) {
        int passengerById;
        ArrayList<Passenger> sortedById = (ArrayList<Passenger>) passengerList.clone();
        //uses natural order comparing to sort the passengers
        Collections.sort(sortedById);
        //uses a binary search on the sorted array
        passengerById =Collections.binarySearch(sortedById, passengerIn );
        if(passengerById <0)
        {
            System.out.println("Value Not Found in array");
        }
        else
        {
           System.out.println("Value Found at index " + passengerById);
        }

    }
}