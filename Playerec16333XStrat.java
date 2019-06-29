/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author Kee
 */
public class Playerec16333XStrat extends GomokuPlayer {

//    public static void main(String[] args) {
//        Color[][] board = new Color[8][8];
//        Playerec16333MiniMaxAB player = new Playerec16333MiniMaxAB();
//        Integer[] OccupiedSquares = player.getOccupiedSquares(board);
//        for (int i = 0; i < 128 && OccupiedSquares[i] != null; i = i + 2) {
//            System.out.println(board[OccupiedSquares[i]][OccupiedSquares[i + 1]]);
//        }
//        System.out.println("Done First board");
//        Integer[] bestPosition = player.MINIMAXMaxPlayer(board, Color.white,Color.black, -100, 100, 0);
//        //   int Utility = player.getBoardUtility(player.MakeMove(board, Color.white, bestPosition[0], bestPosition[1]), Color.white, Color.black);
//        Integer[] OccupiedSquares2 = player.getOccupiedSquares(board);
//        for (int i = 0; i < 128 && OccupiedSquares2[i] != null; i = i + 2) {
//            System.out.println(board[OccupiedSquares2[i]][OccupiedSquares2[i + 1]]);
//        }
//        int Utility = player.getBoardUtility(player.MakeMove(board, Color.white, 0, 1), Color.white, Color.black);
//        int Utility2 = player.getBoardUtility(player.MakeMove(board, Color.white, 0, 1), Color.white, Color.black);
//        int Utility3 = player.getBoardUtility(player.MakeMove(board, Color.white, 0, 1), Color.white, Color.black);
//        System.out.println("Board Utility:" + Utility);
//        System.out.println("Board Utility2:" + Utility2);
//        System.out.println("Board Utility3:" + Utility3);
////        System.out.println("BEST POSITIONS:");
////        System.out.println(bestPosition[0]);
////        System.out.println(bestPosition[1]);
////        System.out.println("UTILITY:");
////        System.out.println(bestPosition[2]);
//
//    }

