# PizzaMe
# Project Requirements
User Story

 As a user I want to find the nearest pizza places to me so that I can select the closest one.

 Acceptance Criteria

 *   Using the GPS on my device, it should present a list of nearby pizza places

*   The list should show basic information such as Name, Address, City, State, distance(in miles), phone number

*   Selecting an entry from the list shows a detail page for that location.

*   From the detail page I should be able to open it in maps, or call the number.

 Technical Requirements

 Rest query : api<https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%2778759%27%20and%20query%3D%27pizza%27&format=json&diagnostics=true&callback=>

 (Note: you will need to modify the API to use your device's geo location instead of a hardcoded zip code ref: https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%2794085%27%20and%20query%3D%27pizza%27&format=json&diagnostics=true&callback=)

Android Technology to be used

*   RecyclerView

*   Should be developed in MVVM style and include automated unit tests


# Comments by Saurav
There are many room for improvements. 
- If the emulator is started with a clean slate and the user is prompted for google play update, the app freezes in Getting Location state. This can be remedied by restarting the app.
- If the user is in lat lon that doesn't return any results, the user sees the loading state. This can be handled by showing a proper error message upon detecting that no results was returned on the first call to the server.
- Current test cases focuses on the view model classes. More test cases could be utilized such as Espresso tests and other instrumentation tests.
- Splash screen can be added.
- UI can use more polishing

# Screenshot
![alt text](https://github.com/sauravrp/PizzaMe/blob/master/screenshots/pizzame.gif)


