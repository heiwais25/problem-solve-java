package leetcode.reconstruct_itinerary;

import java.util.*;

class Solution {
    private Map<String, List<String>> connections;

    private List<List<String>> itineraries;

    private Map<String, Integer> ticketCounts;

    private Map<String, >

    private String getTicketKey(List<String> ticket) {
        return ticket.get(0) + ticket.get(1);
    }

    // 현재 지점에서 쓸 수 있는 걸 다 쓰고 경로를 반환
    private List<String> dfs(String current, int cnt, Deque<String> path) {
        if (cnt == 0) {
            List<String> itinerary = new ArrayList<>(path);
            itineraries.add(itinerary);
            return;
        }

        List<String> nextTarget = connections.get(current);
        if (nextTarget == null) {
            return;
        }

        for (String next : nextTarget) {
            String key = current + next;
            int ticketCount = ticketCounts.getOrDefault(key, 0);
            if (ticketCount > 0) {
                ticketCounts.put(key, ticketCount - 1);
                path.add(next);
                dfs(next, cnt - 1, path);
                path.pollLast();
                ticketCounts.put(key, ticketCount);
            }
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        connections = new HashMap<>();
        itineraries = new ArrayList<>();
        ticketCounts = new HashMap<>();
        for (List<String> ticket : tickets) {
            connections.computeIfAbsent(ticket.get(0), s -> new ArrayList<>())
                    .add(ticket.get(1));

            String key = getTicketKey(ticket);
            Integer counter = ticketCounts.getOrDefault(key, 0);
            counter++;
            ticketCounts.put(key, counter);
        }

        Deque<String> path = new ArrayDeque<>();
        path.add("JFK");
        dfs("JFK", tickets.size(), path);
        itineraries.sort(Comparator.comparing(Object::toString));
        return itineraries.size() > 0 ? itineraries.get(0) : new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        List<List<String>> tickets = List.of(
                List.of("JFK", "SFO"),
                List.of("JFK", "ATL"),
                List.of("SFO", "ATL"),
                List.of("ATL", "JFK"),
                List.of("ATL", "SFO")
        );

        List<String> course = new Solution().findItinerary(tickets);
        System.out.println(course);
    }
}
