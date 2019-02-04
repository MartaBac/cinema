package cinema;

public class Seat {
	private int row;
	private int column;
	private boolean free, usable;
	
	public Seat(Integer row, Integer column, boolean usable){
		this.setRow(row);
		this.setColumn(column);
		this.free = true;
		this.usable = usable;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}
}
