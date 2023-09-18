import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class createQuery {

    /**
     *
     * @param listOfqueryParts
     * @throws IOException
     */
    public static void create(ArrayList<String> listOfqueryParts) throws IOException {
        {
            String tablename = listOfqueryParts.get(2);
            File f = new File("./Database/"+tablename + ".hrv");


            if (f.exists()) {
                System.out.println("Table already exists");
                System.exit(0);
            }
            FileWriter fw1 = new FileWriter("./Database/"+listOfqueryParts.get(2) + ".hrv", true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            String col = "";
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> onlycol = new ArrayList<>();


            //System.out.println(listOfqueryParts);

            for (int i=4;i<listOfqueryParts.size()-1;i++){
                columns.add(listOfqueryParts.get(i));
            }
            //System.out.println(columns);
            String key = columns.toString();
            int pkey = 0;
            int fkey = 0;

            if (key.contains("primary")) {

                for (String str : listOfqueryParts) {
                    if (str.equals("primary")) {
                        pkey = listOfqueryParts.indexOf(str);

                    }
                }

                String pColumnName = listOfqueryParts.get(pkey + 2);
                pColumnName = pColumnName.substring(1, pColumnName.length() - 1);



            }

            if (key.contains("foreign")) {

                for (String str : listOfqueryParts) {
                    if (str.equals("foreign")) {
                        fkey = listOfqueryParts.indexOf(str);

                    }
                }

                String fColumnName = listOfqueryParts.get(fkey + 2);
                fColumnName = fColumnName.substring(1, fColumnName.length() - 1);

            }


            for (int i = 4; i < pkey; i++) {
                if (!listOfqueryParts.get(i).trim().contains(",")) {
                    onlycol.add(listOfqueryParts.get(i));
                }
            }


            //System.out.println(onlycol);

            if(pkey!=0 || fkey!= 0) {
                for (int i = 0; i < onlycol.size(); i++) {
                    if (i == 0)
                        col = col + onlycol.get(i);
                    else
                        col = col + "#" + onlycol.get(i);

                }
            }

            else{
                for(int i=0;i<columns.size();i++)
                {
                    if(i%2==0){
                        if(i==0)
                            col = col+columns.get(i);
                        else
                            col = col+"#"+columns.get(i);
                    }
                }
            }


            bw1.write(col);
            bw1.newLine();
            bw1.close();

            System.out.println("Table created successfully");

        }
    }
}
