This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


# My Awesome KMP Note Taking App # # Build and Run Instructions

### Android

1. **Clone the repository:** bash git clone https://github.com/pawarbhushan08/NoteTakingKMP.git
2. **Open the project in Android Studio:**
  - Launch Android Studio.
  - Select "Open an existing Android Studio project."- Navigate to the cloned repository and select the project folder.

3. **Build and run the app:**
  - Connect your Android device or start an emulator.
  - Click the "Run" button (green triangle) in the toolbar.

### iOS 

*//TODO:Provide iOS build and run instructions.*

## Features

* **Feature 1:** Add notes with title and Description.
* **Feature 2:** Update Notes title &/or description.
* **Feature 3:** Delete Note.
* ...

## Code Structure and Architecture

### Overview: The app follows a Model-View-ViewModel (MVVM) architectural pattern. This helps to separate concerns and improve code maintainability.

### Key Components

* **Models:** Data classes representing the app's data (e.g., `Note`).
* **ViewModels:** Classes responsible for managing UI state and interacting with the data layer.
* **Views:** Activities, and Composables that display the UI and handle user interactions.
* **Repositories:** Classes that abstract data access logic (e.g., from a database or network).

### Package Structure

The code is organized into packages based on feature or functionality:

* `ui`: Contains UI-related components (Composables).
* `data`: Contains data access logic (Data Sources, (Repositories for future)).
* `domain`: Contains business logic (Models, StateLogic, Actions).
* `di`: Contains dependency injection setup.
* `vm`: Contains all viewmodels of.
* ...

### Architectural Patterns

* **MVVM:** As mentioned earlier, the app uses MVVM for separation of concerns.
* **Repository Pattern:** Used to abstract data access and provide a clean interface for ViewModels.
* **DependencyInjection:** Used to manage dependencies and improve testability (e.g., with Koin).
* ...

## Contributing

*If you want to allow contributions, provide guidelines here.*

## License

*For reference and study purpose*
