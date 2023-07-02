# Raffles App


Raffles App is a mobile application that allows users to access and view information about raffles using data from the website "kimkazandi.com". The app utilizes JSOUP for web scraping, follows the MVVM architecture pattern, and includes features such as a Drawer Navigation menu, Room Database, and SharedPreferences.

## Technologies Used
The following technologies were utilized in the development of the Raffles App:

- MVVM (Model-View-ViewModel): This architectural pattern was employed to separate the app's components into three distinct layers. The Model represents the data and business logic, the View is responsible for displaying the user interface, and the ViewModel acts as a mediator between the Model and View, handling data binding and user interactions.

- JSOUP: JSOUP is a library used for web scraping and parsing HTML documents. In this project, JSOUP was utilized to extract the relevant data from the "kimkazandi.com" website by traversing and manipulating the HTML structure.

- Drawer Navigation Menu: The app features a Drawer Navigation menu, providing users with easy navigation and access to different sections of the application. It allows users to switch between different views and functionalities within the app.

- Room Database: Room is an Android library that provides an abstraction layer over SQLite, enabling efficient database operations. In this project, Room Database was utilized to store and retrieve raffle data locally. The app utilizes Room to cache and persist the fetched raffle information, enabling quick and offline access to the data.

- SharedPreferences: SharedPreferences is an Android framework feature used for storing small amounts of key-value data. In this project, SharedPreferences were utilized to store user preferences, such as the user's favorite raffles. This allows users to maintain a personalized list of favorite raffles across app sessions.

## Features
- Fetching and Parsing Data : The app retrieves raffle information from "kimkazandi.com" by scraping the relevant sections of the website using JSOUP. This data is then parsed and stored in a Room Database for quick and efficient access.
- Data Refresh : If the last login was more than 3 hours ago, the app clears the local database and fetches updated data from the website, effectively refreshing the information. If the time elapsed is less than 3 hours, the app directly reads the data from the local database.
- Raffle Listing and Categorization : Users can view a list of raffles, which are categorized based on their respective sections on the website. The app provides a user-friendly interface for browsing and searching through the raffle listings.
- Raffle Details : Tapping on a specific raffle in the list opens a detailed view, similar to the detail page on the website. Users can view all the relevant information and details about the selected raffle.
- Favorite Raffles : Users have the option to add raffles to their favorites list. These favorite raffles are displayed separately in the Drawer Navigation menu for quick access and easy reference.

## Project Images
<p align="center">
<img width="250" alt="projess1" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/23a566f4-122f-4566-b642-070d3ff516e0">
<img width="250" alt="projess2" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/6202a4d6-bf29-4772-8607-d70b1235d921">
<img width="250" alt="projess3" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/ae7a4cf7-9b7a-4d33-adf3-a831e5f255af">
<img width="250" alt="projess4" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/200d84a8-a09c-4e62-aba9-a20f3b782002">
<img width="250" alt="projess5" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/f73dbbf4-6909-4d95-b5f0-04b330a1b241">
<img width="250" alt="projess6" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/c80ba2e6-fbde-4362-ab1e-2fcedbe947cc">
<img width="250" alt="projess7" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/24d57075-288f-4971-997a-f74a99186e0c">
<img width="250" alt="projess8" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/ac5a5565-e52c-44db-8306-76225c4e9259">
<img width="250" alt="projess9" src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/e08908d3-78ce-403f-89a9-a00e12badcd0">

</p>

## Project Realization
<p align="center">
  <img src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/4ab0b21f-b029-400d-88a4-45933da6f641" width="250" alt="gif1">
  <img src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/da9b2222-12d1-4b22-bdb3-6422006756c9" width="250" alt="gif2">
  <img src="https://github.com/akinemreyazici/Raffles_App/assets/116732291/71bf129f-005f-4693-bfaf-b3499a9b60f7" width="250" alt="gif3">
</p>