    public Color[][] getBoardCopy(Color[][] board) {
        Color[][] newBoard = new Color[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public boolean isGameDone(Color[][] board, Color me) {

        Integer[] occupiedSquares = getOccupiedSquares(board);

        if (occupiedSquares[127] != null) { // if board is fully occupied
            return true;
        }

        int j = 0;
        int length = occupiedSquares.length;
        while ((j < length) && (occupiedSquares[j] != null)) {
            //Initialise row and column
            int r = occupiedSquares[j];
            int c = occupiedSquares[j + 1];
            //Initialise stone counter for each color
            int meCount = 0;
            int themCount = 0;

            for (int l = 0, h = 4; h >= 0; l--, h--) {
                for (int i = l; i <= h; i++) {
                    if (((r + i) < 0) || ((r + i) > 7)) {
                        break;
                    }
                    if (board[r + i][c] == null) {
                        break;
                    } else if ((themCount > 0) && (meCount > 0)) {
                        break;
                    } else if (board[r + i][c] == me) {
                        meCount = meCount + 1;
                    } else {
                        themCount = themCount + 1;
                    }
                }
                if (themCount == 5 || meCount == 5) {
                    //  System.out.println("Winner Reached");
                    return true;
                }
                meCount = 0;
                themCount = 0;
            }

//      SQUARES B and F
//      for each position from r+2,c-2 to r-2,c+2
            for (int l = 0, h = 4; h >= 0; l--, h--) {
                for (int i = l; i <= h; i++) {
                    if ((r - i) < 0 || (r - i) > 7 || (c + i) > 7 || (c + i) < 0) {
                        break;
                    } else if (board[r - i][c + i] == null) {
                        break;
                    } else if ((themCount > 0) && (meCount > 0)) {
                        break;
                    } else if (board[r - i][c + i] == me) {
                        meCount = meCount + 1;
                    } else {
                        themCount = themCount + 1;
                    }
                }
                if (themCount == 5 || meCount == 5) {
                    return true;
                }
                meCount = 0;
                themCount = 0;
            }

//      SQUARES C and G
//            for each position from r,c-2 to r,c+2
            for (int l = 0, h = 4; h >= 0; l--, h--) {
                for (int i = l; i <= h; i++) {
                    if ((c + i) > 7 || (c + i) < 0) {
                        break;
                    } else if (board[r][c + i] == null) {
                        break;
                    } else if ((themCount > 0) && (meCount > 0)) {
                        break;
                    } else if (board[r][c + i] == me) {
                        meCount = meCount + 1;
                    } else {
                        themCount = themCount + 1;
                    }
                }
                if (themCount == 5 || meCount == 5) {
                    return true;
                }
                meCount = 0;
                themCount = 0;
            }

//      SQUARES D and h
//      for each position from r-2,c-2 to r+2,c+2
            for (int l = 0, h = 4; h >= 0; l--, h--) {
                for (int i = l; i <= h; i++) {
                    if ((r + i) > 7 || (r + i) < 0 || (c + i) > 7 || (c + i) < 0) {
                        break;
                    } else if (board[r + i][c + i] == null) {
                        break;
                    } else if ((themCount > 0) && (meCount > 0)) {
                        break;
                    } else if (board[r + i][c + i] == me) {
                        meCount = meCount + 1;
                    } else {
                        themCount = themCount + 1;
                    }
                }
                if (themCount == 5 || meCount == 5) {
                    return true;
                }
                meCount = 0;
                themCount = 0;
            }
            j = j + 2;
        }
//        System.out.println("IsGameDoneEndsFALSE");
        return false;
    }

    public Integer[] MINIMAXMaxPlayer(Color[][] board, Color me,Color them, int a, int b, int depth) {
        depth = depth + 1;
        //  System.out.println("MAXPLAYER" + a + "" + b);
        Integer[] results = new Integer[3];
        results[0] = null;
        results[1] = null;
        results[2] = getBoardUtility(board, me, them);
        if (depth == 4) {
            //     System.out.println("CurrentUtility:"+results[2]);
            return results;
        }
        if (isGameDone(board, me)) {
            // System.out.println("MaxUtility of a DONE board");
            //  System.out.println(results[2]);
            return results;
        }

        Integer[] best = new Integer[3];
        best[0] = null;
        best[1] = null;
        best[2] = -100;
        Integer[] freeSquares = getFreeSquares(board);

        int value;

        int i = 0;
        int length = freeSquares.length;
        while ((i < length) && (freeSquares[i] != null)) {
            Color[][] boardCopy = getBoardCopy(board);
            Integer[] ResultArray = MINIMAXMinPlayer(MakeMove(boardCopy, me, freeSquares[i], freeSquares[i + 1]), me,them, a, b, depth);
            value = ResultArray[2];
//            System.out.println("Max Player Round i:" + i + " value:" + value + " Best So far:" + best[2] + " Position:" + freeSquares[i] + "," + freeSquares[i + 1]);
//            System.out.println("BestPosition:" + best[0] + " " + best[1]);
//            System.out.println("a:"+a+" b:"+b);
            if (value > best[2]) {
//                System.out.println("Max Player Round i:" + i + " value:" + value + " Best So far:" + best[2] + " Position:" + freeSquares[i] + "," + freeSquares[i + 1]);
//                System.out.println("BestPosition:" + best[0] + " " + best[1]);
                best[0] = freeSquares[i];
                best[1] = freeSquares[i + 1];
                best[2] = value;
            }
            if (value >= b) {
                Integer[] nullReturn = new Integer[3];
                nullReturn[0] = null;
                nullReturn[1] = null;
                nullReturn[2] = value;
                return nullReturn;
            }
            if (value > a) {
                a = value;
            }
            i = i + 2;
        }
//        System.out.println("Returning best:" + best[0] + "," + best[1] + " Val:" + best[2] + " Depth:" + depth);
        return best;
    }

    public Integer[] MINIMAXMinPlayer(Color[][] board, Color me, Color them, int a, int b, int depth) {

        depth = depth + 1;
        //  System.out.println("MinPLAYER" + a + "" + b);
        Integer[] results = new Integer[3];
        results[0] = null;
        results[1] = null;
        results[2] = getBoardUtility(board, me, them);
        if (depth == 4) {
            // System.out.println("CurrentUtility:"+results[2]);
            return results;
        }
        if (isGameDone(board, me)) {
            //  System.out.println("Min Utility of a DONE BOARD");
            //   System.out.println(results[2]);
            return results;
        }

        Integer[] best = new Integer[3];
        Integer[] freeSquares = getFreeSquares(board);

        best[0] = null;
        best[1] = null;
        best[2] = 100;
        int value;

        int i = 0;
        int length = freeSquares.length;
        while (i < length && freeSquares[i] != null) {
            Color[][] boardCopy = getBoardCopy(board);
            Integer[] ResultArray = MINIMAXMaxPlayer(MakeMove(boardCopy, them, freeSquares[i], freeSquares[i + 1]), me,them, a, b, depth);
            value = ResultArray[2];
            if (value < best[2]) {
                best[0] = freeSquares[i];
                best[1] = freeSquares[i + 1];
                best[2] = value;
            }
            if (value <= a) {
                Integer[] nullReturn = new Integer[3];
                nullReturn[0] = null;
                nullReturn[1] = null;
                nullReturn[2] = value;
                return nullReturn;
            }
            if (value < b) {
                b = value;
            }
            i = i + 2;
        }
        return best;
    }

    public Color[][] MakeMove(Color[][] board, Color color, int c, int r) {
        board[c][r] = color;
        return board;
    }

    public int getBoardUtility(Color[][] board, Color me, Color them) {
        Integer[] OccupiedSquares = getOccupiedSquares(board);
        //   System.out.println("Err2");
        int[] bestPair;
        int BestMaxScore = 0;
        int BestMinScore = 0;
        int i = 0;
        if (OccupiedSquares[0] == null) {
            return 0;
        }
//        System.out.println("Entering getUtilityScoreMax loop");
//        System.out.println("freesquares length=" + freeSquares.length);
//        System.out.println("freeSquares[i]=" + freeSquares[i]);

        while ((i < 128) && (OccupiedSquares[i] != null)) {
            //   System.out.println("InsideLoop i=" + i);
            bestPair = getUtilityScoreBest(board, me, them, OccupiedSquares[i], OccupiedSquares[i + 1]);
            // System.out.println("Current bestPair: Max:" + bestPair[0]+" Min:"+bestPair[1]);
            //   System.out.println("Err3 " + i);
            if (bestPair[0] > BestMaxScore) {
                BestMaxScore = bestPair[0];
            }
            if (bestPair[1] < BestMinScore) {
                BestMinScore = bestPair[1];
            }
            i = i + 2;
        }
        //  System.out.println("BestMax:" + BestMaxScore + " BestMin:" + BestMinScore);
        // Returns utility score of board.
        return BestMaxScore + BestMinScore;
    }

    public Color[] nullArray(Color[] squares) {
        int length = squares.length;
        for (int i = 0; i < length; i++) {
            squares[i] = null;
        }
        return squares;
    }

    public int[] getUtilityScoreBest(Color[][] board, Color me, Color them, int r, int c) {
        //think more..
        /*
      Utility function
      Pass move (new Board State and color).
      Return utility score for that move.
      
                Psuedcode. Lets squares around position be A,B,C,D,E,F,G,H
   H    A    B
   G  (r,c)  C
   F    E    D
        Will check every line of 4 squares from current position and give a score
        according to how many of our peices/nulls/adversery peices there are.
        Maximum score is returned for each line.
         */
        //   int topScore = 0; //Top score so far

        int maxBest = 0;
        int minBest = 0;
        int mine = 0; // Counter for my chips
        int theirs = 0; //Counter for adversery chips
        Color[] SquaresOf5 = new Color[5]; //line of 5 squares
        Color[] XStrategyMine = new Color[]{null, me, me, me, null};
        Color[] XStrategyThem = new Color[]{null, them, them, them, null};

        int XCounterMineAEGC = 0; //Counters for X Shape in AE or GC
        int XCounterThemAEGC = 0;

        int XCounterMineBFHD = 0;
        int XCounterThemBFHD = 0;

        int scoreA = 0;
        int maxA = 0;
        int minA = 0;
//      SQUARES A and E
//      for each position from (r+2,c) to (r-2,c)
        for (int l = 0, h = 4; h >= 0; l--, h--) {
            for (int i = l, square = 0; i <= h; i++, square++) {
                if ((r + i) < 0 || (r + i) > 7) {
                    scoreA = 0;
                    break;
                } else if (board[r + i][c] == me) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = me;
                    }
                    mine = mine + 1;
                    scoreA = scoreA + 2;
                } else if (board[r + i][c] == null) {
                    // scoreA = scoreA + 1;
                } else if (board[r + i][c] == them) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = them;
                    }
                    theirs = theirs + 1;
                    scoreA = scoreA - 2;
                }
            }
            //   System.out.println("ScoreA:"+scoreA);
            if (theirs > 4) {
                scoreA = scoreA - 50;
            } else if (mine > 4) {
                scoreA = scoreA + 50;
            } else if (l == -2) { // Only make XStrategy check when l==-2, Save computation
                if (Arrays.equals(SquaresOf5, XStrategyMine)) {
                    XCounterMineAEGC = XCounterMineAEGC + 1;
                } else if (Arrays.equals(SquaresOf5, XStrategyThem)) {
                    XCounterThemAEGC = XCounterThemAEGC + 1;
                }
            }
            if ((theirs == 3) && (mine == 1)) { //Block from making a 4
                scoreA = 19;
            } else if ((mine == 3) && (theirs == 1)) {
                scoreA = - 19;
            }
            if ((theirs > 1) && (mine > 1)) { //if there is more than 1 of each color in a line of 5 then pass
                //pass
            } else if (scoreA > maxA) {
                maxA = scoreA;
            } else if (scoreA < minA) {
                minA = scoreA;
            }
            //Reset counters
            scoreA = 0;
            theirs = 0;
            mine = 0;
        }
        if (maxA > maxBest) {
            maxBest = maxA;
        }
        if (minA < minBest) {
            minBest = minA;
        }
        SquaresOf5 = nullArray(SquaresOf5); // Clear line of 5 squares

        int scoreB = 0;
        int maxB = 0;
        int minB = 0;
