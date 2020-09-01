# gist-app
A simple Android app following the best practices recommended by Jetpack. 

This app was develop using Visual Studio 4.0 and tested using emulator Pixel 3 API 29.

# Main architecture decisions
- It uses Navigation Component to navigate among the fragments. It uses safeargs
 to pass params among fragments and it has slide animation when transitioning fragments. 
 To get more details open navigation.xml to see Navigation Graph.
 
- Koin is used to Dependency Inject due its simplicity.  

- Retrofit is used to access Gist Api(https://api.github.com/gists/public?page=0) 
Using LiveData.

- Coroutines + Kotlin Flow are used to do background processing. I'm used to use RXJava
but this time I decided to try.

- Picasso loads images into ImageView and also cache them for offline view. Specially at Favorite
page.

- MVVM Pattern is used to separate responsibilities among the layers and it also helps 
to survives configuration change. We don't need manually save the in the Bundle in case
a rotation per example.

- LiveData is preferred used once it is lifecycle aware, so we don't need to dispose resource.

- There are 3 kind of "Pojos" and each one has it layer scope:
    DTO is used in Service Api layer(Retrofit).
    Entity is used in Database layer(Room)
    Model is used in ViewModel layer(There is no prefix in the class name)

Summarizing the viewmodels and up components like fragments only know models.
The Repository is responsible abstract and convert these objects like: DTO to Model,
Entity to Model or Model to Entity in case of storing data.     

- Android Paging is used for infinite scrolling, loading state and error handler.

- JsonDeserializer is used for parse the files field because it dynamic in Gist Api.

- Room is used for persistence.

- It has testes for database following this approach: https://developer.android.com/training/data-storage/room/testing-db.
Also the LiveData getOrAwaitValue idea is used for testing livedata. 

- It has unit tests for repositories and viewmodel using different ways to mock data.

- It has two flavours "free" and "paid". Search per user is only visible for paid version.
