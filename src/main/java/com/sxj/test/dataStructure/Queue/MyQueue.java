package com.sxj.test.dataStructure.Queue;

public interface MyQueue<E> {
    void add(E e) throws Exception;
    E remove() throws Exception;
    int size();
}
