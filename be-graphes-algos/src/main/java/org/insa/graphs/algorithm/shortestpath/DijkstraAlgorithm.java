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
        final int nbNodes = graph.size();
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        notifyOriginProcessed(data.getOrigin());
        boolean found = false;
        ArrayList<Label> labels = new ArrayList<Label>();
        for (Node sommet: graph.getNodes()){
            Label label= new Label(sommet);
            if (label.equals(data.getOrigin())) {
                label.setCost(0);
                tas.insert(label);
            }
            labels.add(label);
        }
        ArrayList<Arc> predecessorArcs = new ArrayList<>();

        Label min = tas.deleteMin();
        while (min.getSommet_courant() != data.getDestination()){
            min.setMarque(true);
            for (Arc successor : min.getSommet_courant().getSuccessors()){
                for (Label label : labels){
                    if (label.getSommet_courant().equals(successor.getOrigin())){
                        if (!label.getMarque()){
                            double costforcomparison = label.getCost();
                            label.setCost(Math.min(label.getCost(), min.getCost()+ successor.getLength()));
                            if (costforcomparison!=label.getCost()){
                                tas.insert(label);
                                label.setPere(successor);
                            }
                        }
                    }
                }
            }
            min = tas.deleteMin();
            predecessorArcs.add(min.getPere());
        }
        Collections.reverse(predecessorArcs);
        ShortestPathSolution solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, predecessorArcs));;
        return solution;
    }

}
