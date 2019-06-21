package Util;

public class Node<E> {
    public Node previous;    //上一个结点
    public Node next;        //下一个结点
    public Object element;   //元素数据


    public Node(Node previous,Node next,Object element){
        super();
        this.previous=previous;
        this.next=next;
        this.element=element;
    }

    public Node(Object element){
        super();
        this.element=element;
    }
}
