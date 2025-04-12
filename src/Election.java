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

    public void rigElection(String candidate) {
        int votesToDistribute = totalVotes;
        for (String c : candidatesMap.keySet()) {
            candidatesMap.put(c, 0);
        }

        int riggedVotes = votesToDistribute / 2 + 1;
        candidatesMap.put(candidate, riggedVotes);
        votesToDistribute -= riggedVotes;

        for (String c : candidatesMap.keySet()) {
            if (!c.equals(candidate) && votesToDistribute > 0) {
                candidatesMap.put(c, 1);
                votesToDistribute--;
            }
        }
        updateMaxHeap();
    }

    public List<String> getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> tempHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        tempHeap.addAll(candidatesMap.entrySet());

        for (int i = 0; i < k; i++) {
            topCandidates.add(tempHeap.poll().getKey());
        }
        return topCandidates;
    }
}
