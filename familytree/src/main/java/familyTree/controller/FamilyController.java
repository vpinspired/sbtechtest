package familyTree.controller;

import familyTree.exceptions.InvalidRequestException;
import familyTree.model.FamilyMember;
import familyTree.repository.FamilyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
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


    @GetMapping(path = "{id}/descendants", produces = "application/json")
    public List<FamilyMember> getDescendantsOfMember(@PathVariable int id) {
        List<FamilyMember> familyMemberDescendantList;
        try {
            familyMemberDescendantList = familyData.getDescendants(getFamilyMember(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMemberDescendantList;
    }

    @GetMapping(path = "/{id}/children", produces = "application/json")
    public List<FamilyMember> getChildren(@PathVariable int id) {
        List<FamilyMember> familyMemberChildrenList;
        try {
            familyMemberChildrenList = familyData.getChildren(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMemberChildrenList;
    }

    @GetMapping(path = "/{id}/parents", produces = "application/json")
    public List<FamilyMember> getParents(@PathVariable int id) {
        List<FamilyMember> familyMemberParentList;
        try {
            familyMemberParentList = familyData.getParents(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMemberParentList;
    }

    @GetMapping(path = "/{id}/ancestors", produces = "application/json")
    public List<FamilyMember> getAncestors(@PathVariable int id) {
        List<FamilyMember> familyMemberAncestorList;
        try {
            familyMemberAncestorList = familyData.getAncestors(getFamilyMember(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMemberAncestorList;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public FamilyMember getFamilyMember(@PathVariable int id) {
        FamilyMember familyMember;
        try {
            familyMember = familyData.getFamilyMember(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
        return familyMember;
    }

    @PostMapping
    public ResponseEntity<Object> addToFamily(@PathVariable String id, @RequestBody FamilyMember familyMember) throws IOException {
        familyData.addFamilyMember(familyMember);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(familyMember.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public void updateFamilyMember(@RequestBody FamilyMember familyMember) throws IOException {
        if (familyMember == null || familyMember.getId() > 0) {
            throw new InvalidRequestException(HttpStatus.NOT_FOUND + " Id Not Found");
        }

        try {
            FamilyMember fm = familyData.getFamilyMember(familyMember.getId());

            fm.setName(familyMember.getName());
            fm.setFamilyId(familyMember.getFamilyId());
            fm.setFamilyParentId(familyMember.getFamilyId());
            fm.setGender(familyMember.getGender());
            familyData.updateFamilyMember(fm);

        } catch (RuntimeException irex) {
            throw new InvalidRequestException(HttpStatus.NOT_FOUND + "Not Found " + irex);
        }
        catch (IOException ioEx){
            throw new IOException(ioEx.getMessage());
        }

    }

    @DeleteMapping(value = "{id}")
    public void deleteFamilyMember(@PathVariable(value = "id") int id) throws IOException {

        if (familyData.getFamilyMember(id) == null) {
            throw new InvalidRequestException(HttpStatus.NOT_FOUND + " Family member with id Not Found");
        }
        familyData.deleteFamilyMember(id);
    }
}