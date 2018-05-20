public class Team {
    private String nameleader;
    private int count;
    private String[] ListTeam = new String[count];
    private String fileprez;
    private String filecod;
    private String cases;
    private String hack;

    Team(String nameleader, int count) {
        this.nameleader = nameleader;
        this.count = count;
    }

    public String[] getListTeam() {
        return ListTeam;
    }

    public void setListTeam(String[] listTeam) {
        ListTeam = listTeam;
    }

    public String getFileprez() {
        return fileprez;
    }

    public void setFileprez(String fileprez) {
        this.fileprez = fileprez;
    }

    public String getFilecod() {
        return filecod;
    }

    public void setFilecod(String filecod) {
        this.filecod = filecod;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getHack() {
        return hack;
    }

    public void setHack(String hack) {
        this.hack = hack;
    }

}
