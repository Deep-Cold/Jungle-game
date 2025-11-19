# User Manual

## 1. Overview
Jungle Game is a turn-based strategy game played through a console interface. Players interact exclusively via text commands. Every action is triggered by typing a command and pressing Enter. This manual explains all accepted commands, their syntax, expected outputs, and how to handle invalid inputs so you can play confidently.

## 2. Launching the Program
Download the executable `JungleGame.jar` (located in the project root). Place it in a empty folder. Run the following command inside a terminal opened in the same directory:
```
java -jar JungleGame.jar
```
The console displays:
```
Welcome to Jungle Game v1.0!

Main Menu
> 
```
All subsequent commands must be typed at this prompt (`>`). Commands are case-insensitive unless otherwise noted.

## 3. Main Menu Commands
| Command        | Format           | Description                                                                                               |
|----------------|------------------|-----------------------------------------------------------------------------------------------------------|
| `startNewGame` | `startNewGame`   | Starts a fresh game with the default board, pieces, and rules.                                            |
| `loadGame`     | `loadGame`       | Opens the archive selector so you can continue a previously saved game.                                   |
| `watchReplay`  | `watchReplay`    | Opens the replay viewer, allowing you to step through recorded matches.                                   |
| `exit`         | `exit`           | Quits the program immediately.                                                                            |

- **Invalid command behavior**: If the input is empty or unrecognized, the program responds with `Invalid command` and reprints the prompt without changing game state.
- **Too many parameters**: If you accidentally add extra tokens (e.g., `startNewGame now`), the program responds with `Too many arguments, please enter again`.

## 4. In-Game Commands
After starting or loading a game, you enter gameplay mode. The prompt indicates whose turn it is, and you must use the commands below.

### 4.1 Movement Commands
Use the following format to move a piece:
```
PIECENAME DIRECTION
```
- **PIECENAME** must be one of: `rat`, `cat`, `dog`, `wolf`, `leopard`, `tiger`, `lion`, `elephant`.
- **DIRECTION** must be one of: `U` (up), `D` (down), `L` (left), `R` (right).
Example: `tiger R`

If the move is legal, the board updates and a message like `tiger : (A, 1) -> (A, 2)` prints. If the move is illegal (e.g., moving off-board, entering river illegally, violating trap rules), the game prints the reason such as `You can not place move this piece to a river matrix!`, `Your move out of the board!`, or `You can not capture target piece!`. The turn does not advance until a legal move is made.

### 4.2 Withdraw Command
`withdraw`

Undoes the last move(s) according to rule limits (each player may withdraw up to three times). The console confirms with `lowerPlayer +Withdraw`.

Note that you can only do this command on your own turn.

### 4.3 Saving
`saveGame filename`

- Saves the current match under `archive/filename.jungle`.

`saveReplay filename`

- Saves the current replay buffer to `replay/filename.replay`.

**Note**

- Filenames should be alphanumeric without spaces, you may also use `-`.
- Successful saves overwrite existing files with the same name. Invalid filenames result in an explanatory error.


### 4.5 Closing a Game Session Without Saving
`close` Returns to the main menu without saving. Unsaved progress is lost.

## 5. Replays
During `watchReplay`, commands appear at the replay prompt:
- `next`: advance to the next recorded state.
- `prev`: go back one state.
- `exit`: leave replay mode and return to the menu.

## 6. Loading Games and Replays
When `loadGame` or `watchReplay` is issued, the system lists available files. You typically navigate by entering the name shown. If a file is missing or corrupted, the game prints an error (e.g., `Cannot load archive`). After a successful load, gameplay or replay mode begins immediately, following the same command rules listed earlier.

## 7. Output Interpretation
- **Move Logs**: Lines such as `rat : (G, 3) -> (F, 3)` indicate piece, origin coordinate, and destination. Symbols `+` or `-` may appear to show piece placement/removal after specific moves.
- **Validation Messages**: The engine prints descriptive error strings whenever a rule is violated. Treat these as guidance for legal moves.
- **Withdraw Notices**: `lowerPlayer +Withdraw` indicates a withdrawal by the lower-side player; `-Withdraw` indicates the action has been revoked, which only happens in replay.
- **Endgame**: When a player wins (e.g., by entering the opponent’s den), the console prints the winner (`upperPlayer`, `lowerPlayer`) and exits to the main menu. The system will automatically generate the replay file for you.

## 8. Handling Invalid Input
- Empty input or whitespace is ignored.
- Unknown commands yield `Invalid command`.
- Commands with incorrect token counts (extra parameters) trigger `Too many arguments` warnings.
- Illegal in-game moves display specific rule explanations without advancing the turn.

## 9. Best Practices for Effective Play
1. **Plan Moves**: Remember piece strengths (elephant beats all except rats, rats can attack elephants only from adjacent squares or in rivers).
2. **Use Withdraw Sparingly**: Each player has 3 withdraws. Track usage carefully.
3. **Replay for Learning**: `watchReplay` helps analyze games. Combine `next`/`prev` commands to review tactics and mistakes.

## 10. Troubleshooting
- **No Response After Command**: Ensure you pressed Enter.  The program might be waiting for further input if the prompt (`>`) doesn't reappear.
- **Repeated Error Messages**: Read the exact message (e.g., “You can not move in your den”) and adjust moves accordingly.
- **Corrupted Save/Replays**: Delete the problematic file from `archive/` or `replay/` and create a fresh save.

