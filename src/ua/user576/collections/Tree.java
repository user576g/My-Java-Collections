package ua.user576.collections;

import java.lang.StringBuilder;

public class Tree<E extends Comparable<E>> {

    private Node<E> root;

    // добавляет элемент в контейнер
    // если в контейнере есть элемент равный по compareTo добавляемому,
    // то добавления не происходит и метод возвращает false
    // в противном случае элемент попадает в контейнер и метод возвращает true
    // первый добавляемый элемент становится корнем дерева
    // автобалансировки в дереве нет
    public boolean add(E element) {
        Node<E> newNode = new Node<>();
        newNode.data = element;
        Node<E> parentNode = null;
        Node<E> toInsert = root;

        while (null != toInsert) {
            parentNode = toInsert;
            switch (element.compareTo(toInsert.data)) {
                case -1:
                    toInsert = toInsert.left;
                    break;
                case 0:
                    return false;
                case 1:
                    toInsert = toInsert.right;
                    break;
            }
        }

        newNode.parent = parentNode;
        if (null == parentNode) {
            root = newNode; 	// tree was empty
        } else if (-1 == element.compareTo(parentNode.data)) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        return true;
    }

    // добавляет все элементы из массива в контейнер (вызов в цикле метода add, см. выше)
    public void add(E[] elements) {
        for (E e : elements) {
            add(e);
        }
    }

    private Node<E> find(E el) {
        Node<E> n = root;
        while (null != n) {
            int res = n.data.compareTo(el);
            if (0 == res) {
                return n;
            } else if (1 == res) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        return null;
    }

    private Node<E> treeMinimum(Node<E> n) {
        while (null != n.left) {
            n = n.left ;
        }
        return n;
    }

    // удаляет элемент из контейнера
    // если удаляемого элемента в контейнере нет, то возвращает false
    // в противном случае удаляет элемент и возвращает true
    // ВАЖНО! при удалении элемента дерево не должно потерять свойства бинарного дерева поиска
    public boolean remove(E element) {
        Node<E> n = find(element);
        if (null == n) {
            return false;
        }

        if (null == n.left) {
            transplant(n, n.right);
        } else if (null == n.right) {
            transplant(n, n.left);
        } else {
            Node<E> y = treeMinimum(n.right);
            if (y.parent != n) {
                transplant(y, y.right);
                y.right = n.right;
                y.right.parent = y;
            }
            transplant(n, y);
            y.left = n.left;
            y.left.parent = y;
        }

        return true;
    }

    // распечатывает дерево, так чтобы было видно его древовидную структуру, см. ниже пример
    public void print() {
        if (null == root) {
            System.out.println("Tree is empty!");
            return;
        }
        inorderPrint(0, root);
    }

    private void inorderPrint(int deep, Node<E> node) {
        if (null != node) {
            inorderPrint(deep + 1, node.right);
            for (int i = 0; i < deep; ++i) {
                System.out.print("  ");
            }
            System.out.println(node.data);
            inorderPrint(deep + 1, node.left);
        }
    }

    private void transplant(Node<E> u, Node<E> v) {
        if (null == u.parent) {
            root = v;
        } else if (u.parent.left == u) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (null != v) {
            v.parent = u.parent;
        }
    }

    // вложенный класс, объекты этого класса составляют дерево
    private static class Node<E> {
        protected Node<E> parent, left, right;
        protected E data;

        @Override
        public String toString() {

            StringBuilder s = new StringBuilder("parent = ");
            if (null == parent) {
                s.append("null");
            } else {
                s.append(parent.data);
            }

            s.append("\nDATA = " + data);

            s.append("\nleft = ");
            if (null == left) {
                s.append("null");
            } else {
                s.append(left.data);
            }

            s.append("\nright = ");
            if (null == right) {
                s.append("null");
            } else {
                s.append(right.data);
            }

            return s.toString();
        }
    }
}