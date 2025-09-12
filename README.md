# Library Management System

This project simulates a library management system using Java 21 and an in-memory H2 database. It implements various design patterns to demonstrate software design principles and best practices.

## Features

- **Singleton Pattern**: Ensures a single instance of the database connection.
- **Factory Method Pattern**: Creates different types of books and members.
- **Abstract Factory Pattern**: Creates families of related objects for books and members.
- **Builder Pattern**: Constructs complex Book and Member objects.
- **Strategy Pattern**: Implements different search strategies for books.
- **Observer Pattern**: Notifies observers about changes in book status.
- **Decorator Pattern**: Adds functionalities to books dynamically.
- **Chain of Responsibility Pattern**: Validates book, member, and loan data.

## Project Structure

```
Chapter
├── src
│   └── main
│       └── java
│           └── com
│               └── library
│                   ├── LibraryManagementApplication.java
│                   ├── config
│                   │   └── DatabaseConfig.java
│                   ├── model
│                   │   ├── Book.java
│                   │   ├── Member.java
│                   │   ├── Loan.java
│                   │   └── Author.java
│                   ├── repository
│                   │   ├── BookRepository.java
│                   │   ├── MemberRepository.java
│                   │   └── LoanRepository.java
│                   ├── service
│                   │   ├── LibraryService.java
│                   │   ├── BookService.java
│                   │   ├── MemberService.java
│                   │   └── LoanService.java
│                   ├── patterns
│                   │   ├── singleton
│                   │   │   └── DatabaseConnection.java
│                   │   ├── factory
│                   │   │   ├── BookFactory.java
│                   │   │   └── MemberFactory.java
│                   │   ├── abstractfactory
│                   │   │   ├── EntityFactory.java
│                   │   │   ├── BookEntityFactory.java
│                   │   │   └── MemberEntityFactory.java
│                   │   ├── builder
│                   │   │   ├── BookBuilder.java
│                   │   │   └── MemberBuilder.java
│                   │   ├── strategy
│                   │   │   ├── SearchStrategy.java
│                   │   │   ├── SearchByTitle.java
│                   │   │   ├── SearchByAuthor.java
│                   │   │   └── SearchContext.java
│                   │   ├── observer
│                   │   │   ├── Observer.java
│                   │   │   ├── Subject.java
│                   │   │   ├── LibraryObserver.java
│                   │   │   └── NotificationService.java
│                   │   ├── decorator
│                   │   │   ├── BookDecorator.java
│                   │   │   ├── DigitalBookDecorator.java
│                   │   │   └── ReservedBookDecorator.java
│                   │   └── chainofresponsibility
│                   │       ├── ValidationHandler.java
│                   │       ├── BookValidationHandler.java
│                   │       ├── MemberValidationHandler.java
│                   │       └── LoanValidationHandler.java
│                   └── utils
│                       ├── DatabaseUtils.java
│                       └── DateUtils.java
├── src
│   └── test
│       └── java
│           └── com
│               └── library
│                   ├── service
│                   │   ├── LibraryServiceTest.java
│                   │   └── BookServiceTest.java
│                   └── patterns
│                       ├── SingletonTest.java
│                       └── FactoryTest.java
├── src
│   └── main
│       └── resources
│           ├── application.properties
│           ├── schema.sql
│           └── data.sql
├── pom.xml
└── README.md
```

## Setup Instructions

1. Clone the repository or download the project files.
2. Navigate to the project directory.
3. Ensure you have Java 21 and Maven installed.
4. Run `mvn clean install` to build the project.
5. Run the application using `mvn spring-boot:run`.

## Usage

Once the application is running, you can manage books, members, and loans through the provided services. The system supports various operations such as adding books, searching for members, and processing loans.

## Testing

Unit tests are provided for the service classes and design pattern implementations. You can run the tests using:

```
mvn test
```

## License

This project is licensed under the MIT License.