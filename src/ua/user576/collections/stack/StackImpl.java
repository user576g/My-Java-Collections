package ua.user576.collections.stack;

import java.util.Iterator;

import ua.user576.collections.linkedlist.ListImpl;

public class StackImpl implements Stack {
	
	private ListImpl ls = new ListImpl();
	
	@Override
	public void clear() {
		ls.clear();
	}

	@Override
	public int size() {
		return ls.size();
	}

	

	@Override
	public void push(Object element) {
		ls.addLast(element);
	}

	@Override
	public Object pop() {
		Object toReturn = ls.getLast();
		ls.removeLast();
		return toReturn;
	}

	@Override
	public Object top() {
		return ls.getLast();
	}

	@Override
	public String toString() {
		return ls.toString();
	}
	
	@Override
	public Iterator<Object> iterator() {
		return ls.anotherIterator();
	}

}
