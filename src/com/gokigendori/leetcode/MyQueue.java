package com.gokigendori.leetcode;

public class MyQueue {
    int[] value;
    int last;
    int front;

    /** Initialize your data structure here. */
    public MyQueue() {
        value = new int[100];
        last = 0;
        front = 0;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        value[last] = x;
        last++;
        if (value.length <= last) {
            last = 0;
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (empty()) {
            return 0;
        }
        int v = value[front];
        front++;
        if (value.length <= front) {
            front = 0;
        }
        return v;
    }

    /** Get the front element. */
    public int peek() {
        if (empty()) {
            return 0;
        }
        return value[front];
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return last == front;
    }
}

