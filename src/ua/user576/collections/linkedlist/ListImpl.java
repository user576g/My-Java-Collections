package ua.user576.collections.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
	
	private Node firstEl;
	private Node lastNode;
	private int size;
	
	@Override
	public void clear() {
		Node currentNode = firstEl;
		while (currentNode != null) {
			Node toRemove = currentNode;
			currentNode = currentNode.next;
			toRemove.data = null;
			toRemove.prev = null;
			toRemove.next = null;
		}
		firstEl = null;
		lastNode = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (firstEl != null) {
			Node i = firstEl;
			while (i.next != null) {
			sb.append(i.data + ", ");
			i = i.next;
			}
			sb.append(i.data);
		}
		sb.append(']');
		return sb.toString();
	}

	@Override
	public void addFirst(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.prev = null;
		if (firstEl == null) {
			newNode.next = null;
			firstEl = newNode;
			lastNode = newNode;
		} else {
			Node secondNode = firstEl;
			newNode.next = secondNode;
			firstEl = newNode;
			secondNode.prev = newNode;
		}
	}

	@Override
	public void addLast(Object element) {
		if (lastNode == null) {
			Node first = new Node();
			first.data = element;
			first.next = null;
			first.prev = null;
			lastNode = first;
			firstEl = first;
		} else {
			Node newNode = new Node();
			newNode.data = element;
			newNode.prev = lastNode;
			newNode.next = null;
			Node closerToFirst = lastNode;
			closerToFirst.next = newNode;
			lastNode = newNode;
		}
		++size;
	}

	@Override
	public void removeFirst() {
		if (null == firstEl) {
			throw new NoSuchElementException();
		} else if (firstEl == lastNode) {
			firstEl.data = null;
			firstEl = null;
			lastNode = null;
		} else {
			Node second = firstEl.next;
			firstEl.data = null;
			firstEl.next = null;
			second.prev = null;
			firstEl = second;
		}
		--size;
	}

	@Override
	public void removeLast() {
		if (null == firstEl && null == lastNode) {
			throw new NoSuchElementException();
		} else if (firstEl == lastNode) {
			firstEl.data = null;
			firstEl = null;
			lastNode = null;
		} else {
			Node penultimate = lastNode.prev;
			lastNode.data = null;
			lastNode.prev = null;
			penultimate.next = null;
			lastNode = penultimate;
		}
		--size;
	}

	@Override
	public Object getFirst() {
		return firstEl.data;
	}

	@Override
	public Object getLast() {
		return lastNode.data;
	}

	@Override
	public Object search(Object element) {
		if (element == null) {
			return null;
		}
		for (Object i : this) {
			if (element.equals(i)) {
				return i;
			}
		}
		return null;
	}

	@Override
	public boolean remove(Object element) {
		
		Iterator<Object> it = this.iterator();
		if (element == null) {
			for (Object i = it.next(); it.hasNext(); i = it.next()) {
				if (element == i) {
					it.remove();
					return true;
				}
			}
		} else {
			for (Object i = it.next(); it.hasNext(); i = it.next()) {
				if (element.equals(i)) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<Object> {
		
		protected Node currentNode = firstEl;
		private boolean isAvailableRemove;
		
		@Override
		public boolean hasNext() {
			return null != currentNode;
		}
		
		void changeCurrentNode() {
			currentNode = currentNode.next;
		}
		
		@Override
		public Object next() {
			if (currentNode == null) {
				throw new NoSuchElementException();
			} else {
				Object toOutput = currentNode.data;
				changeCurrentNode();
				isAvailableRemove = true;
				return toOutput;
			}
		}
		
		void findCaseAndPerform() {
			Node nodeToRemove = null;
			if (currentNode == firstEl.next && lastNode != firstEl) {
					nodeToRemove = firstEl;
					currentNode.prev = null;
					nodeToRemove.prev = null;	
					firstEl = currentNode;
			} else if (currentNode == null && lastNode == firstEl) {
					nodeToRemove = firstEl;
					firstEl = null;
					lastNode = null;	
			} else if (currentNode == null) {
					nodeToRemove = lastNode;
					Node secondNode = nodeToRemove.prev;
					secondNode.next = null;
					lastNode = secondNode;
					nodeToRemove.prev = null;	
			} else {
					nodeToRemove = currentNode.prev;
					Node thirdNode = nodeToRemove.prev;
					currentNode.prev = thirdNode;
					thirdNode.next = currentNode;
					nodeToRemove.next = null;
					nodeToRemove.prev = null;
			}
			nodeToRemove.data = null;
		}
		
		@Override
		public void remove() {
			if (isAvailableRemove) {
				 findCaseAndPerform();
				--size;
			} else {
				throw new IllegalStateException();
			}
			isAvailableRemove = false;
		}
	}
	
	public Iterator<Object> anotherIterator() {
		return new AnotherIterator() ;
	}
	
	private final class AnotherIterator extends IteratorImpl {
		private AnotherIterator() {
			currentNode = lastNode;
		}
		
		@Override
		void changeCurrentNode() {
			currentNode = currentNode.prev;
		}
		
		@Override
		void findCaseAndPerform() {
			
			Node nodeToRemove = null;
			
			if (currentNode == null && lastNode == firstEl) {
					nodeToRemove = firstEl;
					firstEl = null;
					lastNode = null;
			} else if (currentNode == lastNode.prev && null != currentNode) {
					nodeToRemove = lastNode;
					currentNode.next = null;
					lastNode = currentNode;
					nodeToRemove.prev = null;
			} else if (currentNode == null) {
					nodeToRemove = firstEl;
					Node secondNode = nodeToRemove.next;
					secondNode.prev = null;
					firstEl = secondNode;
					nodeToRemove.next = null;	
			} else {
					nodeToRemove = currentNode.next;
					Node thirdNode = nodeToRemove.next;
					currentNode.next = thirdNode;
					thirdNode.prev = currentNode;
					nodeToRemove.next = null;
					nodeToRemove.prev = null;
			}
			
			nodeToRemove.data = null;
		}
	}
}
