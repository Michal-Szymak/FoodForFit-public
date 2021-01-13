package user;

public class User {

    private String name;
    private Gender gender;
    private int age;
    private Preferences preferences;

    public User() {
    }

    public User(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void addHealthLabelToPreferences(HealthLabel healthLabel){
        preferences.getAlergy().add(healthLabel);
    }

    public void addDietLabelToPreferences(DietLabel dietLabel){
        preferences.getDietLabel().add(dietLabel);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
