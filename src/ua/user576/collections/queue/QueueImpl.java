package ua.user576.collections.queue;

import java.util.Iterator;
import java.lang.StringBuilder;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;


public class QueueImpl implements Queue {
	
	private static class Node {
		
		private Object data = null;
		private Node next = null;
		private Node prev = null;
	}
	
	private Node tail = null;
	private Node head = null;
	private int size;
	
	@Override
	public void clear() {
		tail = null;
		head = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	

	@Override
	public void enqueue(Object element) {
		if (tail == null) {
			Node first = new Node();
			first.data = element;
			first.next = null;
			first.prev = null;
			tail = first;
			head = first;
		} else {
			Node newNode = new Node();
			newNode.data = element;
			newNode.next = tail;
			newNode.prev = null;
			Node higherToHead = tail;
			higherToHead.prev = newNode;
			tail = newNode;
		}
		++size;
	}

	@Override
	public Object dequeue() {
		Object toRemoveObj;
		if (head == null && tail == null) {
			toRemoveObj = null;
//			throw new 
		} else if (head == tail && tail != null) {
			toRemoveObj = head.data;
			head.data = null;
			head.prev = null;
			head.next = null;
			head = null;
			tail = null;
		} else {
			Node toRemoveNode = head;
			head = head.prev;
			toRemoveObj = toRemoveNode.data;
			toRemoveNode.data = null;
			toRemoveNode.prev = null;
			head.next = null;
		}
		--size;
		return toRemoveObj;
	}

	@Override
	public Object top() {
		Object top;
		if (tail == null && head != null) {
			throw new NoSuchElementException();
		} else {
			top = head.data;
		}
		return top;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node i = head;
		while (i != null) {
			sb.append(i.data);
			sb.append(", ");
			i = i.prev;
		}
		if (tail != null) {
			sb = sb.delete(sb.length() - 2, sb.length());
		}
		sb.append(']');
	
		return sb.toString();
	}
	
	@Override
	public Iterator<Object> iterator() {
		
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<Object> {
		private Node currentNode = head;
		private boolean isAvailableRemove;
//		private Node nodeToRemove = null;
		
		@Override
		public boolean hasNext() {
			if (null != currentNode) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Object next() {
			Object toOutput;
			if (currentNode == null) {
				throw new NoSuchElementException();
			} else {
				toOutput = currentNode.data;
				currentNode = currentNode.prev;
			}
			isAvailableRemove = true;
			return toOutput;
		}

		@Override
		public void remove() {
			if (isAvailableRemove) {
				Node nodeToRemove = null;
				if (head.prev == currentNode && tail != head) {
					nodeToRemove = head;
					currentNode.next = null;
					nodeToRemove.data = null;
					nodeToRemove.prev = null;	
					head = currentNode;
				} else if (currentNode == null && tail == head) {
					tail.data = null;
					tail = null;
					head = null;	
				} else if (currentNode == null && tail != head) {
					nodeToRemove = tail;
					Node secondNode = nodeToRemove.next;
					secondNode.prev = null;
					tail = secondNode;
					nodeToRemove.data = null;
					nodeToRemove.next = null;	
				} else {
					nodeToRemove = currentNode.next;
					Node thirdNode = nodeToRemove.next;
					currentNode.next = thirdNode;
					thirdNode.prev = currentNode;
					nodeToRemove.data = null;
					nodeToRemove.next = null;
					nodeToRemove.prev = null;
				}
				--size;
			} else {
				throw new IllegalStateException();
			}
			isAvailableRemove = false;
		}
		
		
		
		
	}
	
}
