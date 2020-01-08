
public class Pair {
	Integer first;
	Integer second;
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public void setPair(int first, int second){
		this.first = first;
		this.second = second;
	}
	
	public int getFirst() {
		if(first == null) return -100;
		return first.intValue();
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		if(second == null) return -100;
		return second.intValue();
	}
	public void setSecond(int second) {
		this.second = second;
	}
}
