# CountryExplorerApp

CountryExplorerApp is an Android app that allows users to explore country details such as name, region, code, and capital. The app fetches data from a remote source and displays it in a user-friendly interface with efficient RecyclerView implementation.

## Features

- Displays a list of countries with their name, region, code, and capital.
- Uses RecyclerView with ListAdapter for efficient item updates.
- Handles empty and error states gracefully.
- Supports proper handling of null values by displaying "NA" when necessary.
- Optimized with DiffUtil to minimize UI updates.
  
## Technologies Used

- Kotlin
- Android Jetpack (RecyclerView, ViewModel, LiveData, Data Binding)
- Retrofit for network calls
- DiffUtil for optimized RecyclerView updates
- MVVM architecture

## Setup

1. Clone this repository:

2. Open the project in **Android Studio**.

3. Make sure you have the required dependencies and SDK versions set up.

4. Build and run the app on your preferred device or emulator.
