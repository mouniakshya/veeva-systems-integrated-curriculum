# India Population Directory

A simple menu-driven Java console application for managing state, city, and population data. It supports adding states and cities, updating population figures, and searching or summarizing the directory.

## Features

- Add states and cities
- Update a city's population
- Look up a city within a specified state
- Display the cities and total population for a state
- Search for a city across all states
- List cities whose populations are above a chosen threshold
- Display all states and their cities

The application starts with sample data for Andhra Pradesh, Telangana, and Karnataka. This is example data included in the program, not a complete or authoritative population dataset.

## Requirements

- Java Development Kit (JDK) installed
- A terminal or command prompt

## Run the application

From this directory, compile the Java files:

```bash
javac Main.java PopulationDirectory.java StateData.java
```

Then start the program:

```bash
java Main
```

Follow the numbered menu and enter the requested values. Choose **9** to exit.

## Files

- `Main.java` — Starts the application, loads sample data, and handles the interactive menu.
- `PopulationDirectory.java` — Stores the state and city population data and implements the directory operations.
- `StateData.java` — Defines a state data model containing a state name and a collection of cities.
