package familyTree.controller;

import familyTree.model.FamilyMember;
import familyTree.repository.FamilyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/FamilyTree")
public class FamilyController {
    final FamilyData familyData;

    public FamilyController(FamilyData familyData) {
        this.familyData = familyData;
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<FamilyMember> getFamily() {
        List<FamilyMember> familyMemberList;
        try {
            familyMemberList = familyData.getFamilyMembers();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMemberList;
    }
}