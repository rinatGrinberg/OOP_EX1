ID:314972902 Rinat Grinberg
GIT:
https://github.com/rinatGrinberg/OOP_EX1.git
commit 5bf554b8779b90d6899e219d4089c8cee346ffdb

The task include three interfaces that i was asked:
node_info
weigted_graph_algorithems
weigted_graph


In the WGraph_DS class I created an internal class of a node and defined two hashmap data structures so that the complexities would be as low as we would get rid of.
I have defined two data structures as can be seen below:

getNode(int key):was the same as ex0, O(1) thanks to the hashmap.
hasEdge(int node1, int node2):was the same as ex0, O(1) thanks to the hashmap.
the differences between ex0 was the nighbors hasamap that was used.
here i used some cditions in order to check if the edges are really exist or not. 
like if the edges are ollready exist of if the W is negative.
getEdge(int node1, int node2);O(1) thanks to the hashmap.
addNode(int key);O(1) thanks to the hashmap.
connect(int node1, int node2, double w);using hasEdge() function to check if the connection between the to vertexs are allredy exist of not and after that connect or not.O(1) thanks to the hashmap.
getV();O(1) thanks to the hashmap.
getV(int node_id);created a collection that returned after checking witch 
One structure that contains all the nodes of the graph:

Private HashMap Graph <complete, node_info>; // {n0.key = n0, n1.key = n1} -> Login (n0, n1)

And another structure that contains a key that represents a single node and its value in Knesset encouraged a hashmap where the key represents the vertex to which the first node is connected and when the value is the weight of the side} edges

HashMap Private <integer, HashMap <integer, neighbors >> neighbors; // {n0.key = {n1.key}, n1.key = {n0.key}}

In addition, in order to compare the dist between 2 vertex I used the Dijkstra's algorithm. 
In addition, I created <compareTo <node_info function to compare the distances obtained from the Dijkstra to determine distance.

private HashMap <Integer, Integer> prev; // {root-> n0.key = null, n1.key = n0.key, n2.key = n0.key}
private HashMap <Integer, Double> dist; // {n0.key = n0 distance from source}

In order to be able to remember where each node came from (for the realization of the dextra) I created a linked list of nodes out of nothing where the data structure is a hashma to download the complexity.
As can be seen above ^^ In terms of the complexity of the algorithm: O (n ^ 2)
In terms of Load and save: e = I used FileI / OnputStream ObjectI / OnputStream so I could use the object
I also implemented the Serializable properly

 FileInputStream fileIn = new FileInputStream (file);
ObjectInputStream objectIn = new ObjectInputStream (fileIn);
I also built the equals function to compare the two graphs (as I saw in the tests)

TESTS:
I created test files and sampled end cases like inserting / lowering a side twice null
And various exceptions.
