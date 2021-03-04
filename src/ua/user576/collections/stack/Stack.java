package ua.user576.collections.stack;

import ua.user576.collections.Container;

public interface Stack extends Container {
	
	// Pushes the specified element onto the top.
	void push(Object element);
	
	// Removes and returns the top element.
	Object pop();

	// Returns the top element.
	Object top();
	
}
