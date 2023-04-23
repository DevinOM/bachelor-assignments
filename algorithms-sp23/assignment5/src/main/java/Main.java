import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Node> nodes = initializeNodesAndEdges();

        // Get the key string of the source node from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key of source node: ");
        String sourceNodeKey = scanner.nextLine();
        Node sourceNode = nodes.get(sourceNodeKey);
        DijkstraAlgorithm(nodes, sourceNode);

        // Print results
        nodes.forEach((key, node) -> System.out.println(node.toString()));
    }

    public static void DijkstraAlgorithm(Map<String, Node> nodes, Node sourceNode) {
        // Set source node distance to 0
        sourceNode.setDistance(0);
        sourceNode.setShortestPath(List.of(sourceNode));
        // Create a toBeCheckedList which includes all nodes
        List<Node> toBeChecked = new ArrayList<>(nodes.values());
        while (!toBeChecked.isEmpty()) { // while toBeCheck contains elements
            Node v = toBeChecked.stream().min(Comparator.comparing(Node::getDistance)).orElse(null);
            toBeChecked.remove(v);
            v.getAdjacentNodes().forEach((adjacentNode, weight) -> {
                if (adjacentNode.getDistance() > (v.getDistance() + weight)) {
                    if (v.getDistance() == Integer.MAX_VALUE) {
                        // v is not reachable from the source node
                        return;
                    }
                    adjacentNode.setDistance(v.getDistance() + weight);
                    List<Node> newShortestPath = new LinkedList<>(v.getShortestPath());
                    newShortestPath.add(adjacentNode);
                    adjacentNode.setShortestPath(newShortestPath);
                }
            });
        }
    }

    public static Map<String, Node> initializeNodesAndEdges() {
        // Create the Nodes
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");

        // Create the edges/adjacent nodes
        nodeA.addAdjacentNode(nodeE, 1);
        nodeA.addAdjacentNode(nodeH, 10);
        nodeB.addAdjacentNode(nodeC, 2);
        nodeD.addAdjacentNode(nodeA, 4);
        nodeD.addAdjacentNode(nodeH, 1);
        nodeE.addAdjacentNode(nodeF, 3);
        nodeF.addAdjacentNode(nodeB, 1);
        nodeF.addAdjacentNode(nodeG, 7);
        nodeF.addAdjacentNode(nodeI, 1);
        nodeH.addAdjacentNode(nodeE, 5);
        nodeH.addAdjacentNode(nodeI, 9);
        nodeI.addAdjacentNode(nodeJ, 1);
        nodeJ.addAdjacentNode(nodeG, 1);

        // Store nodes in map
        Map<String, Node> nodes = new HashMap<>();
        nodes.put(nodeA.getKey(), nodeA);
        nodes.put(nodeB.getKey(), nodeB);
        nodes.put(nodeC.getKey(), nodeC);
        nodes.put(nodeD.getKey(), nodeD);
        nodes.put(nodeE.getKey(), nodeE);
        nodes.put(nodeF.getKey(), nodeF);
        nodes.put(nodeG.getKey(), nodeG);
        nodes.put(nodeH.getKey(), nodeH);
        nodes.put(nodeI.getKey(), nodeI);
        nodes.put(nodeJ.getKey(), nodeJ);

        return nodes;
    }
}