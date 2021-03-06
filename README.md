# To Run
Requirements:

1. Command Prompt or Terminal
2. Apache Maven installed and exported to Path
3. JUnit installed with Eclipse
4. Java JDK 1.8 or greater

### Building / JUnits
  To run only JUnits, import project into Eclipse and ensure JUnit is installed
  
  Navigate to PieceValidatorTest, right-click and Run As > Junit Test

  In root of project,  ```mvn clean install site```
  
  This will output a jar file to the target/ folder of the project and run JUnits with compilation
### Running
  Navigate to target/ folder of project and perform ```java -jar /path/to/jar/```
  
  
# Chess
The objective of this project is to take in a stringed chess anotation of given pieces for both White and Black pieces.

(For Example):

 1. Black Positions
    
    Ka8, Rb8, Rc7, Pa7, Pb6, Pc5, Bd6*
    
 2. White Positions
  
    Ka1, Qa3, Pa4, Pb3, Bd4, Pc3, Pd3, Nc4*

These inputs would generate:

Ka8  Rb8  c8  d8  e8  f8  g8  h8

Pa7  b7  Rc7  d7  e7  f7  g7  h7

a6  Pb6  c6  Bd6  e6  f6  g6  h6

a5  b5  Pc5  d5  e5  f5  g5  h5

Pa4  b4  Nc4  Bd4  e4  f4  g4  h4

Qa3  Pb3  Pc3  Pd3  e3  f3  g3  h3

a2   b2   c2   d2   e2   f2   g2   h2

Ka1  b1  c1  d1  e1  f1  g1  h1

# Data Types

### Tile

Attributes:

```java
int row;
char col;
Piece p;
```
Methods:
```java
@Override
equals - for example, used for checking if target tile is same as piece to move
@Override
hashCode
```

### Piece

Attributes:

```java
PieceEnum pe;
ColorEnum color;
```

# Board Management

### BoardFactory

Attributes:

```java
ArrayList<Tile> board;
```

Methods:
```java
resetBoard()
generate(List<Tile> pieces)
```

### BoardInput

Methods:
```java
static void inputWhitePieces(List<Tile> pieces)
static void inputBlackPieces(List<Tile> pieces)
static void getNextMove(List<Tile> pieces)
```

### BoardController

Attributes:
```java
BoardFactory bf
```

Methods:
```java
play()
```
# Testing Procedures
*Used the same inputs previously stated as an example

Utilized JUnit and Mockito to ascertain the return values of the following methods in PieceValidator.java:
```java
  validateMove
  //Provides Validation for total expected output for all possible pieces
  getMoves  
  //The following test cases verify the validation methodologies 
  //employed for each type of piece. Combined test output validation with real chess board
  validatePawnMove
  validateKingMove
  validateQueenMove
  validateRookMove
  validateBishopMove
  validateKnightMove
```
