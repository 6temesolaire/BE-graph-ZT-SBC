package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label {
    private Node sommet_courant;
    private boolean marque;
    private double cout;
    private Arc pere;

    public Label(Node sommet_courant) {
        this.sommet_courant = sommet_courant;
        this.marque = false;
        this.cout = Double.POSITIVE_INFINITY;
        this.pere = null;
    }

    public Node getSommet_courant() {
        return this.sommet_courant;
    }

    public boolean getMarque() {
        return this.marque;
    }

    public double getCost() {
        return this.cout;
    }

    public Arc getPere() {
        return this.pere;
    }

    public void setMarque(boolean marque) {
        this.marque = marque;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setPere(Arc pere) {
        this.pere = pere;
    }

    public int compareTo(Label other) {
        return Double.compare(this.cout, other.cout);
    }
}
