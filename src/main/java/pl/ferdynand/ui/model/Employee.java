package pl.ferdynand.ui.model;

public class Employee {
    private String fullName;
    private String organizationUnit;
    private String profileHref;


    //    private String phoneNumber;
    private String vCardLink;

    public Employee() {
        this(null, null, null);
    }

    public Employee(String fullName, String organizationUnit, String profileHref) {
        this.fullName = fullName;
        this.organizationUnit = organizationUnit;
//        this.phoneNumber = phoneNumber;
//        this.vCardLink = vCardLink;
        this.profileHref = profileHref;
    }

    public String getProfileHref() {
        return profileHref;
    }

    public void setProfileHref(String profileHref) { this.profileHref = profileHref;    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getvCardLink() {
        return vCardLink;
    }

    public void setvCardLink(String vCardLink) {
        this.vCardLink = vCardLink;
    }



}
