package familyTree.repository;

import familyTree.model.FamilyMember;
import java.util.ArrayList;
import java.util.List;

public class FamilyData {
    public List<FamilyMember> FamilyMembers = new ArrayList<>();

    public FamilyData() {
        // First Gen
        FamilyMembers.add(new FamilyMember(1, "Stan", "1-2", null, "M"));
        FamilyMembers.add(new FamilyMember(2, "Sue", "1-2", null, "F"));
        FamilyMembers.add(new FamilyMember(3,"Bob", "3-4", null, "M"));
        FamilyMembers.add(new FamilyMember(4,"Beryl", "3-4", null, "F"));

        // Second Gen
        FamilyMembers.add(new FamilyMember(5, "Avis", "5-7", "1-2", "F"));
        FamilyMembers.add(new FamilyMember(6,"Bert",  "6-8",  "3-4", "M"));
        FamilyMembers.add(new FamilyMember(7, "Tim", "5-7", "1-2", "M"));
        FamilyMembers.add(new FamilyMember(8,"Freda",  "6-8",  "3-4", "F"));

        // Third Gen
        FamilyMembers.add(new FamilyMember(9, "Stanley", "9-10", "5-7", "M"));
        FamilyMembers.add(new FamilyMember(10, "Nicky", "9-10", "5-7", "F"));
    }

    public List<FamilyMember> getFamilyMembers() {
        return FamilyMembers;
    }
}
