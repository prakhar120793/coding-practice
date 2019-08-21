import java.util.Arrays;

public class RevealCardsIncreasingOrder {

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        return deckRevealedIncreasingRecursive(deck, true);
    }

    private int[] deckRevealedIncreasingRecursive(int[] deck, boolean revealFirst) {
        if (deck.length < 2) {
            return deck;
        }
        int mid;
        if(revealFirst) {
            mid = (int)Math.ceil(((double)deck.length) / 2.0);
        } else {
            mid = (int)Math.floor(((double)deck.length) / 2.0);
        }
        int[] rightHalf = new int[deck.length - mid];
        int counter = 0;
        for (int i = mid; i < deck.length; i++) {
            rightHalf[counter++] = deck[i];
        }
        int[] solutionFromRecursion;
        if(deck.length % 2 ==0 ){
            if(revealFirst) {
                solutionFromRecursion = deckRevealedIncreasingRecursive(rightHalf, true);
            } else {
                solutionFromRecursion = deckRevealedIncreasingRecursive(rightHalf, false);
            }
        } else {
            if(revealFirst){
                solutionFromRecursion = deckRevealedIncreasingRecursive(rightHalf, false);
            } else {
                solutionFromRecursion = deckRevealedIncreasingRecursive(rightHalf, true);
            }

        }

        int[] solution = new int[deck.length];
        int counter1=0, counter2=0;
        for (int i = 0; i < deck.length; i++) {
            if(revealFirst){
                if(i%2==0){
                    solution[i] = deck[counter1++];
                } else {
                    solution[i] = solutionFromRecursion[counter2++];
                }
            } else {
                if(i%2==0){
                    solution[i] = solutionFromRecursion[counter2++];
                } else {
                    solution[i] = deck[counter1++];
                }
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        int[] sol=new RevealCardsIncreasingOrder().deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7});
    }
}