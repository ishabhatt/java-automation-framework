
# Java Automation Framework

A modular and extensible Java-based automation framework designed for scalable and maintainable test automation.

## ✨ Features

- **Java & TestNG Integration** – Uses TestNG for test orchestration and reporting.
- **Data-Driven Testing** – External test data support via the `testdata/` folder.
- **Page Object Model (POM)** – Clean separation of test logic and UI locators.
- **Factory Design Pattern** – Dynamically initializes page objects.
- **Base Test Architecture** – Centralized setup and teardown for test cases.
- **Cross-Browser Testing** – Executes tests on Chrome, Firefox, etc.
- **Logging (Log4j2)** – Robust logging support for debugging and reporting.
- **Parallel Execution** – Accelerated test runs using multi-threading with TestNG.
- **Screenshot on Failure** – Captures screenshots when a test fails.
- **CI/CD Ready** – Easily integrable with GitHub Actions or Jenkins pipelines.
- **Maven Build System** – Dependency management and build automation.
- **Docker Support** – Enables test execution in containerized environments.
- **Advanced Reporting** – Integrated ExtentReports with rich test logs and screenshots.

## 🧱 Framework Design & Architecture

### Design Patterns Used

#### 1. Page Object Model (POM)
Each page/screen in the application has a corresponding Java class. This class contains WebElement definitions and interactions as methods, which promotes reusability and maintainability.

#### 2. Factory Design Pattern
A `PageFactory` implementation dynamically provides instances of page classes. This avoids redundancy and separates object creation logic.

#### 3. Singleton Pattern
Used to manage the WebDriver instance throughout the test lifecycle.

#### 4. Base Class Inheritance
Test classes extend a common base class for unified setup/teardown logic. This manages WebDriver, logging, and report generation.

## 📁 Project Structure

```
java-automation-framework/
├── src/
│   └── test/
│       └── java/
│           └── pages/
│           └── tests/
│           └── utils/
│           └── base/
├── testdata/
├── docker-compose.yaml
├── pom.xml
├── testng-master.xml
├── README.md
└── docs/
    └── DesignPrinciples.md
```

## 🚀 Getting Started

### Prerequisites
- Java 8+
- Maven 3.x
- Docker (optional, for container execution)

### Installation & Execution

```bash
git clone https://github.com/ishabhatt/java-automation-framework.git
cd java-automation-framework
mvn clean install
mvn test
docker-compose up --build  # Optional for containerized runs
```

## 📘 Documentation

For a detailed explanation of design decisions and framework architecture, see:

📄 [Design Principles Documentation](docs/DesignPrinciples.md)

## 🤝 Contributing

We welcome contributions! Please fork the repository, create a new branch, and submit a pull request for review.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
