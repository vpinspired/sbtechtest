package familyTree.repository;

import familyTree.model.FamilyMember;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FamilyData {
    public List<FamilyMember> familyMembers = new ArrayList<>();

    public FamilyData() {
        // First Gen
        familyMembers.add(new FamilyMember(1, "Stan", "1-2", null, "M"));
        familyMembers.add(new FamilyMember(2, "Sue", "1-2", null, "F"));
        familyMembers.add(new FamilyMember(3,"Bob", "3-4", null, "M"));
        familyMembers.add(new FamilyMember(4,"Beryl", "3-4", null, "F"));

        // Second Gen
        familyMembers.add(new FamilyMember(5, "Avis", "5-7", "1-2", "F"));
        familyMembers.add(new FamilyMember(6,"Bert",  "6-8",  "3-4", "M"));
        familyMembers.add(new FamilyMember(7, "Tim", "5-7", "1-2", "M"));
        familyMembers.add(new FamilyMember(8,"Freda",  "6-8",  "3-4", "F"));

        // Third Gen
        familyMembers.add(new FamilyMember(9, "Stanley", "9-10", "5-7", "M"));
        familyMembers.add(new FamilyMember(10, "Nicky", "9-10", "5-7", "F"));
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
