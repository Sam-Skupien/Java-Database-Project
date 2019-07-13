import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

public class BoutiqueCoffee {

    private static Connection connection;
    private Statement statement;


        public static void main(String[] args) throws SQLException {


            try  {

                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
                System.out.println("Connected to Boutiqe Coffee Database.\n");
                BoutiqueCoffee db = new BoutiqueCoffee();

            } catch (SQLException e) {
                System.out.println("Failure to Connect.\n");
                e.printStackTrace();
            }

            BoutiqueCoffee db = new BoutiqueCoffee();
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

                        System.out.println("Enter Store Name: [string]\n");
                        scan.skip("\n");
                        StoreName = scan.nextLine();
                        System.out.println("Enter Store Address: [string]\n");
                        address = scan.nextLine();
                        System.out.println("Enter Store Type: [string]\n");
                        storeType = scan.nextLine();
                        System.out.println("Enter Store GPS Latitude: [double]\n");
                        gpsLat = scan.nextDouble();
                        System.out.println("Enter Store GPS Longitude: [double]\n");
                        gpsLong = scan.nextDouble();

                        addStoreRes = addStore (StoreName, address, storeType, gpsLong, gpsLat);

                        System.out.println("Addstore operation result: " + addStoreRes + "\n\n");
                        break;

                    case 2: // Add Coffee

                        String coffeeName;
                        String description;
                        int intensity;
                        double price;
                        double rewardPoints;
                        double redeemPoints;
                        int addCoffeeRes;

                        System.out.println("Enter Coffee Name: [string]\n");
                        scan.skip("\n");
                        coffeeName = scan.nextLine();
                        System.out.println("Enter Coffee Description: [string]\n");
                        description = scan.nextLine();
                        System.out.println("Enter Intensity: [int and >= 0]\n");
                        intensity = scan.nextInt();
                        System.out.println("Enter Price: [double and >= 1.50]\n");
                        price = scan.nextDouble();
                        System.out.println("Enter Reward Points: [double <= Redeem_Points * 0.10 ]\n");
                        rewardPoints = scan.nextDouble();
                        System.out.println("Enter redeemPoints: [double]\n");
                        redeemPoints = scan.nextDouble();




                        addCoffeeRes = addCoffee (coffeeName, description, intensity, price, rewardPoints, redeemPoints);

                        System.out.println("AddCoffee operation result: " + addCoffeeRes + "\n\n");
                        break;

                    case 3: // Offer Coffee

                        int offerCoffeeStoreId;
                        int offerCoffeeCoffeeId;
                        int offerCoffeeRes;

                        System.out.println("Enter Store ID: \n");
                        scan.skip("\n");
                        offerCoffeeStoreId = scan.nextInt();
                        System.out.println("Enter Coffee ID: \n");
                        offerCoffeeCoffeeId = scan.nextInt();

                        offerCoffeeRes = offerCoffee(offerCoffeeStoreId, offerCoffeeCoffeeId);

                        System.out.println("offerCoffee operation result: " + offerCoffeeRes + "\n\n");
                        break;

                    case 4: // Add Promotion

                        String addPromotionName;
                        String strStartDate;
                        String strEndDate;
                        Date startDate;
                        Date endDate;
                        int addPromotionResult;

                        System.out.println("Enter Promotion Name: \n");
                        scan.skip("\n");
                        addPromotionName = scan.nextLine();
                        System.out.println("Enter Start Date: \n");
                        strStartDate = scan.nextLine();
                        startDate = Date.valueOf(strStartDate);
                        System.out.println("Enter End Date: \n");
                        strEndDate = scan.nextLine();
                        endDate = Date.valueOf(strEndDate);

                        addPromotionResult = addPromotion(addPromotionName, startDate,  endDate);

                        System.out.println("addPromotion operation result: " + addPromotionResult + "\n\n");
                        break;

                    case 5: // Promote For

                        int promoteForPromotionId;
                        int promoteForCoffeeId;
                        int promoteForResult;

                        System.out.println("Enter Promotion ID: \n");
                        scan.skip("\n");
                        promoteForPromotionId = scan.nextInt();
                        System.out.println("Enter Coffee ID: \n");
                        promoteForCoffeeId = scan.nextInt();

                        promoteForResult = promoteFor (promoteForPromotionId, promoteForCoffeeId);

                        System.out.println("promoteFor operation result: " + promoteForResult + "\n\n");
                        break;

                    case 6: // Has Promotion

                        int hasPromotionStoreId;
                        int hasPromotionPromotionId;
                        int hasPromotionResult;

                        System.out.println("Enter Store ID: \n");
                        scan.skip("\n");
                        hasPromotionStoreId = scan.nextInt();
                        System.out.println("Enter Promotion ID: \n");
                        hasPromotionPromotionId = scan.nextInt();

                        hasPromotionResult = hasPromotion (hasPromotionStoreId, hasPromotionPromotionId);

                        System.out.println("hasPromotion operation result: " + hasPromotionResult + "\n\n");
                        break;

                    case 7: // Add Member Level

                        String memberLevelName;
                        double memberBoosterFactor;
                        int addMemberLevelResult;

                        System.out.println("Enter Name: \n");
                        memberLevelName = scan.nextLine();
                        System.out.println("Enter Booster Factor: \n");
                        memberBoosterFactor = scan.nextDouble();

                        //addMemberLevelResult = addMemberLevel (memberLevelName, memberBoosterFactor);

                        //System.out.println("addMemberLevel operation result: " + addMemberLevelResult + "\n\n");
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

                        //addCustomerResult = addCustomer (addCustomerFirstName, addCustomerLastName, addCustomerEmail, addCustomerMemberLevelId, addCustomerTotalPoints);

                        //System.out.println("addCustomer operation result: " + addCustomerResult + "\n\n");
                        break;

                    case 9: // Add Purchase

                        int numPurchases;
                        int numRedeems;
                        int addPurchaseCustomerId;
                        int addPurchaseStoreId;
                        String strAddPurchasePurchaseTime;
                        Date addPurchasePurchaseTime;
                        List <Integer> coffeeIds = new ArrayList<>();
                        List <Integer> purchaseQuantities = new ArrayList<>();
                        List <Integer> redeemQuantities = new ArrayList<>();
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


                        //addPurchaseResult = addPurchase(addPurchaseCustomerId, addPurchaseStoreId, addPurchasePurchaseTime, coffeeIds, purchaseQuantities, redeemQuantities);
                        //System.out.println("addPurchase operation result: " + addPurchaseResult + "\n\n");

                        break;

                    case 10: // get all coffee's

                        //List <Integer> allCoffees = getCoffees();

                        /*
                        for(int i = 0; i < allCoffees.size(); i++){
                            System.out.println("Coffee ID's: " + allCoffees.get(i) + "\n");
                        }*/
                        break;

                    case 11: // get coffee by keywords

                        String coffeeKeyword1;
                        String coffeeKeyword2;

                        System.out.println("Enter the first keyword: \n");
                        coffeeKeyword1 = scan.nextLine();
                        System.out.println("Enter the Second keyword: \n");
                        coffeeKeyword2 = scan.nextLine();

                        //List <Integer> coffeeKeywords = getCoffeesByKeywords (coffeeKeyword1, coffeeKeyword2);
                        /*
                        for(int i = 0; i < coffeeKeywords.size(); i++){
                            System.out.println("Coffee's are: " + coffeeKeywords.get(i) + "\n");
                        }
                        break;*/



                    case 12: // get points by customer ID

                        int getPointsCustomerId;
                        double returnedPoints;

                        System.out.println("Enter Customer ID: \n");
                        getPointsCustomerId = scan.nextInt();

                        //returnedPoints = getPointsByCustomerId (getPointsCustomerId);

                        //System.out.println("Customer ID: " + getPointsCustomerId + " total points are: " + returnedPoints + "\n");
                        break;

                    case 13: //

                    case 14: //

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

            int result = -1;
            ResultSet res;

            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into store (name, address, store_type, gps_long, gps_lat)" +
                "values ('" + name + "','" + address + "', '" + storeType + "','" + gpsLong + "' ,'" + gpsLat + "');";

                result = s.executeUpdate(sqlString);

                if(result != -1) {
                    // assumption: name and address paired together are unique enough to identify a store
                    String sqlString2 = "select store_id from store where name = '" + name + "' and address = '" + address + "';";
                    res = s.executeQuery(sqlString2);

                    while(res.next()) {
                        result = res.getInt("store_id");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

            return result;

        }


        // case 2
        public int addCoffee (String name, String description,int intensity, double price, double rewardPoints, double redeemPoints){

            int result = -1;
            ResultSet res;

            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into coffee (name, description, intensity, price, reward_points, redeem_points)" +
                        "values ('" + name + "','" + description + "', '" + intensity + "','" + price + "' ,'" + rewardPoints + "','" + redeemPoints + "');";

                result = s.executeUpdate(sqlString);

                if(result != -1) {
                    // assumption: name is unique enough to identify a coffee
                    String sqlString2 = "select coffee_id from coffee where name = '" + name + "';";
                    res = s.executeQuery(sqlString2);

                    while(res.next()) {
                        result = res.getInt("coffee_id");
                    }
                }



            } catch (SQLException e) {
                e.printStackTrace();

            }
            return result;
        }



        // case 3
        public int offerCoffee (int storeId, int coffeeId){

            int res = -1;


            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into offerCoffee (store_id, coffee_id)" +
                        "values ('" + storeId + "','" + coffeeId + "');";

                res = s.executeUpdate(sqlString);

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return res;

        }

        //case 4
        public int addPromotion (String name, Date startDate, Date endDate){

            int result = -1;
            ResultSet res;

            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into promotion (name, start_date, end_date)" +
                        "values ('" + name + "','" + startDate + "', '" + endDate + "');";

                result = s.executeUpdate(sqlString);

                if(result != -1) {
                    // assumption: name, start date and end date is unique enough to identify promotion
                    String sqlString2 = "select promotion_id from promotion where name = '" + name + "' and "
                                        + "start_date = '" + startDate + "'"
                                        + "and end_date = '" + endDate + "';";
                    res = s.executeQuery(sqlString2);

                    while(res.next()) {
                        result = res.getInt("promotion_id");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return result;
        }


        // case 5
        public int promoteFor (int promotionId, int coffeeId){

            int res = -1;

            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into promoteFor (promotion_id, coffee_id)" +
                        "values ('" + promotionId + "','" + coffeeId + "');";

                res = s.executeUpdate(sqlString);

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return res;
        }


        // case 6
        public int hasPromotion (int storeId, int promotionId){

            int res = -1;

            try{

                Statement s = connection.createStatement();

                String sqlString = "insert into hasPromotion (store_id, promotion_id)" +
                        "values ('" + storeId + "','" + promotionId + "');";

                res = s.executeUpdate(sqlString);

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return res;
        }

        /*
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
        public double getPointsByCustomerId (int customerId){
            ;
        }


        // case 13
        public List<Integer> getTopKStoresInPastXMonth (int k, int x){
            ;
        }


        // case 14
        public List<Integer> getTopKCustomersInPastXMonth (int k, int x){
            ;
        }*/
}
