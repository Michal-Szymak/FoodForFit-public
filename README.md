# FoodForFit
Application created as a group project during infoShare Academy Java + Spring Bootcamp between 12/2020 and 02/2021. Its main functionality is to provide weekly meal plan according to users' preferences.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Available endpoints](#endpoints)

## General info
This application was created to provide users with custom meal plans that meet their expectations. Although at first it was created as a bootcamp project it is still work in progress, meant to be expanded and upgraded.

*Sidenote: This is a public mirror of FoodForFit repository*
	
## Technologies
Project is created with:
- Java SE 11
- Spring framework
- MySQL database for Users and their Preferences
- Maven
- Thymeleaf
- JUnit 5
- AssertJ 3.16.1

## Endpoints
Below you can find a list of currently available endpoints:
- /register - used to register new User
- /login - used for registered Users (that are present in database) to log in
- /profile - shows basic information about a User and Users' diet preferences
- /diet - used to set or update Users' diet preferences such as
  * Daily Meals - number of meals per day
  * Daily Calories - total number of calories per day expected in our meal plan
  * Health Labels - allowing User to specify what types of products are meant to be avoided
  * Diet Labels - general preferences about diet type, such as low-carb, high-protein etc.
- /calendar - a view showing meals for next week, with their names, photos and possibility to display list of ingredients for a meal
