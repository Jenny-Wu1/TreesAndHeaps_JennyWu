import java.util.*;
public class Election {
    private Map<String, Integer> candidatesMap;
    private PriorityQueue<Map.Entry<String, Integer>> maxHeap;
    private int totalVotes;
    public void initializeCandidates(LinkedList<String> candidates, int p) {
        candidatesMap = new HashMap<>();
        for (String candidate : candidates) {
            candidatesMap.put(candidate, 0);
        }
        maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        totalVotes = p;
    }

    private void updateMaxHeap() {
        maxHeap.clear();
        maxHeap.addAll(candidatesMap.entrySet());
    }

    public void castVote(String candidate) {
        if (candidatesMap.containsKey(candidate)) {
            candidatesMap.put(candidate, candidatesMap.get(candidate) + 1);
            updateMaxHeap();
        }
    }

    public void castRandomVote() {
        Random gen = new Random();
        List<String> candidateList = new ArrayList<>(candidatesMap.keySet());
        String randomCandidate = candidateList.get(gen.nextInt(candidateList.size()));
        castVote(randomCandidate);
        updateMaxHeap();
    }

}
