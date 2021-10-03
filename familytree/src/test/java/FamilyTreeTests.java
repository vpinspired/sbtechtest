import familyTree.model.FamilyMember;
import familyTree.repository.FamilyData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FamilyTreeTests {


    @Test
    public void test()  {
        // Arrange
        List<FamilyMember> fml = new ArrayList<>();

        FamilyMember familyMemberM = new FamilyMember();
        familyMemberM.setId(1);
        familyMemberM.setName("Stan");
        familyMemberM.setFamilyParentId("");
        familyMemberM.setFamilyId("1-2");
        familyMemberM.setGender("M");
        fml.add(familyMemberM);

        FamilyMember familyMemberF = new FamilyMember();
        familyMemberF.setId(2);
        familyMemberF.setName("Sue");
        familyMemberF.setFamilyParentId("");
        familyMemberF.setFamilyId("1-2");
        familyMemberF.setGender("F");
        fml.add(familyMemberF);

        // Act
        List<FamilyMember> results = new FamilyData().getFamilyMembers();

        // Assert
        assertThat(results.size()).isEqualTo(2);
    }
}
