# QuickDictionary

A simple dictionary application that allows users to look up word meanings using a public API. The project provides two interfaces: a Java Swing-based desktop application and a web-based interface.

## Features

- **Word Search**: Enter any English word to get its definition.
- **Desktop App**: Java Swing GUI for a native desktop experience.
- **Web App**: Browser-based interface for easy access.
- **Error Handling**: Handles cases like word not found, network issues, and invalid inputs.
- **Responsive Design**: Web interface is styled for better user experience.

## Prerequisites

- **For Desktop App**:
  - Java 8 or higher installed on your system.
  - Internet connection to fetch data from the API.

- **For Web App**:
  - A modern web browser (Chrome, Firefox, Safari, etc.).
  - Internet connection to fetch data from the API.

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd QuickDictionary-Java
   ```

### Running the Desktop App

1. Navigate to the `src` directory:
   ```
   cd src
   ```

2. Compile the Java files:
   ```
   javac -d . *.java */*.java
   ```

3. Run the application:
   ```
   java Main
   ```

### Running the Web App

1. Open the `frontend/index.html` file in your web browser.
   - You can double-click the file or use a local server for better experience.

## Usage

### Desktop App
- Launch the application as described above.
- Enter a word in the text field.
- Click the "Search Meaning" button.
- The definition will appear in the text area below.

### Web App
- Open `frontend/index.html` in your browser.
- Type a word in the input field.
- Click the "Search" button.
- The result will display below, including the word, meaning, and an example if available.

## API Used

This application uses the free [Dictionary API](https://api.dictionaryapi.dev/) to fetch word definitions. No API key is required.

## Project Structure

```
QuickDictionary-Java/
├── src/
│   ├── Main.java                 # Entry point for the desktop app
│   ├── api/
│   │   └── DictionaryApi.java    # Handles API calls
│   ├── exceptions/
│   │   └── ApiException.java     # Custom exception for API errors
│   ├── model/
│   │   └── WordMeaning.java      # Model class for word and meaning
│   ├── ui/
│   │   └── DictionaryUI.java     # Swing UI components
│   └── util/
│       └── JsonUtils.java        # Utility for parsing JSON responses
├── frontend/
│   ├── index.html                # Web app HTML
│   ├── styles.css                # Web app styles
│   └── javascript.js             # Web app logic
└── README.md                     # This file
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
