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

1. Clone this repository [master branch].

2. Open the project in **Android Studio**.

3. Make sure you have the required dependencies and SDK versions set up.

4. Build and run the app on your preferred device or emulator.

## 🚀 Future Scope

Here are some planned improvements and enhancements for the app:

- **Pagination Support:** Implement paginated data loading to handle large datasets more efficiently.
- **Robust Backend Value Handling:** Improve data validation to handle inconsistent or incomplete API responses with default values on client end.
- **Network Listener Integration:** Automatically retry data loading when network connectivity is restored, removing the need to press the retry button manually.
- **Offline Support:** Cache country data for offline viewing and better user experience in poor network conditions.
- **UI/UX Enhancements:** Improve overall visual design and animations for a more modern, responsive experience.
