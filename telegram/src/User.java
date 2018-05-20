public class User {
    private String firstname;
    private String secondname;
    private String specialty;
    private String email;
    private String phonenumber;
    private int teamcount = 0;
    User (String firstname, String secondname, String specialty,String email,String phonenumber)
    {
        this.firstname = firstname;
        this.secondname = secondname;
        this.specialty = specialty;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getTeamcount() {
        return teamcount;
    }

    public void setTeamcount(int teamcount) {
        this.teamcount = teamcount;
    }

}
