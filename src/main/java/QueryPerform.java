import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryPerform {

    /**
     *
     * @param query
     * @param uname
     * @throws IOException
     */
    public static void runQuery(String query,String uname)throws IOException{

        String[] query_parts = query.split(" ");
        List<String> fLenghtList = Arrays.asList(query_parts);
        ArrayList<String> listOfqueryParts = new ArrayList<String>(fLenghtList);

        switch(listOfqueryParts.get(0).toLowerCase()){

            case "create":

                createQuery.create(listOfqueryParts);
                break;

            case "select":


                selectQuery.select(listOfqueryParts);
                break;

            case "delete":

                deleteQuery.delete(listOfqueryParts);
                break;


            case "update":

                updateQuery.update(listOfqueryParts);
                break;

            case "insert":

                insertQuery.insert(listOfqueryParts);
                break;

        }
    }
}
