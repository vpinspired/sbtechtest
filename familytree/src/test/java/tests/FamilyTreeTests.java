package tests;

import familyTree.model.FamilyMember;
import familyTree.repository.FamilyData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FamilyTreeTests {

    @Autowired
    private FamilyData familyData =  new FamilyData();

    public FamilyTreeTests()  {
    }

    @Test
    public void givenMaleAndFemaleWhenAddedToFamilyTreeThenTwoFamilyMembersExist() throws IOException {
        // Arrange
        List<FamilyMember> familyMemberList = new ArrayList<>();

        FamilyMember familyMemberM = new FamilyMember();
        familyMemberM.setId(1);
        familyMemberM.setName("Stan");
        familyMemberM.setFamilyParentId("");
        familyMemberM.setFamilyId("1-2");
        familyMemberM.setGender("M");
        familyMemberList.add(familyMemberM);

        FamilyMember familyMemberF = new FamilyMember();
        familyMemberF.setId(2);
        familyMemberF.setName("Sue");
        familyMemberF.setFamilyParentId("");
        familyMemberF.setFamilyId("1-2");
        familyMemberF.setGender("F");
        familyMemberList.add(familyMemberF);

        familyData.saveDataToFile(familyMemberList);

        // Act
        List<FamilyMember> results = new FamilyData().getFamilyMembers();

        // Assert
        assertThat(results.size()).isEqualTo(2);
    }


    @Test
    public void  givenChildWhenAddedToFamilyTreeThenChildAddedToExistingParents() throws Exception{
        // Arrange
        List<FamilyMember> existingMembers = familyData.getFamilyMembers();

        FamilyMember familyMemberChild = new FamilyMember();
        familyMemberChild.setId(3);
        familyMemberChild.setName("Avis");
        familyMemberChild.setFamilyParentId("1-2");
        familyMemberChild.setFamilyId("");
        familyMemberChild.setGender("F");
        existingMembers.add(familyMemberChild);

        familyData.saveDataToFile(existingMembers);

        // Act
        String maleParent = familyData.getFamilyMember(1).getGender();
        String femaleParent = familyData.getFamilyMember(2).getGender();
        List<FamilyMember> results = familyData.getFamilyMembers();

        // Assert
        assertThat(results.size()).isEqualTo(3);
        assertThat(maleParent).contains("M");
        assertThat(femaleParent).contains("F");
    }
}
