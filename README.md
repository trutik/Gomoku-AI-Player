# GomokuAIPlayer
A Gomoku player AI based on the Minimax algorithm, uses alpha-beta search to decide the best (time was limited to 10 seconds so search depth is limited) next move. The pseudo-code for the minimax algorithm was provided and I was required to implement it in java. I added recognition of the "X" shape as once it is created, the player who makes it has won so the X shape was given a high rating in the utility function. Therefore it is sought out by the AI and ,if the search depth is set high enough, it will prevent the other player from making the "X".   

Credits:
Minimax algorithm pseudo code from Simon Dixon's lecture slides.
