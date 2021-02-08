package com.objavieni.configuration;

import com.objavieni.mealDistribution.MealDistributor;
import com.objavieni.meals.RecipeService;
import com.objavieni.repository.UserRepository;
import com.objavieni.request.Request;
import com.objavieni.request.RequestManager;
import com.objavieni.service.UserService;
import com.objavieni.user.Preferences;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public UserService getUserService(){
        return new UserService();
    }

//    @Bean
//    public Request getRequest() {
//        return new Request();
//    }
//
////    @Bean
//    public Preferences getPreferences() {
//        return new Preferences();
//    }

//    @Bean
//    public RequestManager getRequestManager() {
//        return new RequestManager();
//    }
}
//    @Bean
//    public RecipeService getRecipeService(){
//        return new RecipeService(getRequest(),getPreferences(),getRequestManager());
//    }
//    @Bean
//    public MealDistributor getMealDistributor(){
//        return new MealDistributor(getRecipeService().getRecipeList(),getPreferences());
//    }
//}
