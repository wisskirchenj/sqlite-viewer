# IDEA EDU Course

Implemented in the <b>Java Desktop Application Developer</b> Track of hyperskill.org JetBrain Academy.  

Project goal is to further practice graphical Java application design with event threading and
listener / observer techniques; especially using a JTable in an application. Moreover, the growing
app may serve as a useful database inspection tool.

## Technology / External Libraries

- Java 19
- Swing
- Lombok
- Unit Tests with Junit-Jupiter and Mockito
- Mockito MockStatic and MockAppender for Log4J in Unit-Tests
- Gradle 8.0

## Program description

A SWING application that connects to a SQLite database to extract the data into a JTable.

Have fun!

## Project completion

Project was completed on 24.02.23.

## Repository Contents

Sources for all project tasks (4 stages) with tests and configurations.

## Progress

06.02.23 Project started. Setup of build and repo with gradle on Kotlin basis.

13.02.23 Stage 1 completed. Setup and layout of JFrame with file entry field and open button.

20.02.23 Stage 2 completed. Connect to database given by user entry and populate combo box with the tables contained
in that database. When a table in the combo box is selected, the query text area is prepopulated with a query for this
table.

21.02.23 Stage 3 completed. Implement Execution of arbitrary queries for any table in a connected database. Add Jtable
with DefaultTableModel filled by the DbAdapter on Execute button action.

24.02.23 Final Stage 4 completed. Implement JOptionPanes in error cases, open databases readonly to avoid Database SQL
injection, use MockAppender to assert on Logging Events and mockito MockStatic to mock away blocking JOptionPane dialog.
