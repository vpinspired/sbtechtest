package familyTree.model;

public class FamilyMember {
    private int id;
    private String name;
    private String familyId;
    private String familyParentId = null;
    private String gender;


    public FamilyMember(int id, String name, String fid, String pfid, String gen) {
        this.id = id;
        this.name = name;
        this.familyId = fid;
        this.familyParentId = pfid;
        this.gender = gen;
    }

    public FamilyMember() {
    }

    @Override
    public String toString() {
        return "FamilyMember [id=" + id
                + ", name=" + name
                + ", familyId=" + familyId
                + ", familyParentId=" + familyParentId
                + ", gender=" + gender + "]";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamilyId() {
        return familyId;
    }

    public String getFamilyParentId() {
        return familyParentId;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public void setFamilyParentId(String familyParentId) {
        this.familyParentId = familyParentId;
    }
}
