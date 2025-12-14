#Stock Trading Platform (Console-Based)


*Overview*
        ->This project is a console-based Stock Trading Platform implemented in Java using JDBC and
          PostgreSQL.
        ->It simulates a basic stock trading environment where users can register, log in, view available
          stocks, perform buy and sell operations, and track their portfolio based on transaction history.
        ->The application follows an Object-Oriented Programming (OOP) approach to manage users, stocks,
          and transactions, while using a relational database for persistence.


*Features*
1. User Authentication
        ->User registration with username and password
        ->User login using stored credentials
        ->In-memory session handling using a static userId until application exit

2. Stock Market Display
        ->Displays all available stocks from the database
        ->Shows stock symbol, name, and price

3. Buy and Sell Operations
        ->Users can buy or sell stocks using stock symbol and quantity
        ->Each operation creates a transaction entry in the database
        ->User’s total investment is updated accordingly

4. Portfolio Tracking
        ->Displays all transactions of the logged-in user
        ->Portfolio data is derived directly from the transaction table
        ->Shows total investment made by the user since registration


*Technology Stack*
1. Language: Java
2. Database: PostgreSQL
3. Database Access: JDBC
4. Architecture: Console-based, OOP-driven design


*Database Design (Logical)*
        ->The application uses the following core tables:
            1. User : Stores user credentials and total investment
            2. Stocks : Stores stock symbol, name, and price
            3. Transactions : Stores buy/sell records linked to users
        ->Transactions are used as the single source of truth for portfolio tracking.


*Application Flow*
    1. Application starts with a greeting message based on system time
    2. User chooses to log in or register
    3. Successful login creates an active session
    4. User can:
        ->View portfolio
        ->Buy or sell stocks
        ->View available stocks
    5. All data is persisted in the database
    6. Application exits on user command


*Object-Oriented Design*
        ->The system uses separate classes for core entities: User, Stock, Transaction
        ->Business logic is handled through methods in the main application class, while database
          persistence is handled using JDBC.


Notes
    1. Stock prices are static and retrieved from the database
    2. Profit/loss calculation is not included, as price fluctuation is outside the scope
    3. Portfolio is derived from stored transactions, not stored separately
    4. Database persistence is preferred over file I/O for reliability and structured storage


Conclusion
    ->This project fulfills the requirements of a basic stock trading simulation by combining OOP
      principles with database-backed persistence. It focuses on correctness, clarity, and structured data
      handling rather than real-time market complexity.
