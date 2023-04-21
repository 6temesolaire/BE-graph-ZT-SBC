package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
    private Node sommet_courant;
    private boolean marque;
    private double coutRealise;
    private Arc pere;

    public Label(Node sommet_courant) {
        this.sommet_courant = sommet_courant;
        this.marque = false;
        this.coutRealise = Double.POSITIVE_INFINITY;
        this.pere = null;
    }

    public Node getSommet_courant() {
        return this.sommet_courant;
    }

    public boolean getMarque() {
        return this.marque;
    }

    public double getCost() {
        return this.coutRealise;
    }

    public Arc getPere() {
        return this.pere;
    }

    public void setMarque(boolean marque) {
        this.marque = marque;
    }

    public void setCost(double cout) {
        this.coutRealise = cout;
    }

    public void setPere(Arc pere) {
        this.pere = pere;
    }

    public int compareTo(Label other) {
        return Double.compare(this.coutRealise, other.coutRealise);
    }
}
