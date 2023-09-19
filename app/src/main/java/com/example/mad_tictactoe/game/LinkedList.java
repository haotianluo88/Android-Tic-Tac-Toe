package com.example.mad_tictactoe.game;
public class LinkedList {
// Linked list nodes holds the data and the previous and next node
    private class LinkedListNode {
        public int index;
        public Object data;
        public LinkedListNode next, prev;

        public LinkedListNode(int inIndex, Object inData) {
            index = inIndex;
            data = inData;
            next = null;
            prev = null;
        }
    }

    private LinkedListNode head;
//  When creating a linked list, the head is null
    public LinkedList() {
        head = null;
    }
//  Checks whether the head is null or not which would determine if the list is empty or not
    public boolean isEmpty() {
        return (head == null);
    }
//  Insert a node behind another node specified by the user
    public void insertNode(int inCurrentIndex, int inNewIndex, Object inData)
    {
        LinkedListNode newNode = new LinkedListNode(inNewIndex, inData);
//      If the list is empty
        if (isEmpty()) {
//          Set the new node as the head
            head = newNode;
            head.next = null;
//      Else is the list is not empty
        } else {
            LinkedListNode currentNode = peekNode(inCurrentIndex);
//          Set the new node behind a node specified by the user (currentNode)
            newNode.next = null;
            newNode.prev = currentNode;
            currentNode.next = newNode;
        }
    }
//  Finds the specific node
    public LinkedListNode peekNode(int inIndex)
    {
        LinkedListNode currentNode;
        currentNode = head;
//      While there is a next node and the current node is not the correct node
        while (currentNode.next != null && currentNode.index != inIndex) {
//          Cycles to the next node
            currentNode = currentNode.next;
        }
        return currentNode;
    }
//  Gets the data from the node
    public Object getObject(LinkedListNode node)
    {
        Object data;
        data = node.data;
        return data;
    }
}