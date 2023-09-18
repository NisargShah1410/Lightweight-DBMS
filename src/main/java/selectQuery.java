import java.io.*;
import java.util.*;

public class selectQuery {

    /**
     *
     * @param listOfqueryParts
     * @throws IOException
     */
    public static void select(ArrayList<String> listOfqueryParts) throws IOException {
        int x=0;
        while(!listOfqueryParts.get(x).equals("from"))  /// listofqueryParts  = each part of query
            x+=1;

        String tablename = listOfqueryParts.get(x+1);

        //checking if the required table is present in the database
        File f = new File("./Database/"+tablename+".hrv");
        if(!f.exists())
        {
            System.out.println(tablename+" table not present in database.");
            System.exit(0);
        }


        //getting the conditions from where clause if present
        String str="";
        List<String> fixedLenList;
        String[] ele = str.split("and");
        fixedLenList = Arrays.asList(ele);
        ArrayList<String> conditions = new ArrayList<String>(fixedLenList);

        String[] elem ;

        //Reading data from the file ( table )
        FileReader fr = new FileReader("./Database/"+tablename+".hrv");
        BufferedReader br = new BufferedReader(fr);
        String retreivedData="";
        ArrayList<String> userData = new ArrayList<String>();   //userData = rows from table
        String csv="";
        String[] elements;
        List<String> fixedLenghtList;
        ArrayList<String> listOfString;

        while((retreivedData=br.readLine())!= null){
            userData.add(retreivedData);
        }

        br.close();



        ArrayList<String> rowsNeeded = new ArrayList<>();
        ArrayList<Integer> rowsNumber = new ArrayList<>();


        for (String string : userData) {
            csv = string;
            elements = csv.split("#");
            fixedLenghtList = Arrays.asList(elements);
            listOfString = new ArrayList<String>(fixedLenghtList); // a single row's content in arraylist

            String sep = listOfqueryParts.get(1);

            if(listOfqueryParts.get(1).equals("*")){
                for (String itr : listOfString) {
                    System.out.print(itr+" ");
                }
            }

            else{


                ele = sep.split(",");
                fixedLenList = Arrays.asList(ele);
                rowsNeeded = new ArrayList<String>(fixedLenList);

                if(rowsNumber.isEmpty()){
                    for (String itr : rowsNeeded) {
                        int index = listOfString.indexOf(itr);
                        rowsNumber.add(index);
                    }
                }

                // printing the rows with required columns
                for (int itr : rowsNumber) {
                    System.out.print(listOfString.get(itr)+" ");
                }
            }


            System.out.println();

        }


    }
}
