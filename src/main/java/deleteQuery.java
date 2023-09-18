import java.io.*;
import java.util.*;

public class deleteQuery {

    public static void delete(ArrayList<String> listOfqueryParts) throws IOException {

        int flag = 0;


        String str = "";
        if((listOfqueryParts.size()<=2)){
            System.out.println("Error in query");
            System.exit(0);
        }

        else{

            ArrayList<String> temp = new ArrayList<>();
            String tablename = listOfqueryParts.get(2);

            File f = new File("./Database/"+tablename + ".hrv");
            if(!f.exists()){
                System.out.println(tablename+" table not present in database.");
                System.exit(0);
            }

            // condition storing and checking when reading from cols to find the match
            for (int i = 4; i < listOfqueryParts.size(); i++)
                str = str+" "+listOfqueryParts.get(i);
            String[] ele = str.split("and");
            List<String> fixedLenList = Arrays.asList(ele);
            ArrayList<String> conditions = new ArrayList<String>(fixedLenList);

            ArrayList<String> cols= new ArrayList<>();
            ArrayList<String> dataCond=new ArrayList<>();

            for (String itr : conditions) {
                itr = itr.trim();
                String[] elem = itr.split("=");

                List<String> list = new ArrayList<String>(Arrays.asList(elem));

                list.remove("=");
                elem = list.toArray(new String[0]);

                cols.add(elem[0]);
                dataCond.add(elem[1]);

            }

            FileReader fr = new FileReader("./Database/"+tablename + ".hrv");
            BufferedReader br = new BufferedReader(fr);
            String retreivedData = "";
            ArrayList<String> userData = new ArrayList<String>();   //userData = cols from table
            String csv = "";
            String[] elements1;
            List<String> fixedLenghtList1;
            ArrayList<String> listOfString1;

            while((retreivedData=br.readLine())!= null){
                userData.add(retreivedData);
            }

            br.close();

            ArrayList<Object> colsNumber = new ArrayList<>();

            for (String string : userData) {
                ArrayList<Integer> checker = new ArrayList<>();
                csv = string;
                elements1 = csv.split("#");
                fixedLenghtList1 = Arrays.asList(elements1);
                listOfString1 = new ArrayList<String>(fixedLenghtList1); // a single row's content in arraylist
                Map<Integer,String> map = new HashMap<>();
                for (int i = 0; i < listOfString1.size(); i++) {
                    map.put(i,listOfString1.get(i));
                }

                //System.out.println(map);

                if(colsNumber.isEmpty()){
                    for (String itr : cols) {
                        int index = listOfString1.indexOf(itr);
                        colsNumber.add(index);
                    }
                }

                //int toggle = 0;

                for (int i = 0; i < colsNumber.size(); i++) {

                    if(flag==0){
                        flag=1;
                        temp.add(string);
                        continue;
                    }




                    if((dataCond.get(i)).equals(map.get(colsNumber.get(i)))){
                        checker.add(1);
                        //System.out.print(dataCond.get(i)+" ");
                    }
                    else
                        checker.add(0);
                }

                //if the condition matches for a row then that row is added
                if(checker.contains(0)){
                    temp.add(string);
                    //System.out.println("No particular row is there meeting the conditon(s) specified");
                }



                //System.out.println();


            }

            FileWriter filew = new FileWriter("./Database/"+tablename+".hrv");
            BufferedWriter bufferedw = new BufferedWriter(filew);
            ArrayList<String> temp_dup_removed = new ArrayList<>();

            for (String itr : temp) {
                if(!temp_dup_removed.contains(itr))
                    temp_dup_removed.add(itr);
            }


            for (String itr : temp_dup_removed) {
                bufferedw.write(itr);
                bufferedw.newLine();
            }
            System.out.println("row(s) deleted succesfully");
            bufferedw.close();

        }
    }
}
