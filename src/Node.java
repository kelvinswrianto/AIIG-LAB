

public class Node {
	int  cost;
	int i, j;
	
	public Node(int i, int j, int cost) {
		this.i = i;
		this.j = j;
		this.cost = cost;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	
}
