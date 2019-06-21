package Util;

public class myLinkedList<E> {

    private Node first;
    private Node last;
    public int size;
  
    public void add (int index,E e){
        checkRange(index);
        Node newNode = new Node(e);
        Node temp = getNode(index);
        if(temp!=null) {
            Node up=temp.previous;
            if(up!=null){
                up.next=newNode;
                newNode.previous=up;
                temp.previous=newNode;
                newNode.next=temp;
            }
            else if(up==null){
                System.out.println("----------------");
                temp.previous=newNode;
                newNode.next=temp;
                first=newNode;
            }
//            if(temp.next==null){
//                System.out.println("----------------");
//            }
            size++;
        }
    }

    public void add (E e){
        Node node = new Node(e);
        if(first==null){
//            node.previous=null;
//            node.next=null;
            first=node;
            last=node;
        }else{
            node.previous=last;
            node.next=null;
            last.next=node;
            last=node;
        }
        size++;
    }


    public void remove(int index){
        checkRange(index);
        Node temp=getNode(index);
        if(temp!=null){
            Node up=temp.previous;
            Node down=temp.next;
            if(up!=null){
                up.next=down;
            }else if(up==null){
                first=down;
            }
            if(down!=null){
                down.previous=up;
            }else if(down==null){
                last=up;
            }
            size--;
        }

    }


    private void checkRange(int index){
        if(index<0||index>size-1){
            throw new RuntimeException("索引数字不合法:"+index);
        }
    }


    public E get(int index){
        checkRange(index);

        Node temp=getNode(index);

        return  temp!=null?(E)temp.element:null;
    }


    private Node getNode(int index){
        checkRange(index);
        Node temp=null;
        if(index<=(size<<1))/*size<<1相当于size/2*/{
            temp=first;
            for(int i=0;i<index;i++){
                temp=temp.next;
            }
        }else{
            temp=last;
            for(int i=size-1;i>index;i--){
                temp=temp.previous;
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        Node temp=first;
        sb.append("[");
        while(temp!=null){
            sb.append(temp.element+",");
            temp=temp.next;
        }
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }
    
}
