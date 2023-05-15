package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class LabelStar extends Label{

  private double EstimatedCost;

  public LabelStar(Node sommet_courant, Node destination) {
    super(sommet_courant);
    this.EstimatedCost = sommet_courant.getPoint().distanceTo(destination.getPoint());
  }

  public double getEstimatedCost() {
    return this.EstimatedCost;
  }

  @Override
  public double getTotalCost() {
    return super.getCost() + this.getEstimatedCost();
  }
}
