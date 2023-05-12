package org.insa.graphs.algorithm.utils;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    @Test
    // vérifier que le chemin construit par l'algo est valide en comparant avec Bellman.
    public void testDijkstraCheminValide1() throws Exception {
        final String mapInsa = "C:\\Users\\keke3\\Downloads\\insa.mapgr";
        final GraphReader readerInsa = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
        final Graph graphInsa = readerInsa.read();
        final Node nodeOrigin = graphInsa.get(109);
        final Node nodeDestination = graphInsa.get(140);
        final ShortestPathData data = new ShortestPathData(graphInsa, nodeOrigin, nodeDestination, ArcInspectorFactory.getAllFilters().get(0));
        final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(data);
        final ShortestPathSolution solutionDijkstra = dijkstra.run();
        final BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(data);
        final ShortestPathSolution solutionBellman = bellman.run();
        assertEquals(solutionBellman.getPath().getLength(), solutionDijkstra.getPath().getLength(), 0.0001);
    }

    @Test
    // Verifie que la solution n'est pas possible
    public void testDijkstraCheminValideNonValide() throws IOException {
        final String map = "C:\\Users\\keke3\\Downloads\\bretagne.mapgr";
        final GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(map))));
        final Graph graphInsa = reader.read();
        final Node nodeOrigin = graphInsa.get(546721);
        final Node nodeDestination = graphInsa.get(383731);
        final ShortestPathData data = new ShortestPathData(graphInsa, nodeOrigin, nodeDestination, ArcInspectorFactory.getAllFilters().get(0));
        final DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(data);
        final ShortestPathSolution solutionDijkstra = dijkstra.run();
        assertEquals(solutionDijkstra.getStatus(), ShortestPathSolution.Status.INFEASIBLE);
    }

    @Test
    public void testDijkstraCheminValide3() {

    }

    @Test
    public void testDijkstraCheminValide4() {

    }

    @Test
    public void testDijkstraCoutValide1() {

    }

    @Test
    public void testDijkstraCoutValide2() {

    }

    @Test
    public void testDijkstraCoutValide3() {

    }

    @Test
    public void testDijkstraCoutValide4() {

    }
}
