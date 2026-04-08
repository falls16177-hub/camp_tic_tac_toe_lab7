# Lab 07B: Software Engineering Camp Part B: OOP Tic Tac Toe

This document contains the required software engineering artifacts for the OOP re-engineering of Tic Tac Toe.

## Candidate Class List
- App
- MainFrame
- BoardPanel
- TTTTileButton
- TTTGame
- TTTBoard
- Player
- GameStatus
- ~~ComputerPlayer~~
- ~~ScoreKeeper~~
- ~~PersistenceService~~
- ~~SoundPlayer~~

## CRC Cards

- Class: App
  - Responsibilities:
    - Start the Swing application and show the main window
  - Collaborators:
    - MainFrame

- Class: MainFrame
  - Responsibilities:
    - Application window; menu (New/Reset/Exit)
    - Owns the `BoardPanel`; displays status text
  - Collaborators:
    - BoardPanel, TTTGame

- Class: BoardPanel
  - Responsibilities:
    - Display a 3x3 grid of `TTTTileButton`
    - Handle tile clicks and delegate moves to `TTTGame`
    - Update status via `StatusListener`
  - Collaborators:
    - TTTGame, TTTTileButton, MainFrame

- Class: TTTTileButton
  - Responsibilities:
    - Represent a single board tile (row/col) as a button
    - Render the current mark (X/O) and manage enabled/disabled state
  - Collaborators:
    - BoardPanel, Player

- Class: TTTGame
  - Responsibilities:
    - Manage current player and turn switching
    - Apply moves to the board; expose game status and winner
    - Reset game state
  - Collaborators:
    - TTTBoard, Player, GameStatus

- Class: TTTBoard
  - Responsibilities:
    - Store board state (3x3 grid of marks)
    - Validate and place marks; determine wins/draws
    - Reset board
  - Collaborators:
    - Player, GameStatus

- Class: Player (enum)
  - Responsibilities:
    - Represent players X and O; provide mark and the opposite player
  - Collaborators:
    - TTTGame, TTTBoard

- Class: GameStatus (enum)
  - Responsibilities:
    - Represent IN_PROGRESS, X_WINS, O_WINS, DRAW
  - Collaborators:
    - TTTBoard, TTTGame

- ~~Class: ComputerPlayer~~
  - Responsibilities: Simple AI for single-player
  - Collaborators: TTTGame, TTTBoard
  - Culled: Not required by assignment

- ~~Class: ScoreKeeper~~
  - Responsibilities: Track wins/losses across games
  - Collaborators: TTTGame
  - Culled: Out of scope for this lab

- ~~Class: PersistenceService~~
  - Responsibilities: Save/load game state
  - Collaborators: TTTGame, TTTBoard
  - Culled: Out of scope

- ~~Class: SoundPlayer~~
  - Responsibilities: Play sounds on moves/wins
  - Collaborators: UI classes
  - Culled: Out of scope

## UML Diagram (Mermaid)

```mermaid
classDiagram
    class App {
        +main(String[]): void
    }
    class MainFrame {
        -JLabel statusLabel
        -TTTGame game
        -BoardPanel boardPanel
        +onStatusText(String): void
    }
    class BoardPanel {
        -TTTGame game
        -TTTTileButton[][] buttons
        +resetBoard(): void
    }
    class TTTTileButton {
        -int row
        -int col
        +setMark(Player): void
        +resetTile(): void
    }
    class TTTGame {
        -TTTBoard board
        -Player current
        +applyMove(int,int): boolean
        +reset(): void
        +currentPlayer(): Player
        +status(): GameStatus
        +winner(): Player?
    }
    class TTTBoard {
        -Player[][] cells
        +place(int,int,Player): boolean
        +winner(): Player?
        +status(): GameStatus
        +reset(): void
    }
    enum Player {
        X
        O
        +other(): Player
    }
    enum GameStatus {
        IN_PROGRESS
        X_WINS
        O_WINS
        DRAW
    }

    App --> MainFrame
    MainFrame o-- BoardPanel
    MainFrame --> TTTGame
    BoardPanel o-- TTTTileButton
    BoardPanel --> TTTGame
    TTTGame o-- TTTBoard
    TTTBoard --> Player
    TTTGame --> Player
    TTTBoard --> GameStatus
    TTTGame --> GameStatus
```

## Notes on Design Decisions
- We separated concerns using a light MVC approach:
  - Model: `TTTBoard`, `Player`, and `GameStatus` contain pure game logic/state.
  - Controller/State: `TTTGame` coordinates turns and exposes status.
  - View: `MainFrame`, `BoardPanel`, and `TTTTileButton` render UI and delegate to the controller.
- The `TTTTileButton` class is a simple, reusable Swing component that holds its board position and can display marks.
- Swing event handling remains thin; most logic lives in model/controller.

## How to Run (IntelliJ)
- Open the project in IntelliJ.
- Ensure you have a `src` root marked as Sources (IntelliJ usually does this automatically).
- Right-click `ttt.App` → Run 'App.main()'.
- The Tic Tac Toe window should appear.

## How to Test (Lightweight Runner)
- Right-click `ttt.TTTBoardTest` → Run 'TTTBoardTest.main()'.
- This runner prints basic pass/fail checks for board logic.

## Screenshots
- Insert your GUI screenshots here when you run the app.

