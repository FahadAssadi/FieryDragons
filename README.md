# Fiery Dragons

## Overview

Fiery Dragons is a Java-based game implemented using Swing and AWT. The project follows Object-Oriented Programming (OOP) principles and design patterns to ensure maintainability and scalability.

## Project Structure

All implementation files can be found in `Project/Sprint2/src`.

- **Main Entry Point**: The main function is located in [`main.Game`](Project/Sprint2/src/main/Game.java).
- **Backend**: All backend-related files are located in [`main.game`](Project/Sprint2/src/main/game).
- **Miscellaneous**: The Settings and Utility files are located in [`main.misc`](Project/Sprint2/src/main/misc).
- **Resources**: All assets and JSON files used in the game are located in [`resources`](Project/Sprint2/src/resources).
- **Testing**: All testing files are located in [`main.testing`](Project/Sprint2/src/main/testing).
- **UI Elements**: All UI elements related to the game are located in [`main.ui`](Project/Sprint2/src/main/ui).

## Technologies Used

- **Java SDK**: 22
- **Swing and AWT**: For building the graphical user interface.
- **JSON.simple**: For JSON parsing and manipulation.
- **SnakeYAML**: For YAML parsing and manipulation.

## Design Patterns

The project utilizes several OOP design patterns:

- **Strategy Pattern**: Implemented in the [`BehaviourStrategy`](Project/Sprint2/src/main/game/player/behaviour/BehaviourStrategy.java) interface and its implementations like [`HumanBehaviour`](Project/Sprint2/src/main/game/player/behaviour/HumanBehaviour.java).
- **Singleton Pattern**: Used in the Settings class to manage game settings.
- **Factory Pattern**: Used for creating various game objects dynamically.
- **Observer Pattern**: Used for event handling and updating UI components based on game state changes.

## Utility Methods

The [`Utility`](Project/Sprint2/src/main/misc/Utility.java) class provides various utility methods for common tasks:

- **JSON Handling**:
  - `incrementValueInJSONFile(InputStream inputStream, String key)`: Increments a value in a JSON file.
  - `readJSONFileToArr(InputStream inputStream)`: Reads a JSON file and parses its content into a `JSONArray`.
  - `readJSONFileToObj(InputStream inputStream)`: Reads a JSON file and parses its content into a `JSONObject`.

- **YAML Handling**:
  - `readYamlFile(String fileName)`: Reads a YAML file and parses its content into a `Map`.
  - `writeYamlFile(Map<String, Object> data, String fileName)`: Writes data to a YAML file.

## Video Demonstration

Watch the video demonstration of the game [here](https://youtu.be/iiM_17GldbQ).

## Executable Platform

- **Platform**: Windows 11

## How to Run

1. Ensure you have Java SDK 22 installed.
2. Navigate to the project directory.
3. Compile the project using your preferred IDE or command line.
4. Run the main class `main.Game`.

## Contribution

Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.
