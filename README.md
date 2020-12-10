<h2 style="text-align: center;">Stage 2/5: Easy does it</h2>

<h2 style="text-align: center;">Description</h2>

<p>Now it is time to make a working game. In this version of the program, the user will be playing as X, and the "easy" level computer will be playing as O. </p>

<p>Let's make it so that at this level the computer will make random moves. This level will be perfect for those who play this game for the first time! Well, though... You can create a level of difficulty that will always give in and never win the game. You can implement such a level along with "easy" level, if you want, but it will not be tested.</p>

<p>When starting the program, an empty field should be displayed and the first to start the game should be the user as X. Next the computer should make its move as O. And so on until someone will win or there will be a draw.</p>

<p>At the very end of the game you need to print the final result of the game.</p>

<h2 style="text-align: center;">Example</h2>

<p>The example below shows how your program should work.</p>

<pre><code class="language-no-highlight">---------
|       |
|       |
|       |
---------
Enter the coordinates: 2 2
---------
|       |
|   X   |
|       |
---------
Making move level "easy"
---------
| O     |
|   X   |
|       |
---------
Enter the coordinates: 3 1
---------
| O     |
|   X   |
|     X |
---------
Making move level "easy"
---------
| O     |
| O X   |
|     X |
---------
Enter the coordinates: 1 1
---------
| O     |
| O X   |
| X   X |
---------
Making move level "easy"
---------
| O     |
| O X O |
| X   X |
---------
Enter the coordinates: 2 1
---------
| O     |
| O X O |
| X X X |
---------
X wins</code></pre>