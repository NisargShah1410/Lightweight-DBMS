import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String []args) throws IOException
    {
        FileWriter fw1 = new FileWriter("accounts.hrv",true);
        //FileWriter fw2 = new FileWriter("logs.hrv",true);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        //BufferedWriter bw2 = new BufferedWriter(fw2);

//        buffWriter.write("accounts.hrv",true);
//        buffWriter.write("logs.hrv",true);

        System.out.println("Select one of the following options:");
        System.out.println("1. Login \n2. Sign up");
        System.out.println("Enter your choice:");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        String uname="",passw="",securityQue="",securityAns="",hashGenerated="",ans="";
        switch(choice){
            case 1:
                System.out.print("Username: ");
                uname = sc.nextLine();
                System.out.print("Password: ");
                passw = sc.nextLine();
                hashGenerated = Authentication.md5(passw);


                FileReader fr = new FileReader("accounts.hrv");
                BufferedReader br = new BufferedReader(fr);
                String retreivedData="";
                ArrayList<String> userData = new ArrayList<String>();
                String csv="";
                String[] elements = csv.split("#");
                List<String> fixedLenghtList = Arrays.asList(elements);
                ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);

                while((retreivedData=br.readLine())!= null){
                    userData.add(retreivedData);
                }
                br.close();

                for (String string : userData) {
                    csv = string;
                    elements = csv.split("#");
                    fixedLenghtList = Arrays.asList(elements);
                    listOfString = new ArrayList<String>(fixedLenghtList);
                    if(listOfString.get(0).equals(uname))
                        retreivedData = string;
                }

                if(retreivedData == null){
                    System.out.println("No user found");
                    break;
                }

                csv = retreivedData;
                elements = csv.split("#");
                fixedLenghtList = Arrays.asList(elements);
                listOfString = new ArrayList<String>(fixedLenghtList);

                if(hashGenerated.equals(listOfString.get(1)))
                {
                    System.out.println("First authentication step completed . Now next step starts: ");
                    System.out.println("Security question: "+listOfString.get(2));
                    System.out.print("Enter security answer: ");
                    ans = sc.nextLine();

                    if(ans.equals(listOfString.get(3)))
                        System.out.println("Authentication Successful. User logged in.");
                    else{
                        System.out.println("Authentication failed.");
                        break;
                    }
                }
                else{
                    System.out.println("Authentication failed.");
                    break;
                }

                File directory = new File("./Database");

                if(!directory.exists()) {
                    System.out.println("Database doesn't exist . Creating a new database .....");
                    //System.out.println("Database doesn't exist . Creating a database");
                    directory.mkdirs();
                    System.out.println("Database created successfully!");
                }
                else
                    System.out.println("Database already exists!");


                System.out.print("Write query you want to perform: ");
                String query = sc.nextLine();

                FileWriter fw2 = new FileWriter("logs.hrv",true);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                String time = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss").format(new java.util.Date());
                bw2.write(uname+" # " +query+" # "+time);
                bw2.newLine();
                bw2.close();

                if(query.equals("exit"))
                    break;


                //Query execution is done through this call
                QueryPerform.runQuery(query,uname);


                break;
            case 2:
                System.out.print("Username: ");
                uname = sc.nextLine();
                System.out.print("Password: ");
                passw = sc.nextLine();
                hashGenerated = Authentication.md5(passw);
                System.out.print("Enter security question: ");
                securityQue = sc.nextLine();
                System.out.print("Enter security answer: ");
                securityAns = sc.nextLine();
                System.out.println("You are now registered");
                bw1.write(uname+"#"+hashGenerated+"#"+securityQue+"#"+securityAns);
                bw1.newLine();
                bw1.close();
                break;
            default:
                System.out.print("Please only select from option 1 or 2 ");
        }

        sc.close();
    }
}
