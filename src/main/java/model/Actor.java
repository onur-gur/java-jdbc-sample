package model;

public class Actor extends Model {

    private String name;
    private Gender gender;
    private Integer birthYear;
    private Integer deathYear;
    private int oscarNominations = 0;
    private int oscars = 0;
    private int wins = 0;
    private int nominations = 0;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNominations() {
        return nominations;
    }

    public void setNominations(int nominations) {
        this.nominations = nominations;
    }

    public int getOscarNominations() {
        return oscarNominations;
    }

    public void setOscarNominations(int oscarNominations) {
        this.oscarNominations = oscarNominations;
    }

    public int getOscars() {
        return oscars;
    }

    public void setOscars(int oscars) {
        this.oscars = oscars;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }
}

