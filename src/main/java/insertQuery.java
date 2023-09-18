import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class insertQuery {

    public static void insert(ArrayList<String> listOfqueryParts) throws IOException {
        //System.out.println("insert");
        String valuePart ;
        String vp="";

        String tablename = listOfqueryParts.get(2);

        File f = new File("./Database/"+tablename + ".hrv");

        if(!f.exists()){
            System.out.println(tablename+" table not present in database.");
            System.exit(0);
        }

        String[] ele;
        List<String> fixedLenList;
        ArrayList<String> conditions;
        if(listOfqueryParts.get(3).equals("values")){

            valuePart = listOfqueryParts.get(5);
            //System.out.println(valuePart);

            int s =  valuePart.length();


            for (int i = 1; i < valuePart.length()-1; i++) {
                vp = vp+valuePart.charAt(i);
            }

            System.out.println(vp);
            ele = vp.split(",");
            fixedLenList = Arrays.asList(ele);
            conditions = new ArrayList<String>(fixedLenList);
            //System.out.println(conditions);

            FileWriter fw = new FileWriter("./Database/"+tablename+".hrv",true);
            BufferedWriter bw = new BufferedWriter(fw);

            String input="";
            for (int i = 0; i < conditions.size(); i++) {
                if(i==0)
                    input=input+conditions.get(i);
                else
                    input=input+"#"+conditions.get(i);
            }
            //System.out.print(input);

            bw.write(input);
            bw.newLine();
            bw.close();
            System.out.println("Row inserted successfully");
        }
        else
            System.out.println("Wrong syntax for insert query");
    }
}
