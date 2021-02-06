import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 * @author Daniel Earley
 * @version 2.0
 * @since 2021-02-05
 * The purpose of this class is to randomize the names and titles of the characters
 * This class opens a json file and grabs a random name and title before returning 
 * the concatenated final name
 */

public class RandomizeNames {

    /**
     * This function will get the filepath of any file in the resources folder
     * @param fileName
     * @return String filepath
     */
    public static String getFilePath(String fileName){
        // Unfortunately hardcoded path to resource folder
        String relativePath = "\\\\src\\\\main\\\\resources\\\\";
        String finalPath = "";
        
        try {
            // Obtain "canonical path"
            String canonPath = new File(".").getCanonicalPath();
            canonPath = canonPath.replace("\\", "\\\\");

            // Create filepath to JSON file in resources
            finalPath = canonPath + relativePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalPath;
    }

    /**
     * This function grabs a random name and title from the OrcNames.json file
     * @return String name
     */
    public static String getRandomName(){
        // Declare Variables
        JSONParser parser = new JSONParser();
        String concatName = "";

        try {
            // Create JSONObject
            Object obj = parser.parse(new FileReader(getFilePath("OrcNames.json")));
            JSONObject jsonObject = (JSONObject) obj;

            // Grab from JSONArray
            JSONArray orcNameArray = (JSONArray) jsonObject.get("names");
            JSONArray orcTitleArray = (JSONArray) jsonObject.get("titles");

            // Randomize name and title
            Random r = new Random();
            int nameIndex = r.nextInt(orcNameArray.size());
            int titleIndex = r.nextInt(orcTitleArray.size());

            // Create concatenated name
            concatName = (String)orcNameArray.get(nameIndex) + " " + orcTitleArray.get(titleIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return concatName;
    }

    // public static void main(String[] args) {
    //     System.out.println(getRandomName());
    // }
}
