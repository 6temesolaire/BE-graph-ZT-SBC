package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

import java.util.ArrayList;
import java.util.Collections;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();

        BinaryHeap<Label> tas = new BinaryHeap<>();
        notifyOriginProcessed(data.getOrigin());
        Label[] labels = new Label[graph.size()];
        // Creation d'un label pour tout les sommets du graph
        for (Node sommet: graph.getNodes()){
            Label label= new Label(sommet);
            if (label.getSommet_courant().equals(data.getOrigin())) {
                label.setCost(0);
                tas.insert(label);
            }
            labels[sommet.getId()] = label;
        }
        // Liste des predecesseurs
        ArrayList<Arc> predecessorArcs = new ArrayList<>();
        Label min = tas.deleteMin();
        while (min.getSommet_courant() != data.getDestination()){
            min.setMarque(true);
            notifyNodeReached(min.getSommet_courant());
            for (Arc successor : min.getSommet_courant().getSuccessors()){
                // Small test to check allowed roads...
                if (!data.isAllowed(successor)) {
                    continue;
                }
                Label label = labels[successor.getDestination().getId()];
                if (!label.getMarque()){
                    double costForComparison = label.getCost();
                    label.setCost(Math.min(label.getCost(), min.getCost() + successor.getLength()));
                    if (costForComparison!=label.getCost()){
                        tas.insert(label);
                        label.setPere(successor);
                    }
                }
            }
            min = tas.deleteMin();
        }
        notifyDestinationReached(data.getDestination());
        ShortestPathSolution solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, predecessorArcs));;
        return solution;
    }

}
