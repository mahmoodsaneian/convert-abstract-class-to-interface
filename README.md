# Convert Abstract Class to Interface

## Overview  
This project is a **Java-based tool** designed to automatically convert **abstract classes** to **interfaces** in a given codebase. It helps developers refactor legacy code or adapt to design changes more efficiently, especially when there's a need to change an abstract class to an interface while maintaining the class's functionalities.

## Features  
- **Automatic Conversion**: Converts abstract classes to interfaces, keeping the class methods intact.  
- **Simplified Refactoring**: Aims to reduce manual effort required for refactoring abstract classes, making the codebase cleaner and easier to maintain.  
- **Code Parsing**: Analyzes Java source code files to identify abstract classes and convert them to interfaces.

## Technologies Used  
- **Java 17**  
- **Java Reflection** (if applicable)  
- **File I/O** for reading and writing source code files  
- **Regular Expressions** (for pattern matching and class identification)

## Requirements  
- **Java Development Kit (JDK) 17** or later  
- A Java IDE (Optional: IntelliJ, Eclipse, NetBeans)  

## Setup  

### Clone the Repository  
```sh
git clone https://github.com/mahmoodsaneian/convert-abstract-class-to-interface.git
cd convert-abstract-class-to-interface
```

### How to Run  
1. Compile the Java code:  
   ```sh
   javac Main.java
   ```

2. Run the tool:  
   ```sh
   java Main <path-to-java-file-or-directory>
   ```

   - The tool will read the Java files from the given path, identify any abstract classes, and convert them into interfaces. It will output the modified Java files.

### Example  
If your Java file has an abstract class like this:

```java
public abstract class Animal {
    public abstract void makeSound();
}
```

The tool will convert it into:

```java
public interface Animal {
    void makeSound();
}
```

## How It Works  
- The tool reads Java source files and scans for abstract class definitions.  
- It detects the methods within the abstract class and moves them to the interface.  
- It then creates a new interface file while retaining the original class logic for easy conversion.

## Project Structure  
```
convert-abstract-class-to-interface/
│── src/
│   ├── Main.java (Main logic for converting abstract classes to interfaces)
│   ├── AbstractClassToInterfaceConverter.java (Helper class for conversion logic)
│── README.md
│── .gitignore
```  

## Contributing  
Feel free to fork the repository, create issues, or submit pull requests with improvements or bug fixes.

## License  
This project is licensed under the MIT License.

## Repository  
[GitHub: convert-abstract-class-to-interface](https://github.com/mahmoodsaneian/convert-abstract-class-to-interface)

