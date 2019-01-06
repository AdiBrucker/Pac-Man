package Model;

/**
 * Enum that holds the score of each type of answer
 */

public enum Scores {
    EASY_CORRECT,
    INTERMEDIATE_CORRECT,
    HARD_CORRECT,
    EASY_WRONG,
    INTERMEDIATE_WRONG,
    HARD_WRONG;

    int getScore(){
        switch (this){
            case EASY_CORRECT:
                return 100;
            case INTERMEDIATE_CORRECT:
                return 200;
            case HARD_CORRECT:
                return 500;
            case EASY_WRONG:
                return -250;
            case INTERMEDIATE_WRONG:
                return -100;
            case HARD_WRONG:
                return -50;
            default:
                return 0;
        }
    }
}
