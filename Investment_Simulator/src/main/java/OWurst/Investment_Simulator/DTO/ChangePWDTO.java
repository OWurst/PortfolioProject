package OWurst.Investment_Simulator.DTO;

public class ChangePWDTO {
    private String oldpassword;
    private String newpassword;

    public ChangePWDTO() {
    }

    public ChangePWDTO(String oldpassword, String newpassword) {
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    @Override
    public String toString() {
        return "ChangePWDTO [oldPass=" + oldpassword + ", newPass=" + newpassword + "]";
    }
}
