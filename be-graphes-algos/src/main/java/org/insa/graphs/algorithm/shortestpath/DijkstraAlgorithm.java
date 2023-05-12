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

        // Tant que le sommet courant n'est pas la destination
        while (min.getSommet_courant() != data.getDestination()){
            min.setMarque(true);
            notifyNodeMarked(min.getSommet_courant());

            for (Arc successor : min.getSommet_courant().getSuccessors()){
                // Small test to check allowed roads...
                if (!data.isAllowed(successor)) {
                    continue;
                }
                notifyNodeReached(successor.getDestination());
                Label label = labels[successor.getDestination().getId()];
                if (!label.getMarque()){
                    double costForComparison = label.getCost();
                    label.setCost(Math.min(label.getCost(), min.getCost() + data.getCost(successor)));
                    if (costForComparison!=label.getCost()){
                        label.setPere(successor);
                        try {
                            tas.remove(label);
                        } catch (Exception e){
                        } finally {
                            tas.insert(label);
                        }
                    }
                }
            }
            try {
                min = tas.deleteMin();
            } catch (Exception e){
                return new ShortestPathSolution(data, Status.INFEASIBLE);
            }
        }
        // Si le sommet courant est la destination construire le chemin
        if (min.getSommet_courant() == data.getDestination()){
            min.setMarque(true);
            Node sommet_courant = min.getSommet_courant();
            notifyNodeMarked(sommet_courant);
            notifyDestinationReached(sommet_courant);
            while (sommet_courant != data.getOrigin()){
                predecessorArcs.add(min.getPere());
                sommet_courant = min.getPere().getOrigin();
                min = labels[sommet_courant.getId()];
            }
            Collections.reverse(predecessorArcs);
        } else {
            return new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        notifyDestinationReached(data.getDestination());
        ShortestPathSolution solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, predecessorArcs));;
        return solution;
    }

}
