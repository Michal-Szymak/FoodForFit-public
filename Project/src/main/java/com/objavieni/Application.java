package com.objavieni;

import com.objavieni.meals.Recipe;
import com.objavieni.parsing.RecipeParser;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import com.objavieni.user.DietLabel;
import com.objavieni.user.HealthLabel;
import com.objavieni.user.Preferences;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

import static com.objavieni.functions.PreferencesFunction.preferencesToDto;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Application.class, args);

    }
}
