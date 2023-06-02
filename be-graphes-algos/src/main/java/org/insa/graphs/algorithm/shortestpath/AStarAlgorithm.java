package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    public Label createLabel (Node sommet, Node destination, AbstractInputData.Mode mode){
        return new LabelStar(sommet, destination, mode);
    }
}