//      SQUARES B and F
//      for each position from r+2,c-2 to r-2,c+2
        for (int l = 0, h = 4; h >= 0; l--, h--) {
            for (int i = l, square = 0; i <= h; i++, square++) {
                if ((r - i) < 0 || (r - i) > 7 || (c + i) > 7 || (c + i) < 0) {
                    scoreB = 0;
                    break;
                    // scoreB = scoreB - 8;
                } else if (board[r - i][c + i] == me) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = me;
                    }
                    mine = mine + 1;
                    scoreB = scoreB + 2;
                } else if (board[r - i][c + i] == null) {
                    //   scoreB = scoreB + 1;
                } else if (board[r - i][c + i] == them) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = them;
                    }
                    theirs = theirs + 1;
                    scoreB = scoreB - 2;
                }
            }
            if (theirs > 4) {
                scoreB = scoreB - 50;
            } else if (mine > 4) {
                scoreB = scoreB + 50;
            } else if (l == -2) { // Only make XStrategy check when l==-2, Save computation
                if (Arrays.equals(SquaresOf5, XStrategyMine)) {
                    XCounterMineBFHD = XCounterMineBFHD + 1;
                } else if (Arrays.equals(SquaresOf5, XStrategyThem)) {
                    XCounterThemBFHD = XCounterThemBFHD + 1;
                }
            }
            if ((theirs == 3) && (mine == 1)) {
                scoreB = 19;
            } else if ((mine == 3) && (theirs == 1)) {
                scoreB = - 19;
            }
            if ((theirs > 1) && (mine > 1)) { //if there is more than 1 of each color in a line of 5 then pass
                //pass
            } else if (scoreB > maxB) {
                maxB = scoreB;
            } else if (scoreB < minB) {
                minB = scoreB;
            }
            //Reset counters
            scoreB = 0;
            theirs = 0;
            mine = 0;
        }
        if (maxB > maxBest) {
            maxBest = maxB;
        }
        if (minB < minBest) {
            minBest = minB;
        }

        SquaresOf5 = nullArray(SquaresOf5); // Clear line of 5 squares

        int scoreC = 0;
        int maxC = 0;
        int minC = 0;
