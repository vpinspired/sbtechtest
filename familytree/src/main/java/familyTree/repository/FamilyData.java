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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class FamilyData {
    private List<FamilyMember> familyMembers = new ArrayList<>();

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
        FileWriter f = new FileWriter(this.filename);
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

    public List<FamilyMember> getChildren(int id){
        FamilyMember parent = getFamilyMember(id);
        return  familyMembers.stream()
                .filter((x) -> x.getFamilyParentId()
                        .equals(parent.getFamilyId()))
                .collect(Collectors.toList());
    }

    public List<FamilyMember> getDescendants(FamilyMember familyMember){
        List<FamilyMember> children = getChildren(familyMember.getId());
        List<FamilyMember> descendantList = children.isEmpty() ? Collections.emptyList() :
                Stream.concat(children.stream(), children.stream()
                                .map(this :: getDescendants)
                                .flatMap(Collection::stream))
                        .collect(Collectors.toList());

        return descendantList;
    }

    public List<FamilyMember> getParents(int id){
        FamilyMember child = getFamilyMember(id);
        return  familyMembers.stream()
                .filter((x) -> x.getFamilyId()
                        .equals(child.getFamilyParentId()))
                .collect(Collectors.toList());
    }

    public List<FamilyMember> getAncestors(FamilyMember familyMember){
        List<FamilyMember> parentList = this.getParents(familyMember.getId());
        List<FamilyMember> allAncestors = new ArrayList<>();

        ancestorList(parentList, allAncestors, "M");
        ancestorList(parentList, allAncestors, "F");

        return allAncestors;
    }

    private void ancestorList(List<FamilyMember> parentList, List<FamilyMember> allAncestors, String gender) {
        FamilyMember parent =  parentList.stream().filter((x) -> x.getGender().equals(gender)).findAny().orElse(null);
        List<FamilyMember> allMaternalAncestors = (Optional.ofNullable(parent).map(this::getAncestorsOfParent).orElse(Collections.emptyList()));
        for (FamilyMember ancestor : allMaternalAncestors ) {
            allAncestors.add(ancestor);
        }
    }

    private List<FamilyMember> getAncestorsOfParent(FamilyMember parent) {
        return Stream.concat(Stream.of(parent), this.getAncestors(parent).stream())
                .collect(Collectors.toList());
    }

    public void addFamilyMember(FamilyMember familyMember) throws IOException {
        familyMembers.add(familyMember);
        saveDataToFile(familyMembers);
    }

    public void deleteFamilyMember(int id) throws IOException {
        familyMembers.remove(getFamilyMember(id));
        saveDataToFile(familyMembers);
    }

    public void updateFamilyMember(FamilyMember fm) throws IOException {
        int index = familyMembers.indexOf(fm.getId());
        familyMembers.set(index,fm);
        saveDataToFile(familyMembers);
    }
}
