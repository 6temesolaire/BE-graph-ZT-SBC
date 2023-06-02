package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{

  private double estimatedCost;

  public LabelStar(Node sommetCourant, Node Dest, AbstractInputData.Mode mode) {
    super(sommetCourant) ;
    if (mode == AbstractInputData.Mode.LENGTH) {
      this.estimatedCost = sommetCourant.getPoint().distanceTo(Dest.getPoint());
    } else {
      this.estimatedCost = sommetCourant.getPoint().distanceTo(Dest.getPoint()) / Dest.getSuccessors().get(0).getRoadInformation().getMaximumSpeed();
    }
  }

  public double getEstimatedCost() {
    return this.estimatedCost;
  }

  @Override
  public double getTotalCost() {
    return super.getCost() + this.getEstimatedCost();
  }

}