//      SQUARES C and G
//            for each position from r,c-2 to r,c+2
        for (int l = 0, h = 4; h >= 0; l--, h--) {
            for (int i = l, square = 0; i <= h; i++, square++) {
                if ((c + i) > 7 || (c + i) < 0) {
                    //     scoreC = scoreC - 8;
                    scoreC = 0;
                    break;
                } else if (board[r][c + i] == me) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = me;
                    }
                    mine = mine + 1;
                    scoreC = scoreC + 2;
                } else if (board[r][c + i] == null) {
                    //   scoreC = scoreC + 1;
                } else if (board[r][c + i] == them) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = them;
                    }
                    theirs = theirs + 1;
                    scoreC = scoreC - 2;
                }
            }
            if (theirs > 4) {
                scoreC = scoreC - 50;
            } else if (mine > 4) {
                scoreC = scoreC + 50;
            } else if (l == -2) { // Only make XStrategy check when l==-2, Save computation
                if (Arrays.equals(SquaresOf5, XStrategyMine)) {
                    XCounterMineAEGC = XCounterMineAEGC + 1;
                } else if (Arrays.equals(SquaresOf5, XStrategyThem)) {
                    XCounterThemAEGC = XCounterThemAEGC + 1;
                }
            }
            if ((theirs == 3) && (mine == 1)) {
                scoreC = 19;
            } else if ((mine == 3) && (theirs == 1)) {
                scoreC = - 19;
            }
            if ((theirs > 1) && (mine > 1)) { //if there is more than 1 of each color in a line of 5 then pass
                //pass
            } else if (scoreC > maxC) {
                maxC = scoreC;
            } else if (scoreC < minC) {
                minC = scoreC;
            }
            //Reset counters
            scoreC = 0;
            theirs = 0;
            mine = 0;
        }
        if (maxC > maxBest) {
            maxBest = maxC;
        }
        if (minC < minBest) {
            minBest = minC;
        }
        SquaresOf5 = nullArray(SquaresOf5); // Clear line of 5 squares

        int scoreD = 0;
        int maxD = 0;
        int minD = 0;
