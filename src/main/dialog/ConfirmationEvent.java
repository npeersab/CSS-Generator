package main.dialog;

import java.util.EventObject;

public class ConfirmationEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	public static final int YES = 0, NO = 1, CANCEL = 2;
	private int id;
	
	public ConfirmationEvent(Object source, int id) {
		super(source);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}