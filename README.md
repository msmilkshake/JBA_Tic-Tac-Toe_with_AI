<h2 style="text-align: center;">Stage 3/5: Watch 'em fight</h2>

<h2 style="text-align: center;">Description</h2>

<p>It is time to make some variations of the game possible. What if you want to play with a friend and not with AI? What if you get tired of playing the game and want to see a match between two AI? Finally, you need to be able to play either the first move or the second move playing against AI.</p>

<p>Write a menu loop, which can interpret two commands: "start" and "exit".</p>

<p>The command "start" should take two parameters: who will play ‘X’ and who will play ‘O.’ Two parameters are possible for now: "user" to play as a human and "easy" to play as an easy level AI. In the next steps, you will add "medium" and "hard" parameters.</p>

<p>The command "exit" should simply terminate the program.</p>

<p>Do not forget to handle incorrect input!</p>

<h2 style="text-align: center;">Example</h2>

<p>The example below shows how your program should work.</p>

<pre><code class="language-no-highlight">Input command: start
Bad parameters!
Input command: start easy
Bad parameters!
Input command: start easy easy
---------
|       |
|       |
|       |
---------
Making move level "easy"
---------
|       |
|     X |
|       |
---------
Making move level "easy"
---------
|       |
| O   X |
|       |
---------
Making move level "easy"
---------
|       |
| O   X |
|     X |
---------
Making move level "easy"
---------
|       |
| O   X |
|   O X |
---------
Making move level "easy"
---------
|       |
| O X X |
|   O X |
---------
Making move level "easy"
---------
|     O |
| O X X |
|   O X |
---------
Making move level "easy"
---------
| X   O |
| O X X |
|   O X |
---------
X wins

Input command: start easy user
---------
|       |
|       |
|       |
---------
Making move level "easy"
---------
|       |
|       |
|     X |
---------
Enter the coordinates: 2 2
---------
|       |
|   O   |
|     X |
---------
Making move level "easy"
---------
|   X   |
|   O   |
|     X |
---------
Enter the coordinates: 1 1
---------
|   X   |
|   O   |
| O   X |
---------
Making move level "easy"
---------
|   X X |
|   O   |
| O   X |
---------
Enter the coordinates: 3 2
---------
|   X X |
|   O O |
| O   X |
---------
Making move level "easy"
---------
| X X X |
|   O O |
| O   X |
---------
X wins

Input command: start user user
---------
|       |
|       |
|       |
---------
Enter the coordinates: 1 1
---------
|       |
|       |
| X     |
---------
Enter the coordinates: 2 2
---------
|       |
|   O   |
| X     |
---------
Enter the coordinates: 1 2
---------
|       |
| X O   |
| X     |
---------
Enter the coordinates: 2 1
---------
|       |
| X O   |
| X O   |
---------
Enter the coordinates: 1 3
---------
| X     |
| X O   |
| X O   |
---------
X wins

Input command: exit</code></pre>