//      SQUARES D and h
//      for each position from r-2,c-2 to r+2,c+2
        for (int l = 0, h = 4; h >= 0; l--, h--) {
            for (int i = l, square = 0; i <= h; i++, square++) {
                if ((r + i) > 7 || (r + i) < 0 || (c + i) > 7 || (c + i) < 0) {
                    scoreD = 0;
                    break;
                    //        scoreD = scoreD - 8;
                } else if (board[r + i][c + i] == me) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = me;
                    }
                    mine = mine + 1;
                    scoreD = scoreD + 2;
                } else if (board[r + i][c + i] == null) {
                    //   scoreD = scoreD + 1;
                } else if (board[r + i][c + i] == them) {
                    if (l == -2) { //Check for X shape only when checking 2 above and 2 below r,c
                        SquaresOf5[square] = them;
                    }
                    theirs = theirs + 1;
                    scoreD = scoreD - 2;
                }
            }
            if (l == -2) { // Only make XStrategy check when l==-2, Save computation
                if (Arrays.equals(SquaresOf5, XStrategyMine)) {
                    XCounterMineBFHD = XCounterMineBFHD + 1;
                } else if (Arrays.equals(SquaresOf5, XStrategyThem)) {
                    XCounterThemBFHD = XCounterThemBFHD + 1;
                }
            }
            if (theirs > 4) {
                scoreD = scoreD - 50;
            } else if (mine > 4) {
                scoreD = scoreD + 50;
            } else if ((XCounterMineBFHD > 1) || (XCounterMineAEGC > 1)) {
                scoreD = 30;
                XCounterMineBFHD = 0;
                XCounterMineAEGC = 0;
            } else if ((XCounterThemBFHD > 1) || (XCounterThemAEGC > 1)) {
                scoreD = -30;
                XCounterThemBFHD = 0;
                XCounterThemAEGC = 0;
            } else if ((theirs == 3) && (mine == 1)) {
                scoreD = 19;
            } else if ((mine == 3) && (theirs == 1)) {
                scoreD = - 19;
            } 
            if ((theirs > 1) && (mine > 1)) { //if there is more than 1 of each color in a line of 5 then pass
                //pass
            } else if (scoreD > maxD) {
                maxD = scoreD;
            } else if (scoreD < minD) {
                minD = scoreD;
            }
            //Reset counters
            scoreD = 0;
            mine = 0;
            theirs = 0;
        }
        if (maxD > maxBest) {
            maxBest = maxD;
        }
        if (minD < minBest) {
            minBest = minD;
        }
        //   System.out.println("maxBest:" + maxBest + " minBest:" + minBest);
        int[] bestPair = new int[2];
        bestPair[0] = maxBest;
        bestPair[1] = minBest;
        return bestPair;
    }

    public Integer[] getFreeSquares(Color[][] board) {

        Integer[] freeSquares = new Integer[128];
        int i = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == null) {
                    freeSquares[i] = r;
                    freeSquares[i + 1] = c;
                    i = i + 2;
                }
            }
        }
        return freeSquares;
    }

    public Integer[] getOccupiedSquares(Color[][] board) {

        Integer[] occupiedSquares = new Integer[128];
        int i = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    occupiedSquares[i] = r;
                    occupiedSquares[i + 1] = c;
                    i = i + 2;
                }
            }
        }
        return occupiedSquares;
    }

    public Move chooseMove(Color[][] board, Color me) {
        // System.out.println("Err1");
        Color them;
        if (me == Color.white) {
            them = Color.black;
        } else {
            them = Color.white;
        }

        Integer[] bestPosition = MINIMAXMaxPlayer(board, me,them, -100, 100, 0);
        int Utility = getBoardUtility(MakeMove(board, me, bestPosition[0], bestPosition[1]), me, them);
        System.out.println("Board Utility:" + Utility);
        System.out.println("DATAAAA-------");
        System.out.println(bestPosition[0]);
        System.out.println(bestPosition[1]);
        System.out.println(bestPosition[2]);
        return new Move(bestPosition[0], bestPosition[1]);

    }

}
