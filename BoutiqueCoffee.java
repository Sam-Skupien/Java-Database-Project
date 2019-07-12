import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class BoutiqueCoffee {

        public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {
                // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
                // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
                //Class.forName("org.postgresql.Driver");

                System.out.println("Connected to Boutiqe Coffee Database.\n");
                //Statement statement = connection.createStatement();
               //// ResultSet resultSet = statement.executeQuery("SELECT Name FROM coffee");
               // while (resultSet.next()) {
                 //   System.out.printf("%s%n", resultSet.getString("name"));
                //}

                BoutiqueCoffee db = new BoutiqueCoffee();

            } catch (SQLException e) {
                System.out.println("Failure to Connect.\n");
                e.printStackTrace();
            }
        }


        public BoutiqueCoffee(){
            System.out.println("Boutiqe Coffee Database Running...\n");
            runOps();
        }

        private void runOps() {

            Scanner scan = new Scanner(System.in);
            int userOp = 0;

            while(true) {
                System.out.println("Choose an operation:\n" +
                        "1: Add Store\n" +
                        "2: Add Coffee\n" +
                        "3: Store 'x' offers coffee 'y'\n" +
                        "4: Add store promotion \n" +
                        "5: Promote for coffee \n" +
                        "6: Store 'x' has promotion 'y' \n" +
                        "7: Add member level\n" +
                        "8: Add customer\n" +
                        "9: Add purchase\n" +
                        "10: Get all coffees\n" +
                        "11: Get coffee's by keywords\n" +
                        "12: Get points by customer ID\n" +
                        "13: Get top stores with the highest revenue in last x months\n" +
                        "14: Get top K customers\n" +
                        "15: Exit\n");

                userOp = scan.nextInt();

                switch (userOp) {

                    case 1: // Add Store

                        String StoreName;
                        String address;
                        String storeType;
                        double gpsLat;
                        double gpsLong;
                        int addStoreRes;

                        System.out.println("Enter Store Name: \n");
                        StoreName = scan.nextLine();
                        System.out.println("Enter Store Address: \n");
                        address = scan.nextLine();
                        System.out.println("Enter Store Type: \n");
                        storeType = scan.nextLine();
                        System.out.println("Enter Store GPS Latitude: \n");
                        gpsLat = scan.nextDouble();
                        System.out.println("Enter Store GPS Longitude: \n");
                        gpsLong = scan.nextDouble();

                        addStoreRes = addStore (StoreName, address, storeType, gpsLong, gpsLat);

                        System.out.println("Addstore operation result: \n" + addStoreRes);
                        break;

                    case 2: // Add Coffee

                        String coffeeName;
                        String description;
                        int intensity;
                        double price;
                        double rewardPoints;
                        double redeemPoints;
                        int addCoffeeRes;

                        System.out.println("Enter Coffee Name: \n");
                        coffeeName = scan.nextLine();
                        System.out.println("Enter Coffee Description: \n");
                        description = scan.nextLine();
                        System.out.println("Enter Intensity: \n");
                        intensity = scan.nextInt();
                        System.out.println("Enter Price: \n");
                        price = scan.nextDouble();
                        System.out.println("Enter Reward Points: \n");
                        redeemPoints = scan.nextDouble();
                        System.out.println("Enter redeemPoints: \n");
                        rewardPoints = scan.nextDouble();


                        addCoffeeRes = addCoffee (coffeeName, description, intensity, price, rewardPoints, redeemPoints);

                        System.out.println("AddCoffee operation result: \n" + addCoffeeRes);
                        break;

                    case 3: // Offer Coffee

                        int offerCoffeeStoreId;
                        int offerCoffeeCoffeeId;
                        int offerCoffeeRes;

                        System.out.println("Enter Store ID: \n");
                        offerCoffeeStoreId = scan.nextInt();
                        System.out.println("Enter Coffee ID: \n");
                        offerCoffeeCoffeeId = scan.nextInt();

                        offerCoffeeRes = offerCoffee(offerCoffeeStoreId, offerCoffeeCoffeeId);

                        System.out.println("offerCoffee operation result: \n" + offerCoffeeRes);
                        break;

                    case 4: // Add Promotion

                        String addPromotionName;
                        String strStartDate;
                        String strEndDate;
                        Date startDate;
                        Date endDate;
                        int addPromotionResult;

                        System.out.println("Enter Promotion Name: \n");
                        addPromotionName = scan.nextLine();
                        System.out.println("Enter Start Date: \n");
                        strStartDate = scan.nextLine();
                        startDate = Date.valueOf(strStartDate);
                        System.out.println("Enter End Date: \n");
                        strEndDate = scan.nextLine();
                        endDate = Date.valueOf(strEndDate);

                        addPromotionResult = addPromotion (addPromotionName, startDate,  endDate);

                        System.out.println("addPromotion operation result: \n" + addPromotionResult);
                        break;

                    case 5: // Promote For

                        int promoteForPromotionId;
                        int promoteForCoffeeId;
                        int promoteForResult;

                        System.out.println("Enter Promotion ID: \n");
                        promoteForPromotionId = scan.nextInt();
                        System.out.println("Enter Coffee ID: \n");
                        promoteForCoffeeId = scan.nextInt();

                        promoteForResult = promoteFor (promoteForPromotionId, promoteForCoffeeId);

                        System.out.println("promoteFor operation result: \n" + promoteForResult);
                        break;

                    case 6: // Has Promotion

                        int hasPromotionStoreId;
                        int hasPromotionPromotionId;
                        int hasPromotionResult;

                        System.out.println("Enter Promotion ID: \n");
                        hasPromotionStoreId = scan.nextInt();
                        System.out.println("Enter Coffee ID: \n");
                        hasPromotionPromotionId = scan.nextInt();

                        hasPromotionResult = hasPromotion (hasPromotionStoreId, hasPromotionPromotionId);

                        System.out.println("hasPromotion operation result: \n" + hasPromotionResult);
                        break;

                    case 7: // Add Member Level

                        String memberLevelName;
                        double memberBoosterFactor;
                        int addMemberLevelResult;

                        System.out.println("Enter Name: \n");
                        memberLevelName = scan.nextLine();
                        System.out.println("Enter Booster Factor: \n");
                        memberBoosterFactor = scan.nextDouble();

                        addMemberLevelResult = addMemberLevel (memberLevelName, memberBoosterFactor);

                        System.out.println("addMemberLevel operation result: \n" + addMemberLevelResult);
                        break;

                    case 8: // Add customer

                        String addCustomerFirstName;
                        String addCustomerLastName;
                        String addCustomerEmail;
                        int addCustomerMemberLevelId;
                        double addCustomerTotalPoints;
                        int addCustomerResult;

                        System.out.println("Enter First Name: \n");
                        addCustomerFirstName = scan.nextLine();
                        System.out.println("Enter Last Name: \n");
                        addCustomerLastName = scan.nextLine();
                        System.out.println("Enter email: \n");
                        addCustomerEmail = scan.nextLine();
                        System.out.println("Enter Member Level ID: \n");
                        addCustomerMemberLevelId = scan.nextInt();
                        System.out.println("Enter Total Points: \n");
                        addCustomerTotalPoints = scan.nextDouble();

                        addCustomerResult = addCustomer (addCustomerFirstName, addCustomerLastName, addCustomerEmail, addCustomerMemberLevelId, addCustomerTotalPoints);

                        System.out.println("addCustomer operation result: \n" + addCustomerResult);
                        break;

                    case 9: // Add Purchase

                        int numPurchases;
                        int numRedeems;
                        int addPurchaseCustomerId;
                        int addPurchaseStoreId;
                        String strAddPurchasePurchaseTime;
                        Date addPurchasePurchaseTime;
                        List <Integer> coffeeIds;
                        List <Integer> purchaseQuantities;
                        List <Integer> redeemQuantities;
                        int addPurchaseResult;

                        System.out.println("Enter the amount of coffee's purchased: \n");
                        numPurchases = scan.nextInt();
                        System.out.println("Enter the Redeems: \n");
                        numRedeems = scan.nextInt();

                        System.out.println("Enter Customer ID: \n");
                        addPurchaseCustomerId = scan.nextInt();
                        System.out.println("Enter Store ID: ");
                        addPurchaseStoreId = scan.nextInt();
                        System.out.println("Enter Purchase time: \n");
                        strAddPurchasePurchaseTime = scan.nextLine();
                        addPurchasePurchaseTime = Date.valueOf(strAddPurchasePurchaseTime);

                        System.out.println("Enter the " + numPurchases + " Coffee IDs: \n");
                        for(int i = 0; i < numPurchases; i++){

                            int temp = scan.nextInt();
                            coffeeIds.add(temp);
                        }

                        System.out.println("Enter the Amount of Each Coffee Being Purchased: \n");
                        for(int i = 0; i < numPurchases; i++){

                            System.out.println("Amount for " + coffeeIds.get(i) + " :\n");
                            int temp = scan.nextInt();
                            purchaseQuantities.add(temp);
                        }

                        System.out.println("Enter the " + numRedeems + " Redeem Points for each coffee: ");
                        for(int i = 0; i < numRedeems; i++){

                            System.out.println("Amount for " + coffeeIds.get(i) + " :\n");
                            int temp = scan.nextInt();
                            redeemQuantities.add(temp);
                        }


                        addPurchaseResult = addPurchase(addPurchaseCustomerId, addPurchaseStoreId, addPurchasePurchaseTime, coffeeIds, purchaseQuantities, redeemQuantities);
                        break;

                    case 10:

                    case 11:

                    case 12:

                    case 13:

                    case 14:

                    case 15:
                        System.exit(0);


                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + userOp);
                }
            }
        }


        // case 1
        public int addStore (String name, String address, String storeType, double gpsLong, double gpsLat){
            return 0;
        }

        // case 2
        public int addCoffee (String name, String description,int intensity, double price, double rewardPoints, double redeemPoints){
            return 0;
        }

        // case 3
        public int offerCoffee ( int storeId, int coffeeId){
            return 0;
        }

        //case 4
        public int addPromotion (String name, Date startDate, Date endDate){
            return 0;
        }

        // case 5
        public int promoteFor ( int promotionId, int coffeeId){
            return 0;;
        }

        // case 6
        public int hasPromotion ( int storeId, int promotionId){
            return 0;
        }

        // case 7
        public int addMemberLevel (String name,double boosterFactor){
            return 0;
        }

        // case 8
        public int addCustomer (String firstName, String lastName, String email,int memberLevelId, double totalPoints){
            return 0;
        }

        // case 9
        public int addPurchase (int customerId, int storeId, Date purchaseTime, List <Integer> coffeeIds, List <Integer> purchaseQuantities, List <Integer> redeemQuantities)
        {
            return 0;
        }

        // case 10
        public List<Integer> getCoffees() {
            ;
        }

        // case 11
        public List<Integer> getCoffeesByKeywords (String keyword1, String keyword2){
            ;
        }


        // case 12
        public double getPointsByCustomerId ( int customerId){
            ;
        }


        // case 13
        public List<Integer> getTopKStoresInPastXMonth ( int k, int x){
            ;
        }


        // case 14
        public List<Integer> getTopKCustomersInPastXMonth ( int k, int x){
            ;
        }
}
