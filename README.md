# Countries
InterViewProject
Coding Challenge “Countries Application”
Welcome, and thank you very much for taking the time to meet this coding challenge!
The intent of the mini project is to show off your mobile OS platform knowledge, coding skills
and coding style.
The challenge can be completed on either iOS or Android, depending on the role in the team
and your specialization.
Instructions
Please open a GIT repository for your code, at the beginning of the test, and share the GIT
repository access information with us. Then work within that repository.
You will have 2 calendar days to complete the challenge. Of course, the actual work will take
less time, but we use the same time-boundary for everyone. 
Your Task
You challenge, should you choose to accept it, is to create a mobile app (Universal iOS app or
native Android app) called ‘Countries’ which will enable a user to search for countries based on
country name and to see several details about the countries. 
Requirement R1: Online Search
 R1.1 Online Search will be landing screen of the application.
 R1.2 This screen will show search results only when user starts searching.
 R1.3 There should be a search box on top.
 R1.4 The user should be able to search with country name or partial country name.
 R1.5 For each character entered in the search box, the search results should refresh.
 R1.6 The search result should be displayed as a list, with each row in the list
containing the country flag and country name.
 R1.7 The flag image should be loaded lazily.
 R1.8 On selecting an item in the list, the details of the country should be shown.
Requirement R2: Country Details
 R2.1 The Country Details screen will display the details of a country selected in the
search result list of the Online Search function.
 R2.2 This screen will show country flag, country name, capital, calling code, region, sub
region, time zone, currencies and languages.

 R2.3 There will be a button in the screen to save the country for offline. On clicking of
this button, the country should be persisted locally including the flag image. (see R3
Offline Mode, below)
Requirement R3: Offline Mode
 R3.1 When the device is in offline mode, then the landing screen will show all the
countries saved for offline use (see R2.3).
 R3.2 When the device is in offline mode, the search function (equivalent to R1 above) is
only performed on the countries saved for offline use.
 R3.3 When the device is in offline mode, the &quot;save for offline button&quot; will be hidden or
disabled (see R2.3).
Coding and Design Requirements
 For iOS, use Swift (ideally a recent version)
 For Android, use Java and the latest Android SDK. If you prefer to use Kotlin, please let
us know first.
 Design the UI as you feel is best. An &quot;engineering UI&quot; is sufficient.
 Use the following API to get the list of countries:
https://restcountries.eu/rest/v2/name/{name} 
 You can find more details of API in the following URL https://restcountries.eu/
 Feel free to use any number of third-party libraries if necessary or useful
Evaluation Criteria
 Main evaluation criteria are:
o Good architecture and design patterns
o Good coding practises
 Bonus for
o Unit testing
o Clean usable UI
