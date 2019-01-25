package cinema;

public class Seat {
	private int row;
	private int column;
	
	public Seat(Integer row, Integer column){
		this.setRow(row);
		this.setColumn(column);
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
}
