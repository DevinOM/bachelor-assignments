import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class Node implements Comparable<Node> {
    private final String key;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    @Override
    public String toString() {
        if (distance == Integer.MAX_VALUE) {
            return key + " : Unreachable";
        } else {
            String pathString = shortestPath.stream()
                    .map(Node::getKey)
                    .collect(Collectors.joining(" -> "));
            return pathString + " : " + distance;
        }
    }
}
