# 🌿 SWAY — Find Your Balance

**SWAY** is a desktop-based Calorie Tracker application developed using **Java Swing** and **JDBC**.

The application allows users to log in securely, track their daily food intake, manage food entries, and view calorie reports — all tied to their personal account.

This project demonstrates practical implementation of:

- Object-Oriented Programming (OOP)
- Event-Driven Programming
- GUI Development using Swing
- Database Connectivity using JDBC
- User Authentication & Session Management
- Modular Java Application Design

---

## ✨ Features

### 🔐 Login Module
- Secure username & password authentication
- User-specific session (each login sees only their own data)
- Logout functionality from the Home Screen

### 🍽️ Food Entry Module
- Add food items with name, calories, and date
- Entries are saved under the logged-in user's account
- Default date set to today automatically

### 🛠️ Manage Entry Module
- **Update Entry** — Edit calories of an existing food item
- **Delete Entry** — Remove a food item from your records
- All operations are scoped to the logged-in user only

### 📊 Daily Report Module
- View all food entries for a selected date
- Displays food name and calories in a clean table
- Shows **TOTAL CALORIES** at the bottom
- Only shows data belonging to the logged-in user

### 🎨 UI Enhancements
- Consistent mint-green theme across all screens
- Aligned form layouts using `GridBagLayout`
- Hover effects on buttons
- Focus highlights on input fields
- Clean and minimal Swing design

---

## 🧰 Technologies Used

| Technology | Purpose                  |
|------------|--------------------------|
| Java       | Core Programming         |
| Swing      | GUI Development          |
| AWT        | Event Handling & Layouts |
| JDBC       | Database Connectivity    |
| MySQL      | Database                 |
| OOP        | Application Architecture |

---

## 🏗️ Application Architecture

```
User Login
     ↓
Swing GUI (JFrame, JTable, Buttons)
     ↓
Event Listeners
(ActionListener / MouseListener / FocusListener)
     ↓
Application Logic (User Session & Data Filtering)
     ↓
JDBC Layer (PreparedStatement)
     ↓
MySQL Database (sway_db)
```

---

## 📁 Project Structure

```
SWAY/
│
├── lib/
│   └── mysql-connector-j.jar
│
├── src/
│   ├── LoginScreen.java
│   ├── HomeScreen.java
│   ├── FoodEntry.java
│   ├── ManageEntry.java
│   ├── UpdateEntry.java
│   ├── DeleteEntry.java
│   ├── ReportScreen.java
│   └── DBConnection.java
│
├── sway_db_setup.sql
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/SWAY.git
cd SWAY
```

### 2️⃣ Setup Database

Create the database and tables:

```sql
CREATE DATABASE sway_db;
USE sway_db;
```

Then import the setup file:

```bash
SOURCE sway_db_setup.sql;
```

Or manually run:

```sql
CREATE TABLE users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE food_entries (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL,
    food_name  VARCHAR(100) NOT NULL,
    calories   INT NOT NULL,
    entry_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

### 3️⃣ Add Users

```sql
INSERT INTO users (username, password) VALUES ('yourname', 'yourpassword');
```

---

## 📦 Requirements

- Java JDK 17+
- MySQL Server
- MySQL Connector/J (JDBC Driver)

JDBC driver should be placed at:
```
lib/mysql-connector-j.jar
```

---

## 🛠️ Compile the Project

### ✅ Windows
```bash
javac -cp "lib/mysql-connector-j.jar;src" src/*.java
```

### ✅ Linux / macOS
```bash
javac -cp "lib/mysql-connector-j.jar:src" src/*.java
```

---

## ▶️ Run the Application

### Windows
```bash
java -cp "lib/mysql-connector-j.jar;src" LoginScreen
```

### Linux / macOS
```bash
java -cp "lib/mysql-connector-j.jar:src" LoginScreen
```

---

## ⚠️ Important Notes

- MySQL server must be running before launching the app
- JDBC driver must be included in the classpath
- Update database credentials in `src/DBConnection.java`

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/sway_db",
    "root",         // your MySQL username
    "yourpassword"  // your MySQL password
);
```

---

## 🧩 Key Classes

### 🔹 LoginScreen
Handles user authentication. Fetches `user_id` and `username` from the database and passes them to all subsequent screens.

### 🔹 HomeScreen
Main navigation hub. Displays the logged-in user's name and provides access to all modules. Includes a Logout button.

### 🔹 FoodEntry
Form to add food items. Saves entries with the logged-in user's `user_id` so data stays private per account.

### 🔹 ManageEntry
Navigation screen for Update and Delete operations.

### 🔹 UpdateEntry
Allows editing the calorie value of an existing food entry. Only updates entries belonging to the current user.

### 🔹 DeleteEntry
Removes a food entry by name. Only deletes entries belonging to the current user.

### 🔹 ReportScreen
Displays a table of food entries for a selected date. Filters by `user_id` and shows the total calorie count at the bottom.

### 🔹 DBConnection
Utility class that handles MySQL database connection using JDBC `DriverManager`.

---

## 🎓 Concepts Demonstrated

- Java Swing GUI Development
- User Authentication & Session Passing
- Event Delegation Model
- GridBagLayout for Form Alignment
- JDBC PreparedStatement with parameterized queries
- Foreign Key Relationships in MySQL
- Per-user Data Isolation
- Modular Screen Design

---

## 🎧 Event Listeners Used

| Listener       | Usage                              |
|----------------|------------------------------------|
| ActionListener | Button clicks & Enter key on fields|
| MouseAdapter   | Button hover styling               |
| FocusAdapter   | Input field focus highlight        |

---

## 👨‍💻 Authors

- **[Your Name]**
- **[Teammate Name]**

---

## 📜 License

This project is created for educational and learning purposes.

---

## ⭐ Future Improvements

- Password hashing for secure authentication
- Edit food entry feature
- Weekly & monthly calorie analytics
- Dark mode UI
- Export reports to PDF
- Calorie goal setting per user
- BMI calculator integration
