import java.io.*;
import java.util.*;

public class updateQuery {

    public static void update(ArrayList<String> listOfqueryParts) throws IOException {
        int flag = 0,flag1=0;
        int count=0;
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> temp1 = new ArrayList<>();
        ArrayList<Integer> rowsNumber1 = new ArrayList<>();
        String tablename = listOfqueryParts.get(2);

        File f = new File("./Database/"+tablename + ".hrv");
        if(!f.exists()){
            System.out.println(tablename+" table not present in database.");
            System.exit(0);
        }

        String str = "";
        if((listOfqueryParts.size()<=3)){
            System.exit(0);
        }

        else{

            int x = 0;
            while(!listOfqueryParts.get(x).equals("where"))  /// listofqueryParts  = each part of query
                x+=1;

            for (int i = x+1; i < listOfqueryParts.size(); i++)
                str = str+" "+listOfqueryParts.get(i);
            String[] ele = str.split("and");
            List<String> fixedLenList = Arrays.asList(ele);
            ArrayList<String> conditions = new ArrayList<String>(fixedLenList);

            ArrayList<String> rows= new ArrayList<>();
            ArrayList<String> dataCond=new ArrayList<>();

            for (String itr : conditions) {
                itr = itr.trim();
                String[] elem = itr.split("=");

                List<String> list = new ArrayList<String>(Arrays.asList(elem));

                list.remove("=");
                elem = list.toArray(new String[0]);

                rows.add(elem[0]);
                dataCond.add(elem[1]);

            }

            //System.out.println(rows);
            //System.out.println(dataCond);

            String str1="";
            for (int i = 4; i < x; i++)
                str1 = str1+" "+listOfqueryParts.get(i);
            String[] ele1 = str1.split("and");
            fixedLenList = Arrays.asList(ele1);
            ArrayList<String> conditions1 = new ArrayList<String>(fixedLenList);

            ArrayList<String> rows1= new ArrayList<>();
            ArrayList<String> dataCond1=new ArrayList<>();
            String[] elem1 ;
            for (String itr : conditions1) {
                itr = itr.trim();
                elem1 = itr.split("=");

                List<String> list = new ArrayList<String>(Arrays.asList(elem1));

                list.remove("=");
                elem1 = list.toArray(new String[0]);

                rows1.add(elem1[0]);
                dataCond1.add(elem1[1]);

            }

            //System.out.println(rows1);
            //System.out.println(dataCond1);

            FileReader fr = new FileReader("./Database/"+tablename + ".hrv");
            BufferedReader br = new BufferedReader(fr);
            String retreivedData = "";
            ArrayList<String> userData = new ArrayList<String>();   //userData = rows from table
            String csv = "";
            String[] elements1;
            List<String> fixedLenghtList1;
            ArrayList<String> listOfString1;

            while((retreivedData=br.readLine())!= null){
                userData.add(retreivedData);
            }

            br.close();

            //rowsNeeded = new ArrayList<>();
            ArrayList<Object> rowsNumber = new ArrayList<>();

            for (String string : userData) {
                ArrayList<Integer> checker = new ArrayList<>();
                ArrayList<Integer> checker1 = new ArrayList<>();
                csv = string;
                elements1 = csv.split("#");
                fixedLenghtList1 = Arrays.asList(elements1);
                listOfString1 = new ArrayList<String>(fixedLenghtList1); // a single row's content in arraylist
                Map<Integer,String> map = new HashMap<>();
                for (int i = 0; i < listOfString1.size(); i++) {
                    map.put(i,listOfString1.get(i));
                }

                //System.out.println(map);

                if(rowsNumber.isEmpty()){
                    for (String itr : rows) {
                        int index = listOfString1.indexOf(itr);
                        rowsNumber.add(index);
                    }
                }

                //int toggle = 0;

                if(flag==0){
                    flag=1;
                    //System.out.println(string);
                    temp1.add(string);
                    //System.out.println(temp1);
                    continue;
                }

                for (int i = 0; i < rowsNumber.size(); i++) {


                    if((dataCond.get(i)).equals(map.get(rowsNumber.get(i)))){
                        checker.add(1);
                        //System.out.print(dataCond.get(i)+" ");
                    }
                    else
                        checker.add(0);
                }

                if(checker.contains(0)){
                    //System.out.println("No particular row is there meeting the conditon(s) specified");
                    //System.exit(0);;
                }
                else
                    temp.add(string);


                //System.out.println();

                if(rowsNumber1.isEmpty()){
                    for (String itr : rows1) {
                        int index = listOfString1.indexOf(itr);
                        rowsNumber1.add(index);
                    }
                }


                for (int i=0;i<rowsNumber1.size();i++){
                    if((dataCond.get(i)).equals(map.get(rowsNumber.get(i)))){
                        //System.out.println(listOfString1);
                        listOfString1.remove(i);
                        listOfString1.add(i, dataCond1.get(i).toString());
                        continue;
                    }
                }

                //System.out.println(listOfString1);
                String update = "";
                for(int i=0;i<listOfString1.size();i++)
                {
                    if(i==0)
                        update = update+listOfString1.get(i);
                    else
                        update = update+"#"+listOfString1.get(i);
                }

                temp1.add(update);

            }


            //System.out.println(temp1);



            FileWriter filew1 = new FileWriter("./Database/"+tablename+".hrv");
            BufferedWriter bufferedw1 = new BufferedWriter(filew1);
            //ArrayList<String> temp_dup_removed1 = new ArrayList<>();

            for (String itr : temp1) {
                bufferedw1.write(itr);
                bufferedw1.newLine();
            }
            bufferedw1.close();
            System.out.println("row(s) updated successfully");

        }
    }
}
