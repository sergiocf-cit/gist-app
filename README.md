# gist-app
A simple Android app following the best and new practices recommended by Jetpack. 

This app was develop using Visual Studio 4.0

# Main architecture decisions

- Navigation Component to navigate among the fragments also using safeargs: open navigation.xml to see Navigation Graph
- Koin for DI  
- Retrofit with Coroutines(I'm used to RXJava but this time I decided to give it a try)
- Picasso to load an image into ImageView
- MVVM to separate responsibilities for each layer
- Lifecycle and LiveData
- There are 3 prefixes of "POJOS":
    DTO for API
    Entity for Room
    Without prefix per instance(Gist) for the model
- Android Paging for infinite scrolling

