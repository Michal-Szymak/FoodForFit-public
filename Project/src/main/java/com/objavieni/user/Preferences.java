package com.objavieni.user;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "preferences")
public class Preferences {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToOne(mappedBy = "preferences")
    private User user;

    @ElementCollection(targetClass = HealthLabel.class)
    @JoinTable(name = "health_labels", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<HealthLabel> allergies;

    @ElementCollection(targetClass = DietLabel.class)
    @JoinTable(name = "diet_labels", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<DietLabel> dietLabels;

    private int countMealsPerDay;

    private int countCaloriesPerDay;

    public Preferences(List<HealthLabel> allergies, List<DietLabel> dietLabels, int countMealsPerDay, int countCaloriesPerDay) {
        this.allergies = allergies;
        this.dietLabels = dietLabels;
        this.countMealsPerDay = countMealsPerDay;
        this.countCaloriesPerDay = countCaloriesPerDay;
    }

    public Preferences() {
        this.allergies = new ArrayList<>();
        this.dietLabels = new ArrayList<>();
    }

    public void addDietLabelToPreferences(DietLabel dietLabel) {
        dietLabels.add(dietLabel);
    }

    public List<HealthLabel> getAllergies() {
        return allergies;
    }

    public List<DietLabel> getDietLabels() {
        return dietLabels;
    }

    public int getCountMealsPerDay() {
        return countMealsPerDay;
    }

    public int getCountCaloriesPerDay() {
        return countCaloriesPerDay;
    }

    public void setDietLabels(List<DietLabel> dietLabels) {
        this.dietLabels = dietLabels;
    }
}
