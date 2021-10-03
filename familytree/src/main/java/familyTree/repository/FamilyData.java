package familyTree.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import familyTree.model.FamilyMember;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class FamilyData {
    public List<FamilyMember> familyMembers;

    private String filename ="FamilyMemberList.txt";

    private List<FamilyMember> getDataFromFile()  {
        try{
            String fileData = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray arr = new JSONArray(fileData);
            for(int i = 0; i < arr.length(); i++){
                try {
                    FamilyMember fm = new FamilyMember();
                    fm.setId(Integer.parseInt(arr.getJSONObject(i).getString("id")));
                    fm.setName(arr.getJSONObject(i).getString("name"));
                    fm.setFamilyId(arr.getJSONObject(i).getString("familyId"));
                    fm.setFamilyParentId(arr.getJSONObject(i).getString("familyParentId"));
                    fm.setGender(arr.getJSONObject(i).getString("gender"));
                    familyMembers.add(fm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return familyMembers;
    }

    public void saveDataToFile(List<FamilyMember> familyMembersList) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonString = gson.toJson(familyMembersList);
        FileWriter f = new FileWriter(filename);
        f.write(jsonString);
        f.close();
    }

    public FamilyData()  {
        familyMembers = getDataFromFile();
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public FamilyMember getFamilyMember(int id) {
        return  familyMembers.stream()
                .filter(x -> x.getId() == id )
                .findFirst()
                .orElseThrow(null);
    }
}
